//package 01-acr-trb.d.game;

public class Main {
  public static void main(String[] args) {
    Warrior player1 = new Warrior();
    PlayableCharacter player2 = new Warrior();

    addInformation(player1);
    addInformation(player2);

    System.out.println(player1.getName() + " is " + player1.getRace());
    System.out.println(player2.getName() + " is " + player2.getRace());

    Warrior warrior = new Warrior();
    warrior.setName("Aragorn");
    warrior.setRace("Human");
    warrior.setWeapon("Sword");
    moveAndAttack(warrior);
    warrior.specialAttack();

    Mage mage = new Mage();
    mage.setName("Gandalf");
    mage.setRace("Mage");
    moveAndAttack(mage);

    Barbarian barbarian = new Barbarian();
    barbarian.move();

    Cleric cleric = new Cleric();
    cleric.heal();
    cleric.heal("Potion of life");
    cleric.heal("Light magic", 7);
  }

  public static void addInformation(PlayableCharacter player) {
    player.setName("Aragorn");
    player.setRace("Human");
  }

  public static void moveAndAttack(PlayableCharacter player) {
    player.move();
    player.attack();
  }
}