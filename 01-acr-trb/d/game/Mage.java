//package 01-acr-trb.d.game;

public class Mage extends PlayableCharacter {
  private String magic;

  public String getMagic() {
    return magic;
  }

  public void setMagic(String magic) {
    this.magic = magic;
  }

  @Override
  public void attack() {
    System.out.println("The mage is doing a magic.");
  }
}