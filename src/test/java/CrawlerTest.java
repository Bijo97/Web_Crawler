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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.atLeast;

public class CrawlerTest{


    @Test
    public void shouldHaveBaseAddress() throws NoDataItemsException, MalformedURLException, URISyntaxException {
        // ARRANGE
        String base_address = "files/Catalog.html";

        // Create SUT
        Crawler SUT = new Crawler(base_address);

        // ACT

        // ASSERT
        assertEquals("Should have correct base address", base_address, SUT.getBaseAddress());
    }

    @Test(expected = NoDataItemsException.class)
    public void ifBaseAddressIsEmptyThrowException() throws NoDataItemsException, MalformedURLException, URISyntaxException {
        //ARRANGE
        String base_address = "";

        // Create SUT
        Crawler SUT = new Crawler(base_address);

        //ACT

        //ASSERT
    }

    @Test
    public void couldExtractPageLinksFromBaseAddress() throws URISyntaxException, NoDataItemsException, IOException {
        // ARRANGE
        String base_address = "files/Catalog.html";
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
        assertTrue("Page links found should be at least 1",pagesFound >= 1);
    }

    @Test
    public void scrapperScrapMethodIsCalledAsManyAsPagesFound() throws NoDataItemsException, MalformedURLException, URISyntaxException {
        // ARRANGE
        String base_address = "files/Catalog.html";
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

    @Test
    public void converterConvertMediaMethodIsCalledWhenConvertingMedia() throws URISyntaxException, NoDataItemsException, MalformedURLException {
        // ARRANGE
        String base_address = "files/Catalog.html";

        // Create mockMedia
        Media mockMedia = mock(Media.class);
        //Specify behaviours

        // Create mockConverter
        Converter mockConverter = mock(Converter.class);
        //Specify behaviours

        // Create SUT
        Crawler SUT = new Crawler(base_address);

        // ACT
        SUT.setConverter(mockConverter);
        SUT.convertMedia(mockMedia);

        // ASSERT
        verify(mockConverter).convertMedia(mockMedia);
    }

    @Test
    public void sdf(){}
}
