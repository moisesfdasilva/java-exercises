//package 01-acr-trb.d.game;

public class Cleric extends PlayableCharacter {
  private String magic;

  public String getMagic() {
    return magic;
  }

  public void setMagic(String magic) {
    this.magic = magic;
  }

  public void heal() {
    System.out.println("The cleric is healing someone.");
  }

  public void heal(String potion) {
    System.out.println("The cleric is healing with a potion" + potion + ".");
  }

  public void heal(String spell, int power) {
    System.out.println("The cleric is using a magic " + spell + " with a power " + power + ".");
  }
}