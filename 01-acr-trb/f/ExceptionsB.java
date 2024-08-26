//package 01-acr-trb.f;

import java.util.Scanner;

public class ExceptionsB {
  public static void main(String[] args) {
    int[] numeros = new int[10];
    Scanner scanner = new Scanner(System.in);
    try {
      System.out.println("Enter a number: ");
      int index = scanner.nextInt();
      numeros[index] = 10;
      numeros[index] = 2/0;
      System.out.println(numeros);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Ops, non existent index!");
    } catch(ArithmeticException e) {
      System.out.println("Ops, division by zero!");
    }
    scanner.close();
    System.out.println("End of program.");
  }
}
