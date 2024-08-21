//package 01-acr-trb.b.smartschool;
import java.util.Scanner;

public class Secretary {
  static void collectInfo() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter student name: ");
    String name = scanner.next();

    System.out.print("Test 1: ");
    Float testA = Float.parseFloat(scanner.next());

    System.out.print("Test 2: ");
    Float testB = Float.parseFloat(scanner.next());

    System.out.print("Test 3: ");
    Float testC = Float.parseFloat(scanner.next());

    scanner.close();

    System.out.println(calculateGradeAverage(name, testA, testB, testC));
  }

  static String calculateGradeAverage(String name, float testA, float testB, float testC) {
    return name + "'s average test is " + ((testA + testB + testC)/3);
  }
}
