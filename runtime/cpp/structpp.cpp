#include <iostream>
#include <sstream>
#include <iomanip>
#include <vector>
#include <typeinfo>
#include "structpp.hpp"

#define CAST_PTR(t, p)    ((t *) p)
using namespace structpp;

StructDecoder::StructDecoder(StructFactory *factory)
{
    this->factory = factory;
}

void StructDecoder::read_struct(Struct *s)
{
    s->decode(*this);
}

void StructDecoder::read_vector(void *pv, const std::string sname, size_t num)
{
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

int BinaryStructDecoder::get_bytes(const void *ptr, int start, int len)
{
    uint8_t *byte_ptr = (uint8_t *) ptr;
    input->read((char*) (byte_ptr + start), len);
    reverse_byte_order(byte_ptr, 0, len);
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
            std::cerr << "unsupported operation" << std::endl;
            break;
    }
}

StructEncoder::StructEncoder(void)
{
    
}

void StructEncoder::write_struct(Struct *pp, const std::string prototype)
{
    pp->encode(*this);
}

void StructEncoder::write_vector(void *pv, const std::string prototype, const std::string tname, DataType dtype)
{
    begin_write_vector(pv, prototype);
    
    std::vector<Struct *> *vec = (std::vector<Struct *> *) pv;
    for (int i = 0; i < vec->size(); ++i) {
        Struct *s = (*vec)[i];
        s->encode(*this);
    }
    
    end_write_vector(pv, prototype);
}

void StructEncoder::write_array(void *pa, const std::string prototype, const std::string tname, DataType dtype)
{
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

Struct::Struct(size_t len)
{
    this->data.resize(len);
}

Struct::Struct(void)
{
    
}

Struct::~Struct(void)
{
}

size_t Struct::size(void)
{
    return this->data.bytes();
}

void Struct::encode(StructEncoder &encoder)
{
    std::stringstream ss;
    ss << "Struct(" << this->data.size() << ')';
    encoder.write_array(&this->data, ss.str(), "byte", dt_byte);
}

void Struct::decode(StructDecoder &decoder)
{
    decoder.read_array(&this->data, dt_byte, this->data.size());
}

void Struct::print(std::ostream &os)
{
}

void TextStructEncoder::indent()
{
    for (int i = 0; i < level; ++i) {
        *output << "    ";
    }
}

void TextStructEncoder::tostr(DataType dtype, void *pp, std::ostream &ss)
{
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

TextStructEncoder::TextStructEncoder(std::ostream *output)
{
    this->output = output;
    this->level = 0;
}

void TextStructEncoder::begin_write_struct(const std::string sname)
{
    indent();
    *output << sname << " {" << std::endl;
    ++level;
}

void TextStructEncoder::end_write_struct(const std::string sname)
{
    --level;
    indent();
    *output << "}" << std::endl;
}

void TextStructEncoder::write_basic(void *pp, const std::string prototype, const std::string tname, DataType dtype, DataType ctype, int index)
{
    std::stringstream ss;
    tostr(dtype, pp, ss);
    indent();
    *output << tname << " = " << ss.str() << std::endl;
}

int BinaryStructEncoder::put_bytes(const void *ptr, int start, int len)
{
    uint8_t *byte_ptr = (uint8_t *) ptr;
    reverse_byte_order(byte_ptr, 0, len);
    output->write((char*) (byte_ptr + start), len);
    return len;
}

BinaryStructEncoder::BinaryStructEncoder(std::ostream *output)
{
    this->output = output;
}

void BinaryStructEncoder::write_basic(void *pp, const std::string prototype, const std::string tname, DataType dtype, DataType ctype, int index)
{
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
