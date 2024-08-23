//package 01-acr-trb.d.game;

public class Main {
  public static void main(String[] args) {
    Warrior warrior = new Warrior();
    warrior.setName("Aragorn");
    warrior.setRace("Human"); 
    warrior.setWeapon("Sword"); 

    System.out.println(warrior.getName() + " is " + warrior.getRace() + " and he has a " + warrior.getWeapon());
  }
}
