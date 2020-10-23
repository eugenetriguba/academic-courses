// Eugene Triguba
// ytriguba17@ole.augie.edu
// Homework 5: Part a
// C++ Programming
#include <iostream>

#include "PPPGraphics/Simple_window.h"
#include "PPPGraphics/Graph.h"

using namespace std;
using namespace Graph_lib;

int main()
{
   Point top_left_corner {100, 100};
   Simple_window window {top_left_corner, 600, 400, "My window"}; 

   Axis xa {Axis::x, Point{20, 300}, 280, 10, "x axis" };
   window.attach(xa);
   window.set_label("Canvas #2");

   Axis ya {Axis::y, Point{20, 300}, 280, 10, "y axis" };
   ya.set_color(Color::dark_red);
   ya.label.set_color(Color::cyan);
   window.attach(ya);
   window.set_label("Canvas #3");

   Function sine {sin, 0, 100, Point{20, 150}, 1000, 50, 50};
   sine.set_color(Color::blue);
   window.attach(sine);
   window.set_label("Canvas #4");

   Polygon poly;
   poly.add(Point{300, 200});
   poly.add(Point{350, 100});
   poly.add(Point{400, 200});
   poly.set_color(Color::red);
   poly.set_style(Line_style::dash);
   window.attach(poly);
   window.set_label("Canvas #5");

   Rectangle r {Point{200,200}, 100, 50};
   window.attach(r);
   window.set_label("Canvas #6");

   Closed_polyline poly_rect;
   poly_rect.add(Point{100,50});
   poly_rect.add(Point{200,50});
   poly_rect.add(Point{200,100});
   poly_rect.add(Point{100,100});
   poly_rect.add(Point{50,75});
   window.attach(poly_rect);

   r.set_fill_color(Color::yellow);
   poly.set_style(Line_style(Line_style::dash, 4));
   poly_rect.set_style(Line_style(Line_style::dash, 2));
   poly_rect.set_fill_color(Color::green);
   window.set_label("Canvas #7");

   Text t {Point{150,150}, "Hello, graphical world!"};
   window.attach(t);
   window.set_label("Canvas #8");

   t.set_font(Graph_lib::Font::times_bold);
   t.set_font_size(20);
   window.set_label("Canvas #9");

   Image ii {Point{300,300}, "image.jpg"};
   window.attach(ii);
   window.set_label("Canvas #10");

   Circle c {Point{100,200}, 50};
   Ellipse e {Point{100,200}, 75, 25};
   e.set_color(Color::dark_blue);
   Mark m {Point{100,200}, 'x'};

   ostringstream oss;
   oss << "screen size: " << x_max() << "*" << y_max() << "; window size: "
       << window.x_max() << "*" << window.y_max() << "window size: "
       << window.x_max() << "*" << window.y_max();
   Text sizes {Point{100,20}, oss.str()};

   Image cal {Point{225,225}, "snow_cpp.gif"};
   cal.set_mask(Point{40,40}, 200, 150);
   window.attach(c);
   window.attach(m);
   window.attach(e);
   window.attach(sizes);
   window.attach(cal);
   window.set_label("Canvas #11");

   window.wait_for_button();
}
