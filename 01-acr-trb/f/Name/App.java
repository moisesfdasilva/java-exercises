//package 01-acr-trb.f.Name;

import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter your name:");
    String name = scanner.next();
    scanner.close();

    try {
      showName(name);
    } catch (NameException e) {
      System.out.println("Name exception!");
    }
  }

  public static void showName(String name) {
    if (hasNumber(name)) {
      throw new NameException();
    }

    System.out.printf("Welcome, %s", name);
  }

  public static boolean hasNumber(String word) {
    for (char c : word.toCharArray()) {
      if (Character.isDigit(c)) {
        return true;
      }
    }

    return false;
  }
}
