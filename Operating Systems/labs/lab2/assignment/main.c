// Eugene Triguba
// ytriguba17@ole.augie.edu
// Lab 2: main.c

#include <stdio.h>
#include "printArr.h"
#include "addTwoArrays.h"

#define ARRAY_SIZE = 5

/*
 * Reads in two arrays of integers (of the same size) from stdIn, adds them
 * together, stores the result in another array, and prints out all three
 * arrays at the end.
 * 
 * Input: user input from the keyboard to fill two arrays
 * Output: the two user inputted arrays and a result array with each
 *         corresponding element of the two arrays added.
 */
int main() 
{
    int arr1[ARRAY_SIZE];
    int arr2[ARRAY_SIZE];
    int result[ARRAY_SIZE] = {0};

    int i;
    printf("Enter 5 numbers to fill Array 1\n");
    for (i = 0; i < ARRAY_SIZE; ++i)
    {
        printf("Enter element %d: ", i + 1);
        scanf("%d", &arr1[i]);
    }

    printf("Enter 5 numbers to fill Array 2\n");
    for (i = 0; i < ARRAY_SIZE; ++i)
    {
        printf("Enter element %d: ", i + 1);
        scanf("%d", &arr2[i]);
    }

    addTwoArrays(arr1, arr2, result, ARRAY_SIZE);
    
    printf("First Array: ");
    printArr(arr1, ARRAY_SIZE);

    printf("Second Array: ");
    printArr(arr2, ARRAY_SIZE);

    printf("Result: ");
    printArr(result, ARRAY_SIZE);

    return 0;
}