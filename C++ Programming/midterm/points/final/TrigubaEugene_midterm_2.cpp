/**
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 * COSC 226: C++ Programming
 * Midterm Exam
 * October 10th, 2020
 *
 * “On my honor, I pledge that I have upheld the Honor Code,
 * and that the work I have done on this assignment has been
 * honest, and that the work of others in this class has to
 * the best of my knowledge, been honest as well.”
 * Name: Eugene Triguba
 *
 * This program reads in a set of points in the format (x, y),
 * writes those same points out to a file named mydata.txt, then
 * reads in the points from the file and checks that they are
 * equivelent to the originally entered points.
 *
 * Exit status of 1 is returned from the program if we have an io
 * failure while we are reading in points from cin or from the file.
 * However, any points in a bad format are simply not saved and if during
 * the initial point entering phase, asked to try again.
 *
 * Exit status of 2 is returned from the program if we are unable to open
 * the mydata.txt file for writing.
 *
 * Exit status of 3 is returned from the program if we are unable to open
 * the mydata.txt file for reading.
 */

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

// main helpers
void write_to_stream(ostream &os, vector<Point> &v);

// public point methods
Point create_point(int x, int y);
bool operator==(const Point &p1, const Point &p2);
bool operator!=(const Point &p1, const Point &p2);
std::istream &operator>>(std::istream &is, Point &p);
std::ostream &operator<<(std::ostream &os, const Point &p);

// private point methods
string point_to_string(const Point &p);
void remove_whitespace(string &s);
void parse_point(const string &input, Point &p);
bool remove_char(string &s, char c);

const string DATA_FILE = "mydata.txt";
enum exit_code { io_failure = 1, write_file_failure = 2, read_file_failture = 3 };

int main()
{
    vector<Point> original_points;

    cout << "Enter points, in the format (x, y) and EOF to finish: " << endl;
    do {
        Point p;
        cin >> p;

        if (cin.eof()) {
            cin.clear();
            break;
        }

        if (cin.bad()) {
            cout << "Unexpected error occurred with i/o that is unrecoverable..exiting"
                 << endl;
            exit(exit_code::io_failure);
        }

        if (cin.fail()) {
            cout << "Invalid point entered, not saved." << endl;
            cin.clear();
            continue;
        }

        original_points.push_back(p);
    } while (true);

    cout << "\nPoints that have been read in: " << endl;
    write_to_stream(cout, original_points);

    ofstream outfile(DATA_FILE);
    if (outfile.is_open()) {
        write_to_stream(outfile, original_points);
        outfile.close();
    }
    else {
        cout << "Could not open " << DATA_FILE << " for writing" << endl;
        exit(exit_code::write_file_failure);
    }

    vector<Point> points_from_file;
    ifstream infile(DATA_FILE);
    if (infile.is_open()) {
        while (!infile.eof()) {
            Point p;
            infile >> p;

            if (infile.bad()) {
                cout << "Unexpected error occurred with i/o that is "
                        "unrecoverable..exiting"
                     << endl;
                exit(exit_code::io_failure);
            }

            if (infile.fail()) {
                infile.clear();
                infile.ignore(numeric_limits<streamsize>::max(), '\n');
                continue;
            }

            points_from_file.push_back(p);
        }
    }
    else {
        cout << "Could not open " << DATA_FILE << " for reading" << endl;
        exit(exit_code::read_file_failture);
    }

    cout << endl;
    for (int i = 0; i < original_points.size(); i++) {
        cout << "original=" << original_points[i] << " file=" << points_from_file[i]
             << endl;
    }

    return 0;
}

/**
 * Print all `Point`s from a vector into an ostream.
 *
 * Args:
 *   os - The stream to write to.
 *   v - The vector to write the contents of to os.
 */
void write_to_stream(ostream &os, vector<Point> &v)
{
    for (vector<Point>::iterator iter = v.begin(); iter != v.end(); iter++) {
        os << *iter << endl;
    }
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
