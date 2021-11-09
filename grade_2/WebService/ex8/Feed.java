package ex8;

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
    public class Item {
        private String title;
        private String link;
        private String description;

        public Item(String title, String link, String description) {
            this.title = title;
            this.link = link;
            this.description = description;
        }

        public String toString() {
            return "title : " + title + "\nlink : " + link + "\ndescription : " + description;
        }
    }

    private String urlString;
    private Document document;

    public Feed(String urlString) {
        this.urlString = urlString;
        try {
            URL url = new URL(this.urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            DOMImplementationLS implementation = (DOMImplementationLS) registry.getDOMImplementation("XML 1.0");
            LSInput input = implementation.createLSInput();
            input.setByteStream(inputStream);
            input.setEncoding("UTF-8");
            LSParser parser = implementation.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);
            parser.getDomConfig().setParameter("namespaces", false);
            this.document = parser.parse(input);
        } catch (Exception e) {

        }
    }

    public ArrayList<Item> getItemList() {
        ArrayList<Item> itemList = new ArrayList<Item>();
        try {
            XPath xPath = XPathFactory.newInstance().newXPath();

            NodeList itemNodeList = (NodeList) xPath.evaluate("/rss/channel/item", document, XPathConstants.NODESET);

            for (int i = 0; i < itemNodeList.getLength(); i++) {
                String title = xPath.evaluate("title", itemNodeList.item(i));
                String link = xPath.evaluate("link", itemNodeList.item(i));
                String description = xPath.evaluate("description", itemNodeList.item(i));
                Item item = new Item(title, link, description);
                itemList.add(item);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return itemList;
    }

    public static void main(String[] args) {
        for (String url : new String[] { "https://www.nicovideo.jp/newarrival?rss=2.0&lang=ja-jp" }) {
            Feed feed = new Feed(url);
            for (Item item : feed.getItemList()) {
                System.out.println(item);
            }
        }
    }
}