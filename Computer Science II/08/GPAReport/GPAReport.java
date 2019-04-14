// Eugene Triguba
// ytriguba17@ole.augie.edu
// GPAReport.java
// Homework 8: 09-TextIO

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.Iterator;

public class GPAReport
{
    // Desc: The program reads the student information stored in "data.txt", computes 
	//   the GPA of the student, and produces a report outputted to "report.txt"
    // Input: The input file "data.txt" contains the following items, one on each line:
    //	 Student name (a string such as John Johnson)
    //	 Number of course (an int such as 2)
    //	 Course Name (a string such as Computer Science I)
    //	 Credit hours (an int such as 4)
    //	 Grade (any one of A, B, C, D, F)
    //	 Course Name (e.g. Introduction to Art)
    //	 Credit hours 
    //	 Grade
    // Output: A report in the following format is written to a file named "report.txt":
    //	 Name		    Courses			      Credit Hours	Grade	
    //	 -----------------------------------------------------------------------------------
    //	 John Johnson	Computer Science I	  4		        A
    //	 		        Introduction to Art	  3		        B
    //	 GPA: 3.57
    public static void main(String[] args) throws FileNotFoundException
    {
        Vector<ReportCard> totalReportCards = new Vector<ReportCard>();
        loadInReportCards(totalReportCards, "data.txt");
        writeReportCards(totalReportCards, "report.txt");
    }

    // Desc: Read in data from fileName into reports.
    // Input: fileName file must be in the following format: 
    //   Student name (a string such as John Johnson)
    //	 Number of course (an int such as 2)
    //	 Course Name (a string such as Computer Science I)
    //	 Credit hours (an int such as 4)
    //	 Grade (any one of A, B, C, D, F)
    //	 Course Name (e.g. Introduction to Art)
    //	 Credit hours 
    //	 Grade
    // Output: Error message if fileName does not exist.
    // Post: totalReportCards has reports added to it from fileName
    public static void loadInReportCards(Vector<ReportCard> totalReportCards, String fileName) 
        throws FileNotFoundException
    {
        File f = new File(fileName);
        if (!f.exists())
        {
            System.out.printf("File %s must exist.\n", fileName);
            System.exit(1);
        }

        Scanner inFile = new Scanner(f);
        while (inFile.hasNext())
        {
            ReportCard report = new ReportCard();
            report.load(inFile);
            totalReportCards.add(report);
        }
        inFile.close();
    }

    // Desc: writes each ReportCard in totalReportCards to "report.txt"
    // Output: An error message if "report.txt" exists.
    //   A report in the following format is written to "report.txt":
    //	 Name		    Courses			      Credit Hours	Grade	
    //	 -----------------------------------------------------------------------------------
    //	 John Johnson	Computer Science I	  4		        A
    //	 		        Introduction to Art	  3		        B
    //	 GPA: 3.57
    public static void writeReportCards(Vector<ReportCard> totalReportCards, String fileName) 
        throws FileNotFoundException
    {
        File f = new File(fileName);
		if (f.exists())
		{
			System.out.printf("Output file %s already exists.\n", fileName);
		    System.exit(2);
		}

        PrintWriter outFile = new PrintWriter(f);
        ReportCard.writeHeader(outFile);
        Iterator<ReportCard> iter = totalReportCards.iterator();
        while (iter.hasNext())
        {
            ReportCard report = iter.next();
            report.write(outFile);
        }
        outFile.close();
    }
}