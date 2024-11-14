import java.util.Scanner;

public class Atividade601 {
  public static void main(String[] args) {
    int a, b;
    Scanner scanner = new Scanner(System.in);

    System.out.print("Entre com um número inteiro: ");
    a = scanner.nextInt();

    System.out.print("Entre com outro número inteiro: ");
    b = scanner.nextInt();

    System.out.println(a + " + " + b + " = " + (a + b));
    System.out.println(a + " - " + b + " = " + (a - b));
    System.out.println(a + " x " + b + " = " + (a * b));
    System.out.println(a + " / " + b + " = " + (a / b));

    scanner.close();
  }
}