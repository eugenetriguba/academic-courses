/*
 * Write two functions that reverse the order of elements in a vector<int>. 
 * For example, 1, 3, 5, 7, 9 becomes 9, 7, 5, 3, 1. The first reverse 
 * function should produce a new vector with the reversed sequence, 
 * leaving its orig-inal vector  unchanged.  The  other  reverse  function
 * should  reverse  the  elements of its vector without using any other 
 * vectors (hint: swap).
 */

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// Reverse the elements of a vector by passing by value.
// i.e. returning a new vector
//
// Args:
//   vec - The vector to reverse.
//
// Returns:
//   A new vector containing the elements of vec in reverse.
template <typename T>
vector<T> reverse_by_value(vector<T> vec)
{
    reverse(vec.begin(), vec.end());
    return vec;
}

// Reverse the elements of a vector by passing by reference.
// i.e. modifying the passed in vector.
//
// Args:
//   vec - The vector to reverse.
//
// "The  other  reverse  function  should  reverse  the
// elements of its vector without using any other vectors (hint: swap)."
//
// Implementation uses std::iter_swap.
// https://github.com/llvm-mirror/libcxx/blob/a12cb9d211019d99b5875b6d8034617cbc24c2cc/include/algorithm#L2259-L2291
template <typename T>
void reverse_by_ref(vector<T> &vec)
{
    reverse(vec.begin(), vec.end());
}

template <class Container>
void print_container(Container &cont)
{
    for (int i = 0; i < cont.size(); i++)
    {
        cout << cont[i] << " ";
    }

    cout << endl;
}

int main()
{
    vector<int> values{1, 3, 5, 7, 9};

    reverse_by_ref(values);
    printf("Pass by reference: ");
    print_container(values);

    values = {1, 3, 5, 7, 9};
    vector<int> reversed_values = reverse_by_value(values);
    printf("Pass by value: ");
    print_container(reversed_values);

    return 0;
}
