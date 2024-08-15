import java.util.Scanner;

public class NumberConversion3 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Entre com o número: ");
    String input = scanner.next();
    scanner.close();
    
    short shortNum = Short.parseShort(input);
    System.out.println(shortNum);
    long longNum = Long.parseLong(input);
    System.out.println(longNum);
    float floatNum = Float.parseFloat(input);
    System.out.println(floatNum);
    double doubleNum = Double.parseDouble(input);
    System.out.println(doubleNum);
  }
}
