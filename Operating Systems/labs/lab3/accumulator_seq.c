#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

void sumArr(int [], int, int *);

#define MAX_SIZE 50000

/*
 * Sums and computes the average of 50,000 integers
 */
int main(void)
{
   int accumulator;
   int arr[MAX_SIZE];
   int i;

   accumulator = 0;

   for (i = 0; i < MAX_SIZE; i++)
       arr[i] = i;

   sumArr(arr, sizeof(arr) / sizeof(arr[0]), &accumulator);

   printf("Summary: \n");
   printf("\t Sum: %d\n", accumulator);
   printf("\t Average: %d\n", accumulator / MAX_SIZE);

   return 0;
}

/*
 * Sums an array
 *
 * arr[]       -- an array filled with integers
 * arrLength   -- the length of arr[]
 * accumulator -- a pointer for storing the sum
 *
 * Post: accumulator is set to the sum of arr[]
 */
void sumArr(int arr[], int arrLength, int *accumulator)
{
    int i;

    for (i = 0; i < arrLength; i++)
        *accumulator += arr[i];
}

