/*
 * Write a program that performs as a very simple calculator.
 * Your calculator should be able to handle the four basic math operations --
 * add, subtract, multiply, divide -- on two input values. Your program should
 * prompt the user to enter three arguments: two double values and a character
 * to represent an operation. If the entry arguments are 35.6, '+', and 24.1,
 * the program output should be "The sum of 35.6 and 24.1 is 59.7".
 * Note that we will enter these as:  val1 op val2. Loop the program to allow
 * for multiple tries during the same program run. Use any non-numeric character
 * to exit.
 *
 * To compile and run: g++ main.cpp -o part2 && ./part2
 */

#include "../math.hpp"
#include <cstdio>
#include <iomanip>
#include <iostream>
#include <istream>
#include <limits>
#include <sstream>

using namespace std;

string format_result(double num1, string op, double num2, double result);

int main()
{
    cout << "Welcome to the simple calculator! It can add, subtract, multiply, "
         << "and divide on two values. \nPlease enter your expression in the "
            "form: val op val. Enter in a non-numeric value to quit.\n\n";

    bool keepGoing = true;
    while (keepGoing) {
        cout << "Enter an expression: ";
        double num1;
        string operand;
        double num2;
        cin >> num1;
        cin >> operand;
        cin >> num2;

        if (cin.fail()) {
            keepGoing = false;
            continue;
        }

        Math<double> m{vector<double> {num1, num2}};

        if (operand.compare("+") == 0) {
            cout << format_result(num1, operand, num2, m.sum());
        }
        else if (operand.compare("-") == 0) {
            cout << format_result(num1, operand, num2, m.difference());
        }
        else if (operand.compare("/") == 0) {
            cout << format_result(num1, operand, num2, m.quotient());
        }
        else if (operand.compare("*") == 0) {
            cout << format_result(num1, operand, num2, m.product());
        }
        else {
            cout << "Invalid operand." << endl;
        }
    }
}

// format_result formats the result of a computation
// to be of the form "num1 op num2 = result".
string format_result(double num1, string op, double num2, double result)
{
    stringstream out;

    out << fixed << setprecision(2) << num1 << " " << op << " " << num2 << " = "
        << result << endl;

    return out.str();
}
