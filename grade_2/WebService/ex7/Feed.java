package ex7;

import java.util.*;
import java.io.*;
import java.net.*;
import org.w3c.dom.*;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;
import javax.xml.xpath.*;

public class Feed {
    /**
     * url
     */
    private String urlString;
    /**
     * 取得したxmlのデータ (DOM)
     */
    private Document document;

    /**
     * ここで引数で受け取ったurlからDOMを構築する
     * 
     * @param urlString : URL
     */
    public Feed(String urlString) {
        this.urlString = urlString;
        // InputStreamの用意
        try {
            // 特定のサイトにアクセス
            URL url = new URL(this.urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();

            // DOM実装(implementation)の用意 (Load and Save用)
            DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            DOMImplementationLS implementation = (DOMImplementationLS) registry.getDOMImplementation("XML 1.0");
            // 読み込み対象の用意
            LSInput input = implementation.createLSInput();
            input.setByteStream(inputStream);
            input.setEncoding("UTF-8");
            // 構文解析器(parser)の用意
            LSParser parser = implementation.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);
            parser.getDomConfig().setParameter("namespaces", false);
            // DOMの構築
            this.document = parser.parse(input);
        } catch (Exception e) {

        }
    }

    /**
     * Feedから取得したItemを返す
     * 
     * @return ArrayList_Item : アイテムのリスト
     */
    public ArrayList<Item> getItemList() {
        // 戻り値用のリスト
        ArrayList<Item> itemList = new ArrayList<Item>();
        try {
            // XPathっていうDOMからデータを抽出できる変換器を生成
            XPath xPath = XPathFactory.newInstance().newXPath();

            // DOMを変換器にぶち込んで、NodeListっていう使いやすい形式に変換 (構造的にはリストみたいになってる)
            NodeList itemNodeList = (NodeList) xPath.evaluate("/rss/channel/item", document, XPathConstants.NODESET);

            // NodeListの中のものを一つ一つ解析している
            for (int i = 0; i < itemNodeList.getLength(); i++) {
                // titleという名前の要素を取得
                String title = xPath.evaluate("title", itemNodeList.item(i));
                // linkという名前の要素を取得
                String link = xPath.evaluate("link", itemNodeList.item(i));
                // descriptionという名前の要素を取得
                String description = xPath.evaluate("description", itemNodeList.item(i));

                // 取得したそれぞれの要素からItemを作成
                Item item = new Item(title, link, description);

                // 戻り値用のリストに格納
                itemList.add(item);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return itemList;
    }

    public static void main(String[] args) {
        // new String[] で使い捨て用の配列を用意している
        for (String url : new String[] { "http://www.gnavi.co.jp/gntop/rank/rss/trend_all.xml",
                "https://kyoko-np.net/index.xml" }) {
            Feed unko = new Feed(url);
            // 取得したそれぞれのitemのループを回して出力する
            for (Item item : unko.getItemList()) {
                System.out.println(item);
            }
        }
    }
}