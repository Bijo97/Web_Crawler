import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.jsoup.nodes.Document;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.atLeast;

public class CrawlerTest{

    @Rule
    public Timeout globalTimeout = new Timeout(900);

    private static String watchedLog;
    @Rule
    public TestWatcher watchman= new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println(description + " " + "failed!\n");
        }

        @Override
        protected void succeeded(Description description) {
            System.out.println(description + " " + "succeeded!\n");
        }
    };

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
        Object[] _object = new Object[]{"default","dummy"};
        when(mockScrapper.scrapping()).thenReturn(_object);

        // Create mockConverter
        Converter mockConverter = mock(Converter.class);
        //Specify behaviours
        when(mockConverter.convertMedia(any(Media.class))).thenReturn("DefaultJSON");


        // Create SUT
        Crawler SUT = new Crawler(base_address);

        // ACT
        SUT.setScrapper(mockScrapper);
        SUT.setConverter(mockConverter);
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

        // Create mockConverter
        Converter mockConverter = mock(Converter.class);
        //Specify behaviours
        when(mockConverter.convertMedia(any(Media.class))).thenReturn("DefaultJSON");


        // Create SUT
        Crawler SUT = new Crawler(base_address);

        // ACT
        SUT.setScrapper(mockScrapper);
        SUT.setConverter(mockConverter);
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
    public void converterConvertMetadataMethodIsCalledWhenConvertingMetadata() throws URISyntaxException, NoDataItemsException, MalformedURLException {
        // ARRANGE
        String base_address = "files/Catalog.html";

        // Create metadata parameters
        String _strategy = "DFS";
        int _numberOfPage = 5;
        int _timeElapsed = 600;
        int _searchDepth = 3;

        // Create mockConverter
        Converter mockConverter = mock(Converter.class);
        //Specify behaviours

        // Create SUT
        Crawler SUT = new Crawler(base_address);

        // ACT
        SUT.setConverter(mockConverter);
        SUT.convertMetadata(_strategy,_numberOfPage,_timeElapsed,_searchDepth);

        // ASSERT
        verify(mockConverter).convertMetadata(_strategy,_numberOfPage,_timeElapsed,_searchDepth);
    }
}