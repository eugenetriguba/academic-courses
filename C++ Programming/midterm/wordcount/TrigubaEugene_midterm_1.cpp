/**
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 * COSC 226: C++ Programming
 * Midterm Exam
 * October 10th, 2020
 *
 * “On my honor, I pledge that I have upheld the Honor Code,
 * and that the work I have done on this assignment has been
 * honest, and that the work of others in this class has to
 * the best of my knowledge, been honest as well.”
 * Name: Eugene Triguba
 *
 * Given text input, produce a word count of all
 * words in that text, ignoring punctuation and case
 * differences.
 */

#include <algorithm>
#include <iostream>
#include <map>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

/**
 * Punct_stream is like an istream but the user can add
 * to the set of whitespace characters.
 */
class Punct_stream {
  public:
    Punct_stream(istream &is) : source(is), sensitive(true){};

    /**
     * Set the entire whitespace set.
     *
     * Args:
     *   s - The set of whitespace characters.
     */
    void set_whitespace(const string &s) { white = s; }

    /**
     * Add to the whitespace set.
     *
     * Args:
     *   c - the character to add to be considered whitespace.
     */
    void add_whitespace(char c) { white += c; }

    /**
     * Check if a character is in the whitespace set.
     *
     * Args:
     *   c - The character to add to the whitespace set.
     *
     * Returns:
     *   True if the character was added. False otherwise.
     */
    bool is_whitespace(char c)
    {
        for (char w : white) {
            if (c == w) {
                return true;
            }
        }

        return false;
    }

    /**
     * Set the case sensitivity.
     *
     * Args:
     *   b - The case sensitivity value. False to ignore
     *       case differences.
     */
    void case_sensitive(bool b) { sensitive = b; }

    /**
     * Check if case sensitivity differences are
     * considered.
     *
     * Returns:
     *   True if they are; False otherwise.
     */
    bool is_case_sensitive() { return sensitive; }

    Punct_stream &operator>>(string &s)
    {
        while (!(buffer >> s)) {
            if (buffer.bad() || !source.good()) {
                return *this;
            }
            buffer.clear();

            string line;
            getline(source, line);

            for (char &ch : line) {
                if (is_whitespace(ch)) {
                    ch = ' ';
                }
                else if (!sensitive) {
                    ch = tolower(ch);
                }
            }

            buffer.str(line);
        }

        return *this;
    }

    operator bool() { return !(source.fail() || source.bad()) && source.good(); }

  private:
    istream &source;
    istringstream buffer; // we let buffer do our formatting
    string white;         // characters considered "whitespace"
    bool sensitive;       // is the stream case sensitive?
};

int main()
{
    Punct_stream ps{cin};
    ps.set_whitespace(";:,.?!()\"{}<>/&$@#%^*|~");
    ps.case_sensitive(false);

    cout << "Please enter words:" << endl;
    map<string, int> words;
    for (string word; ps >> word;)
        words[word]++;

    cout << "\nWord count:\n\n";
    for (map<string, int>::iterator it = words.begin(); it != words.end(); it++) {
        cout << it->first << ": " << it->second << endl;
    }
}
