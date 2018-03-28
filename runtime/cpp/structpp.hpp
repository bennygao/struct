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
    
    class Struct;
    
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
        StructDecoder(StructFactory *factory);
        
        virtual void read_basic(void *pp, DataType dtype) = 0;
        virtual void read_struct(Struct *s);
        virtual void read_vector(void *pv, const std::string sname, size_t num);
        virtual void read_array(void *pa, DataType dtype, size_t num);
        
        template<typename T> void read_array_elements(varray<T> *va, DataType dtype, size_t num) {
            T value;
            for (size_t i = 0; i < num; ++i) {
                read_basic(&value, dtype);
                (*va)[i] = value;
            }
        }
    };
    
    class BinaryStructDecoder : public StructDecoder {
    private:
        std::istream *input;
        
        int get_bytes(const void *ptr, int start, int len);
        
        template<typename T> T read(void) {
            T v = 0;
            get_bytes(&v, 0, sizeof(T));
            return v;
        }
        
    public:
        BinaryStructDecoder(std::istream *input, StructFactory *factory);
        
        virtual void read_basic(void *pp, DataType dtype) override;
    };
    
    class StructEncoder : public StructIO {
    public:
        StructEncoder(void);
        
        inline bool basic_type(DataType type) {
            return type == dt_byte || type == dt_boolean || type == dt_short
            || type == dt_int || type == dt_long || type == dt_float
            || type == dt_double;
        }
        
        virtual void begin_write_struct(const std::string sname) {}
        virtual void end_write_struct(const std::string sname) {}
        virtual void write_struct(Struct *pp, const std::string prototype);
        
        virtual void write_basic(void *pp, const std::string prototype, const std::string tname, DataType dtype, DataType ctype, int index) = 0;
        
        virtual void begin_write_vector(void *pv, const std::string prototype) {};
        virtual void end_write_vector(void *pv, const std::string prototype) {};
        virtual void write_vector(void *pv, const std::string prototype, const std::string tname, DataType dtype);
        
        virtual void begin_write_array(void *pa, const std::string prototype) {}
        virtual void end_write_array(void *pa, const std::string prototype) {}
        
        template<typename T> void write_array_elements(varray<T> *p, const std::string prototype, const std::string tname, DataType dtype) {
            T v;
            for (int i = 0; i < p->size(); ++i) {
                v = (*p)[i];
                write_basic(&v, prototype, tname, dtype, dt_array, i);
            }
        }
        
        virtual void write_array(void *pa, const std::string prototype, const std::string tname, DataType dtype);
    };
    
    class Struct {
    private:
        varray<uint8_t> data;
        
        Struct(size_t len);
        
    protected:
        Struct(void);
        
    public:
        static Struct *instance(int len) {
            return new Struct(len);
        }
        
        ~Struct(void);
        
        virtual size_t size(void);
        virtual void encode(StructEncoder &encoder);
        virtual void decode(StructDecoder &decoder);
        virtual void print(std::ostream &os);
    };
    
    class TextStructEncoder : public StructEncoder {
    private:
        std::ostream *output;
        int level;
        
        void indent();
        void tostr(DataType dtype, void *pp, std::ostream &ss);
        
    public:
        TextStructEncoder(std::ostream *output);
        
        virtual void begin_write_struct(const std::string sname) override;
        virtual void end_write_struct(const std::string sname) override;
        virtual void write_basic(void *pp, const std::string prototype, const std::string tname, DataType dtype, DataType ctype, int index) override;
    };
    
    class BinaryStructEncoder : public StructEncoder {
    private:
        std::ostream *output;
        
        int put_bytes(const void *ptr, int start, int len);
        
        template<typename T> void write(T v) {
            put_bytes(&v, 0, sizeof(T));
        }
        
    public:
        BinaryStructEncoder(std::ostream *output);
        
        virtual void write_basic(void *pp, const std::string prototype, const std::string tname, DataType dtype, DataType ctype, int index) override;
    };
    
} /* namespace structpp */

#endif /* __struct_hpp__ */


