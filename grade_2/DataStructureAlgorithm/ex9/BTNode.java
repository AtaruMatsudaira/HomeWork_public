package ex9;

public class BTNode {
    final private String label;
    final private BTNode left;
    final private BTNode right;

    public BTNode(String label, BTNode left, BTNode right) {
        this.label = label;
        this.left = left;
        this.right = right;
    }

    public String printNode() {
        return printNode("");
    }

    public String printNode(String prefix) {
        // TODO: ツリーを表す文字列を返す
        String str = "";
        if (this.left != null) {
            str += prefix + this.left.printNode("  ");
        }
        str += prefix + this + "\n";
        // right
        if (this.right != null) {
            str += prefix + this.right.printNode("  ");
        }
        return str;
    }

    public BTNode searchPreorder(String name) {
        // TODO: 行きがけ順で深さ優先探索する
        System.out.print(this);
        if (name.equals(this.label)) {
            return this;
        }
        // this.left 探索
        if (this.left != null) {
            BTNode hit = this.left.searchPreorder(name);
            if (hit != null) {
                return hit;
            }
        }
        // this.right 探索
        if (this.right != null) {
            BTNode hit = this.right.searchPreorder(name);
            if (hit != null) {
                return hit;
            }
        }
        return null;
    }

    public BTNode searchPostorder(String name) {
        // TODO: 帰りがけがけ順で深さ優先探索する
        // this.left 探索
        if (this.left != null) {
            BTNode hit = this.left.searchPostorder(name);
            if (hit != null) {
                return hit;
            }
        }
        // this.right 探索
        if (this.right != null) {
            BTNode hit = this.right.searchPostorder(name);
            if (hit != null) {
                return hit;
            }
        }
        System.out.print(this);
        if (name.equals(this.label)) {
            return this;
        }
        return null;
    }

    public String toString() {
        return "[" + label + "]";
    }

    public static void main(String[] args) {
        BTNode tree = new BTNode("A", new BTNode("B", new BTNode("C", null, null), new BTNode("E", null, null)),
                new BTNode("D", new BTNode("F", null, null), new BTNode("G", null, null)));
        System.out.println(tree.printNode());
        System.out.println("行きがけ順");
        for (String target : new String[] { "A", "B", "C", "D", "E", "F", "G", "Z" }) {
            System.out.println(target + "を探す");
            BTNode result = tree.searchPreorder(target);
            System.out.println(); // 改行
            System.out.println("あった? " + result);
        }
        System.out.println();
        System.out.println("帰りがけ順");
        for (String target : new String[] { "A", "B", "D", "Z" }) {
            System.out.println(target + "を探す");
            BTNode result = tree.searchPostorder(target);
            System.out.println(); // 改行
            System.out.println("あった? " + result);
        }
    }
}