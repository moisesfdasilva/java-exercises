public class NumberConversion {
  public static void main(String[] args) {
    short numA = 10;
    System.out.println(numA);
    int numB = numA;
    System.out.println(numB);
    long numC = numB;
    System.out.println(numC);
    float numD = numC;
    System.out.println(numD);
    double numE = numD;
    System.out.println(numE);

    double bigNum = 40_000_000.49d;
    System.out.println(bigNum);
    int intNum = (int) bigNum;
    System.out.println(intNum);
    short shortNum = (short) intNum;
    System.out.println(shortNum);
  }
}
