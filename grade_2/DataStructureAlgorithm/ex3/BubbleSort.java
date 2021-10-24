package ex3;

import ex4.SortBase;

public class BubbleSort extends SortBase {

    public BubbleSort(String fileName) {
        super(fileName);
    }

    public void sort() {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = array.length - 1; j > i; j--) {

                if (array[j] < array[j - 1]) {
                    int t = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = t;
                }
            }
        }
    }

    public static void main(String[] args) {
        String file1 = "rand2.txt";
        String file2 = "result_ex3-1_rand2.txt";

        BubbleSort bs = new BubbleSort(file1);
        bs.sort();
        bs.output(file2);
    }
}