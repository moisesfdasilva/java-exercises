//package 01-acr-trb.d.game;

public class Warrior extends PlayableCharacter {
  private String weapon;

  public String getWeapon() {
    return weapon;
  }

  public void setWeapon(String weapon) {
    this.weapon = weapon;
  }

  @Override
  public void attack() {
    System.out.println("The warrior is attacking with his weapon.");
  }

  public void specialAttack() {
    if (!isAlive) {
      System.out.println("Character is dead and he can not do special attack.");
      return;
    }

    System.out.println(this.getName() + " is doing a special attack.");
  }
}