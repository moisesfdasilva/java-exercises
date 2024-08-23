// package 01-acr-trb.d.game;

public class PlayableCharacter {
  private String name;
  private String race;

  protected boolean isAlive;

  public PlayableCharacter() {
    this.isAlive = true;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRace() {
    return race;
  }

  public void setRace(String race) {
    this.race = race;
  }

  public void move() {
    if (!isAlive) {
      System.out.println("Character is dead and he can not move.");
      return;
    }

    System.out.println(name + " is moving.");
  }

  public void attack() {
    if (!isAlive) {
      System.out.println("Character is dead and he can not attack.");
      return;
    }

    System.out.println(name + " is attacking.");
  }
}