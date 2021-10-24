package ex2;

import java.util.ArrayList;
import java.util.Collections;

public class SortScoreComparable {
    public static void main(String[] args) {
        ArrayList<Score> score = new ArrayList<Score>();

        score.add(new Score("taro", 12, 97));
        score.add(new Score("jiro", 42, 54));
        score.add(new Score("sabu", 42, 47));
        score.add(new Score("siro", 57, 97));
        score.add(new Score("goro", 87, 40));
        score.add(new Score("roku", 99, 99));
        score.add(new Score("nana", 14, 23));
        score.add(new Score("hati", 42, 54));

        System.out.println("ソート前");
        for (Score s : score) {
            System.out.println(s.getName() + " : Math: " + s.getMath() + " : English : " + s.getEnglish());
        }

        Collections.sort(score);

        System.out.println("ソート後");
        for (Score s : score) {
            System.out.println(s.getName() + " : Math: " + s.getMath() + " : English : " + s.getEnglish());
        }
    }
}

class Score implements Comparable<Score> {
    private String name;
    private int math;
    private int english;

    public Score(String name, int math, int english) {
        this.name = name;
        this.math = math;
        this.english = english;
    }

    public String getName() {
        return name;
    }

    public int getMath() {
        return math;
    }

    public int getEnglish() {
        return english;
    }

    public int compareTo(Score another) {
        // ここを作る
        // 順序付けは以下の通り
        // 1. 数学が良い順とする
        // 2. 数学が同点の場合は英語が良い順
        // 3. それでも順位が付かない場合は同位とする
        var mine = this;
        if (mine.math != another.math) { // 数学で比較時
            if (mine.math > another.math)
                return -1;
            else
                return 1;
        } else if (mine.english != another.english) { // 英語の点数で比較時
            if (mine.english > another.english)
                return -1;
            else
                return 1;
        } else {
            return 0;
        }

    }
}