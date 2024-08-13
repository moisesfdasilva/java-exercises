public class Main {
  public static void main(String[] args) {
    Lamp lamp = new Lamp();
    lamp.turnOn();
    System.out.println("The lamp is " + lamp.isLampOn());
    lamp.turnOff();
    System.out.println("The lamp is " + lamp.isLampOn());
    lamp.turnOn();

    Resident resident = new Resident(
      "Moshe",23,73.5f,1.78f
    );
    System.out.println(resident.name + ", " + resident.age);
    System.out.println("BMI: " + resident.BodyMassIndex());

    SmartHouse house = new SmartHouse();
    System.out.println("Internet connection " + house.ConnectToTheInternet());
  }
}
