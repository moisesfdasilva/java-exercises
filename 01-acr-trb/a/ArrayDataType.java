public class ArrayDataType {
  public static void main(String[] args) {
    int[] someArrayA = new int[5];
    someArrayA[0] = 10;
    someArrayA[1] = 11;
    someArrayA[2] = 12;
    someArrayA[3] = 13;
    someArrayA[4] = 14;

    for (int element : someArrayA) {
      System.out.println(element);
    }

    String[] someArrayB = {"fusca", "uno", "palio", "gol", "golf"};

    for (String element : someArrayB) {
      System.out.println(element);
    }
  }
}
