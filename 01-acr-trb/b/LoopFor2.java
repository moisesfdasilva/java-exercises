public class LoopFor2 {
  public static void main(String[] args) {
    String[] students = { "Alpha", "Bravo", "Charlie", "Delta", "Eco",
      "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima",
      "Mike", "November", "Oscar", "Papa", "Quebec", "Romeu", "Sierra",
      "Tango", "Uniform", "Victor", "Whiskey", "X-Ray", "Yankee", "Zulu" };
   
    String log = "The list has " + students.length + " students";
    System.out.println(log);
    
    for (int i = 0; i < students.length; i++) {
      log = "Id: " + (i + 1) + " Student: " + students[i];
      System.out.println(log);
    }
  }
}