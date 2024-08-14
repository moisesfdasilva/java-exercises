public class LoopWhile {
  public static void main(String[] args) {
    String[] people = { "Alpha", "Bravo", "Charlie", "Delta", "Eco",
      "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima",
      "Mike", "November", "Oscar", "Papa", "Quebec", "Romeu", "Sierra",
      "Tango", "Uniform", "Victor", "Whiskey", "X-Ray", "Yankee", "Zulu" };
   
    int i = 0;
    String log;
    
    while (i < people.length) {
      log = "Person... " + (i + 1) + "... " + people[i];
      System.out.println(log);
      i++;
    }
  }
}