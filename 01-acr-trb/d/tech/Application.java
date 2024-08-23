//package 01-acr-trb.d.tech;

public class Application {
  public static void main(String[] args) {
    Laptop laptop = new Laptop("Samsung", 100, 14);
    Desktop desktop = new Desktop("Dell", 500);
    PowerBank powerBank = new PowerBank();

    checkDevice(laptop);
    checkDevice(desktop);

    plugDevice(laptop);
    plugDevice(powerBank);

    ComputerDevice genericDevice = new Laptop("Apple", 256.0, 13.3);

    if (genericDevice instanceof Laptop) {
      Laptop specificLaptop = (Laptop) genericDevice;

      System.out.println(specificLaptop.getStorageCapacity());
    }
  }

  public static void checkDevice(ComputerDevice device) {
    System.out.println("Verifying device brand " + device.getBrand());
    device.bootUp();
  }

  public static void plugDevice(Chargeable chargeable) {
    System.out.println("Battery level " + chargeable.getBatteryLevel());
    chargeable.charge();
  }
}
