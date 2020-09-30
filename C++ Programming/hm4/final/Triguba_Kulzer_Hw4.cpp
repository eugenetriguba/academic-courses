// Eugene Triguba <ytriguba17@ole.augie.edu>
// Jonas Kulzer <jkulzer18@ole.augie.edu>
//
// C++ Programming: Homework 4

#include <algorithm>
#include <chrono>
#include <fstream>
#include <iostream>
#include <limits>
#include <sstream>
#include <stdexcept>
#include <string>
#include <thread>
#include <vector>

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
string point_to_string(const Point &p);
void remove_whitespace(string &s);
void parse_point(const string &input, Point &p);
void remove_char(string &s, char c);
void print_into_stream(ostream &os, vector<Point> &v);
void pause_current_thread(int seconds);
string format_string_for_equivelence(const Point &p1, const Point &p2);

const string DATA_FILE = "mydata.txt";

int main()
{
    vector<Point> original_points;

    cout << "Enter points, in the format (1, 2) and EOF to finish: " << endl;
    do {
        Point p;
        cin >> p;

        if (cin.eof()) {
            break;
        }

        if (cin.fail()) {
            cout << "Invalid point entered, not saved." << endl;
            cin.clear();
            continue;
        }

        original_points.push_back(p);
    } while (true);

    cout << "\nPoints that have been read in: " << endl;
    print_into_stream(cout, original_points);

    ofstream outfile(DATA_FILE);
    if (outfile.is_open()) {
        print_into_stream(outfile, original_points);
        outfile.close();
    }
    else {
        cout << "Could not open " << DATA_FILE << " for writing" << endl;
        exit(1);
    }

    cout << "\nPausing for 10 seconds..." << endl;
    pause_current_thread(10);

    vector<Point> processed_points;
    ifstream infile(DATA_FILE);
    if (infile.is_open()) {
        while (!infile.eof()) {
            Point p;
            infile >> p;
            processed_points.push_back(p);
        }
    }
    else {
        cout << "Could not open " << DATA_FILE << " for reading" << endl;
        exit(2);
    }

    // Assumption: original_points and processed_points are
    // parallel vectors. This should hold true unless you
    // delete a point from the file in the pause period
    // instead of simply modifying one.
    cout << "\nTesting equivelence:" << endl;
    for (int i = 0; i < original_points.size(); i++) {
        cout << format_string_for_equivelence(original_points[i], processed_points[i])
             << endl;
    }

    return 0;
}

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
bool operator!=(const Point &p1, const Point &p2) 
{ 
    return !(p1 == p2); 
}

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

// Removes all occurrences of whitespace in s
// using `iswspace`.
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
    const char delimiter = ',';
    const int delimiter_index = input.find(delimiter);

    string x_coordinate = input.substr(0, delimiter_index);
    string y_coordinate = input.substr(delimiter_index);

    // Removes all chars so even (((1,,,,2))) would be allowed
    // and read in as p.x=1 and p.y=2. Maybe not the best to allow.
    // Let's just call it "robust"
    remove_char(x_coordinate, '(');
    remove_char(y_coordinate, ',');
    remove_char(y_coordinate, ')');

    p.x = stoi(x_coordinate);
    p.y = stoi(y_coordinate);
}

// Removes all occurrences of c in s.
void remove_char(string &s, char c) 
{ 
    s.erase(remove(s.begin(), s.end(), c), s.end());
}

// Prints all `Point`s from a vector into an ostream.
void print_into_stream(ostream &os, vector<Point> &v)
{
    for (vector<Point>::iterator iter = v.begin(); iter != v.end(); iter++) {
        os << *iter << endl;
    }
}

void pause_current_thread(int seconds)
{
    chrono::seconds sleep_time(seconds);
    this_thread::sleep_for(sleep_time);
}

// Formats two Points to the format:
//   original=(x, y) processed=(x, y) [SAME|DIFFER]
string format_string_for_equivelence(const Point &original, const Point &processed)
{
    ostringstream oss;
    oss << "original=" << original << " processed=" << processed << " ";

    original == processed ? oss << "SAME" : oss << "DIFFER";

    return oss.str();
}