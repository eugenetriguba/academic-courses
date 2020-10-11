#include <algorithm>
#include <iostream>
#include <sstream>
#include <stdexcept>
#include <string>
#include <vector>

#include "point.h"

using namespace std;

string point_to_string(const Point &p);
void remove_whitespace(string &s);
void parse_point(const string &input, Point &p);
bool remove_char(string &s, char c);

/**
 * Constructs a Point by setting the
 * x and y values of it.
 *
 * Args:
 *   x - The x value of the Point
 *   y - The y value of the Point
 *
 * Returns:
 *   A new Point with the x and y values set.
 */
Point create_point(int x, int y)
{
    Point p;
    p.x = x;
    p.y = y;

    return p;
}

/**
 * Overloads the == operator to work with Points
 * by checking if their x and y values are the same.
 *
 * Args:
 *   p1 - The first Point to compare.
 *   p2 - The second Point to compare.
 *
 * Returns:
 *   True if p1 and p2's x and y values are the same; False otherwise.
 */
bool operator==(const Point &p1, const Point &p2)
{
    return (p1.x == p2.x && p1.y == p2.y);
}

/**
 * Overloads the != operator to work with Points
 * by checking if their x and y values are not the same.
 *
 * Args:
 *   p1 - The first Point to compare.
 *   p2 - The second Point to compare.
 *
 * Returns:
 *   True if p1 and p2's x and y values are not the same; False otherwise.
 */
bool operator!=(const Point &p1, const Point &p2) { return !(p1 == p2); }

/**
 * Overloads the << operater to work with Points
 * by adding the Point into the ostream in the format
 * "(x, y)"
 *
 * Args:
 *   os - The ostream to put the stringified Point into.
 *   p - The Point to put into `os`.
 *
 * Returns:
 *   The original stream `os`.
 */
ostream &operator<<(ostream &os, const Point &p)
{
    os << point_to_string(p);
    return os;
}

/**
 * Stringifies a Point.
 *
 * Args:
 *   p - The Point to stringify.
 *
 * Returns:
 *   The Point p in the format "(x, y)"
 */
string point_to_string(const Point &p)
{
    ostringstream buffer;

    buffer << "(" << p.x << ", " << p.y << ")";

    return buffer.str();
}

/**
 * Overloads the >> operater to work with Points.
 *
 * If the next line in `is` does not contain a point
 * in a valid format i.e. (1, 2) or it is in a valid
 * format but the number is too large to fit inside
 * an int then the failbit is set on `is`.
 *
 * Args:
 *   is - The istream to retrieve characters from.
 *   p - The point to set x and y of from the chars in `is`.
 *
 * Returns:
 *   The original stream `is`.
 */
istream &operator>>(istream &is, Point &p)
{
    string buffer;
    getline(is, buffer);

    remove_whitespace(buffer);

    try {
        parse_point(buffer, p);
    }
    catch (const exception &e) {
        is.setstate(ios_base::failbit);
    }

    return is;
}

/**
 * Removes all occurrences of whitespace in s
 * using `iswspace`.
 *
 * Args:
 *   s - The string to remove whitespace from.
 */
void remove_whitespace(string &s)
{
    s.erase(remove_if(s.begin(), s.end(), iswspace), s.end());
}

/**
 * Parses a point out of a string and adds the x
 * and y values into the given Point.
 *
 * The expected format is: (int,int)
 * where the input has no whitespace characters
 * and the `int`s are valid integers.
 *
 * Args:
 *   input - The string containing the point.
 *   p - The Point to set x and y properties of.
 *
 * Throws:
 *   invalid_argument if input is not in the expected format.
 *   out_of_range if the int is too large to fit into an int.
 */
void parse_point(const string &input, Point &p)
{
    const int delimiter_index = input.find(',');

    if (delimiter_index == string::npos) {
        throw invalid_argument("error: missing comma delimiter");
    }

    string x_coordinate = input.substr(0, delimiter_index);
    string y_coordinate = input.substr(delimiter_index);

    if (!remove_char(x_coordinate, '(')) {
        throw invalid_argument("error: missing left parenthesis");
    }

    remove_char(y_coordinate, ',');
    if (!remove_char(y_coordinate, ')')) {
        throw invalid_argument("error: missing right parenthesis");
    }

    p.x = stoi(x_coordinate);
    p.y = stoi(y_coordinate);
}

/**
 * Removes first occurrence of c in s.
 *
 * Returns:
 *   True if the char was removed; False otherwise.
 */
bool remove_char(string &s, char c)
{
    auto it = find(s.begin(), s.end(), c);

    if (it != s.end()) {
        s.erase(it);
        return true;
    }
    else {
        return false;
    }
}
