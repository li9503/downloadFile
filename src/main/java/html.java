
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class html {
    public static void main(String[] args) {

        String html = getHtml("https://www.ysts8.com/Yshtml/Ys25829.html");
        Matcher results = getRes(html, "<li>", "</li>");
        while (results.find()) {
            String result = results.group();
            if (result.indexOf("play") != -1) {
                String title=result.substring(result.indexOf("[")+1,result.indexOf("]"));
                String url=result.substring(result.indexOf("/play"),result.indexOf("\" title"));
                System.out.println(title+"------------"+getMP3Url("https://www.ysts8.com/"+url));
            }
        }

    }

    public static Matcher getRes(String str, String start, String end) {
        Pattern HTML_REG = Pattern.compile(start + "(.*?)" + end);
        return HTML_REG.matcher(str);
    }

    public static String getHtml(String url) {

        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(url), 10000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc.toString();
    }

    public static String getMP3Url(String url) {
        String mp3Html = getHtml(url);
        Matcher results = getRes(mp3Html, "<iframe", "iframe>");
        String result = "";
        while (results.find()) {
            result = results.group();
        }
//        System.out.println(result);
        String iframe = result.substring(result.indexOf("src") + 5, result.indexOf("id=\"play\"") - 2).replace("amp;","");
//        System.out.println(iframe);
        String mp3Url = getHtml("https://www.ysts8.com" + iframe);
//        System.out.println(mp3Url);
        String murl=mp3Url.substring(mp3Url.indexOf("murl",mp3Url.indexOf("preurl =")) , mp3Url.indexOf("'.mp3?';")+6);
        String uurl = mp3Url.substring(mp3Url.indexOf("'.mp3?';") + 10, mp3Url.indexOf("function next()") - 3);
        String mp3 = mp3Url.substring(mp3Url.indexOf("mp3:'"), mp3Url.indexOf("}).jPlayer(\"play\");") - 3);
//        System.out.println(murl);
//        System.out.println(uurl);

//        System.out.println(code[code.length - 1]);
//        System.out.println(mp3);
//        System.out.println("处理后结果------------");
        String[] murls=murl.replace(" = ","").split("'");
        String[] uurls=uurl.replace(" = ","").split("'");
        String res=mp3.replace("'","").replace("+","").replace(uurls[0],uurls[uurls.length-1]).replace(murls[0],murls[1]);
//        System.out.println(res);
        return res.replace("mp3:","");
    }


}