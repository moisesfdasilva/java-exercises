public class App {
  public static void main(String[] args) {
    Television television = new Television("Phillips","LCD", 32);

    television.turnOn();
    television.turnOff();

    System.out.println(television.info());

    System.out.println("Is television on?");
    System.out.println(television.isOn());

    System.out.println("What is the volume?");
    System.out.println(television.getVolume());

    double sizeCm = Television.convertToCentimeters(32);
    System.out.println("The size of television in cm is: " + sizeCm);
  }
}
