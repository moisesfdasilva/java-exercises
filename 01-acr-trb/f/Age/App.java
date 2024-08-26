//package 01-acr-trb.f.Age;

import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    System.out.println("Enter your age:");
    Scanner scanner = new Scanner(System.in);
    int age = scanner.nextInt();

    try {
      verifyAge(age);
      System.out.println("Confirmed!");
    } catch (AgeException e) {
      System.out.println("Non confirmed, age exception!");
    } finally {
      scanner.close();
    }
  }

  private static boolean verifyAge(int age) throws AgeException {
    if (age < 18) {
      throw new AgeException();
    } else {
      return true;
    }
  }
}