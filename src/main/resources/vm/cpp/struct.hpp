//
//  struct.hpp
//  cppstruct
//
//  Created by 高波 on 2018/3/26.
//  Copyright © 2018年 高波. All rights reserved.
//

#ifndef __struct_hpp__
#define __struct_hpp__

#include <iostream>
#include <iomanip>
#include <sstream>
#include <vector>
#include <typeinfo>

#define CAST_PTR(t, p)    ((t *) p)

namespace structpp {
    typedef enum {
        dt_none,

        dt_byte,
        dt_boolean,
        dt_short,
        dt_int,
        dt_long,
        dt_float,
        dt_double,

        dt_struct,

        dt_vector,
        dt_array
    } DataType;

    template<typename T> class varray {
    private:
        T* m_array;
        size_t m_size;

    public:
        varray(int size) : varray() {
            this->m_array = new T[size];
            this->m_size = size;
            memset(m_array, 0, sizeof(T) * m_size);
        }

        varray(void) {
            m_array = NULL;
            m_size = 0;
        }

        ~varray() {
            clear();
        }

        T& operator[](size_t index) {
            return m_array[index];
        }

        varray<T>& operator=(const char *text) {
            size_t text_len = strlen(text);
            size_t bufsize = bytes();
            memcpy(m_array, text, bufsize > text_len ? text_len : bufsize);
            return *this;
        }

        void resize(size_t size) {
            if (m_size != size) {
                clear();
                if ((m_size = size) > 0) {
                    m_array = new T[size];
                }
            }
        }

        size_t size() {
            return m_size;
        }

        size_t bytes() {
            return m_size * sizeof(T);
        }

        T* array() {
            return m_array;
        }

        void copy(const T* src, int start, int from, int len) {
            memcpy(m_array + start, src + from, sizeof(T) * len);
        }

        void clear() {
            if (m_array != NULL) {
                delete[] m_array;
            }

            m_array = NULL;
            m_size = 0;
        }
    };

    class StructEncoder;
    class StructDecoder;

    class Struct {
    private:
        size_t len;
        char *data;

        Struct(size_t len) {
            this->len = len;
            if (len > 0) {
                this->data = new char[len];
            } else {
                this->data = NULL;
            }
        }

    protected:
        Struct(void) {
            this->len = 0;
            this->data = NULL;
        }

    public:
        static Struct *instance(int len) {
            return new Struct(len);
        }

        ~Struct(void) {
            if (this->data != NULL) {
                delete[] this->data;
            }
        }

        virtual size_t size(void) {
            return this->len;
        }

        virtual void encode(std::ostream &output) {
            if (this->len > 0) {
                output.write(this->data, this->len);
            }
        }

        virtual void encode(StructEncoder &format) {
            if (this->len > 0) {

            }
        }

        virtual void decode(std::istream &input) {
            if (this->len > 0) {
                input.read(this->data, this->len);
            }
        }

        virtual void decode(StructDecoder &format) {
        }

        virtual void print(std::ostream &os) {
        }
    };

    class StructFactory {
    public:
        virtual Struct *create(const std::string sname) = 0;
    };

    class StructIO {
    private:
        bool big_endian;

    public:
        StructIO() {
            uint16_t v = 0x0102;
            big_endian = ((uint8_t *) &v)[0] == 0x01 ? true : false;
        }

        void reverse_byte_order(uint8_t *ptr, int start, int len) {
            if (len > 1 && big_endian) {
                uint8_t v;
                for (int i = 0, j = len - 1, half = len >> 1; i < half; ++i, --j) {
                    v = ptr[i + start];
                    ptr[i + start] = ptr[j + start];
                    ptr[j + start] = v;
                }
            }
        }
    };

    class StructDecoder : public StructIO {
    private:
        StructFactory *factory;

    public:
        StructDecoder(StructFactory *factory) {
            this->factory = factory;
        }

        virtual void read_basic(void *pp, DataType dtype) = 0;

        virtual void read_struct(Struct *s) {
            s->decode(*this);
        }

        virtual void read_vector(void *pv, const std::string sname, size_t num) {
            std::vector<Struct *> *vec = (std::vector<Struct *> *) pv;
            for (size_t i = 0; i < num; ++i) {
                Struct *s = factory->create(sname);
                if (s == NULL) {
                    std::cerr << "unsupported operation" << std::endl;
                } else {
                    s->decode(*this);
                    vec->push_back(s);
                }
            }
        }

        template<typename T> void read_array_elements(varray<T> *va, DataType dtype, size_t num) {
            T value;
            for (size_t i = 0; i < num; ++i) {
                read_basic(&value, dtype);
                (*va)[i] = value;
            }
        }

        virtual void read_array(void *pa, DataType dtype, size_t num) {
            switch (dtype) {
                case dt_byte:
                    read_array_elements<uint8_t>((varray<uint8_t> *) pa, dtype, num);
                    break;
                case dt_boolean:
                    read_array_elements<bool>((varray<bool> *) pa, dtype, num);
                    break;
                case dt_short:
                    read_array_elements<uint16_t>((varray<uint16_t> *) pa, dtype, num);
                    break;
                case dt_int:
                    read_array_elements<uint32_t>((varray<uint32_t> *) pa, dtype, num);
                    break;
                case dt_long:
                    read_array_elements<uint64_t>((varray<uint64_t> *) pa, dtype, num);
                    break;
                case dt_float:
                    read_array_elements<float>((varray<float> *) pa, dtype, num);
                    break;
                case dt_double:
                    read_array_elements<double>((varray<double> *) pa, dtype, num);
                    break;
                default:
                    throw std::invalid_argument("unsupported data type of array element");
            }
        }
    };

    class BinaryStructDecoder : public StructDecoder {
    private:
        std::istream *input;

        int get_bytes(const void *ptr, int start, int len) {
            uint8_t *byte_ptr = (uint8_t *) ptr;
            input->read((char*) (byte_ptr + start), len);
            reverse_byte_order(byte_ptr, 0, len);
            return len;
        }

        template<typename T> T read(void) {
            T v = 0;
            get_bytes(&v, 0, sizeof(T));
            return v;
        }

    public:
        BinaryStructDecoder(std::istream *input, StructFactory *factory) : StructDecoder(factory) {
            this->input = input;
        }

        virtual void read_basic(void *pp, DataType dtype) override {
            switch (dtype) {
                case dt_byte:
                    *(CAST_PTR(uint8_t, pp)) = read<uint8_t>();
                    break;
                case dt_boolean:
                    *(CAST_PTR(bool, pp)) = read<bool>();
                    break;
                case dt_short:
                    *(CAST_PTR(uint16_t, pp)) = read<uint16_t>();
                    break;
                case dt_int:
                    *(CAST_PTR(uint32_t, pp)) = read<uint32_t>();
                    break;
                case dt_long:
                    *(CAST_PTR(uint64_t, pp)) = read<uint64_t>();
                    break;
                case dt_float:
                    *(CAST_PTR(float, pp)) = read<float>();
                    break;
                case dt_double:
                    *(CAST_PTR(double, pp)) = read<double>();
                    break;
                default:
                    std::cerr << "unsupported operation" << std::endl;
                    break;
            }
        }
    };

    class StructEncoder : public StructIO {
    public:
        StructEncoder() {}

        inline bool basic_type(DataType type) {
            return type == dt_byte || type == dt_boolean || type == dt_short
            || type == dt_int || type == dt_long || type == dt_float
            || type == dt_double;
        }

        virtual void begin_write_struct(const char *sname) {}
        virtual void end_write_struct(const char *sname) {}
        virtual void write_struct(Struct *pp, const char *prototype) {
            pp->encode(*this);
        }

        virtual void write_basic(void *pp, const char *prototype, const char *tname, DataType dtype, DataType ctype, int index) = 0;

        virtual void begin_write_vector(void *pv, const char *prototype) {};
        virtual void end_write_vector(void *pv, const char *prototype) {};
        virtual void write_vector(void *pv, const char *prototype, const char *tname, DataType dtype) {
            begin_write_vector(pv, prototype);

            std::vector<Struct *> *vec = (std::vector<Struct *> *) pv;
            for (int i = 0; i < vec->size(); ++i) {
                Struct *s = (*vec)[i];
                s->encode(*this);
            }

            end_write_vector(pv, prototype);
        }

        virtual void begin_write_array(void *pa, const char *prototype) {}
        virtual void end_write_array(void *pa, const char *prototype) {}

        template<typename T> void write_array_elements(varray<T> *p, const char *prototype, const char *tname, DataType dtype) {
            T v;
            for (int i = 0; i < p->size(); ++i) {
                v = (*p)[i];
                write_basic(&v, prototype, tname, dtype, dt_array, i);
            }
        }

        virtual void write_array(void *pa, const char *prototype, const char *tname, DataType dtype) {
            begin_write_array(pa, prototype);

            switch (dtype) {
                case dt_byte:
                    write_array_elements<uint8_t>((varray<uint8_t> *) pa, prototype, tname, dtype);
                    break;
                case dt_boolean:
                    write_array_elements<bool>((varray<bool> *) pa, prototype, tname, dtype);
                    break;
                case dt_short:
                    write_array_elements<uint16_t>((varray<uint16_t> *) pa, prototype, tname, dtype);
                    break;
                case dt_int:
                    write_array_elements<uint32_t>((varray<uint32_t> *) pa, prototype, tname, dtype);
                    break;
                case dt_long:
                    write_array_elements<uint64_t>((varray<uint64_t> *) pa, prototype, tname, dtype);
                    break;
                case dt_float:
                    write_array_elements<float>((varray<float> *) pa, prototype, tname, dtype);
                    break;
                case dt_double:
                    write_array_elements<double>((varray<double> *) pa, prototype, tname, dtype);
                    break;
                default:
                    std::cerr << "unsupported operation" << std::endl;
                    break;
            }

            end_write_array(pa, prototype);
        }
    };

    class TextStructEncoder : public StructEncoder {
    private:
        std::ostream *output;
        int level;

        void indent() {
            for (int i = 0; i < level; ++i) {
                *output << "    ";
            }
        }

        void tostr(DataType dtype, void *pp, std::ostream &ss) {
            switch (dtype) {
                case dt_byte:
                    ss << std::hex << std::uppercase << std::setw(2) << std::setfill('0') << *(CAST_PTR(uint8_t, pp));
                    break;
                case dt_boolean:
                    ss << ((*CAST_PTR(bool, pp)) ? "true" : "false");
                    break;
                case dt_short:
                    ss << "(DEC:" << std::dec << *CAST_PTR(int16_t, pp)
                    << " HEX:" << std::uppercase << std::setw(4) << std::setfill('0') << *CAST_PTR(int16_t, pp)
                    << ")";
                    break;
                case dt_int:
                    ss << "(DEC:" << std::dec << *CAST_PTR(int32_t, pp)
                    << " HEX:" << std::uppercase << std::setw(8) << std::setfill('0') << *CAST_PTR(int32_t, pp)
                    << ")";
                    break;
                case dt_long:
                    ss << "(DEC:" << std::dec << *CAST_PTR(int64_t, pp)
                    << " HEX:" << std::uppercase << std::setw(16) << std::setfill('0') << *CAST_PTR(int64_t, pp)
                    << ")";
                    break;
                case dt_float:
                    ss << *CAST_PTR(float, pp);
                    break;
                case dt_double:
                    ss << *CAST_PTR(double, pp);
                    break;
                default:
                    std::cerr << "unsupported operation" << std::endl;
            }
        }

    public:
        TextStructEncoder(std::ostream *output) {
            this->output = output;
            this->level = 0;
        }

        virtual void begin_write_struct(const char *sname) override {
            indent();
            *output << sname << " {" << std::endl;
            ++level;
        }

        virtual void end_write_struct(const char *sname) override {
            --level;
            indent();
            *output << "}" << std::endl;
        }

        virtual void write_basic(void *pp, const char *prototype, const char *tname, DataType dtype, DataType ctype, int index) override {
            std::stringstream ss;
            tostr(dtype, pp, ss);
            indent();
            *output << tname << " = " << ss.str() << std::endl;
        }
    };

    class BinaryStructEncoder : public StructEncoder {
    private:
        std::ostream *output;

        int put_bytes(const void *ptr, int start, int len) {
            uint8_t *byte_ptr = (uint8_t *) ptr;
            reverse_byte_order(byte_ptr, 0, len);
            output->write((char*) (byte_ptr + start), len);
            return len;
        }

        template<typename T> void write(T v) {
            put_bytes(&v, 0, sizeof(T));
        }

    public:
        BinaryStructEncoder(std::ostream *output) {
            this->output = output;
        }

        virtual void write_basic(void *pp, const char *prototype, const char *tname, DataType dtype, DataType ctype, int index) override {
            switch (dtype) {
                case dt_byte:
                    output->put(*(CAST_PTR(uint8_t, pp)));
                    break;
                case dt_boolean:
                    output->put(*CAST_PTR(bool, pp) ? true : false);
                    break;
                case dt_short:
                    write<uint16_t>(*CAST_PTR(int16_t, pp));
                    break;
                case dt_int:
                    write<uint32_t>(*CAST_PTR(int32_t, pp));
                    break;
                case dt_long:
                    write<uint64_t>(*CAST_PTR(int64_t, pp));
                    break;
                case dt_float:
                    write<float>(*CAST_PTR(float, pp));
                    break;
                case dt_double:
                    write<double>(*CAST_PTR(double, pp));
                    break;
                default:
                    std::cerr << "unsupported operation" << std::endl;
            }
        }
    };

} /* namespace structpp */
#endif /* __struct_hpp__ */

