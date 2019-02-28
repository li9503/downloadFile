import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class html {
    public static void main(String[] args) {
for(int i=0;i<10;i++) {
    String mp3Html = getMP3Url("https://www.ysts8.com/play_25831_51_1_5.html");
}
//        System.out.println(mp3Html);
       /* String html = getHtml("https://www.ysts8.com/Yshtml/Ys25829.html");
        Matcher results = getRes(html, "<li>", "</li>");
        while (results.find()) {
            String result = results.group();
            if (result.indexOf("play") != -1) {
                System.out.println(result);
            }

        }*/

    }

    public static Matcher getRes(String str, String start, String end) {
        Pattern HTML_REG = Pattern.compile(start + "(.*?)" + end);
        return HTML_REG.matcher(str);
    }

    public static String getHtml(String url) {

        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(url), 5000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc.toString();
    }

    public static String getMP3Url(String url){
        String mp3Html=getHtml(url);
       Matcher results= getRes(mp3Html,"<iframe","iframe>");
        String result="";
        while (results.find()) {
             result = results.group();
        }
        String iframe=result.substring(result.indexOf("src")+5,result.indexOf("id=")-1) ;
        String mp3Url=  getHtml("https://www.ysts8.com"+iframe);
        String d1=mp3Url.substring(mp3Url.indexOf("'.mp3?';")+9,mp3Url.indexOf("function next()")-3);
        String d2=mp3Url.substring(mp3Url.indexOf("mp3:'"),mp3Url.indexOf("}).jPlayer(\"play\");")-3);
String[] code=d1.split("'");
        System.out.println(d1);
        System.out.println(code[code.length-1]);
        System.out.println(d2);

        return mp3Url;
    }


}