package ex4;

public class QuickSort extends ex4.SortBase {

    public QuickSort(String fieldName) {
        super(fieldName);
    }

    private int partition(int l, int r) {
        // ここを作る
        // クイックソートにおける分割を行う
        int i = l - 1;
        int j = r;
        int p = array[r];
        while (true) {
            while (array[++i] < p)
                ;
            while (i < --j && p < array[j])
                ;
            if (i >= j)
                break;
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j++;
        }
        int temp = array[i];
        array[i] = array[r];
        array[r] = temp;
        return i;
    }

    private void quickSort(int l, int r) {
        // ここを作る
        // クイックソートを実装する
        // 配列aの中身をソートする
        if (l >= r) {
            return;
        }
        int center = partition(l, r);
        quickSort(l, center - 1);
        quickSort(center + 1, r);
    }

    public void sort() {
        quickSort(0, array.length - 1);
    }

    public static void main(String[] args) {
        String file1 = "sorted2.txt";
        String file2 = "result_ex4-1_sorted2.txt";

        QuickSort qs = new QuickSort(file1);
        qs.sort();
        qs.output(file2);
    }
}
