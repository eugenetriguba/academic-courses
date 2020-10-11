CC=g++ -std=c++11 -stdlib=libc++ -Wc++11-extensions

SRC_FILES=src/point.cpp
SRC_ENTRY=src/main.cpp

BUILD_OUTFILE = main
OUTPUT_FILE = mydata.txt

build: $(SRC_ENTRY) $(SRC_FILES)
	$(CC) $(SRC_ENTRY) $(SRC_FILES) -o $(BUILD_OUTFILE)

clean:
	@rm -f $(BUILD_OUTFILE) $(OUTPUT_FILE)