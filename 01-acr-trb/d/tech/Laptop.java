//package 01-acr-trb.d.tech;

public class Laptop extends ComputerDevice implements Chargeable{
  private double size;

  public Laptop(String brand, double storageCapacity, double size) {
    super(brand, storageCapacity);

    this.size = size;
  }

  @Override
  public void bootUp() {
    System.out.println("Laptop size " + size + " is starting...");
  }

  @Override
  public void charge() {
    System.out.println("Laptop is loading...");
  }

  @Override
  public int getBatteryLevel() {
    return 85;
  }
}
