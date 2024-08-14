import java.util.Random;

public class Conditional2 {
  public static void main(String[] args) {
    int result = nextResult();

    if (result > 5) {
      printMsg(result, "above average");
    } else {
      printMsg(result, "below average");
    }
  }

  static int nextResult() {
    return new Random().ints(0, 11).findFirst().getAsInt();
  }

  static void printMsg(int num, String text) {
    System.out.println("The number is " + num + ", this is " + text);
  }
}
