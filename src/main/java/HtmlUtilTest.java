import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebConsole;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Created by xuyh at 2017/11/6 14:03.
 */
public class HtmlUtilTest {



    public static void main(String[] args) {
        HtmlUtilTest htmlUtilTest=new HtmlUtilTest();
        htmlUtilTest.test();
    }

    public void test() {
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);//新建一个模拟谷歌Chrome浏览器的浏览器客户端对象
        WebConsole.Logger logger = null;
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
        HtmlPage page = null;
        try {
            page = webClient.getPage("https://www.ysts8.com/play/flw.asp?url=%D0%FE%BB%C3%D0%A1%CB%B5%2F%CE%D2%D4%DA%C4%A9%CA%C0%D3%D0%CC%D7%B7%BF%2F007%2Emp3&jiidx=/play%5F25826%5F49%5F1%5F8%2Ehtml&jiids=/play%5F25826%5F49%5F1%5F6%2Ehtml&id=25826&ji=7&said=49");//尝试加载上面图片例子给出的网页
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }

        webClient.waitForBackgroundJavaScript(30000);//异步JS执行需要耗时,所以这里线程要阻塞30秒,等待异步JS执行结束
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------");
        String pageXml = page.asXml();//直接将加载完成的页面转换成xml格式的字符串
        //TODO 下面的代码就是对字符串的操作了,常规的爬虫操作,用到了比较好用的Jsoup库
        System.out.println();
        webClient.close();
        Document document = Jsoup.parse(pageXml);//获取html文档
        System.out.println(document.toString());
        List<Element> infoListEle = document.getElementsByAttributeValue("class", "toolbox");//获取元素节点等
        infoListEle.forEach(element -> {
            /*System.out.println(element.getElementsByClass("current")
                    .first().getElementsByClass("playc")
                    .first().getElementById("play")
                    .getElementsByAttribute("src").toString());*/
          /*  System.out.println(element.getElementsByTag("h2").first().getElementsByTag("a").text());
            System.out.println(element.getElementsByTag("h2").first().getElementsByTag("a").attr("href"));*/
        });
    }
}
