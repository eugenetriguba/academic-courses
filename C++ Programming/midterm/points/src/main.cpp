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
 * Exit status of 1 is returned from the program if we are unable to open
 * the mydata.txt file for writing.
 *
 * Exit status of 2 is returned from the program if we are unable to open
 * the mydata.txt file for reading.
 */

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

void write_to_stream(ostream &os, vector<Point> &v);

const string DATA_FILE = "mydata.txt";
enum exit_code { write_file_failure = 1, read_file_failture = 2 };

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
