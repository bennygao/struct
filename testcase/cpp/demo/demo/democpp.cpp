#include "democpp.hpp"

using namespace structpp;
using namespace demo;

///////////////////////////////////////////////////////////////
// struct AGeneric
AGeneric::AGeneric(void) : Struct("AGeneric")
{
    // initialize cmd
    this->cmd = (uint16_t) 0;

    // initialize len
    this->len = (int32_t) 0;

}

size_t AGeneric::size(void)
{
    size_t __size = 0;

    __size += 2 * 1;

    __size += 4 * 1;

    return __size;
}

void AGeneric::write(structpp::StructEncoder &encoder)
{
    encoder.write_basic(&this->cmd, "uint16", "cmd", dt_short, dt_none, 0);

    encoder.write_basic(&this->len, "int", "len", dt_int, dt_none, 0);

}

AGeneric::~AGeneric()
{
    std::cerr << "destroy AGeneric" << std::endl;
}

///////////////////////////////////////////////////////////////
// struct Shelf
//
// 货架
Shelf::Shelf(void) : Struct("Shelf")
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

    // initialize frame
    this->frame.resize(26);

    // initialize gift
    this->gift = Gift::instance();

}

size_t Shelf::size(void)
{
    size_t __size = 0;

    __size += 4 * 1;

    __size += 4 * 1;

    this->displayedGoodsNum = (int32_t) this->displayedGoods.size();
    for (int i = 0; i < this->displayedGoods.size(); ++i) {
        __size += this->displayedGoods[i]->size();
    }

    for (int i = 0; i < this->goodsList.size(); ++i) {
        __size += this->goodsList[i]->size();
    }

    for (int i = 0; i < 3; ++i) {
        __size += this->goodsArray[i]->size();
    }

    __size += 64;

    __size += 1 * 26;

    if (this->gift != NULL) {
        __size += this->gift->size();
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

    encoder.write_array(&this->frame, "byte[26]", "frame", dt_byte);

    encoder.write_struct(this->gift, "Gift", "gift");

}

Shelf::~Shelf()
{
    std::cerr << "destroy Shelf" << std::endl;
    for (int i = 0; i < this->displayedGoods.size(); ++i) {
        delete this->displayedGoods[i];
    }

    for (int i = 0; i < this->goodsList.size(); ++i) {
        delete this->goodsList[i];
    }

    for (int i = 0; i < this->goodsArray.size(); ++i) {
        delete this->goodsArray[i];
    }

    delete this->gift;

}

///////////////////////////////////////////////////////////////
// struct Goods
//
// 商品
Goods::Goods(void) : Struct("Goods")
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

size_t Goods::size(void)
{
    size_t __size = 0;

    __size += 4 * 1;

    __size += 64;

    __size += 8 * 1;

    if (this->generic != NULL) {
        __size += this->generic->size();
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

Goods::~Goods()
{
    std::cerr << "destroy Goods" << std::endl;
    delete this->generic;

}

///////////////////////////////////////////////////////////////
// struct Gift
//
// 礼品
Gift::Gift(void) : Struct("Gift")
{
    // initialize id
    this->id = (int32_t) 0;

    // initialize goods
    this->goods = Goods::instance();

}

size_t Gift::size(void)
{
    size_t __size = 0;

    __size += 4 * 1;

    if (this->goods != NULL) {
        __size += this->goods->size();
    }

    return __size;
}

void Gift::write(structpp::StructEncoder &encoder)
{
    encoder.write_basic(&this->id, "int", "id", dt_int, dt_none, 0);

    encoder.write_struct(this->goods, "Goods", "goods");

}

Gift::~Gift()
{
    std::cerr << "destroy Gift" << std::endl;
    delete this->goods;

}
