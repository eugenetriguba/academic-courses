// Eugene Triguba
// ytriguba17@ole.augie.edu
// ParkingGarage.java

import java.util.Scanner;
import java.text.DecimalFormat;;

public class ParkingGarage
{
    // Desc: The program computes the cost of parking a car in a public garage
    //       at the rate $5.00/hour. The client will always be charged for whole
    //       hours. For example, if a car parked for 2 hours and 1 minute, the client
    //       will be charged for 3 hours.
    // Input: User inputs the entry time and exit time in 24-hr clock format (hh:mm) 
    // Output: The enter and exit times, the length of time the car is parked and the 
    //         total charges.
    public static void main(String[] args)
    {
        ParkingGarageVisit parking = new ParkingGarageVisit();

        System.out.println("All times should be entered in a 24-hour clock format <hh:mm>");
        System.out.print("Entered the garage at: ");
        parking.readEnterTime();
        System.out.print("Exited the garage at: ");
        parking.readExitTime();

        parking.printReceipt();
    }
}

// Models a visit to a parking garage by storing the enter time, exit time,
// parking time, and total cost. This class is able to calculate the parking
// time and total cost based on the enter time and exit time. It's able to read
// in the enter time and exit time from the user via the keyboard as well as
// print out a reciept after.
class ParkingGarageVisit
{
    private final double HOURLY_CHARGE = 5.00;

    private Time24 enterTime;
    private Time24 exitTime;
    private Time24 parkingTime;
    private double totalCost;

    // Post: enterTime, exitTime, and parkingTime are initialized.
    //       totalCost is set to 0.0.
    public ParkingGarageVisit()
    {
        this.enterTime = new Time24();
        this.exitTime = new Time24();
        this.parkingTime = new Time24();
        this.totalCost = 0.0;
    }

    // Input: an enter time via the keyboard
    // Pre: time must be in the format hh:mm
    // Post: enterTime set to the inputted value.
    //       parkingTime is calculated and set.
    //       totalCost is calculated and set.
    public void readEnterTime()
    {
        Scanner keyboard = new Scanner(System.in);
        this.enterTime.readTime(keyboard);
        calculateParkingTime();
        calculateTotalCost();
    }

    // Input: an exit time via the keyboard
    // Pre: time must be in the format hh:mm
    // Post: exitTime set to the inputted value.
    //       parkingTime is calculated and set.
    //       totalCost is calculated and set.
    public void readExitTime()
    {
        Scanner keyboard = new Scanner(System.in);
        this.exitTime.readTime(keyboard);
        calculateParkingTime();
        calculateTotalCost();
    }

    // Output: prints out the enter time, exit time, parking time, and total
    //         charge to the screen all on a new line.
    public void printReceipt()
    {
        DecimalFormat usd = new DecimalFormat("0.00");
        System.out.println("Car enters at: " + this.enterTime);
        System.out.println("Car exits at: " + this.exitTime);
        System.out.println("Parking time: " + this.parkingTime);
        System.out.println("Charges: $" + usd.format(this.totalCost));
    }

    // Return: enterTime, exitTime, parkingTime, and totalCost all outputted as a String in
    //         the format: "Enter time: %s - Exit Time: %s - Parking Time: %s - Total Cost: %s"
    public String toString()
    {
        DecimalFormat usd = new DecimalFormat("0.00");
        return String.format("Enter Time: %s - Exit Time: %s - Parking Time: %s - Total Cost: %s",
            this.enterTime, this.enterTime, this.parkingTime, usd.format(this.totalCost));
    }

    // Post: totalCost is calculated and set. 
    //       The parking time is always rounded up to the next whole hour for
    //       the total cost. ex: 2:01 would cost as much as 3 hours.
    private void calculateTotalCost()
    {
        if (this.parkingTime.getMinute() > 0) 
            this.totalCost = (this.parkingTime.getHour() + 1) * this.HOURLY_CHARGE;
        else 
            this.totalCost = this.parkingTime.getHour() * this.HOURLY_CHARGE;
    }

    // Post: parkingTime is calculated and set.
    private void calculateParkingTime()
    {
        this.parkingTime = this.enterTime.interval(this.exitTime);
    }
}