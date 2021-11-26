package ex10;

import java.util.*;

public class MathFormulaTree {
    private MathFormulaTree left;
    private MathFormulaTree right;
    private String label;

    public static MathFormulaTree createFromReversePolish(String equation) {
        // 逆ポーランド記法で書かれた式の文字列から数式の木を作る．
        // スタックを使用するが，スタックの要素は文字列や数値ではなく数式の木（部分木）を用いる．
        Stack<MathFormulaTree> stack = new Stack<>();
        Scanner scanner = new Scanner(equation);
        while (scanner.hasNext()) {
            String elem = scanner.next();
            if (isOperator(elem)) {
                // TODO: stack から 2 つ取り出し elem を親とする Tree にまとめて stack につむ
                // stack: [1, 2, 3] elem: +
                // → stack: [1, [+, 2, 3]]
                stack.push(new MathFormulaTree(stack.pop(), stack.pop(), elem));
            } else {
                // TODO: 数字は stack につむ
                // stack: [1, 2] elem: 3
                // → stack: [1, 2, 3]
                stack.push(new MathFormulaTree(null, null, elem));
            }
        }
        scanner.close();
        return stack.pop();
    }

    private static boolean isOperator(String elem) {
        return elem.equals("+") || elem.equals("*");
    }

    private MathFormulaTree(MathFormulaTree left, MathFormulaTree right, String label) {
        // ここは createFromReversePolish() から呼び出して使用される．
        this.left = left;
        this.right = right;
        this.label = label;
    }

    public void printTree() {
        printTree("");
    }

    public void printTree(String prefix) {
        if (left != null) {
            left.printTree(prefix + "\t");
        }
        System.out.println(prefix + "[" + label + "]");
        if (right != null) {
            right.printTree(prefix + "\t");
        }
    }

    public String toPolish() {
        // TODO: 数式の木からポーランド記法で書かれた式の文字列を作成して返す．
        // 前置記法 → 行きがけ順
        // 最後にスペースが入っていても良い
        String str = "";
        str += label;

        if (right != null) {
            str += right.toPolish();
        }

        if (left != null) {
            str += left.toPolish();
        }
        return " " + str + " ";
    }

    public String toInfix() {
        // 中置記法 → 通りがけ順
        String str = "";
        if (left != null) {
            str += left.toInfix();
        }
        str += label;

        if (right != null) {
            str += right.toInfix();
        }
        return "(" + str + ")";
    }

    public int evolution() {
        // TODO: 計算結果を返す
        switch (label) {
        case "+":
            return this.left.evolution() + this.right.evolution();
        case "*":
            return this.left.evolution() * this.right.evolution();
        default:
            // 数値
            return Integer.parseInt(this.label);
        }
    }

    public static void main(String[] args) {
        String input = "2 6 * 5 3 9 + + *";
        System.out.println("後置記法");
        System.out.println(input);
        MathFormulaTree tree = MathFormulaTree.createFromReversePolish(input);
        System.out.println("数式の木");
        tree.printTree();
        System.out.println("前置記法");
        System.out.println(tree.toPolish());
        System.out.println("中置記法");
        System.out.println(tree.toInfix());
        System.out.println("計算結果");
        System.out.println(tree.evolution());
    }
}