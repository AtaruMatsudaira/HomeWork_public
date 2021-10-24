package ex4;

public class MergeSort extends SortBase {

    public MergeSort(String fileName) {
        super(fileName);
    }

    private void mergeSort(int low, int high) {
        int i, j, k, center;
        int[] work = new int[array.length];
        if (low < high) {
            center = (low + high) / 2; // 真ん中
            mergeSort(low, center); // 左を整列
            mergeSort(center + 1, high); // 右を整列
            for (i = center; i >= low; i--) {
                work[i] = array[i];
            } // 左半分
            for (j = center + 1; j <= high; j++) {
                work[high - (j - (center + 1))] = array[j]; // 右半分を逆順
            }
            i = low;
            j = high;
            for (k = low; k <= high; k++) {
                if (work[i] < work[j]) {
                    array[k] = work[i++];
                } else {
                    array[k] = work[j--];
                }
            }
        }
    }

    public void sort() {
        mergeSort(0, array.length - 1);
    }

    public static void main(String[] args) {
        String file1 = "rand2.txt";
        String file2 = "result_ex4-2_rand2.txt";

        MergeSort ms = new MergeSort(file1);
        ms.sort();
        ms.output(file2);

    }
}
