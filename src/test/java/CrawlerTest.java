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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.atLeast;

public class CrawlerTest{
    @Test
    public void shouldHaveBaseAddress() throws NoDataItemsException, MalformedURLException, URISyntaxException {
        // ARRANGE
        String base_address = "http://www.google.com";

        // Create SUT
        Crawler SUT = new Crawler(base_address);

        // ACT

        // ASSERT
        assertEquals("Should have correct base address", base_address, SUT.getBaseAddress());
    }

    @Test
    public void scrapperScrapMethodIsCalledAsManyAsPagesFound() throws NoDataItemsException, MalformedURLException, URISyntaxException {
        // ARRANGE
        String base_address = "http://www.google.com";
        // Create mockScrapper
        Scrapper mockScrapper = mock(Scrapper.class);
        //Specify behaviours

        // Create SUT
        Crawler SUT = new Crawler(base_address);

        // ACT
        SUT.setScrapper(mockScrapper);
        SUT.run();
        int pagesFound = SUT.getPagesFound();

        // ASSERT
        verify(mockScrapper, atLeast(pagesFound)).scrapping();
    }

    @Test(expected = MalformedURLException.class)
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
