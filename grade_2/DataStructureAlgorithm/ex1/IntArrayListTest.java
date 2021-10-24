package ex1;

import java.util.*;

public class IntArrayListTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 整数を要素とするArrayListの宣言と生成
        ArrayList<Integer> intList = new ArrayList<Integer>();
        System.out.println("整数値を入力してください。整数以外を入力するまで繰り返します。");
        while (true) {
            try {
                // 入力部分
                // ここを作る
                intList.add(scanner.nextInt());
            } catch (InputMismatchException e) {
                System.out.println("入力が完了しました。データの数は" + intList.size());
                break;
            }
        }
        // 入力された値を表示する
        System.out.println("入力された値は");
        double sum = 0;
        // ここを作る
        for (var num : intList) {
            System.out.print(num + "\t");
            sum += num;
        }
        System.out.println("");

        // 合計を表示する
        // ここを作る
        System.out.println("合計は" + sum);
        // 平均を表示する
        // ここを作る
        System.out.println("平均は" + sum / intList.size());
        // scannerを閉じる
        scanner.close();
    }
}