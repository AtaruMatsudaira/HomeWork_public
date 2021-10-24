package ex2;

import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class SortByNumeric2 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader("rand2.txt"))) {
            String line;
            while ((line = reader.readLine()) != null)
                list.add(line);

        } catch (IOException e) {
            System.out.println("Reader Error");
            return;
        }
        System.out.println("ソート前");
        for (String str : list) {
            System.out.println(str);
        }

        Collections.sort(list);

        System.out.println("ソート後");
        for (String str : list) {
            System.out.println(str);
        }

        Collections.sort(list, new NumericComparator());

        System.out.println("順序付けを変更後");
        try (var writer = new PrintWriter(new BufferedWriter(new FileWriter("result_ex2-5.txt")))) {
            for (var line : list) {
                System.out.println(line);

                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println("Writer Error");
            return;
        }
    }
}