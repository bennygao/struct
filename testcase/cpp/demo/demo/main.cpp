//  main.cpp
//  demo

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
    
    ofstream output("/tmp/cpp.out", ios::binary | ios::trunc);
    if (!output.is_open()) {
        cerr << "open /tmp/cpp.out failed" << endl;
        return -2;
    }
    shelf->encode(output);
    output.close();
    
    delete shelf;
    cout << StructInstanceCounter::tostr();
    return 0;
}
