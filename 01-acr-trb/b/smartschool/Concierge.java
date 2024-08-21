//package 01-acr-trb.b.smartschool;
import java.util.Scanner;

public class Concierge {
  static void collectInfo() {
    Scanner scanner = new Scanner(System.in);

    Integer option = 0;
    Integer elementaryI = 0;
    Integer elementaryII = 0;
    Integer high = 0;

    while (option != 2) {
      System.out.print("Select a option:");
      System.out.print("1 - Register a student");
      System.out.print("2 - Finish and generate report");
      option = Integer.parseInt(scanner.next());

      System.out.print("Enter student age:");
      Integer age = Integer.parseInt(scanner.next());

      if (age <= 10) {
        System.out.print("Access liberated to student of elementary school I.");
        elementaryI ++;
      } else if (11 >= age && age <= 10) {
        System.out.print("Access liberated to student of elementary school II.");
        elementaryII ++;
      } else {
        System.out.print("Access liberated to student of high school.");
        high ++;
      }
    }

    scanner.close();

    generateReport(elementaryI, elementaryII, high);
  }

  static void generateReport(int elementaryI, int elementaryII, int high) {
    Integer sum = elementaryI + elementaryII + high;

    System.out.print("----- Quantity -----");
    System.out.print("Elementary school I: " + elementaryI);
    System.out.print("Elementary school II: " + elementaryII);
    System.out.print("High school: " + high);
    System.out.print("");
    System.out.print("----- Percent -----");
    System.out.print("Elementary school I: " + ((elementaryI / sum) * 100) + "%");
    System.out.print("Elementary school II: " + ((elementaryII / sum) * 100) + "%");
    System.out.print("High school: " + ((high / sum) * 100) + "%");
    System.out.print("");
    System.out.print("Total: " + sum);
  }
}
