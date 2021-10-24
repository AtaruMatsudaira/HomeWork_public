package ex3;

import java.util.*;

import ex4.SortBase;

public class TestSort {
    public static void main(String[] args) {
        String file1 = "rand2.txt";
        String file2 = "sorted2.txt";
        String file3 = "reverse2.txt";
        String file4 = "same2.txt";

        // ここを作る
        // 各ファイルに対して，各ソートアルゴリズムを実行する
        // 各アルゴリズムでソートする際に，その処理時間を測定する
        String[] files = { file1, file2, file3, file4 };
        for (String file : files) {
            HashMap<String, SortBase> sortMap = new HashMap<String, SortBase>();
            sortMap.put("バブルソート", new BubbleSort(file));
            sortMap.put("　選択ソート", new SelectionSort(file));
            sortMap.put("　挿入ソート", new InsertionSort(file));

            System.out.println(file + "のソート");
            for (Map.Entry<String, SortBase> pair : sortMap.entrySet()) {
                long start = System.currentTimeMillis();
                pair.getValue().sort();
                long end = System.currentTimeMillis();
                System.out.println(pair.getKey() + " : " + (end - start) + "[ms]");
            }
        }
    }
}