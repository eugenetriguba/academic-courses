#ifndef MATH_H
#define MATH_H

#include <algorithm>
#include <iostream>
#include <numeric>
#include <sstream>
#include <stdexcept>
#include <vector>

using namespace std;

template <typename T> class Math {
public:
    Math(vector<T> values);
    T sum();
    T min();
    T max();
    T difference();
    T product();
    double quotient();

private:
    vector<T> values;
};
template class Math<int>;
template class Math<float>;
template class Math<double>;

// Constructs a Math object by storing values.
//
// The values must have at least 1 value. Otherwise,
// a std::length_error is thrown.
//
// Valid typename T's are int, float, and double.
template <typename T> Math<T>::Math(vector<T> values)
{
    this->values = values;

    if (values.size() < 1) {
        throw length_error("vector must have at least 1 value.");
    }
}

template <typename T> T Math<T>::min()
{
    return *min_element(values.begin(), values.end());
}

template <typename T> T Math<T>::max()
{
    return *max_element(values.begin(), values.end());
}

template <typename T> T Math<T>::sum()
{
    return accumulate(values.begin(), values.end(), 0.0);
}

template <typename T> T Math<T>::difference()
{
    return accumulate(values.begin() + 1, values.end(), values[0], minus<T>());
}

template <typename T> T Math<T>::product()
{
    return accumulate(values.begin(), values.end(), 1.0, multiplies<T>());
}

template <typename T> double Math<T>::quotient()
{
    return accumulate(values.begin() + 1, values.end(), ((double) values[0]),
                      divides<double>());
}

#endif