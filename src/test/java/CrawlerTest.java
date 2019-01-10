import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Test;

public class CrawlerTest {
    @Test
    public void connectionShouldEstablished(){
        String url = "http://www.google.com";
        Jsoup.connect(url);
    }

    @Test
    public void shouldHasBaseAddress() throws NoDataItemsException {
        String baseAddress = "http://www.google.com";
        Crawler crawler = new Crawler(baseAddress);
        Assert.assertNotNull(crawler.getBaseAddress());
    }

    @Test(expected = NoDataItemsException.class)
    public void baseAddressIsNotEmpty() throws NoDataItemsException {
        String baseAddress = "";
        Crawler crawler = new Crawler(baseAddress);
    }


}
