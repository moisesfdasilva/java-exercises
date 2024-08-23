//package 01-acr-trb.d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListA {
  public static void main(String[] args) {
    ArrayList<Integer> nums = new ArrayList<Integer>();
    nums.add(7);
    nums.add(2);
    nums.add(1);
    nums.add(9);
    System.out.println(nums);

    Collections.sort(nums);
    System.out.println(nums);

    List<String> names = Arrays.asList("John","Mary","Peter","Rose");
    System.out.println(names);
    names.set(0,"Joana");
    System.out.println(names);

    List<String> names2 = Arrays.asList(new String[]{"John","Mary","Peter","Rose"});
    System.out.println(names2);
    names2.set(0,"Joana");
    System.out.println(names2);

    String[]namesArray = new String[]{"John","Mary","Peter","Rose"};
    List<String> names3 = Arrays.asList(namesArray);
    System.out.println(names3);
    names3.set(0,"Joana");
    System.out.println(names3);

    // immutable
    List<String> names4 = List.of("John","Mary","Peter","Rose");
    System.out.println(names4);

    List<String> names5 = new ArrayList<String>(Arrays.asList("John","Mary","Peter","Rose"));
    System.out.println(names5);
    names5.set(0,"Joana");
    names5.add("Zara");
    names5.remove(2);
    names5.remove("Rose");
    System.out.println(names5);
    System.out.println(names5.size());
    System.out.println(names5.contains("Zara"));
  }
}
