#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>		// Needed for fork() call
#include <sys/types.h>	// Needed for waitpid routine (and other things...)
#include <sys/ipc.h>	// Needed for shared memory routines
#include <sys/shm.h>	// Needed for shared memory routines
#include <sys/wait.h>	// Needed for waitpid() routine

void sumArr(int [], int, int, int *);
void finalizeProcessing(int, int);

#define MAX_SIZE 50000

int main(void)
{
   pid_t child1_pid, child2_pid;	// Child process ID's
   int status;				        // Status variable for waitpid() call

   int sharedMemoryID;			// The shared memory identifier
   int *sharedMemoryPtr;		// A pointer to a block of shared memory
   int *job1Accumulator;
   int *job2Accumulator;

   int arr[MAX_SIZE];
   int arrLength;
   int i;

   for (i = 0; i < MAX_SIZE; i++)
      arr[i] = i;

   arrLength = sizeof(arr) / sizeof(arr[0]);

   // Initialize the shared memory segment
   sharedMemoryID = shmget(IPC_PRIVATE, 2 * sizeof(int), 0660);
   sharedMemoryPtr = (int *)shmat(sharedMemoryID, (void *)0, 0);
   job1Accumulator = sharedMemoryPtr;
   job2Accumulator = (sharedMemoryPtr + 1);
   *job1Accumulator = 0;
   *job2Accumulator = 0;

   if ((child1_pid = fork()) == 0) // first child process
   {
      printf("Process ID = %d (inside first forked() process)\n", child1_pid);
      sumArr(arr, 0, arrLength / 2, job1Accumulator);
      exit(0);
   }

   if ((child2_pid = fork()) == 0) // second child process
   {
      printf("Process ID = %d (inside second forked() process)\n", child2_pid);
      sumArr(arr, arrLength / 2, arrLength, job2Accumulator);
      exit(0);
   }

   printf("Process ID of first process  = %d\n", child1_pid);
   printf("Process ID of second process = %d\n", child2_pid);

   // Wait for both processes to complete
   waitpid(child1_pid, &status, 0);
   waitpid(child2_pid, &status, 0);

   // Summarize information from the two processing jobs
   finalizeProcessing(*job1Accumulator, *job2Accumulator);

   return 0;
}

/*
 * Sums an array from startingIndex
 * to endingIndex
 *
 * arr[]           -- an array filled with integers
 * startingIndex   -- the index from where to start the sum
 * endingIndex     -- the index from where to end the sum
 * accumulator     -- a pointer for storing the sum
 *
 * Post: accumulator is set to the sum of arr[]
 */
void sumArr(int arr[], int startingIndex, int endingIndex, int *accumulator)
{
    int i;

    for (i = startingIndex; i < endingIndex; i++)
       *accumulator += arr[i];
}

/*
 * Finds the sum of the two parameters, the average,
 * and prints out a report.
 *
 * firstHalf  -- should be the first half of the sum calculated
 *               by the first process
 * secondHalf -- should be the second half of the sum calculated
 *               by the second process
 *
 * Output: a summary of the processing which includes the
 *         sum of the first process, the sum of the second
 *         process, the total (both processes), and the average.
 */
void finalizeProcessing(int firstHalf, int secondHalf)
{
   int total;
   int average;

   total = firstHalf + secondHalf;
   average = total / MAX_SIZE;

   printf("Processing Summary:\n");
   printf("   First process sum:   %d\n", firstHalf);
   printf("   Second process sum:  %d\n", secondHalf);
   printf("   Total sum:           %d\n", total);
   printf("   Average:             %d\n", average);
}

