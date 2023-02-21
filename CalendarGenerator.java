import java.util.*;
import java.time.*;

public class CalendarGenerator {
  // An array to store the names of months
  static String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

  /**
   * This method generates a calendar for a given year.
   * @param year The year for which the calendar should be generated
   */
  public static void generateCalendar(int year) {
    // Get the current date and extract year, month, and day information from it
    LocalDate currentDate = LocalDate.now();
    int currentYear = currentDate.getYear();
    int currentMonth = currentDate.getMonthValue();
    int currentDay = currentDate.getDayOfMonth();
    
    // Get a Calendar instance set to the specified year
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    
    // Loop through each month
    for (int i = 0; i < 12; i++) {
      System.out.println("\n" + months[i] + " " + year);
      System.out.println("Sun\tMon\tTue\tWed\tThu\tFri\tSat");
      
      // Set the calendar to the current month
      calendar.set(Calendar.MONTH, i);
      calendar.set(Calendar.DAY_OF_MONTH, 1);
      
      // Get the day of the week for the first day of the month
      int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
      // Loop through the days of the week until we reach the first day of the month
      for (int j = Calendar.SUNDAY; j < dayOfWeek; j++) {
        System.out.print("\t");
      }
      
      // Get the last day of the month
      int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
      // Loop through each day of the month
      for (int j = 1; j <= lastDay; j++) {
        // If the current date is today, print it in green
        if (year == currentYear && i == currentMonth - 1 && j == currentDay) {
          System.out.print("\033[32m[" + j + "]\033[0m\t");
        // If the current date is in the past, print it in red
        } else if (year < currentYear || (year == currentYear && i < currentMonth - 1) || (year == currentYear && i == currentMonth - 1 && j < currentDay)) {
          System.out.print("\033[31m" + j + "\033[0m\t");
        // Otherwise, print it in the default color
        } else {
          System.out.print(j + "\t");
        }
        // If the day is Saturday, start a new line
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
          System.out.println();
        }
        // Move to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1);
      }
    }

  }
  
  public static void main(String[] args) {
    // creates an instance of the Calendar class
    Calendar calendar = Calendar.getInstance();
    // and retrieves the current year
    int year = calendar.get(Calendar.YEAR);
    // calls the generateCalendar method and passes the current year as an argument.
    generateCalendar(year);
    // creates a Scanner object
    Scanner sc = new Scanner(System.in);
    String YEAR;
    // asks the user to enter a year
    System.out.println("\nEnter any year (YYYY): or ('q' for exit)");
    // while loop will continue until the user enters 'q' or the input is null
    while(( YEAR = sc.next()) != null){
      if(YEAR.equals("q")) break;
      // call the generateCalendar method and pass the entered year as an argument.
      generateCalendar(Integer.parseInt(YEAR));
      System.out.println("\nEnter any year(YYYY): or ('q' for exit)");
    }
    // Scanner object is closed to prevent resource leaks
    sc.close();
  }
}
