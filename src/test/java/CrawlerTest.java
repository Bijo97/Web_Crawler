import org.jsoup.Jsoup;
import org.junit.Test;

public class CrawlerTest {
    @Test
    public void connectionShouldEstablished(){
        String url = "http://www.google.com";
        Jsoup.connect(url);
    }
}
