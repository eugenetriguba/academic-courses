#include <stdio.h>
#include "printArr.h"

/*
 * Prints out an array
 * 
 * Output: The contents of arr[] outputted to the console in 
 *         the format [ele1, ele2, ...]. If arr[] is empty,
 *         [] is outputted.
 */
void printArr(int arr[], int arrSize)
{
    int i;

    if (arrSize == 0)
    {
        printf("[]");
        return;
    }

    printf("[%d", arr[0]);
    for (i = 1; i < arrSize; ++i)
        printf(", %d", arr[i]);
    printf("]\n");
}