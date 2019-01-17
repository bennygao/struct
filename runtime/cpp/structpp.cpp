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

#include <iostream>
#include <sstream>
#include <iomanip>
#include <vector>
#include <typeinfo>
#include "structpp.hpp"

#define CAST_PTR(t, p)  ((t *) p)
using namespace structpp;

///////////////////////////////////////////////////////////////
// Global class static members define
///////////////////////////////////////////////////////////////
std::map<std::string, int> StructInstanceCounter::counter;
ReentrantLock StructInstanceCounter::lock;

///////////////////////////////////////////////////////////////
// methods implements of StructDecoder
///////////////////////////////////////////////////////////////
StructDecoder::StructDecoder(StructFactory *factory)
{
    this->factory = factory;
}

void StructDecoder::read_struct(Struct *s)
{
    begin_read_struct();
    s->read(*this);
    end_read_struct();
}

void StructDecoder::read_vector(void *pv, const std::string sname, size_t num)
{
    std::vector<Struct *> *vec = (std::vector<Struct *> *) pv;
    Struct *s;
    for (size_t i = 0; i < num; ++i) {
        if (i >= vec->size()) {
            s = factory->create(sname);
            if (s == NULL) {
                throw std::invalid_argument("create Struct instance error");
            } else {
                vec->push_back(s);
            }
        } else {
            s = (*vec)[i];
        }
        
        read_struct(s);
    }
}

void StructDecoder::read_array(void *pa, DataType dtype, size_t num)
{
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

///////////////////////////////////////////////////////////////
// methods implements of BinaryStructDecoder
///////////////////////////////////////////////////////////////
int BinaryStructDecoder::get_bytes(const void *ptr, int start, int len)
{
    uint8_t *byte_ptr = (uint8_t *) ptr;
    input->read((char*) (byte_ptr + start), len);
    convert_byte_order(byte_ptr, start, len);
    return len;
}

BinaryStructDecoder::BinaryStructDecoder(std::istream *input, StructFactory *factory) : StructDecoder(factory)
{
    this->input = input;
}

void BinaryStructDecoder::read_basic(void *pp, DataType dtype)
{
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
            throw std::invalid_argument("unsupported basic data type");
            break;
    }
}

void BinaryStructDecoder::read_string(void *pp)
{
    varray<char> *va = CAST_PTR(varray<char>, pp);
    input->read(va->array(), va->size());
}

void BinaryStructDecoder::read_bitfield(uint32_t *pp, int nbits)
{
    uint32_t value = 0;
    for (int i = 0; i < nbits; ++i) {
        if (i > 0) {
            value <<= 1;
        }
        if (index < 0) {
            input->read((char *) &bits8, 1);
            index = 7;
        }
        
        if (bits8 & (0x01 << index--)) {
            value |= 0x01;
        }
    }
    *pp = value;
}

void BinaryStructDecoder::begin_read_struct(void)
{
    this->bits8 = 0;
    this->index = -1;
}

void BinaryStructDecoder::end_read_struct(void)
{
    this->bits8 = 0;
    this->index = -1;
}

///////////////////////////////////////////////////////////////
// methods implements of StructEncoder
///////////////////////////////////////////////////////////////
StructEncoder::StructEncoder(void)
{
    
}

void StructEncoder::write_struct(Struct *pp, const std::string propname)
{
    begin_write_struct(pp, propname);
    pp->write(*this);
    end_write_struct(pp, propname);
}

void StructEncoder::write_vector(void *pv, const std::string prototype, const std::string propname)
{
    begin_write_vector(pv, prototype, propname);
    
    std::vector<Struct *> *vec = (std::vector<Struct *> *) pv;
    std::stringstream ss;
    for (size_t i = 0; i < vec->size(); ++i) {
        ss.str("");
        ss << '[' << i << ']';
        Struct *s = (*vec)[i];
        this->write_struct(s, ss.str());
    }
    
    end_write_vector(pv, prototype, propname);
}

void StructEncoder::write_array(void *pa, const std::string prototype, const std::string propname, DataType dtype)
{
    switch (dtype) {
        case dt_byte:
            write_array_elements<uint8_t>((varray<uint8_t> *) pa, prototype, propname, dtype);
            break;
        case dt_boolean:
            write_array_elements<bool>((varray<bool> *) pa, prototype, propname, dtype);
            break;
        case dt_short:
            write_array_elements<uint16_t>((varray<uint16_t> *) pa, prototype, propname, dtype);
            break;
        case dt_int:
            write_array_elements<uint32_t>((varray<uint32_t> *) pa, prototype, propname, dtype);
            break;
        case dt_long:
            write_array_elements<uint64_t>((varray<uint64_t> *) pa, prototype, propname, dtype);
            break;
        case dt_float:
            write_array_elements<float>((varray<float> *) pa, prototype, propname, dtype);
            break;
        case dt_double:
            write_array_elements<double>((varray<double> *) pa, prototype, propname, dtype);
            break;
        default:
            throw std::invalid_argument("unsupported data type of array element");
            break;
    }
}

///////////////////////////////////////////////////////////////
// methods implements of Struct
///////////////////////////////////////////////////////////////
Struct::Struct(size_t len)
{
    StructInstanceCounter::onStructConstructed(GENERIC_STRUCT_NAME);
    this->name = GENERIC_STRUCT_NAME;
    this->clazz = sc_struct;
    this->data.resize(len);
}

Struct::Struct(std::string name, StructClass clazz)
{
    StructInstanceCounter::onStructConstructed(GENERIC_STRUCT_NAME);
    this->name = name;
    this->clazz = clazz;
}

Struct::~Struct()
{
    StructInstanceCounter::onStructDestructed(GENERIC_STRUCT_NAME);
}

size_t Struct::calcsize(void)
{
    return this->data.bytes();
}

void Struct::write(StructEncoder &encoder)
{
    std::stringstream ss;
    ss << "byte[" << this->data.size() << ']';
    encoder.write_array(&this->data, ss.str(), "data", dt_byte);
}

void Struct::read(StructDecoder &decoder)
{
    decoder.read_array(&this->data, dt_byte, this->data.size());
}

void Struct::print(std::ostream &os)
{
    TextStructEncoder encoder(&os);
    encoder.write_struct(this, "");
}

void Struct::encode(StructEncoder &encoder)
{
    encoder.write_struct(this, "");
}

void Struct::encode(std::ostream &output)
{
    BinaryStructEncoder encoder(&output);
    encode(encoder);
}

void Struct::decode(std::istream &input, StructFactory &factory)
{
    BinaryStructDecoder decoder(&input, &factory);
    decode(decoder);
}

void Struct::decode(StructDecoder &decoder)
{
    decoder.read_struct(this);
}

void TextStructEncoder::indent()
{
    for (int i = 0; i < level; ++i) {
        *output << "    ";
    }
}

///////////////////////////////////////////////////////////////
// methods implements of TextStructEncoder
///////////////////////////////////////////////////////////////
void TextStructEncoder::tostr(DataType dtype, void *pp, std::ostream &ss)
{
    switch (dtype) {
        case dt_byte:
            ss << std::hex << std::uppercase << std::setw(2) << std::setfill('0') << (uint32_t) *(CAST_PTR(uint8_t, pp));
            break;
        case dt_boolean:
            ss << ((*CAST_PTR(bool, pp)) ? "true" : "false");
            break;
        case dt_short:
            ss << "(DEC:" << std::dec << *CAST_PTR(int16_t, pp)
            << " HEX:" << std::hex << std::uppercase << std::setw(4) << std::setfill('0') << (uint32_t) *CAST_PTR(int16_t, pp)
            << ")";
            break;
        case dt_int:
            ss << "(DEC:" << std::dec << *CAST_PTR(int32_t, pp)
            << " HEX:" << std::hex << std::uppercase << std::setw(8) << std::setfill('0') << *CAST_PTR(int32_t, pp)
            << ")";
            break;
        case dt_long:
            ss << "(DEC:" << std::dec << *CAST_PTR(int64_t, pp)
            << " HEX:" << std::hex << std::uppercase << std::setw(16) << std::setfill('0') << *CAST_PTR(int64_t, pp)
            << ")";
            break;
        case dt_float:
            ss << *CAST_PTR(float, pp);
            break;
        case dt_double:
            ss << *CAST_PTR(double, pp);
            break;
        default:
            throw std::invalid_argument("unsupported data type");
    }
}

TextStructEncoder::TextStructEncoder(std::ostream *output)
{
    this->output = output;
    this->level = 0;
}

void TextStructEncoder::begin_write_struct(Struct *pp, const std::string propname)
{
    indent();
    *output << pp->struct_name();
    if (propname != "") {
        *output << ' ' << propname;
    }
    *output << " = {" << std::endl;
    ++level;
}

void TextStructEncoder::end_write_struct(Struct *pp, const std::string propname)
{
    --level;
    indent();
    *output << "}" << std::endl;
}

void TextStructEncoder::write_bitfield(uint32_t fv, int nbits, const std::string propname)
{
    indent();
    *output << propname << ':' << nbits << " = BIN:";
    uint32_t mask = 0x01 << (nbits - 1);
    for (int i = 0; i < nbits; ++i) {
        *output << ((mask & fv) != 0 ? '1' : '0');
        mask >>= 1;
    }
    *output << std::endl;
}

void TextStructEncoder::write_basic(void *pp, const std::string prototype, const std::string propname, DataType dtype, DataType ctype, size_t index)
{
    std::stringstream ss;
    tostr(dtype, pp, ss);
    
    if (ctype == dt_none) {
        indent();
        *output << prototype << ' ' << propname << " = " << ss.str() << std::endl;
    } else {
        if (index > 0) {
            *output << ", ";
        }
        *output << ss.str();
    }
}

void TextStructEncoder::write_string(void *pa, const std::string prototype, const std::string propname)
{
    varray<char> *va = (varray<char> *) pa;
    indent();
    *output << propname << " = \"";
    if (va->size() > 0) {
        char *array = va->array();
        for (size_t i = 0; i < va->size() && array[i] != 0; ++i) {
            output->write(array + i, 1);
        }
    }
    *output << "\"" << std::endl;
}

void TextStructEncoder::begin_write_array(size_t len, const std::string prototype, const std::string propname)
{
    indent();
    *output << prototype << ' ' << propname << " = " << len << "[";
}

void TextStructEncoder::end_write_array(size_t len, const std::string prototype, const std::string propname)
{
    *output << "]" << std::endl;
}

void TextStructEncoder::begin_write_vector(void *pv, const std::string prototype, const std::string propname)
{
    indent();
    std::vector<Struct *> *vec = CAST_PTR(std::vector<Struct *>, pv);
    *output << prototype << ' ' << propname << " = " << vec->size() << "[";
    if (vec->size() > 0) {
        *output << std::endl;
    }
    ++level;
}

void TextStructEncoder::end_write_vector(void *pv, const std::string prototype, const std::string propname)
{
    --level;
    std::vector<Struct *> *vec = CAST_PTR(std::vector<Struct *>, pv);
    if (vec->size() > 0) {
        indent();
    }
    *output << "]" << std::endl;
}

///////////////////////////////////////////////////////////////
// methods implements of BinaryStructEncoder
///////////////////////////////////////////////////////////////
int BinaryStructEncoder::put_bytes(const void *ptr, int start, int len)
{
    uint8_t *byte_ptr = (uint8_t *) ptr;
    convert_byte_order(byte_ptr, start, len);
    output->write((char*) (byte_ptr + start), len);
    return len;
}

BinaryStructEncoder::BinaryStructEncoder(std::ostream *output)
{
    this->output = output;
    this->bits8 = 0;
    this->index = 0;
}

void BinaryStructEncoder::write_basic(void *pp, const std::string prototype, const std::string propname, DataType dtype, DataType ctype, size_t index)
{
    switch (dtype) {
        case dt_byte:
        case dt_boolean:
            output->write(CAST_PTR(char, pp), 1);
            break;
        case dt_short:
            write<uint16_t>(*CAST_PTR(uint16_t, pp));
            break;
        case dt_int:
            write<uint32_t>(*CAST_PTR(uint32_t, pp));
            break;
        case dt_long:
            write<uint64_t>(*CAST_PTR(uint64_t, pp));
            break;
        case dt_float:
            write<float>(*CAST_PTR(float, pp));
            break;
        case dt_double:
            write<double>(*CAST_PTR(double, pp));
            break;
        default:
            throw std::invalid_argument("unsupported basic data type");
    }
}

void BinaryStructEncoder::write_string(void *pa, const std::string prototype, const std::string propname)
{
    varray<char> *va = (varray<char> *) pa;
    output->write(va->array(), va->size());
}

void BinaryStructEncoder::begin_write_struct(Struct *pp, const std::string propname)
{
    if (pp->struct_class() == sc_bitfield) {
        this->bits8 = 0;
        this->index = 0;
    }
}

void BinaryStructEncoder::end_write_struct(Struct *pp, const std::string propname)
{
    if (pp->struct_class() == sc_bitfield) {
        if (this->index > 0) {
            this->bits8 <<= 8 - this->index;
            this->output->write((char *) &this->bits8, 1);
            this->bits8 = 0;
            this->index = 0;
        }
    }
}

void BinaryStructEncoder::write_bitfield(uint32_t fv, int nbits, const std::string propname)
{
    uint32_t bitmask;
    for (uint32_t mask = 1 << (nbits - 1); mask > 0; mask >>= 1) {
        bitmask = (mask & fv) == 0 ? 0 : 1;
        this->bits8 = (this->bits8 << 1) | bitmask;
        ++this->index;
        if (this->index == 8) {
            this->output->write((char *) &this->bits8, 1);
            this->bits8 = 0;
            this->index = 0;
        }
    }
}

