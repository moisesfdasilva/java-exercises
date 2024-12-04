import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<Integer> numbers = List.of(9, 95, 955, 9555, 1, 11, 111, 1111, 4, 44, 444, 4444, 7, 77, 777, 7777);

    for (Integer number : numbers) {
      Conversion conversion = new Conversion(number);
      System.out.println(conversion.output());
    }
  }
}

