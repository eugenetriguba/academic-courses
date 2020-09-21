/*
 * Read five names into a vector<string> name,  
 * then prompt the user for the ages of the people
 * named and store the ages in a vector<double> age. 
 * Then print out the five (name[i],age[i]) pairs. Sort
 * the names (sort(name.begin(),name.end())) and print 
 * out the (name[i],age[i]) pairs. The tricky part here 
 * is to get the agevector in the correct order to match
 * the sorted namevector. Hint: Before  sorting  name, 
 * take a copy and use that to make a copy of age 
 * in the right order after sorting name. Then, do that 
 * exercise again but allowing an arbitrary number of names
 * 
 * Why aren't we using std::map instead of a complicated parallel
 * vector setup? Beats me
 */

#include <iostream>
#include <string>
#include <vector>
#include <limits>
#include <stdexcept>

using namespace std;

void print_parallel_vectors(vector<string> &first, vector<double> &second)
{
    for (int i = 0; i < first.size(); i++)
    {
        printf("%s %d\n", first.at(i), second.at(i));
    }
}

int main()
{
    vector<string> names;
    vector<double> ages;

    do
    {
        printf("Enter name #%d: ", names.size() + 1);
        string name;
        cin >> name;

        if (cin.fail())
        {
            cin.clear();
            printf("Invalid name, try again.\n");
            continue;
        }

        names.push_back(name);

    } while (names.size() != 5);

    do
    {
        printf("Enter age for %s: ", names[ages.size()]);
        double age;
        cin >> age;

        if (cin.fail())
        {
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
            printf("Invalid age, please enter a number.\n");
            continue;
        }

        ages.push_back(age);
    } while (ages.size() != 5);

    print_parallel_vectors(names, ages);
}