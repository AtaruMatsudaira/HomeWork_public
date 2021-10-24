package ex1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IntArrayTest {
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int[] numbers = null; // 整数型の配列を入れる変数宣言
      System.out.println("配列の大きさを入れてください。");
      try {
         int n = scanner.nextInt(); // 個数の入力
         if (n <= 0) {
            System.out.println("ゼロより大きな値を入れてください。");
            System.exit(0);
         }
         // 配列の確保
         // ここを作る
         numbers = new int[n];
         
         System.out.println("整数値を" + n + "個入れてください。");
         // 配列に値を入力
         // ここを作る
         for(int i =0;i<n;i++) {
        	 System.out.print(i+"番目;");
        	 numbers[i] = scanner.nextInt();
         }

      } catch (InputMismatchException e) {
         System.out.println("数値の形式が違います。");
         e.printStackTrace();
         System.exit(0);
      }             
      // 入力された値を表示
      System.out.println("入力された値は");
      // ここを作る タブ文字は \t を使うとよい
      for(var num : numbers) {
    	  System.out.print(num + "\t");
      }
      System.out.println("");
      // 合計の表示
      // ここを作る
      double sum=0;
      for(var num : numbers) {
    	  sum+=num;;
      }
      double avg = sum/numbers.length;
      System.out.println("合計は"+ (int)sum);
      System.out.println("平均は"+avg);
      // 平均の表示
      // ここを作る

      // scannerを閉じる
      scanner.close();
   }
}