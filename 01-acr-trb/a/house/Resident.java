public class Resident {
  public String name;
  public int age;
  public double weight;
  public double height;

  public Resident(String name, int age, double weight, double height) {
    this.name = name;
    this.age = age;
    this.weight = weight;
    this.height = height;
  }

  public double BodyMassIndex() {
    return this.weight / (this.height * this.height);
  }
}
