import org.jsoup.Jsoup;
import org.junit.Test;

public class CrawlerTest {
    @Test
    public void connectionShouldEstablished(){
        Jsoup.connect();
    }
}
