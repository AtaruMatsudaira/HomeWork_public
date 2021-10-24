package ex3;

import ex4.SortBase;

public class InsertionSort extends SortBase {

    public InsertionSort(String filename) {
        super(filename);
    }

    public void sort() {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        String file1 = "rand2.txt";
        String file2 = "result_ex3-3_rand2.txt";

        InsertionSort is = new InsertionSort(file1);
        is.sort();
        is.output(file2);
    }
}
