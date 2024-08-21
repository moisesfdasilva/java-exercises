import com.google.common.base.Joiner;

public class Main {
  public static void main(String[] args) {
    String result = Joiner.on(", ").skipNulls().join("Olá", null, "mundo");
    System.out.println(result);
  }
}
