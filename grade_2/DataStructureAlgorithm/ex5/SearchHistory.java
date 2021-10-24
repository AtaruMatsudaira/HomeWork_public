package ex5;

import java.util.*;

public class SearchHistory extends ArrayDeque<String> {
    private int maxSize;

    public SearchHistory(int maxSize) {
        // TODO: 初期化
        super(maxSize);
        this.maxSize = maxSize;
    }

    /**
     * word を追加する。空文字の場合は追加しない。 すでに入っていた文字は入れ直す。 maxSize を超えた分古いデータは削除
     *
     * @param word
     * @return 追加したか否か
     */
    @Override
    public boolean add(String word) {
        // TODO: 空文字は保存せず false を返す
        // TODO: すでに入っている文字は入れ直す
        // TODO: maxSize を超えた分古いデータは削除
        if (!word.equals("")) {
            if (contains(word)) {
                remove(word);
            }
            if (size() >= maxSize) {
                remove();
            }
            return super.add(word);
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Queue<String> queue = new SearchHistory(3);

        String[] orders = new String[] { "Java", "Python", "", "", "JavaScript", "Go", "Swift", "Go", "Rust" };
        for (String s : orders) {
            boolean added = queue.add(s);
            System.out.println("add '" + s + "'");
            if (added) {
                System.out.println(" => " + queue);
            }
        }
    }
}