public class Television {
  private int MAX_VOLUME = 30;
  private int volume = 0;
  
  private boolean isOn = false;

  private String brand;
  private String model;
  private int size;

  private static double INCH_TO_CM = 2.54;

  // public -> same class, same package, same subclass, any class.
  // protected -> same class, same package, same subclass.
  // default -> same class, same package.
  // private -> same class.

  public Television(String brand, String model, int size) {
    this.brand = brand;
    this.model = model;
    this.size = size;
  }

  public int getVolume() {
    return volume;
  }

  public boolean isOn() {
    return isOn;
  }

  public String getBrand() {
    return brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getSize() {
    return size;
  }

  public void turnOn() {
    System.out.println("Turn on television...");
    this.isOn = true;
  }

  public void turnOff() {
    System.out.println("Turn off television...");
    this.isOn = false;
  }

  public String info() {
    return "Brand: %s, Model: %s, Size: %d, Is on: %b".formatted(
      brand, model, size, isOn
    );
  }

  public void increaseVolume() {
    if (volume < MAX_VOLUME) {
      volume++;
    }
  }

  public void decreaseVolume() {
    if (volume > 0) {
      volume--;
    }
  }

  public static double convertToCentimeters(double inches) {
    return inches * INCH_TO_CM;
  }
}