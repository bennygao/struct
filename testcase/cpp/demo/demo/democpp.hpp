#ifndef __struct_demo__
#define __struct_demo__

#include <iostream>
#include <sstream>
#include <map>
#include <vector>
#include "structpp.hpp"

namespace demo {

class AGeneric;
class Shelf;
class Goods;
class Gift;


///////////////////////////////////////////////////////////////
// struct AGeneric
class AGeneric : public structpp::Struct {
public:
    static AGeneric *instance(void) {
		return new AGeneric();
	}

	virtual size_t size(void) override;
	virtual void write(structpp::StructEncoder &encoder) override;

    uint16_t cmd; // prototype: uint16

    int32_t len; // prototype: int

private:
    AGeneric(void);
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

	virtual size_t size(void) override;
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

    // byte数组
    structpp::varray<uint8_t> frame; // prototype: byte[26]

    // 礼品
    Gift* gift; // prototype: Gift

private:
    Shelf(void);
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

	virtual size_t size(void) override;
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
// struct Gift
//
// 礼品
class Gift : public structpp::Struct {
public:
    static Gift *instance(void) {
		return new Gift();
	}

	virtual size_t size(void) override;
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
        map.insert(std::pair<std::string, int>("AGeneric", 1));
        map.insert(std::pair<std::string, int>("Shelf", 2));
        map.insert(std::pair<std::string, int>("Goods", 3));
        map.insert(std::pair<std::string, int>("Gift", 4));
    }

    virtual structpp::Struct *create(const std::string sname) override {
        int id = map.at(sname);
        switch (id) {
            case 1: return AGeneric::instance();
            case 2: return Shelf::instance();
            case 3: return Goods::instance();
            case 4: return Gift::instance();
            default:
                return NULL;
        }
    }
};


} /* namespace demo */

#endif /* __struct_hpp__ */
