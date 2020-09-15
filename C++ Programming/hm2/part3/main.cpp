/*
 * Write a program that reads and stores a series of integers and then computes
 * the sum of the first N integers. First, ask for N, and then read the values
 * into a vector<>, and finally, calculate the sum of the first N values. Note
 * that any number of values can be entered, but only the first N are used in
 * the sum. The stop token is "|". Handle all inputs. Give an error message
 * if the user enters fewer than N numbers. Use your own functions (write them)
 * to handle errors.
 *
 * To compile and run: g++ main.cpp -o part3 && ./part3
 */

#include "../math.hpp"
#include <algorithm>
#include <iostream>
#include <iterator>
#include <sstream>
#include <string>

using namespace std;

template <class Container> void split_str(const string &str, Container &cont);
vector<int> str_to_int(vector<string> &cont, int num_elements);

int main()
{
    cout << "How many integers would you like to enter (N)? ";
    int num_ints;
    cin >> num_ints;

    if (cin.fail()) {
        cout << "error: must be a number." << endl;
        exit(1);
    }

    cout << "Enter N integers, terminated by a `|`: ";
    string input;
    getline(cin, input, '|');

    vector<string> tokens;
    split_str(input, tokens);

    try {
        vector<int> values{str_to_int(tokens, num_ints)};

        Math<int> m{values};
        cout << "Sum: " << to_string(m.sum()) << endl;
    }
    catch (exception &e) {
        cout << e.what() << endl;
        exit(2);
    }
}

// split_str splits a string on whitespace and enters
// those tokens into a new container.
template <class Container> void split_str(const string &str, Container &cont)
{
    istringstream iss(str);
    copy(istream_iterator<string>(iss), istream_iterator<string>(),
         back_inserter(cont));
}

// str_to_int converts a vector of strings into a vector of ints.
//
// It will only convert and enter into the returned vector `num_elements`
// elements.
//
// Throws: invalid_argument if non-integers are entered.
//         out_of_range if the int value entered is too large.
//         length_error if num_elements in greater than the input vector size.
//
// Returns: a vector with num_elements integers in it.
vector<int> str_to_int(vector<string> &input, int num_elements)
{
    vector<int> values;
    vector<string>::iterator end_iter{input.begin()};

    if (num_elements > input.size()) {
        throw length_error("error: entered less integers than expected.");
    }
    else {
        advance(end_iter, num_elements);
    }

    try {
        transform(input.begin(), end_iter, back_inserter(values),
                  [](const string &str) { return stoi(str); });
    }
    catch (invalid_argument &e) {
        throw invalid_argument("error: non-integers entered.");
    }
    catch (out_of_range &e) {
        throw out_of_range("error: int value is too large to fit in an int.");
    }

    return values;
}
