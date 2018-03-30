//
//  main.cpp
//  demo
//
//  Created by 高波 on 2018/3/27.
//  Copyright © 2018年 高波. All rights reserved.
//

#include <iostream>
#include <fstream>
#include "structpp.hpp"
#include "demo.hpp"

using namespace structpp;
using namespace demo;
using namespace std;

int main(int argc, const char * argv[]) {
    ifstream input("/Users/gaobo/Desktop/Hanshows/struct/testcase/data.bin");
    Shelf *shelf = Shelf::instance();
    DemoStructFactory factory;
    shelf->decode(input, factory);
    cout << "size=" << shelf->calcsize() << " tellg=" << input.tellg() << endl;
    shelf->print(cout);
    delete shelf;
    return 0;
}
