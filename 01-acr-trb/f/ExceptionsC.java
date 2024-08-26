//package 01-acr-trb.f;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionsC {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    try {
      int num = scanner.nextInt();
      int result = 25/num;
      System.out.println(result);
    } catch(ArithmeticException e) {
      System.out.println("Ops, division by zero!");
    } catch(InputMismatchException e) {
      System.exit(1);
    } finally {
      scanner.close();
      System.out.println("End of program with finally.");
    }
  }
}
