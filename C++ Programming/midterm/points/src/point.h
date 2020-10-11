#ifndef POINT_H
#define POINT_H

#include <iostream>

struct Point {
    int x;
    int y;
};

Point create_point(int x, int y);

bool operator==(const Point &p1, const Point &p2);
bool operator!=(const Point &p1, const Point &p2);
std::istream &operator>>(std::istream &is, Point &p);
std::ostream &operator<<(std::ostream &os, const Point &p);

#endif