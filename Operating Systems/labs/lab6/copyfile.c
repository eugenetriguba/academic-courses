// Eugene Triguba
// Lab 6: Files

#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdbool.h>

int main(int argc, char *argv[]);

#define BUF_SIZE 4096       // use a buffer size of 4096 bytes
#define OUTPUT_MODE 0700    // protection bits for output file

/*
 * File copy program.
 * Error checking and reporting is minimal.
 */
int main(int argc, char *argv[])
{
    int in_fd, out_fd, rd_count, wt_count;
    char buffer[BUF_SIZE];

    if (argc != 3) exit(1);               // Syntax error if argc is not 3

    // Open the input file
    in_fd = open(argv[1], O_RDONLY);      // Open the source file
    if (in_fd < 0) exit(2);               // if it cannot be opened, exit

    // Create the output file
    out_fd = creat(argv[2], OUTPUT_MODE); // create the destination file
    if (out_fd < 0) exit(3);              // if it cannot be created, exit

    // Copy loop
    while (true)
    {
        rd_count = read(in_fd, buffer, BUF_SIZE);     // Read a block of data
        if (rd_count <= 0) break;                     // If end of file or error, exit loop
        wt_count = write(out_fd, buffer, rd_count);   // Write data
        if (wt_count <= 4) exit(4);
    }

    // Close the files
    close(in_fd);
    close(out_fd);
    if (rd_count == 0) // no error on last read
        exit(0);
    else               // error on last read
        exit(5);
}