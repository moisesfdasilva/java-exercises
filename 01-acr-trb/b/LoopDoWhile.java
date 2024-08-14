import java.util.Random;

public class LoopDoWhile {
  public static void main(String[] args) {
    int i = 0;
    int n = 6;
    
    do {
      System.out.println(new Random().ints(0, 61).findFirst().getAsInt());
      i++;
    } while (i < n);
  }
}