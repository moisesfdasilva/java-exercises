//package 01-acr-trb.d.tech;

public class PowerBank implements Chargeable {
  @Override
  public void charge() {
    System.out.println("Powerbank is loading...");
  }

  @Override
  public int getBatteryLevel() {
    return 65;
  }
}