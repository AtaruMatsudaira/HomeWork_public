package ex6;

import java.util.*;

public class HashMapCounter {
    public static int count(String[] sources, String[] targets) {
        Map<String, Integer> counts = new HashMap<>();
        int sum = 0;
        for (var target : targets) {
            counts.put(target, 0);
        }
        for (var source : sources) {
            if (counts.containsKey(source)) {
                counts.put(source, counts.get(source) + 1);
                sum++;
            }
        }
        // for (var count : counts.entrySet()) {
        // System.out.println(count.getKey() + " : " + count.getValue());
        // sum += count.getValue();
        // }
        return sum;
    }

    public static void main(String[] args) {
        String[] sources = new String[] { "A", "B", "C", "A", "A", "B" };
        String[] targets = new String[] { "A", "B", "D" };

        System.out.println(count(sources, targets));
        // new Scanner(System.in).nextLine();
        Random r = new Random(20202020);
        String[] bigSources = new String[1000000];
        Set<String> bigTargets = new HashSet<>();
        for (int i = 0; i < bigSources.length; i++) {
            bigSources[i] = String.valueOf(r.nextInt(1000000));
        }
        for (int i = 0; i < 1000000; i++) {
            bigTargets.add(String.valueOf(r.nextInt(1000000)));
        }

        System.out.println(count(bigSources, bigTargets.toArray(new String[0])));
    }
}