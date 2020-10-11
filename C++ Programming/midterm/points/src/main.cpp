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
 * writes those same points out to a file named mydata.txt, pauses
 * the running execution thread for 10 seconds, and then reads
 * in the points from the file and checks that they are equivelent
 * to the originally entered points.
 *
 * Exit status of 1 is returned from the program if we have an io
 * failure while we are reading in points from cin or from the file.
 * However, any points in a bad format are simply not saved and if during
 * the intial point entering phase, asked to try again.
 *
 * Exit status of 2 is returned from the program if we are unable to open
 * the mydata.txt file for writing.
 *
 * Exit status of 3 is returned from the program if we are unable to open
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
void pause_current_thread(int seconds);
string format_string_for_equivelence(const Point &p1, const Point &p2);

const string DATA_FILE = "mydata.txt";
const int SLEEP_TIME_IN_SECONDS = 10;
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

    cout << "\nPausing for " << SLEEP_TIME_IN_SECONDS << " seconds..." << endl;
    pause_current_thread(SLEEP_TIME_IN_SECONDS);

    vector<Point> processed_points;
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

            processed_points.push_back(p);
        }
    }
    else {
        cout << "Could not open " << DATA_FILE << " for reading" << endl;
        exit(exit_code::read_file_failture);
    }

    cout << "\nTesting equivelence:" << endl;
    for (int i = 0; i < original_points.size(); i++) {
        if (processed_points.size() - 1 < i) {
            cout << "More items in original_points than processed_points, cannot test "
                    "further."
                 << endl;
            break;
        }

        cout << format_string_for_equivelence(original_points[i], processed_points[i])
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
 * Pauses the current thread for a specified
 * number of seconds.
 *
 * Args:
 *   seconds - the number of to pause the current thread.
 */
void pause_current_thread(int seconds)
{
    chrono::seconds sleep_time(seconds);
    this_thread::sleep_for(sleep_time);
}

/**
 * Creates a string with two points
 * and whether or not they are the same.
 *
 * Args:
 *   original - The original points to compare.
 *   processed - The processed points to compare.
 *
 * Returns:
 *   original=(x, y) processed=(x, y) [SAME|DIFFER]
 */
string format_string_for_equivelence(const Point &original, const Point &processed)
{
    ostringstream oss;
    oss << "original=" << original << " processed=" << processed << " ";

    original == processed ? oss << "SAME" : oss << "DIFFER";

    return oss.str();
}