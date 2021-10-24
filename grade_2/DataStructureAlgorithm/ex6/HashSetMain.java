package ex6;

import java.util.*;
import java.util.stream.Collectors;

public class HashSetMain {

    public static String[] union(String[] list1, String[] list2) {
        // TODO: 和集合
        Set<String> result = new HashSet<String>();
        result.addAll(Arrays.stream(list1).collect(Collectors.toSet()));
        result.addAll(Arrays.stream(list2).collect(Collectors.toSet()));
        return result.toArray(new String[result.size()]);
    }

    public static String[] intersection(String[] list1, String[] list2) {
        // TODO: 積集合
        Set<String> result = new HashSet<String>(Arrays.stream(list1).collect(Collectors.toSet()));
        result.retainAll(Arrays.stream(list2).collect(Collectors.toSet()));
        return result.toArray(new String[result.size()]);
    }

    public static String[] xor(String[] list1, String[] list2) {
        // TODO: 積集合
        Set<String> setAcopy = new HashSet<>(Arrays.stream(list1).collect(Collectors.toSet()));
        Set<String> setBcopy = new HashSet<>(Arrays.stream(list2).collect(Collectors.toSet()));
        Set<String> setAcopy2 = new HashSet<>(setAcopy);
        // まず積集合を得る
        setAcopy.retainAll(setBcopy);
        // 各集合から積集合を除く
        setAcopy2.removeAll(setAcopy);
        setBcopy.removeAll(setAcopy);
        setAcopy2.addAll(setBcopy);
        return setAcopy2.toArray(new String[setAcopy2.size()]);
    }

    public static void main(String[] args) {
        String[] namesA = "Alice,Bob,Charlie,Fred,Diana,Bob".split(",");
        String[] namesB = "Bob,Diana,Elmo,Fred,Diana,Bob".split(",");

        String[] unionResult = union(namesA, namesB);
        Arrays.sort(unionResult);
        String[] intersectionResult = intersection(namesA, namesB);
        Arrays.sort(intersectionResult);
        String[] xorResult = xor(namesA, namesB);
        Arrays.sort(xorResult);

        System.out.println("union");
        System.out.println(String.join(",", unionResult));

        System.out.println("intersection");
        System.out.println(String.join(",", intersectionResult));

        System.out.println("xor");
        System.out.println(String.join(",", xorResult));
    }
}
