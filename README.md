struct 协议生成工具
====
struct是一个专门针对Java程序与C语言开发程序通讯的应用层协议生成工具，网络通讯字节流采用C语言开发习惯的方式，方便C语言程序进行decode处理。

struct规定了一套简单的语法来定义协议，协议定义存放在一个文本文件中，利用struct工具可以编译协议定义文件，生成Java POJOs或者HTML格式的协议说明文档，以及C语言的头文件。

struct让开发者从雷同、繁琐、易错的协议encode/decode编码中脱身，站在更高的层次上应用层维护通讯协议。

# 1. struct协议定义语法
## 1.1 支持的数据类型
> 以下说明中`C语言映射`都使用C语言标准头文件`stdint.h`中的定义。

### 基本数据类型
类型      | 字节数 | Java语言映射 | C语言映射
-------- | ----- | ----------- | --------------
byte     | 1     | byte        | unsigned char
int8     | 1     | byte        | int8_t
uint8    | 1     | byte        | uint8_t
short    | 2     | short       | short
int16    | 2     | short       | int16_t
uint16   | 2     | short       | uint16_t
int      | 4     | int         | int
int32    | 4     | int         | int32_t
uint32   | 4     | int         | uint32_t
long     | 8     | long        | int64_t
int64    | 8     | long        | int64_t
uint64   | 8     | long        | uint64_t
float    | 4     | float       | float
double   | 8     | double      | double

### 字符串
类型      | 字节数 | Java语言映射 | C语言映射
-------- | ----- | ----------- | --------------
string   | N     | java.lang.String | char*

### 嵌套struct
一个struct定义中可以嵌套包含其他struct，可以包含单个struct，也可以包含struct的数组。

### 数组
基本数据类型和嵌套struct都可以定义为数组。**不支持定义字符串类型的数组**。

## 1.2 协议定义文件
协议定义存放在文本文件中，建议以`.struct`作为后缀名，以下协议定义文件简称为`struct文件`。**可以以任何合法的操作系统后缀名作为协议定义文件的后缀名，`.struct`只是建议不是强制。**

下例是一个示例定义`demo.struct`内容。

     1. ###########################################
     2. # 商品陈列协议定义
     3. # 版本: 1.0.1
     4. # 修改人: Tony
     5. ###########################################
     6.
     7. import "base.struct";
     8.
     9. /* 商品 */
    10. struct Goods {
    11.     // 商品ID
    12.     int id = 1;
    13.
    14.    // 商品名称
    15.    string[64] name = "烟台苹果";
    16.
    17.    // 单价(每500克)
    18.    double unitPrice = 15.05;
    19. }
    20.
    21. // 货架
    22. struct Shelf {
    23.     // 货架编号
    24.     int id;
    25.
    26.     // 货架上陈列的商品数量
    27.     int displayedGoodsNum;
    28.
    29.     // 货架上陈列的商品
    30.     Goods[displayedGoodsNum] displayedGoods;
    31. }
    32.
    33. // 礼品
    34. struct Gift {
    35.     // 礼品ID
    36.     int id;
    37.
    38.     // 礼品赠送的商品
    39.     Goods goods;
    40. }

### 包含其他struct文件
第7行，一个struct文件可以用`import`指令包含另外一个struct文件，以下是被包含的`base.struct`文件的内容。

    // 演示基本数据类型
    struct Base {
        // 字符串类型,长度16字节。
        string[16] stringValue = "Hello World!";

        // 字节(byte)类型
        byte byteValue = 0;

        // 短整型
        short shortValue = 1;

        // 整型
        int intValue = 2;

        // 长整型
        long longValue = 3;

        // 浮点数
        float floatValue = 1.11;

        // 双精度浮点数
        double doubleValue = 3.1415926;
    }

可以指定多个`import`包含多个struct件。对于大型的、功能模块较多的系统，开发者可以根据需要把协议定义分开放在多个struct文件中，在最顶层的struct文件中，用import指令把它们都包含进来。

包含的协议定义文件路径名应用双引号括住，可以指定路径，例如:

    # 指定绝对路径
    import "/workspace/project/protocol/base.struct";

    # 指定相对于当前协议定义文件所在目录的相对路径
    import "../model1/base.struct";

    # 不指定路径，则表示与当前协议定义文件在同一目录
    import "base.struct";

### 注释
struct支持三种注释格式，井号`#`和双斜杠`//`都是单行注释，`/* ... */`之间可以注释多行的内容。

井号`#`单行注释在编译时会被完全忽略，而`//`和`/* ... */`注释都会被记录并输出到最终创建的Java POJO class中，也被用于生成HTML格式的协议定义文档。

### 定义一个struct
第9~19行定义了一个简单的struct，struct定义应以`struct StructName {`开始，`}`结束。

     9. /* 商品 */
    10. struct Goods {
    11.     // 商品ID
    12.     int id = 1;
    13.
    14.    // 商品名称
    15.    string[64] name = "烟台苹果";
    16.
    17.    // 单价(每500克)
    18.    double unitPrice = 15.05;
    19. }

第10行定义这个struct的名字为`Goods`。第11~18行给Goods这个struct定义了三个字段，数据类型分别为整数(int)、长度64字节的字符串(string[64])和浮点数(double)。

### 指定字段的初始值
可以为**基本数据类型**和**字符串**类型的字段指定初始值，如第11~18行所示。

每个struct最终都会被生成一个Java POJO，没有指定初始值的基本数据类型字段，在POJO实例化时都会被赋予初值`0`，没有指定初始值的字符串类型字段，在POJO实例化时会被赋予初值`null`。

如果在struct文件中指定了初始值，则在POJO实例化时，会赋予struct文件中定义的初始值。

### 嵌套包含其他struct或者struct的数组
第21~31行定义了一个复合的struct `Shelf`，这个struct中包含了另外一个struct `Goods`的变长数组，数组元素个数引用另外一个字段 `displayedGoodsNum`。

    21. // 货架
    22. struct Shelf {
    23.     // 货架编号
    24.     int id;
    25. 
    26.     // 货架上陈列的商品数量
    27.     int displayedGoodsNum;
    28. 
    29.     // 货架上陈列的商品
    30.     Goods[displayedGoodsNum] displayedGoods;
    31. }

这个`Goods[displayedGoodsNum] displayedGoods` 字段在生成的Java POJO中会被定义为`java.util.List<Goods> displayedGoods;`，因为List是可变长度的，所以开发者在编码时，只需要往这个List中添加元素，无需关注元素的个数，在最终encode为网络字节流时，struct会计算List的个数，自动给`displayedGoodsNum`这个字段赋值。
> **注意**: 作为变长数组下标的字段会在struct encode成网络字节流时被赋值，因此代码中对该字段的赋值无意义。

当然，struct可以简单的只包括另外一个struct，例如第33~40行定义的struct `Gift`。

# 2. 编译struct文件生成代码


