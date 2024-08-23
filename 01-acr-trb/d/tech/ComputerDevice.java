//package 01-acr-trb.d.tech;

public abstract class ComputerDevice {
  private String brand;
  private double storageCapacity;

  public static final double MAX_STORAGE_CAPACITY = 2048.0;

  public ComputerDevice(String brand, double storageCapacity) {
    this.brand = brand;
    this.storageCapacity = storageCapacity;
  }

  public String getBrand() {
    return brand;
  }

  public double getStorageCapacity() {
    return storageCapacity;
  }

  public abstract void bootUp();

  public final void shutDown() {
    System.out.println("Turn off the device...");
  }
}
