/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 *
 * Exam 2: 30-2
 * Symbol.java
 */

public class Symbol implements Comparable<Symbol>
{
    private char operator;
    private int inputPriority;
    private int stackPriority;

    private void setPriority(char operator)
    {
        switch(operator)
        {
            case '+':
            case '-':
                inputPriority = 1;
                stackPriority = 1;
                break;
            case '*':
            case '%':
            case '/':
                inputPriority = 2;
                stackPriority = 2;
                break;

            case '^':
                inputPriority = 4;
                stackPriority = 3;
                break;

            case '(':
                inputPriority = 5;
                stackPriority = -1;
                break;

            case ')':
                inputPriority = 0;
                stackPriority = 0;
                break;
        }
    }

    public Symbol(char ch)
    {
        operator = ch;
        setPriority(operator);
    }
    //Return:-1 if the priority of the Symbol on stack is < item about to be pushed on stack
    //	0 if the priority of the Symbol on stack is = item about to be pushed on stack
    //	1 if the priority of the Symbol on stack is > item about to be pushed on stack
    public int compareTo(Symbol item)
    {
        if (stackPriority < item.inputPriority) return -1;
        else if (stackPriority == item.inputPriority) return 0;
        else return 1;
    }

    public char getOp()
    {
        return operator;
    }
}