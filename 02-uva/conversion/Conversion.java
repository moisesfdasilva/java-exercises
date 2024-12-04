public class Conversion {

  private int numberToConvert;

  public Conversion(int number) {
    this.numberToConvert = number;
  }

  public String output() {
    String romanNumeral = "";
    int number = this.numberToConvert;

    while(number > 0) {
      if (number >= 1000) {
        romanNumeral += "M";
        number -= 1000;
      } else if (number >= 100 && number < 1000) {
        if (number >= 900) {
          romanNumeral += "CM";
          number -= 900;
        } else if (number <= 800 && number >= 500) {
          romanNumeral += "D";
          number -= 500;
        } else if (number < 500 && number >= 400) {
          romanNumeral += "CD";
          number -= 400;
        } else {
          romanNumeral += "C";
          number -= 100;
        }
      } else if (number >= 10 && number < 100) {
        if (number >= 90) {
          romanNumeral += "XC";
          number -= 90;
        } else if (number <= 80 && number >= 50) {
          romanNumeral += "L";
          number -= 50;
        } else if (number < 50 && number >= 40) {
          romanNumeral += "XL";
          number -= 40;
        } else {
          romanNumeral += "X";
          number -= 10;
        }
      } else {
        if (number == 9) {
          romanNumeral += "IX";
          number -= 9;
        } else if (number <= 8 && number >= 5) {
          romanNumeral += "V";
          number -= 5;
        } else if (number == 4) {
          romanNumeral += "IV";
          number -= 4;
        } else {
          romanNumeral += "I";
          number -= 1;
        }
      }
    }

    return romanNumeral;
  }
}

