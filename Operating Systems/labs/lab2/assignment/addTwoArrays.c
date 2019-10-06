#include "addTwoArrays.h"

/*
 * Adds the sum of two corresponding elements from two arrays and 
 * stores the result in the corresponding index of a result array.
 * 
 * Example: arr1[0] = 1 
 *          arr2[0] = 2 
 *          result[0] = 3
 * 
 * Post: result[] is filled with the sum of the two corresponding
 *       elements from arr1 and arr2
 */
void addTwoArrays(int arr1[], int arr2[], int result[], int arrSize)
{
    int i, sum;

    for (i = 0; i < arrSize; ++i)
    {
        sum = arr1[i] + arr2[i];
        result[i] = sum;
    }
}