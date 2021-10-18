package ex5;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class YahooShoppingItem {
    private String title;
    private int price;
    private int shipping;

    public YahooShoppingItem(String url) {
        fetch(url);
    }

    private void fetch(String url) {
        try {
            Document document = Jsoup.connect(url).get();

            Elements titelElements = document.select("#shpMain>div>div>div>.elName");
            for (Element element : titelElements) {
                this.title = element.text();
                break;
            }

            Elements priceElements = document.select(".elPriceNumber");
            for (Element element : priceElements) {
                this.price = NumberFormat.getInstance().parse(element.text()).intValue();
                break;
            }

            Elements shippingElements = document.select(".elPostageValue");
            for (Element element : shippingElements) {
                String shippingText = element.text();
                if (shippingText.contains("送料無料")) {
                    this.shipping = 0;
                } else {
                    shippingText = shippingText.replaceAll("[^0-9]", "");
                    this.shipping = NumberFormat.getInstance().parse(shippingText).intValue();
                }
                break;
            }
        } catch (IOException e) {
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(this);
    }

    public String toString() {
        return "商品名 : " + this.title + " 値段 : " + this.price + " 送料 : " + this.shipping;
    }

    public static void main(String[] args) {

        for (String url : args) {
            new YahooShoppingItem(url);
        }
        String[] urls = new String[] { "https://store.shopping.yahoo.co.jp/akozushop/a-b08hn5b8fj-20210917.html",
                "https://store.shopping.yahoo.co.jp/kobecrab/10019138.html",
                "https://store.shopping.yahoo.co.jp/shokufukutei/0116.html" };
        for (String url : urls) {
            new YahooShoppingItem(url);
        }
    }
}
