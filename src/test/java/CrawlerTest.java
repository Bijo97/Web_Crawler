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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class CrawlerTest{
    @Test
    public void shouldHasBaseAddress() throws NoDataItemsException, MalformedURLException, URISyntaxException {
        // ARRANGE
        String base_address = "http://www.google.com";

        // Create SUT
        Crawler SUT = new Crawler(base_address);

        // ACT

        // ASSERT
        assertEquals("Should has correct base address", base_address, SUT.getBaseAddress());
    }

    @Test(expected = NoDataItemsException.class)
    public void baseAddressIsNotEmpty() throws NoDataItemsException, MalformedURLException, URISyntaxException {
        String base_address = "";
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
