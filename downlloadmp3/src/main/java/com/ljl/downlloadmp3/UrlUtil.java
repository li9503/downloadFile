package com.ljl.downlloadmp3;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author 李景磊
 * @Description
 * @Date 2019/3/2 22:58
 * @ModifiedBy：
 */
public class UrlUtil {
    public static Matcher getRes(String str, String start, String end) {
        Pattern HTML_REG = Pattern.compile(start + "(.*?)" + end);
        return HTML_REG.matcher(str);
    }

    public static Document getHtml(String url) {

        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(url), 10000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static String getDownloadUrl(String url) {
        String mp3Html = getHtml(url).toString();
        Matcher results = getRes(mp3Html, "<iframe", "iframe>");
        String result = "";
        while (results.find()) {
            result = results.group();
        }
        String iframe = result.substring(result.indexOf("src") + 5, result.indexOf("id=\"play\"") - 2).replace("amp;","");
        String mp3Url = getHtml("https://www.ysts8.com" + iframe).toString();
        String murl=mp3Url.substring(mp3Url.indexOf("murl",mp3Url.indexOf("preurl =")) , mp3Url.indexOf("'.mp3?';")+6);
        String uurl = mp3Url.substring(mp3Url.indexOf("'.mp3?';") + 10, mp3Url.indexOf("function next()") - 3);
        String mp3 = mp3Url.substring(mp3Url.indexOf("mp3:'"), mp3Url.indexOf("}).jPlayer(\"play\");") - 3);
        String[] murls=murl.replace(" = ","").split("'");
        String[] uurls=uurl.replace(" = ","").split("'");
        String res=mp3.replace("'","").replace("+","").replace(uurls[0],uurls[uurls.length-1]).replace(murls[0],murls[1]);
        return res.replace("mp3:","").replace("\n","");
    }
}
