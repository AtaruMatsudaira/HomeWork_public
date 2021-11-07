package ex7;

public class Tribonacci {
    public static int tribonacci(int n) {
        if (n == 0 || n == 1) {
            // 第 0,1 項は 0 なので 0 を返す
            return 0;
        } else if (n == 2) {
            // 第 2 項は1なので 1 を返す
            return 1;
        } else {
            // n-1 , n-2 , n-3 の値を足してる
            return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            System.out.printf("tri(%2d) = %3d\n", i, tribonacci(i));
        }
    }
}