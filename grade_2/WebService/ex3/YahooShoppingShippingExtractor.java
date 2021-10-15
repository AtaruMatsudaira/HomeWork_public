package com.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class YahooShoppingShippingExtractor {
    public static void main(String[] args) { // args[0]: URL
        if (args.length < 1) { // コマンドライン引数が使えなかった場合
            args = new String[1];
            args[0] = "https://store.shopping.yahoo.co.jp/amiami/figure-121656-s001.html?sc_i=shp_pc_search_itemlist_shsrg_title"; // ここになにかのURL
        }
        System.out.println(args[0]);
        try {
            URL url = new URL(args[0]);
            Webpage page = new Webpage(url);
            page.run();
        } catch (MalformedURLException e) {
            System.err.println("Wrong URL: " + args[0]);
        }
        String hoge = new Scanner(System.in).nextLine(); // これはVScodeでやるとき用 (提出するときは消す)
    }
}

class Webpage {
    private URL url;

    Webpage(URL url) {
        this.url = url;
    }

    private String rejectTag(String tag) { // タグを除去する関数
        String result = ""; // タグ以外を格納する変数
        boolean inTag = false; // forの中で今タグが何あるかを判定する trueならresultに今のcをたさない
        for (char c : tag.toCharArray()) {
            if (c == '<') { // タグのはじめ
                inTag = true;
            }
            if (inTag == false) { // タグじゃない時
                result += c;
            }
            if (c == '>') { // タグの終わり
                inTag = false;
            }
        }
        return result;
    }

    public void run() {
        String result = "送料の情報がありませんでした..."; // 結果 (何もなかった場合用にこれを入れている)
        try {
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            BufferedReader fin = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
            String line;
            while ((line = fin.readLine()) != null) { // 受信したHTMLを一行ずつループさせている
                if (line.indexOf("送料") >= 0) { // 送料という文字が含まれていた時
                    if (line.indexOf("elPostageText") >= 0) { // elPostageTextが含まれていた時 (HTMLの内部で使ってるclassだかid)
                        if (line.indexOf("span") >= 0) { // spanが含まれていた時
                            String matcher = rejectTag(line); // タグを削除
                            result = matcher.trim(); // 空白、Tabを削除
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("I/O Error: " + e.toString());
            result = "エラーでした";
        }
        System.out.println(result);
    }
}