#!/bin/sh

g++-10 -I /usr/local/include -L /usr/local/lib $1 PPPGraphics/Window.cpp PPPGraphics/Graph.cpp PPPGraphics/GUI.cpp PPPGraphics/Simple_window.cpp -lfltk -lfltk_images
