//package 01-acr-trb.b.smartschool;
import java.util.Scanner;

public class Student {
  static void collectInfo() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter student name: ");
    String name = scanner.next();

    System.out.print("Years: ");
    Integer years = Integer.parseInt(scanner.next());

    System.out.print("Months: ");
    Integer months = Integer.parseInt(scanner.next());

    System.out.print("Days: ");
    Integer days = Integer.parseInt(scanner.next());

    scanner.close();

    System.out.println(calculateAgeInDays(name, years, months, days));
  }

  static String calculateAgeInDays(String name, int years, int months, int days) {
    return name + "'s age in days is " + (years * 365 + months * 30 + days);
  }
}
