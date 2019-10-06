# Summary for Lab 6

At first glance, I was assuming we were doing this lab because the copying for the binary file wouldn’t be the same, or one of them wouldn’t be the same, and there would be some separate way to copy those. But it is, which makes sense after looking through the code and what the methods do. The output files are also of the same size in bytes. The code is fairly simple. It just copies blocks of bytes of one file to the other file.

We have a buffer size of the max amount to read in each iteration. We set the output mode to 0700 (aka the permissions, owner can read, write, and execute). We open up the source file as read only and create the output file. As long as we keep reading things in, we read in a block of data of BUF_SIZE and write that data out to the out_fd.

