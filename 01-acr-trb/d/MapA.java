//package 01-acr-trb.d;

import java.util.HashMap;

public class MapA {
  public static void main(String[] args) {
    HashMap<String, String> names = new HashMap<String, String>();
    names.put("x038702","John");
    names.put("x037802","Mary");
    names.put("x038872","Peter");
    names.put("x039702","Rose");
    System.out.println(names);
    names.put("x039702","Joana");
    System.out.println(names);
    System.out.println(names.get("x038872"));
    names.remove("x038872");
    System.out.println(names);
  }
}
