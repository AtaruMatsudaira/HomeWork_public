package ex4;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.jsoup.*;
import org.jsoup.nodes.*;

public class YahooShoppingShippingExtractorIntValue {
    public static void main(String[] args) {
        for(var url : args){
            new WebPage(url);
        }    
    }
}

class Webpage { // implements Runnable
    private URL url;

    Webpage(URL url) {
        this.url = url;
    }

    public void run() {
        int result = -1;
        try {
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            BufferedReader fin = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
            String line;
            Pattern tagPattern = Pattern.compile("<.+?>");
            Pattern postagePattern_first = Pattern.compile(".+?送料");
            Pattern postagePattern_second = Pattern.compile("円.*?");
            while ((line = fin.readLine()) != null) {
                if (line.indexOf("送料") >= 0 && line.indexOf("elPostageText") >= 0 && line.indexOf("span") >= 0) {
                    if (line.indexOf("送料無料") < 0) {
                        String rejectTagLine = tagPattern.matcher(line).replaceAll("").trim();
                        String postage_first = postagePattern_first.matcher(rejectTagLine).replaceAll("");
                        String postage_second = postagePattern_second.matcher(postage_first).replaceAll("").trim();
                        try {
                            Number number = NumberFormat.getInstance().parse(postage_second);
                            result = number.intValue();
                        } catch (ParseException e) {
                            System.out.println(postage_second);
                            System.out.println(e);
                        }
                    } else {
                        result = 0;
                    }
                    break;
                }

            }
        } catch (IOException e) {
            System.err.println("I/O Error: " + e.toString());
            result = -1;
        }
        System.out.println(result >= 0 ? result + "円" : "送料の情報がありませんでした...");
    }
}
