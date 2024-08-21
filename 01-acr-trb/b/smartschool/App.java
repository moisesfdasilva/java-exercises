//package 01-acr-trb.b.smartschool;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    System.out.println("Welcome to Smart Scholl!");
    System.out.println("Select a department:");
    System.out.println("1 - Student - Calculate age in days");
    System.out.println("2 - Secretary - Calculate grade average");
    System.out.println("3 - Concierge - Generate report");

    short menuOption = 0;
    Scanner scanMenu = new Scanner(System.in);
    menuOption = scanMenu.nextShort();

    switch (menuOption) {
      case 1:
        Student.collectInfo();
        break;
      case 2:
        Secretary.collectInfo();
        break;
      // case 3:
      //   Portaria.coletarInformacoes();
      //   break;
      default:
        System.out.println("Opção Inválida.");
    }

    scanMenu.close();
  }
} 