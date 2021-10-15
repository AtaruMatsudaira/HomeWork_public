package ex5;

import org.jsoup.*;
import org.jsoup.nodes.*;

public class YahooShoppingItem {
    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect("http://www.google.co.jp").get();
            System.out.println(document.html());
        } catch (Exception e) {
        }
        System.out.println("んこ");
    }
}
