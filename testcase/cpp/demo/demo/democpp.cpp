#include "democpp.hpp"

using namespace structpp;
using namespace demo;

///////////////////////////////////////////////////////////////
// struct Base
//
// 演示基本数据类型
Base::Base(void) : Struct("Base", sc_struct)
{
    // initialize stringValue
    this->stringValue.resize(16);
    this->stringValue = "Hello World!";

    // initialize byteValue
    this->byteValue = (uint8_t) 0;

    // initialize uint8Array
    this->uint8Array.resize(4);

    // initialize shortValue
    this->shortValue = (int16_t) 0;

    // initialize uint16Value
    this->uint16Value = (uint16_t) 0;

    // initialize intValue
    this->intValue = (int32_t) 0;

    // initialize uint32Value
    this->uint32Value = (uint32_t) 0;

    // initialize longValue
    this->longValue = (int64_t) 0;

    // initialize floatValue
    this->floatValue = (float) 0.00;

    // initialize doubleValue
    this->doubleValue = (double) 0.00;

    // initialize fixedArray
    this->fixedArray.resize(5);

    // initialize num
    this->num = (int32_t) 0;

    // initialize data

}

Base::~Base()
{
}

size_t Base::calcsize(void)
{
    size_t __size = 0;

    __size += 16;
    __size += 1 * 1;
    __size += 1 * 4;
    __size += 2 * 1;
    __size += 2 * 1;
    __size += 4 * 1;
    __size += 4 * 1;
    __size += 8 * 1;
    __size += 4 * 1;
    __size += 8 * 1;
    __size += 4 * 5;
    __size += 4 * 1;
    __size += 4 * this->data.size();

    return __size;
}

void Base::write(structpp::StructEncoder &encoder)
{
    encoder.write_string(&this->stringValue, "string[16]", "stringValue");

    encoder.write_basic(&this->byteValue, "uint8", "byteValue", dt_byte, dt_none, 0);

    encoder.write_array(&this->uint8Array, "uint8[4]", "uint8Array", dt_byte);

    encoder.write_basic(&this->shortValue, "short", "shortValue", dt_short, dt_none, 0);

    encoder.write_basic(&this->uint16Value, "uint16", "uint16Value", dt_short, dt_none, 0);

    encoder.write_basic(&this->intValue, "int", "intValue", dt_int, dt_none, 0);

    encoder.write_basic(&this->uint32Value, "uint32", "uint32Value", dt_int, dt_none, 0);

    encoder.write_basic(&this->longValue, "long", "longValue", dt_long, dt_none, 0);

    encoder.write_basic(&this->floatValue, "float", "floatValue", dt_float, dt_none, 0);

    encoder.write_basic(&this->doubleValue, "double", "doubleValue", dt_double, dt_none, 0);

    encoder.write_array(&this->fixedArray, "int[5]", "fixedArray", dt_int);

    this->num = (int32_t) this->data.size();
    encoder.write_basic(&this->num, "int", "num", dt_int, dt_none, 0);

    encoder.write_array(&this->data, "int[num]", "data", dt_int);

}

void Base::read(structpp::StructDecoder &decoder)
{
}


///////////////////////////////////////////////////////////////
// bitfield Color
//
// 颜色
// 位0-3 颜色透明度
// 位4-7 颜色值
Color::Color(void) : Struct("Color", sc_bitfield)
{
    this->transparency = 1;
    this->color = 8;
}

Color::~Color()
{
}

size_t Color::calcsize(void)
{
    size_t __size = 0;

    __size += 4;
    __size += 4;

    return (__size + 7) >> 3;
}

void Color::read(structpp::StructDecoder &decoder)
{
}

void Color::write(structpp::StructEncoder &encoder)
{
    encoder.write_bitfield(this->transparency, 4, "transparency");
    encoder.write_bitfield(this->color, 4, "color");
}

///////////////////////////////////////////////////////////////
// struct ImageLayer
//
// 图层
ImageLayer::ImageLayer(void) : Struct("ImageLayer", sc_struct)
{
    // initialize leftTop
    this->leftTop = Point::instance();

    // initialize size
    this->size = Size::instance();

    // initialize color
    this->color = Color::instance();

}

ImageLayer::~ImageLayer()
{
    delete this->leftTop;

    delete this->size;

    delete this->color;

}

size_t ImageLayer::calcsize(void)
{
    size_t __size = 0;

    if (this->leftTop != NULL) {
        __size += this->leftTop->calcsize();
    }
    if (this->size != NULL) {
        __size += this->size->calcsize();
    }
    if (this->color != NULL) {
        __size += this->color->calcsize();
    }

    return __size;
}

void ImageLayer::write(structpp::StructEncoder &encoder)
{
    encoder.write_struct(this->leftTop, "Point", "leftTop");

    encoder.write_struct(this->size, "Size", "size");

    encoder.write_struct(this->color, "Color", "color");

}

void ImageLayer::read(structpp::StructDecoder &decoder)
{
}


///////////////////////////////////////////////////////////////
// struct Point
//
// 坐标
Point::Point(void) : Struct("Point", sc_struct)
{
    // initialize x
    this->x = (int32_t) 0;

    // initialize y
    this->y = (int32_t) 0;

}

Point::~Point()
{
}

size_t Point::calcsize(void)
{
    size_t __size = 0;

    __size += 4 * 1;
    __size += 4 * 1;

    return __size;
}

void Point::write(structpp::StructEncoder &encoder)
{
    encoder.write_basic(&this->x, "int", "x", dt_int, dt_none, 0);

    encoder.write_basic(&this->y, "int", "y", dt_int, dt_none, 0);

}

void Point::read(structpp::StructDecoder &decoder)
{
}


///////////////////////////////////////////////////////////////
// struct Size
//
// 尺寸
Size::Size(void) : Struct("Size", sc_struct)
{
    // initialize width
    this->width = (int32_t) 0;

    // initialize height
    this->height = (int32_t) 0;

}

Size::~Size()
{
}

size_t Size::calcsize(void)
{
    size_t __size = 0;

    __size += 4 * 1;
    __size += 4 * 1;

    return __size;
}

void Size::write(structpp::StructEncoder &encoder)
{
    encoder.write_basic(&this->width, "int", "width", dt_int, dt_none, 0);

    encoder.write_basic(&this->height, "int", "height", dt_int, dt_none, 0);

}

void Size::read(structpp::StructDecoder &decoder)
{
}


///////////////////////////////////////////////////////////////
// struct Task
//
// 任务
Task::Task(void) : Struct("Task", sc_struct)
{
    // initialize ctrlAndSID
    this->ctrlAndSID = CtrlAndSID::instance();

    // initialize mixedGroup
    this->mixedGroup = MixedGroup::instance();

    // initialize crc16
    this->crc16 = (uint16_t) 0;

}

Task::~Task()
{
    delete this->ctrlAndSID;

    delete this->mixedGroup;

}

size_t Task::calcsize(void)
{
    size_t __size = 0;

    if (this->ctrlAndSID != NULL) {
        __size += this->ctrlAndSID->calcsize();
    }
    if (this->mixedGroup != NULL) {
        __size += this->mixedGroup->calcsize();
    }
    __size += 2 * 1;

    return __size;
}

void Task::write(structpp::StructEncoder &encoder)
{
    encoder.write_struct(this->ctrlAndSID, "CtrlAndSID", "ctrlAndSID");

    encoder.write_struct(this->mixedGroup, "MixedGroup", "mixedGroup");

    encoder.write_basic(&this->crc16, "uint16", "crc16", dt_short, dt_none, 0);

}

void Task::read(structpp::StructDecoder &decoder)
{
}


///////////////////////////////////////////////////////////////
// bitfield CtrlAndSID
//
// 任务控制字
CtrlAndSID::CtrlAndSID(void) : Struct("CtrlAndSID", sc_bitfield)
{
    this->ctrl = 0;
    this->SID = 0;
    this->reserved = 0;
}

CtrlAndSID::~CtrlAndSID()
{
}

size_t CtrlAndSID::calcsize(void)
{
    size_t __size = 0;

    __size += 3;
    __size += 4;
    __size += 17;

    return (__size + 7) >> 3;
}

void CtrlAndSID::read(structpp::StructDecoder &decoder)
{
}

void CtrlAndSID::write(structpp::StructEncoder &encoder)
{
    encoder.write_bitfield(this->ctrl, 3, "ctrl");
    encoder.write_bitfield(this->SID, 4, "SID");
    encoder.write_bitfield(this->reserved, 17, "reserved");
}

///////////////////////////////////////////////////////////////
// struct MixedGroup
//
// 混杂模式
MixedGroup::MixedGroup(void) : Struct("MixedGroup", sc_struct)
{
    // initialize power
    this->power = (uint16_t) 0;

    // initialize channel
    this->channel = (uint8_t) 0;

    // initialize num
    this->num = (uint16_t) 0;

    // initialize packets

}

MixedGroup::~MixedGroup()
{
    for (size_t i = 0; i < this->packets.size(); ++i) {
        delete this->packets[i];
    }

}

size_t MixedGroup::calcsize(void)
{
    size_t __size = 0;

    __size += 2 * 1;
    __size += 1 * 1;
    __size += 2 * 1;
    this->num = (uint16_t) this->packets.size();
    for (size_t i = 0; i < this->packets.size(); ++i) {
        __size += this->packets[i]->calcsize();
    }

    return __size;
}

void MixedGroup::write(structpp::StructEncoder &encoder)
{
    encoder.write_basic(&this->power, "uint16", "power", dt_short, dt_none, 0);

    encoder.write_basic(&this->channel, "uint8", "channel", dt_byte, dt_none, 0);

    this->num = (uint16_t) this->packets.size();
    encoder.write_basic(&this->num, "uint16", "num", dt_short, dt_none, 0);

    encoder.write_vector(&this->packets, "Packet[num]", "packets");

}

void MixedGroup::read(structpp::StructDecoder &decoder)
{
}


///////////////////////////////////////////////////////////////
// struct Packet
//
// 数据包
Packet::Packet(void) : Struct("Packet", sc_struct)
{
    // initialize eslId
    this->eslId.resize(4);

    // initialize data
    this->data.resize(26);

}

Packet::~Packet()
{
}

size_t Packet::calcsize(void)
{
    size_t __size = 0;

    __size += 1 * 4;
    __size += 1 * 26;

    return __size;
}

void Packet::write(structpp::StructEncoder &encoder)
{
    encoder.write_array(&this->eslId, "byte[4]", "eslId", dt_byte);

    encoder.write_array(&this->data, "byte[26]", "data", dt_byte);

}

void Packet::read(structpp::StructDecoder &decoder)
{
}


///////////////////////////////////////////////////////////////
// bitfield Property
Property::Property(void) : Struct("Property", sc_bitfield)
{
    this->colorOrder = 0;
    this->layerType = 0;
    this->direction = 0;
}

Property::~Property()
{
}

size_t Property::calcsize(void)
{
    size_t __size = 0;

    __size += 4;
    __size += 2;
    __size += 2;

    return (__size + 7) >> 3;
}

void Property::read(structpp::StructDecoder &decoder)
{
}

void Property::write(structpp::StructEncoder &encoder)
{
    encoder.write_bitfield(this->colorOrder, 4, "colorOrder");
    encoder.write_bitfield(this->layerType, 2, "layerType");
    encoder.write_bitfield(this->direction, 2, "direction");
}

///////////////////////////////////////////////////////////////
// bitfield ExtendProperty
ExtendProperty::ExtendProperty(void) : Struct("ExtendProperty", sc_bitfield)
{
    this->layerType = 0;
    this->decimalCount = 0;
    this->reserved = 0;
}

ExtendProperty::~ExtendProperty()
{
}

size_t ExtendProperty::calcsize(void)
{
    size_t __size = 0;

    __size += 3;
    __size += 6;
    __size += 7;

    return (__size + 7) >> 3;
}

void ExtendProperty::read(structpp::StructDecoder &decoder)
{
}

void ExtendProperty::write(structpp::StructEncoder &encoder)
{
    encoder.write_bitfield(this->layerType, 3, "layerType");
    encoder.write_bitfield(this->decimalCount, 6, "decimalCount");
    encoder.write_bitfield(this->reserved, 7, "reserved");
}

///////////////////////////////////////////////////////////////
// struct AGeneric
AGeneric::AGeneric(void) : Struct("AGeneric", sc_struct)
{
    // initialize cmd
    this->cmd = (int16_t) 0;

    // initialize len
    this->len = (int32_t) 0;

}

AGeneric::~AGeneric()
{
}

size_t AGeneric::calcsize(void)
{
    size_t __size = 0;

    __size += 2 * 1;
    __size += 4 * 1;

    return __size;
}

void AGeneric::write(structpp::StructEncoder &encoder)
{
    encoder.write_basic(&this->cmd, "short", "cmd", dt_short, dt_none, 0);

    encoder.write_basic(&this->len, "int", "len", dt_int, dt_none, 0);

}

void AGeneric::read(structpp::StructDecoder &decoder)
{
}


///////////////////////////////////////////////////////////////
// struct Goods
//
// 商品
Goods::Goods(void) : Struct("Goods", sc_struct)
{
    // initialize id
    this->id = (int32_t) 0;

    // initialize name
    this->name.resize(64);
    this->name = "烟台苹果";

    // initialize unitPrice
    this->unitPrice = (double) 0.00;

    // initialize generic
    this->generic = Struct::instance(6);

    // initialize num
    this->num = (int32_t) 0;

    // initialize data

}

Goods::~Goods()
{
    delete this->generic;

}

size_t Goods::calcsize(void)
{
    size_t __size = 0;

    __size += 4 * 1;
    __size += 64;
    __size += 8 * 1;
    if (this->generic != NULL) {
        __size += this->generic->calcsize();
    }
    __size += 4 * 1;
    __size += 1 * this->data.size();

    return __size;
}

void Goods::write(structpp::StructEncoder &encoder)
{
    encoder.write_basic(&this->id, "int", "id", dt_int, dt_none, 0);

    encoder.write_string(&this->name, "string[64]", "name");

    encoder.write_basic(&this->unitPrice, "double", "unitPrice", dt_double, dt_none, 0);

    encoder.write_struct(this->generic, "Struct(6)", "generic");

    this->num = (int32_t) this->data.size();
    encoder.write_basic(&this->num, "int", "num", dt_int, dt_none, 0);

    encoder.write_array(&this->data, "byte[num]", "data", dt_byte);

}

void Goods::read(structpp::StructDecoder &decoder)
{
}


///////////////////////////////////////////////////////////////
// struct Shelf
//
// 货架
Shelf::Shelf(void) : Struct("Shelf", sc_struct)
{
    // initialize id
    this->id = (int32_t) 0;

    // initialize displayedGoodsNum
    this->displayedGoodsNum = (int32_t) 0;

    // initialize displayedGoods

    // initialize goodsList

    // initialize goodsArray
    for (int i = 0; i < 3; ++i) {
        this->goodsArray.push_back(Goods::instance());
    }

    // initialize name
    this->name.resize(64);
    this->name = "烟台苹果";

    // initialize color
    this->color = Color::instance();

    // initialize frame
    this->frame.resize(26);

    // initialize generic
    this->generic = Struct::instance(6);

    // initialize gift
    this->gift = Gift::instance();

}

Shelf::~Shelf()
{
    for (size_t i = 0; i < this->displayedGoods.size(); ++i) {
        delete this->displayedGoods[i];
    }

    for (size_t i = 0; i < this->goodsList.size(); ++i) {
        delete this->goodsList[i];
    }

    for (size_t i = 0; i < this->goodsArray.size(); ++i) {
        delete this->goodsArray[i];
    }

    delete this->color;

    delete this->generic;

    delete this->gift;

}

size_t Shelf::calcsize(void)
{
    size_t __size = 0;

    __size += 4 * 1;
    __size += 4 * 1;
    this->displayedGoodsNum = (int32_t) this->displayedGoods.size();
    for (size_t i = 0; i < this->displayedGoods.size(); ++i) {
        __size += this->displayedGoods[i]->calcsize();
    }
    for (size_t i = 0; i < this->goodsList.size(); ++i) {
        __size += this->goodsList[i]->calcsize();
    }
    for (size_t i = 0; i < 3; ++i) {
        __size += this->goodsArray[i]->calcsize();
    }
    __size += 64;
    if (this->color != NULL) {
        __size += this->color->calcsize();
    }
    __size += 1 * 26;
    if (this->generic != NULL) {
        __size += this->generic->calcsize();
    }
    if (this->gift != NULL) {
        __size += this->gift->calcsize();
    }

    return __size;
}

void Shelf::write(structpp::StructEncoder &encoder)
{
    encoder.write_basic(&this->id, "int", "id", dt_int, dt_none, 0);

    this->displayedGoodsNum = (int32_t) this->displayedGoods.size();
    encoder.write_basic(&this->displayedGoodsNum, "int", "displayedGoodsNum", dt_int, dt_none, 0);

    encoder.write_vector(&this->displayedGoods, "Goods[displayedGoodsNum]", "displayedGoods");

    encoder.write_vector(&this->goodsList, "Goods[]", "goodsList");

    encoder.write_vector(&this->goodsArray, "Goods[3]", "goodsArray");

    encoder.write_string(&this->name, "string[64]", "name");

    encoder.write_struct(this->color, "Color", "color");

    encoder.write_array(&this->frame, "byte[26]", "frame", dt_byte);

    encoder.write_struct(this->generic, "Struct(6)", "generic");

    encoder.write_struct(this->gift, "Gift", "gift");

}

void Shelf::read(structpp::StructDecoder &decoder)
{
}


///////////////////////////////////////////////////////////////
// struct Gift
//
// 礼品
Gift::Gift(void) : Struct("Gift", sc_struct)
{
    // initialize id
    this->id = (int32_t) 0;

    // initialize goods
    this->goods = Goods::instance();

}

Gift::~Gift()
{
    delete this->goods;

}

size_t Gift::calcsize(void)
{
    size_t __size = 0;

    __size += 4 * 1;
    if (this->goods != NULL) {
        __size += this->goods->calcsize();
    }

    return __size;
}

void Gift::write(structpp::StructEncoder &encoder)
{
    encoder.write_basic(&this->id, "int", "id", dt_int, dt_none, 0);

    encoder.write_struct(this->goods, "Goods", "goods");

}

void Gift::read(structpp::StructDecoder &decoder)
{
}

