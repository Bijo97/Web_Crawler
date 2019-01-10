import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CrawlerTest{
    @Test
    public void connectionShouldEstablished(){
        String url = "http://www.google.com";
        Jsoup.connect(url);
    }

    @Test
    public void shouldHasBaseAddress() throws NoDataItemsException, MalformedURLException, URISyntaxException {
        String base_address = "http://www.google.com";
        Crawler crawler = new Crawler(base_address);
        Assert.assertNotNull(crawler.getBaseAddress());
    }

    @Test(expected = NoDataItemsException.class)
    public void baseAddressIsNotEmpty() throws NoDataItemsException, MalformedURLException, URISyntaxException {
        String base_address = "";
        Crawler crawler = new Crawler(base_address);
    }


    @Test
    public void baseAddressSyntaxIsCorrect() throws MalformedURLException, URISyntaxException, NoDataItemsException {
        String base_address = "http://www.google.com";
        Crawler crawler = new Crawler(base_address);

    }

    @Test
    public void couldExtractURLsUsingBaseAddress() throws URISyntaxException, NoDataItemsException, IOException {
        String base_address = "http://localhost/tci/";
        Crawler crawler = new Crawler(base_address);
        List<String> urls = new ArrayList<>();
        Assert.assertTrue(crawler.visitPage(base_address));

    }


}
