package ex5;

public class MyStack {
    private String[] items; // 要素を入れる配列
    private int size; // スタックの最大サイズ
    private int sp; // 空き場所を指すインデックス

    public MyStack(int size) {
        // TODO: メンバの初期化
        this.size = size;
        items = new String[size];
        sp = 0;
    }

    /**
     * 要素を追加する
     */
    public void push(String str) {
        // TODO: 実装する
        if (sp < this.size) {
            items[sp++] = str;
        } else {
            System.out.println("push エラー");
        }
    }

    /**
     * トップ(最後に入れられた)要素を取り出して返す
     *
     * @return 要素。空なら null
     */
    public String pop() {
        // TODO: 実装する
        if (sp == 0) {
            System.out.println("pop エラー"); // 空ならエラーを表示する
            return null;
        } else {
            return items[--sp];
        }
    }

    /**
     * トップ(最後に入れられた)要素を取り出さずに参照する
     *
     * @return トップ要素。空なら null
     */
    public String top() {
        // TODO: 実装する
        if (sp == 0) {
            System.out.println("top エラー"); // 空ならエラーを表示する
            return null;
        } else {
            return items[sp - 1];
        }
    }

    /**
     * @return 空であるかどうか
     **/
    public boolean isEmpty() {
        // TODO: 実装する
        return sp == 0;
    }

    public String toString() {
        String[] actives = new String[sp];
        for (int i = 0; i < sp; i++) {
            actives[i] = items[i];
        }
        return "aMyStack(" + String.join(", ", actives) + ")";
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack(5);
        System.out.println("push:");
        System.out.println(stack);
        stack.push("abc");
        stack.push("def");
        System.out.println(stack);
        stack.push("ghi");
        System.out.println(stack);
        System.out.println(stack.top());
        while (!stack.isEmpty()) {
            System.out.println("pop: " + stack.pop());
            System.out.println(stack);
        }
        System.out.println("エラーテスト");
        System.out.println(stack);
        stack.pop(); // 空のはずなのにpop!
        stack.top(); // 空のはずなのにtop!
        System.out.println(stack);
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        System.out.println(stack);
        stack.push("5");
        System.out.println(stack);
        stack.push("6"); // 一杯なはずなのにpush!
        System.out.println(stack);
    }
}