#!/bin/sh

g++ $1 PPPGraphics/Window.cpp PPPGraphics/Graph.cpp PPPGraphics/GUI.cpp PPPGraphics/Simple_window.cpp -lfltk -lfltk_images
