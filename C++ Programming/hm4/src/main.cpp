#include <chrono>
#include <fstream>
#include <iostream>
#include <limits>
#include <sstream>
#include <string>
#include <thread>
#include <vector>

#include "point.h"

using namespace std;

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