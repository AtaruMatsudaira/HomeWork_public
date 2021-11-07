package ex7;

import java.util.*;

public class DigitBonacchi {
    //フィボナッチ数の答えを記録しておくMap キーが n 値が フィボナッチ数
    static Map<Integer, Integer> memo = new HashMap<>() {
        {
            put(0, 0);
            put(1, 1);
        }
    };

    public static int digitbonacchi(int n) {
        //nがmemoのkeyに存在するか
        if (memo.containsKey(n)) {
            //すでに算出した値を返す
            return memo.get(n);
        // nがmemoのkeyになったら
        } else {
            //フィボナッチ数の算出
            int value = (digitbonacchi(n - 1) + digitbonacchi(n - 2)) % 1000;
            //メモへの登録
            memo.put(n, value);
            //値を返す
            return value;
        }
    }

    public static void main(String[] args) {
        System.out.println(digitbonacchi(1111));
        System.out.println(digitbonacchi(1112));
        System.out.println(digitbonacchi(1113));
        System.out.println(digitbonacchi(1114));
    }
}