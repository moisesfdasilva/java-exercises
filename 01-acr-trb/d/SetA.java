//package 01-acr-trb.d;

import java.util.HashSet;
import java.util.Set;

public class SetA {
  public static void main(String[] args) {
    // immutable
    Set<String> names = Set.of("John","Mary","Peter","Rose");
    System.out.println(names);

    HashSet<String> names2 = new HashSet<>();
    names2.add("John");
    names2.add("Mary");
    names2.add("Peter");
    names2.add("Rose");
    System.out.println(names2);
    names2.remove("Mary");
    System.out.println(names2);
    System.out.println(names2.size());
    System.out.println(names2.contains("Mary"));
    System.out.println(names2.contains("Rose"));
  }
}
