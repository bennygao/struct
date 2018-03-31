//
//  main.cpp
//  demo
//
//  Created by 高波 on 2018/3/27.
//  Copyright © 2018年 高波. All rights reserved.
//

#include <iostream>
#include <sstream>
#include <fstream>
#include "structpp.hpp"
#include "demo.hpp"

using namespace structpp;
using namespace demo;
using namespace std;

int main(int argc, const char * argv[]) {
    ifstream input("/tmp/data.bin");
    if (!input.is_open()) {
        cerr << "/tmp/data.bin not existed" << endl;
        return -1;
    }
    
    Shelf *shelf = new Shelf();
    DemoStructFactory factory;
    shelf->decode(input, factory);
    cout << "size=" << shelf->calcsize() << " tellg=" << input.tellg() << endl;
    shelf->print(cout);
    
    stringstream ss;
    shelf->encode(ss);
    cout << "total:" << StructInstanceCounter::active_num() << endl;
    cout << StructInstanceCounter::tostr();
    delete shelf;
    cout << StructInstanceCounter::tostr();
    return 0;
}
