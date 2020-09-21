/*
 * Read five names into a vector<string> name,
 * then prompt the user for the ages of the people
 * named and store the ages in a vector<double> age.
 * Then print out the five (name[i],age[i]) pairs. Sort
 * the names (sort(name.begin(),name.end())) and print
 * out the (name[i],age[i]) pairs. The tricky part here
 * is to get the agevector in the correct order to match
 * the sorted namevector. Hint: Before  sorting  name,
 * take a copy and use that to make a copy of age
 * in the right order after sorting name. Then, do that
 * exercise again but allowing an arbitrary number of names
 *
 * Why aren't we using std::map instead of a complicated parallel
 * vector setup? Beats me
 */

#include <algorithm>
#include <iostream>
#include <iterator>
#include <limits>
#include <map>
#include <sstream>
#include <stdexcept>
#include <string>
#include <vector>

using namespace std;

template <typename T> T read_token(istream &in);
ostringstream get_parallel_vector_stream(vector<string> &first, vector<double> &second);

int main()
{
    vector<string> names;
    vector<double> ages;

    cout << "Enter '|' and enter to quit entering names." << endl;
    while (true) {
        cout << "Enter name #" << names.size() + 1 << ": ";
        string token = read_token<string>(cin);

        if (token.compare("|") == 0) {
            if (names.size() < 1) {
                cout << "error: must enter at least one item." << endl;
                continue;
            }

            break;
        }

        names.push_back(token);
    }

    do {
        cout << "Enter age for " << names[ages.size()] << ": ";

        try {
            ages.push_back(read_token<double>(cin));
        }
        catch (invalid_argument &e) {
            cout << e.what() << endl;
        }
    } while (ages.size() != names.size());

    cout << endl << "Unsorted: " << endl;
    cout << get_parallel_vector_stream(names, ages).str();

    vector<string> sortedNames{names};
    sort(sortedNames.begin(), sortedNames.end());

    cout << endl << "Sorted: " << endl;
    for_each(sortedNames.begin(), sortedNames.end(), [&names, &ages](const string name) {
        int index = distance(names.begin(), find(names.begin(), names.end(), name));
        cout << names[index] << " " << ages[index] << endl;
    });
}

// get_parallel_vector_stream outputs all contents of first and second
// to the output stream.
//
// Throws:
//   length_error if first and second are not of the same size.
//
// Returns:
//   An output stream with each parallel value in first and second
//   printed on a newline and separated by a space.
ostringstream get_parallel_vector_stream(vector<string> &first, vector<double> &second)
{
    if (first.size() != second.size()) {
        throw length_error("first and second should have the same size.");
    }

    ostringstream out;
    vector<double>::iterator second_it{second.begin()};

    for (vector<string>::iterator first_it = first.begin(); first_it < first.end();
         first_it++) {
        out << *first_it << " " << *second_it << endl;
        second_it++;
    }

    return out;
}

// read_token retrieves a token of type T from in.
//
// Throws: invalid_argument if the input was invalid.
// Returns: the token that was read.
template <typename T> T read_token(istream &in)
{
    T ch;
    in >> ch;

    if (in.fail()) {
        in.clear();
        in.ignore(numeric_limits<streamsize>::max(), '\n');
        throw invalid_argument("Invalid input");
    }
    else {
        return ch;
    }
}