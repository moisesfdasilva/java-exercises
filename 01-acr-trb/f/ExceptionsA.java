//package 01-acr-trb.f;

public class ExceptionsA {
  public static void main(String[] args) {
    String word = null;
    try {
      int lenWord = word.length();
      System.out.println("The word length is " + lenWord + ".");
    } catch (NullPointerException e) {
      System.out.println("The word is null.");
    }

    try {
      Integer result = 2 / 0;
      System.out.println(result);
    } catch (ArithmeticException e) {
      System.out.println("Ops! Division by zero.");
    }
  }
}
