import java.util.Scanner;

public class NumberConversion2 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter a first number: ");
    String input1 = scanner.next();
    System.out.print("Enter a second number: ");
    String input2 = scanner.next();

    int n1 = Integer.parseInt(input1);
    int n2 = Integer.parseInt(input2);

    int result = n1 + n2;
    System.out.println("The sum is: " + result);
    scanner.close();
  }
}
