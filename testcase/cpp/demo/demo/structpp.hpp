/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

#ifndef __structpp_hpp__
#define __structpp_hpp__

#include <iostream>
#include <iomanip>
#include <sstream>
#include <vector>
#include <typeinfo>
#include <map>

#include <string.h>
#include <pthread.h>

#define GENERIC_STRUCT_NAME "structpp::Struct"

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
        dt_string,
        dt_struct,
        dt_vector,
        dt_array
    } DataType;
    
    typedef enum {
        sc_struct,
        sc_bitfield
    } StructClass;
    
    class ReentrantLock {
    private:
        pthread_mutex_t mutex;
        
    public:
        ReentrantLock(void) {
            mutex = PTHREAD_MUTEX_INITIALIZER;
            pthread_mutexattr_t mta;
            pthread_mutexattr_init(&mta);
            pthread_mutexattr_settype(&mta, PTHREAD_MUTEX_RECURSIVE); // Let same thread can lock repeatedly
            pthread_mutex_init(&mutex, &mta);
        }
        
        virtual ~ReentrantLock() {
            pthread_mutex_destroy(&mutex);
        }
        
        virtual void lock(void) {
            pthread_mutex_lock(&mutex);
        }
        
        virtual void unlock(void) {
            pthread_mutex_unlock(&mutex);
        }
    };
    
    class StructInstanceCounter {
    private:
        static std::map<std::string, int> counter;
        static ReentrantLock lock;
        
    public:
        static void onStructConstructed(const std::string name) {
            lock.lock();
            std::map<std::string, int>::iterator iter = counter.find(name);
            if (iter == counter.end()) {
                counter.insert(std::pair<std::string, int>(name, 1));
            } else {
                iter->second += 1;
            }
            lock.unlock();
        }
        
        static void onStructDestructed(const std::string name) {
            lock.lock();
            std::map<std::string, int>::iterator iter = counter.find(name);
            if (iter == counter.end()) {
                counter.insert(std::pair<std::string, int>(name, 0));
            } else {
                iter->second -= 1;
            }
            lock.unlock();
        }
        
        static size_t active_num(void) {
            lock.lock();
            size_t total = 0;
            for (std::map<std::string, int>::iterator iter = counter.begin(); iter != counter.end(); ++iter) {
                total += iter->second;
            }
            lock.unlock();
            return total;
        }
        
        static std::string tostr(void) {
            lock.lock();
            std::stringstream ss;
            for (std::map<std::string, int>::iterator iter = counter.begin(); iter != counter.end(); ++iter) {
                ss << iter->first << " : " << iter->second << std::endl;
            }
            std::string str = ss.str();
            lock.unlock();
            return str;
        }
    };
    
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
            StructInstanceCounter::onStructConstructed("structpp::varray");
            m_array = NULL;
            m_size = 0;
        }
        
        ~varray() {
            clear();
            StructInstanceCounter::onStructDestructed("structpp::varray");
        }
        
        T& operator[](size_t index) {
            return m_array[index];
        }
        
        varray<T>& operator=(const char *text) {
            memset(m_array, 0, sizeof(T) * m_size);
            strncpy(m_array, text, bytes());
            return *this;
        }
        
        void resize(size_t size) {
            if (m_size != size) {
                T* current = m_array;
                size_t current_size = m_size;
                
                if ((m_size = size) > 0) {
                    m_array = new T[size];
                    memset(m_array, 0, sizeof(T) * m_size);
                } else {
                    m_array = NULL;
                }
                
                if (current != NULL && m_array != NULL) {
                    memcpy(m_array, current, (current_size < m_size ? current_size : m_size) * sizeof(T));
                }
                
                if (current != NULL) {
                    delete[] m_array;
                }
            }
        }
        
        void print(std::ostream &output) {
            uint8_t *byteptr = (uint8_t *) m_array;
            output << std::dec << bytes() << '[';
            for (size_t i = 0; i < bytes(); ++i) {
                if (i > 0) {
                    output << ' ';
                }
                output << std::hex << std::uppercase << std::setw(2) << std::setfill('0') << (uint32_t) byteptr[i];
            }
            output << ']';
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
        virtual void read_string(void *pp) = 0;
        virtual void read_bitfield(uint32_t *pp, int nbits) = 0;
        virtual void read_vector(void *pv, const std::string sname, size_t num);
        virtual void read_array(void *pa, DataType dtype, size_t num);
        
        virtual void begin_read_struct(void) = 0;
        virtual void end_read_struct(void) = 0;
        virtual void read_struct(Struct *s);
        
        template<typename T> void read_array_elements(varray<T> *va, DataType dtype, size_t num) {
            va->resize(num);
            T value;
            for (size_t i = 0; i < va->size(); ++i) {
                read_basic(&value, dtype);
                (*va)[i] = value;
            }
        }
    };
    
    class BinaryStructDecoder : public StructDecoder {
    private:
        std::istream *input;
        uint8_t bits8;
        int index;
        
        int get_bytes(const void *ptr, int start, int len);
        
        template<typename T> T read(void) {
            T v = 0;
            get_bytes(&v, 0, sizeof(T));
            return v;
        }
        
    public:
        BinaryStructDecoder(std::istream *input, StructFactory *factory);
        
        virtual void read_basic(void *pp, DataType dtype) override;
        virtual void read_string(void *pp) override;
        virtual void read_bitfield(uint32_t *pp, int nbits) override;
        virtual void begin_read_struct(void) override;
        virtual void end_read_struct(void) override;
    };
    
    class StructEncoder : public StructIO {
    public:
        StructEncoder(void);

        virtual void begin_write_struct(Struct *pp, const std::string propname) {}
        virtual void end_write_struct(Struct *pp, const std::string propname) {}
        virtual void write_struct(Struct *pp, const std::string propname);
        
        virtual void write_basic(void *pp, const std::string prototype, const std::string propname, DataType dtype, DataType ctype, size_t index) = 0;
        
        virtual void write_bitfield(uint32_t fv, int nbits, const std::string propname) {}
        
        virtual void begin_write_vector(void *pv, const std::string prototype, const std::string propname) {};
        virtual void end_write_vector(void *pv, const std::string prototype, const std::string propname) {};
        virtual void write_vector(void *pv, const std::string prototype, const std::string propname);
        
        virtual void begin_write_array(size_t len, const std::string prototype, const std::string propname) {}
        virtual void end_write_array(size_t len, const std::string prototype, const std::string propname) {}
        
        template<typename T> void write_array_elements(varray<T> *p, const std::string prototype, const std::string propname, DataType dtype) {
            size_t len = p->size();
            begin_write_array(len, prototype, propname);
            T v;
            for (size_t i = 0; i < len; ++i) {
                v = (*p)[i];
                write_basic(&v, prototype, propname, dtype, dt_array, i);
            }
            end_write_array(len, prototype, propname);
        }
        
        virtual void write_array(void *pa, const std::string prototype, const std::string propname, DataType dtype);
        virtual void write_string(void *pa, const std::string prototype, const std::string propname) = 0;
    };
    
    class Struct {
    private:
        std::string name;
        StructClass clazz;
        varray<uint8_t> data;
        
    protected:
        Struct(std::string name, StructClass clazz);
        
    public:
        Struct(size_t len);
        
        virtual ~Struct();

        inline std::string& struct_name(void) {
            return this->name;
        }
        
        inline StructClass struct_class(void) {
            return this->clazz;
        }
        
        virtual size_t calcsize(void);
        virtual void print(std::ostream &os);
        virtual void write(StructEncoder &encoder);
        virtual void read(StructDecoder &decoder);
        
        virtual void encode(std::ostream &output);
        virtual void encode(StructEncoder &encoder);
        
        virtual void decode(std::istream &input, StructFactory &factory);
        virtual void decode(StructDecoder &decoder);
    };
    
    class TextStructEncoder : public StructEncoder {
    private:
        std::ostream *output;
        int level;
        
        void indent();
        void tostr(DataType dtype, void *pp, std::ostream &ss);
        
    public:
        TextStructEncoder(std::ostream *output);
        
        virtual void begin_write_struct(Struct *pp, const std::string propname) override;
        virtual void end_write_struct(Struct *pp, const std::string propname) override;
        virtual void write_basic(void *pp, const std::string prototype, const std::string propname, DataType dtype, DataType ctype, size_t index) override;
        virtual void write_bitfield(uint32_t fv, int nbits, const std::string propname) override;
        virtual void write_string(void *pa, const std::string prototype, const std::string propname) override;
        virtual void begin_write_array(size_t len, const std::string prototype, const std::string propname) override;
        virtual void end_write_array(size_t len, const std::string prototype, const std::string propname) override;
        virtual void begin_write_vector(void *pv, const std::string prototype, const std::string propname) override;
        virtual void end_write_vector(void *pv, const std::string prototype, const std::string propname) override;
    };
    
    class BinaryStructEncoder : public StructEncoder {
    private:
        std::ostream *output;
        uint8_t bits8;
        int index;
        
        int put_bytes(const void *ptr, int start, int len);
        
        template<typename T> void write(T v) {
            put_bytes(&v, 0, sizeof(T));
        }
        
    public:
        BinaryStructEncoder(std::ostream *output);
        
        virtual void begin_write_struct(Struct *pp, const std::string propname) override;
        virtual void end_write_struct(Struct *pp, const std::string propname) override;
        virtual void write_basic(void *pp, const std::string prototype, const std::string propname, DataType dtype, DataType ctype, size_t index) override;
        virtual void write_bitfield(uint32_t fv, int nbits, const std::string propname) override;
        virtual void write_string(void *pa, const std::string prototype, const std::string propname) override;
    };
    
} /* namespace structpp */

#endif /* __struct_hpp__ */
