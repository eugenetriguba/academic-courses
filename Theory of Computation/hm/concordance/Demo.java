// Eugene Triguba
// ytriguba17@ole.augie.edu
// Exam 2
//
// This concordance reads in a text file and extracts all
// identifiers along with the line numbers on which the
// identifiers appear.
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.TreeMap;
import java.util.Map;
import java.util.Vector;
import java.util.Set;
import java.util.Scanner;
import java.util.Iterator;
import java.io.FileNotFoundException;
import java.io.File;

class Demo
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String filename = keyboard.nextLine();
        concordance(filename);
    }

    public static void concordance(String filename)
    {
        TreeMap<String, Vector<Integer>> identCount = new TreeMap<>();
        Pattern p = Pattern.compile("[a-zA-Z_$][a-zA-Z_$0-9]*");
        int lineNumber = 0;
        Scanner fileScanner = null;
        File file = null;

        try {
            file = new File(filename);
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(filename + " file could not be found.");
            System.exit(1);
        }

        while (fileScanner.hasNextLine())
        {
            String line = fileScanner.nextLine();
            lineNumber++;

            Matcher matcher = p.matcher(line);
            while (matcher.find())
            {
                String identifier = line.substring(matcher.start(),matcher.end());
                Vector<Integer> lineNumbers = identCount.get(identifier);
                if (lineNumbers == null) {
                    lineNumbers = new Vector<Integer>();
                }
                lineNumbers.add(lineNumber);
                identCount.put(identifier, lineNumbers);
            }
        }
        writeConcordance(identCount);
    }

    public static void writeConcordance(TreeMap<String, Vector<Integer>> m)
    {
        Set<Map.Entry<String, Vector<Integer>>> entrySetObj = m.entrySet();
        Iterator<Map.Entry<String,Vector<Integer>>> iter = entrySetObj.iterator();

        while (iter.hasNext())
        {
            Map.Entry<String,Vector<Integer>> e = iter.next();
            System.out.printf("%-20s", e.getKey());
            Vector<Integer> lineNumbers = e.getValue();
            System.out.printf("%4d:", lineNumbers.size());
            Iterator<Integer> setIter = lineNumbers.iterator();

            while (setIter.hasNext())
                System.out.printf("%4d", setIter.next());

            System.out.println();
        }
    }
}