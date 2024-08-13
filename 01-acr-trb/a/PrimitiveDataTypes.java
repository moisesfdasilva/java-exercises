class PrimitiveDataTypes {
  public static void main(String[] args) {
    byte varByteA = -127;
    byte varByteB = 127;

    System.out.println(varByteA);
    System.out.println(varByteB);

    short varShortA = -32768;
    short varShortB = 32767;

    System.out.println(varShortA);
    System.out.println(varShortB);

    int varIntA = -2147483648;
    int varIntB = 2147483647;

    System.out.println(varIntA);
    System.out.println(varIntB);

    long varLongA = -9223372036854775808L;
    long varLongB = 9223372036854775807L;

    System.out.println(varLongA);
    System.out.println(varLongB);

    float varFloatA = -4.9E-45f;
    float varFloatB = 3.4028235E38f;

    System.out.println(varFloatA);
    System.out.println(varFloatB);

    double varDoubleA = -4.9E-324d;
    double varDoubleB = 1.7976931348623157E308d;

    System.out.println(varDoubleA);
    System.out.println(varDoubleB);

    char varCharA = 'x';
    char varCharB = 181; // unicode table

    System.out.println(varCharA);
    System.out.println(varCharB);

    boolean varBoolA = false;
    boolean varBoolB = true;

    System.out.println(varBoolA);
    System.out.println(varBoolB);
  }
}