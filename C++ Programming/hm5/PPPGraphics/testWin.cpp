
#include "Simple_window.h"
#include "Graph.h"

using namespace Graph_lib;

int main()
try 
{
	Point tl {100,100};	// Top left corner of our window

	Simple_window win{tl,600,400,"Canvas"};
		// screen coordinate tl for the top left corner
		// window size 600x400
		// title:  Canvas

	win.wait_for_button();	// Display the window
}

catch(exception& e) {
	return 1;
}
catch(...) {
	return 2;
}

