// Eugene Triguba
// ytriguba17@ole.augie.edu
// Homework 5: Part b
// C++ Programming
#include "PPPGraphics/Simple_window.h"
#include "PPPGraphics/Graph.h"

using namespace std;
using namespace Graph_lib;

/**
 * Truncates a number down to its base.
 * Ex: truncate_num(564, 100) -> 500
 *
 * Args:
 *   num: The number to truncate.
 *   base: The base to truncate down to.
 *
 * Returns:
 *   A new truncated number
 */
int truncate_num(int num, int base) { return (num + base / 2) / base * base; }

class Grid : public Simple_window {
  public:
    /**
     * Grid represents a regular Simple_window that has a
     * num_x_cells by num_y_cells of cell_size grid on top of it.
     *
     * Args:
     *   num_x_cells: The number of cells in the x direction.
     *   num_y_cells: The number of cells in the y direction.
     *   cell_size: The size of each cell, in pixels.
     */
    Grid(int num_x_cells, int num_y_cells, int cell_size)
        : Simple_window(Point{100, 100}, 1000, 800, "Window"), num_x_cells(num_x_cells),
          num_y_cells(num_y_cells), cell_size(cell_size),
          x_width(cell_size * num_x_cells), y_width(cell_size * num_y_cells)
    {
        grid.set_color(Color::black);

        for (int x = 0; x < x_width; x += cell_size) {
            grid.add(Point{x, 0}, Point{x, y_width});
        }

        for (int y = 0; y < y_width; y += cell_size) {
            grid.add(Point{0, y}, Point{x_width, y});
        }

        attach(grid);
    }

    /**
     * Fills a cell with a particular color.
     *
     * There is not error checking if the given
     * cell is out of bounds.
     *
     * Example: fill_cell(0, 1, Color::black)
     * would fill the cell at coordinate (0, 1)
     * with the color black.
     *
     * Args:
     *   x: The x coordinate of the cell.
     *   y: The y coordinate of the cell.
     *   c: The color to set the cell to.
     */
    void fill_cell(int x, int y, Color c)
    {
        Rectangle *r =
            new Rectangle{Point{x * cell_size, y * cell_size}, cell_size, cell_size};
        r->set_fill_color(c);
        attach(*r);
    }

    /**
     * Retrieve a Point that has the
     * x and y coordinates of a random
     * cell on the grid.
     *
     * Returns:
     *   The coordinates of a random cell on the grid.
     */
    Point random_cell()
    {
        int upper_y_bound = cell_size * num_y_cells - 100;
        int upper_x_bound = cell_size * num_x_cells - 100;

        return Point{truncate_num(rand() % upper_x_bound, 100),
                     truncate_num(rand() % upper_y_bound, 100)};
    }

  private:
    int num_x_cells;
    int num_y_cells;
    int cell_size;
    int x_width;
    int y_width;
    Lines grid;
};

int main()
{
    const int GRID_DIMENSION = 8;
    const int CELL_SIZE = 100;

    Grid grid_window{GRID_DIMENSION, GRID_DIMENSION, CELL_SIZE};

    for (int i = 0; i < GRID_DIMENSION; i++) {
        grid_window.fill_cell(i, i, Color::red);
    }

    Image img1{Point{0, 6 * CELL_SIZE}, "image2.jpg", Suffix::jpg};
    Image img2{Point{3 * CELL_SIZE, 6 * CELL_SIZE}, "image2.jpg", Suffix::jpg};
    Image img3{Point{6 * CELL_SIZE, 0}, "image2.jpg", Suffix::jpg};
    grid_window.attach(img1);
    grid_window.attach(img2);
    grid_window.attach(img3);

    while (true) {
        Image rand_img{grid_window.random_cell(), "image3.jpg"};
        grid_window.attach(rand_img);
        grid_window.wait_for_button();
    }
}