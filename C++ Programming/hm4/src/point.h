#ifndef POINT_H
#define POINT_H

#include <iostream>
#include <string>

using namespace std;

struct Point {
    int x;
    int y;
};

Point create_point(int x, int y);

bool operator==(const Point &p1, const Point &p2);
bool operator!=(const Point &p1, const Point &p2);
istream &operator>>(istream &is, Point &p);
ostream &operator<<(ostream &os, const Point &p);

#endif