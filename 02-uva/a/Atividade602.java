import java.util.Scanner;

public class Atividade602 {
  public static void main(String[] args) {
    float a, b;
    Scanner scanner = new Scanner(System.in);

    System.out.print("Entre com um número flutuante: ");
    a = scanner.nextFloat();

    System.out.print("Entre com outro número flutuante: ");
    b = scanner.nextFloat();

    System.out.println(a + " + " + b + " = " + (a + b));
    System.out.println(a + " - " + b + " = " + (a - b));
    System.out.println(a + " x " + b + " = " + (a * b));
    System.out.println(a + " / " + b + " = " + (a / b));

    scanner.close();
  }
}