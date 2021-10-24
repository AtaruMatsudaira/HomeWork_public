package ex3;

import ex4.SortBase;

public class SelectionSort extends SortBase {
    public SelectionSort(String filename) {
        super(filename);
    }

    public void sort() {
        // ここを作る
        // 選択ソートを実装する
        // 配列arrayの中身をソートする
        for (int i = 0; i < array.length - 1; i++) {
            int min_pos = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min_pos]) {
                    min_pos = j;
                }
            }
            int temp = array[i];
            array[i] = array[min_pos];
            array[min_pos] = temp;
        }
    }

    public static void main(String[] args) {
        String file1 = "rand2.txt";
        String file2 = "result_ex3-2_rand2.txt";

        SelectionSort ss = new SelectionSort(file1);
        ss.sort();
        ss.output(file2);
    }
}