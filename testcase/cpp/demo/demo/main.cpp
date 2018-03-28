//
//  main.cpp
//  demo
//
//  Created by 高波 on 2018/3/27.
//  Copyright © 2018年 高波. All rights reserved.
//

#include <iostream>
#include "structpp.hpp"
#include "democpp.hpp"

using namespace structpp;
using namespace demo;
using namespace std;

int main(int argc, const char * argv[]) {
    Shelf *shelf = Shelf::instance();
    cout << shelf->size() << endl;
    shelf->print(cout);
    
    stringstream ss;
    BinaryStructEncoder encoder(&ss);
    shelf->encode(encoder);
    cout << ss.tellp() << endl;
    return 0;
}
