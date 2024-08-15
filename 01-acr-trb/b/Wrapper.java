public class Wrapper {
  public static void main(String[] args) {
    Integer value = 7; // autoboxing
    int anotherValue = 8; //unboxing
    System.out.println(value + anotherValue);

    byte b = 10;
    Byte bb = 10;
    System.out.println(b + bb);

    short s = 10000;
    Short ss = 10000;
    System.out.println(s + ss);

    int i = 100000;
    Integer ii = 100000;
    System.out.println(i + ii);

    long l = 100000L;
    Long ll = 100000L;
    System.out.println(l + ll);
    
    float f = 234.5f;
    Float ff = 234.5f;
    System.out.println(f + ff);

    double d = 123.4;
    Double dd = 123.4;
    System.out.println(d + dd);

    boolean bool = true;
    Boolean boolbool = true;
    System.out.println(bool + " " + boolbool);

    char c = 'a';
    Character cc = 'a';
    System.out.println(c + " " + cc);
  }
}
