/**
 *  Write a program that prompts the user to enter two integer values.
 *  Store these values in int variables named val1 and val2.
 *  Write your program to determine the smallest, largest, sum, difference,
 *  product, and ratio of these values and report them to the user.
 *
 *  To compile and run: g++ main.cpp -o part1 && ./part1
 */

#include "../math.hpp"
#include <iostream>
#include <limits>
#include <stdexcept>
#include <vector>

using namespace std;

int read_int(istream &in);

int main()
{
    vector<int> values;
    do {
        cout << "Enter an integer: ";

        try {
            values.push_back(read_int(cin));
        }
        catch (exception &e) {
            cout << e.what() << endl;
        }

    } while (values.size() != 2);

    Math<int> m{values};
    cout << "Min: " << m.min() << endl;
    cout << "Max: " << m.max() << endl;
    cout << "Difference: " << m.difference() << endl;
    cout << "Product: " << m.product() << endl;
    cout << "Ratio: " << m.quotient() << endl;

    return 0;
}

// read_int retrieves an integer from in.
//
// Throws: invalid_argument if the input was not a number.
// Returns: the number that was read.
int read_int(istream &in)
{
    int ch;
    in >> ch;

    if (in.fail()) {
        in.clear();
        in.ignore(numeric_limits<streamsize>::max(), '\n');
        throw invalid_argument("Invalid input, must be an integer.");
    }
    else {
        return ch;
    }
}
