#ifndef __struct_demo__
#define __struct_demo__

#include <iostream>
#include <sstream>
#include <map>
#include <vector>
#include "structpp.hpp"

namespace demo {

class Base;
class Color;
class ImageLayer;
class Point;
class Size;
class Task;
class CtrlAndSID;
class MixedGroup;
class Packet;
class Property;
class ExtendProperty;
class AGeneric;
class Goods;
class Shelf;
class Gift;


///////////////////////////////////////////////////////////////
// struct Base
//
// 演示基本数据类型
class Base : public structpp::Struct {
public:
    static Base *instance(void) {
		return new Base();
	}

    virtual ~Base();
	virtual size_t calcsize(void) override;
	virtual void read(structpp::StructDecoder &decoder) override;
	virtual void write(structpp::StructEncoder &encoder) override;

    // 字符串类型,长度16字节。
    structpp::varray<char> stringValue; // prototype: string[16]
    // 字节(byte)类型
    uint8_t byteValue; // prototype: uint8
    // uint8数组
    structpp::varray<uint8_t> uint8Array; // prototype: uint8[4]
    // 短整型
    int16_t shortValue; // prototype: short
    // uint16
    uint16_t uint16Value; // prototype: uint16
    // 整型
    int32_t intValue; // prototype: int
    // uint32
    uint32_t uint32Value; // prototype: uint32
    // 长整型
    int64_t longValue; // prototype: long
    // 浮点数
    float floatValue; // prototype: float
    // 双精度浮点数
    double doubleValue; // prototype: double
    // 定长整数数组
    structpp::varray<int32_t> fixedArray; // prototype: int[5]
    // 变长数据长度变量
    int32_t num; // prototype: int
    // 变长数组
    structpp::varray<int32_t> data; // prototype: int[num]

private:
    Base(void);
};

///////////////////////////////////////////////////////////////
// bitfield Color
//
// 颜色
// 位0-3 颜色透明度
// 位4-7 颜色值
class Color : public structpp::Struct {
public:
    static Color *instance(void) {
		return new Color();
	}

    virtual ~Color();
	virtual size_t calcsize(void) override;
	virtual void read(structpp::StructDecoder &decoder) override;
	virtual void write(structpp::StructEncoder &encoder) override;

    uint32_t transparency; // 4 bits
    uint32_t color; // 4 bits

private:
    Color(void);
};

///////////////////////////////////////////////////////////////
// struct ImageLayer
//
// 图层
class ImageLayer : public structpp::Struct {
public:
    static ImageLayer *instance(void) {
		return new ImageLayer();
	}

    virtual ~ImageLayer();
	virtual size_t calcsize(void) override;
	virtual void read(structpp::StructDecoder &decoder) override;
	virtual void write(structpp::StructEncoder &encoder) override;

    // 左上角坐标
    Point* leftTop; // prototype: Point
    // 图层大小
    Size* size; // prototype: Size
    // 图层颜色
    Color* color; // prototype: Color

private:
    ImageLayer(void);
};

///////////////////////////////////////////////////////////////
// struct Point
//
// 坐标
class Point : public structpp::Struct {
public:
    static Point *instance(void) {
		return new Point();
	}

    virtual ~Point();
	virtual size_t calcsize(void) override;
	virtual void read(structpp::StructDecoder &decoder) override;
	virtual void write(structpp::StructEncoder &encoder) override;

    // x轴坐标
    int32_t x; // prototype: int
    // y轴坐标
    int32_t y; // prototype: int

private:
    Point(void);
};

///////////////////////////////////////////////////////////////
// struct Size
//
// 尺寸
class Size : public structpp::Struct {
public:
    static Size *instance(void) {
		return new Size();
	}

    virtual ~Size();
	virtual size_t calcsize(void) override;
	virtual void read(structpp::StructDecoder &decoder) override;
	virtual void write(structpp::StructEncoder &encoder) override;

    // 宽度
    int32_t width; // prototype: int
    // 高度
    int32_t height; // prototype: int

private:
    Size(void);
};

///////////////////////////////////////////////////////////////
// struct Task
//
// 任务
class Task : public structpp::Struct {
public:
    static Task *instance(void) {
		return new Task();
	}

    virtual ~Task();
	virtual size_t calcsize(void) override;
	virtual void read(structpp::StructDecoder &decoder) override;
	virtual void write(structpp::StructEncoder &encoder) override;

    // 任务控制字和SessionID
    CtrlAndSID* ctrlAndSID; // prototype: CtrlAndSID
    MixedGroup* mixedGroup; // prototype: MixedGroup
    // CCITT-CRC16
    uint16_t crc16; // prototype: uint16

private:
    Task(void);
};

///////////////////////////////////////////////////////////////
// bitfield CtrlAndSID
//
// 任务控制字
class CtrlAndSID : public structpp::Struct {
public:
    static CtrlAndSID *instance(void) {
		return new CtrlAndSID();
	}

    virtual ~CtrlAndSID();
	virtual size_t calcsize(void) override;
	virtual void read(structpp::StructDecoder &decoder) override;
	virtual void write(structpp::StructEncoder &encoder) override;

    uint32_t ctrl; // 3 bits
    uint32_t SID; // 4 bits
    uint32_t reserved; // 17 bits

private:
    CtrlAndSID(void);
};

///////////////////////////////////////////////////////////////
// struct MixedGroup
//
// 混杂模式
class MixedGroup : public structpp::Struct {
public:
    static MixedGroup *instance(void) {
		return new MixedGroup();
	}

    virtual ~MixedGroup();
	virtual size_t calcsize(void) override;
	virtual void read(structpp::StructDecoder &decoder) override;
	virtual void write(structpp::StructEncoder &encoder) override;

    // 发射功率
    uint16_t power; // prototype: uint16
    // 通讯信道;
    uint8_t channel; // prototype: uint8
    // 后续数据包(Packet)个数
    uint16_t num; // prototype: uint16
    // 由num指定个数的多个数据包
    std::vector<Packet *> packets; // prototype: Packet[num]

private:
    MixedGroup(void);
};

///////////////////////////////////////////////////////////////
// struct Packet
//
// 数据包
class Packet : public structpp::Struct {
public:
    static Packet *instance(void) {
		return new Packet();
	}

    virtual ~Packet();
	virtual size_t calcsize(void) override;
	virtual void read(structpp::StructDecoder &decoder) override;
	virtual void write(structpp::StructEncoder &encoder) override;

    // ESL ID
    structpp::varray<uint8_t> eslId; // prototype: byte[4]
    // 数据
    structpp::varray<uint8_t> data; // prototype: byte[26]

private:
    Packet(void);
};

///////////////////////////////////////////////////////////////
// bitfield Property
class Property : public structpp::Struct {
public:
    static Property *instance(void) {
		return new Property();
	}

    virtual ~Property();
	virtual size_t calcsize(void) override;
	virtual void read(structpp::StructDecoder &decoder) override;
	virtual void write(structpp::StructEncoder &encoder) override;

    uint32_t colorOrder; // 4 bits
    uint32_t layerType; // 2 bits
    uint32_t direction; // 2 bits

private:
    Property(void);
};

///////////////////////////////////////////////////////////////
// bitfield ExtendProperty
class ExtendProperty : public structpp::Struct {
public:
    static ExtendProperty *instance(void) {
		return new ExtendProperty();
	}

    virtual ~ExtendProperty();
	virtual size_t calcsize(void) override;
	virtual void read(structpp::StructDecoder &decoder) override;
	virtual void write(structpp::StructEncoder &encoder) override;

    uint32_t layerType; // 3 bits
    uint32_t decimalCount; // 6 bits
    uint32_t reserved; // 7 bits

private:
    ExtendProperty(void);
};

///////////////////////////////////////////////////////////////
// struct AGeneric
class AGeneric : public structpp::Struct {
public:
    static AGeneric *instance(void) {
		return new AGeneric();
	}

    virtual ~AGeneric();
	virtual size_t calcsize(void) override;
	virtual void read(structpp::StructDecoder &decoder) override;
	virtual void write(structpp::StructEncoder &encoder) override;

    int16_t cmd; // prototype: short
    int32_t len; // prototype: int

private:
    AGeneric(void);
};

///////////////////////////////////////////////////////////////
// struct Goods
//
// 商品
class Goods : public structpp::Struct {
public:
    static Goods *instance(void) {
		return new Goods();
	}

    virtual ~Goods();
	virtual size_t calcsize(void) override;
	virtual void read(structpp::StructDecoder &decoder) override;
	virtual void write(structpp::StructEncoder &encoder) override;

    // 商品ID
    int32_t id; // prototype: int
    // 商品名称
    structpp::varray<char> name; // prototype: string[64]
    // 单价(每500克)
    double unitPrice; // prototype: double
    // 泛型
    Struct* generic; // prototype: Struct(6)
    int32_t num; // prototype: int
    structpp::varray<uint8_t> data; // prototype: byte[num]

private:
    Goods(void);
};

///////////////////////////////////////////////////////////////
// struct Shelf
//
// 货架
class Shelf : public structpp::Struct {
public:
    static Shelf *instance(void) {
		return new Shelf();
	}

    virtual ~Shelf();
	virtual size_t calcsize(void) override;
	virtual void read(structpp::StructDecoder &decoder) override;
	virtual void write(structpp::StructEncoder &encoder) override;

    // 货架编号
    int32_t id; // prototype: int
    // 货架上陈列的商品数量
    int32_t displayedGoodsNum; // prototype: int
    // 货架上陈列的商品
    std::vector<Goods *> displayedGoods; // prototype: Goods[displayedGoodsNum]
    // 变长
    std::vector<Goods *> goodsList; // prototype: Goods[]
    // 定长
    std::vector<Goods *> goodsArray; // prototype: Goods[3]
    // 后面的字段
    structpp::varray<char> name; // prototype: string[64]
    // Color
    Color* color; // prototype: Color
    // byte数组
    structpp::varray<uint8_t> frame; // prototype: byte[26]
    // 基类Struct, 字节长度6
    Struct* generic; // prototype: Struct(6)
    // 礼品
    Gift* gift; // prototype: Gift

private:
    Shelf(void);
};

///////////////////////////////////////////////////////////////
// struct Gift
//
// 礼品
class Gift : public structpp::Struct {
public:
    static Gift *instance(void) {
		return new Gift();
	}

    virtual ~Gift();
	virtual size_t calcsize(void) override;
	virtual void read(structpp::StructDecoder &decoder) override;
	virtual void write(structpp::StructEncoder &encoder) override;

    // 礼品ID
    int32_t id; // prototype: int
    // 礼品赠送的商品
    Goods* goods; // prototype: Goods

private:
    Gift(void);
};

///////////////////////////////////////////////////////////////
// struct instance factory
class DemoStructFactory : public structpp::StructFactory {
private:
    std::map<std::string, int> map;

public:
    DemoStructFactory(void) {
        map.insert(std::pair<std::string, int>("Base", 1));
        map.insert(std::pair<std::string, int>("Color", 2));
        map.insert(std::pair<std::string, int>("ImageLayer", 3));
        map.insert(std::pair<std::string, int>("Point", 4));
        map.insert(std::pair<std::string, int>("Size", 5));
        map.insert(std::pair<std::string, int>("Task", 6));
        map.insert(std::pair<std::string, int>("CtrlAndSID", 7));
        map.insert(std::pair<std::string, int>("MixedGroup", 8));
        map.insert(std::pair<std::string, int>("Packet", 9));
        map.insert(std::pair<std::string, int>("Property", 10));
        map.insert(std::pair<std::string, int>("ExtendProperty", 11));
        map.insert(std::pair<std::string, int>("AGeneric", 12));
        map.insert(std::pair<std::string, int>("Goods", 13));
        map.insert(std::pair<std::string, int>("Shelf", 14));
        map.insert(std::pair<std::string, int>("Gift", 15));
    }

    virtual structpp::Struct *create(const std::string sname) override {
        int id = map.at(sname);
        switch (id) {
            case 1: return Base::instance();
            case 2: return Color::instance();
            case 3: return ImageLayer::instance();
            case 4: return Point::instance();
            case 5: return Size::instance();
            case 6: return Task::instance();
            case 7: return CtrlAndSID::instance();
            case 8: return MixedGroup::instance();
            case 9: return Packet::instance();
            case 10: return Property::instance();
            case 11: return ExtendProperty::instance();
            case 12: return AGeneric::instance();
            case 13: return Goods::instance();
            case 14: return Shelf::instance();
            case 15: return Gift::instance();
            default:
                return NULL;
        }
    }
};


} /* namespace demo */

#endif /* __struct_hpp__ */
