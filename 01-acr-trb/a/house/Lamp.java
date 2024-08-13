public class Lamp {
  boolean lamp = false;

  void turnOn() {
    lamp = true;
  }

  void turnOff() {
    lamp = false;
  }

  boolean isLampOn() {
    return lamp;
  }
}