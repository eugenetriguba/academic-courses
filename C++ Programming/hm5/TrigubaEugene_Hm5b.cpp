// Eugene Triguba
// ytriguba17@ole.augie.edu
// Homework 5: Part b
// C++ Programming
#include "PPPGraphics/Simple_window.h"
#include "PPPGraphics/Graph.h"

using namespace std;
using namespace Graph_lib;

int main()
{
    Point center{x_max() / 2, y_max() / 2};
    Simple_window window{center, 800, 1000, "Window"};

    window.wait_for_button();
}
