import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class WCARestTest {
    private static final Object[] getURLVariables() {
        return new Object[] {
                new Object[] {"www.google.com"},
                new Object[] {"www.yahoo.com"},
                new Object[] {"www.bing.com"},
        };
    }

    @Parameters(method = "getURLVariables")
    @Test
    public void hasUrlStringAsRequest(String _url) throws NoDataItemsException {
        // ARRANGE
        String url = _url;
        // Create SUT
        WCARest SUT = new WCARest(url);

        // ACT

        // ASSERT
        assertEquals("WCARest should have a String request",url,SUT.getRequest());
    }

    @Test(expected = NoDataItemsException.class)
    public void ThrowExceptionWhenURLStringIsNullOrEmpty() throws NoDataItemsException {
        // ARRANGE
        String url = "";
        // Create SUT
        WCARest SUT = new WCARest(url);

        // ACT

        // ASSERT
    }

    @Test
    public void urlCouldContainTypeAndKeyword() throws NoDataItemsException {
        // ARRANGE
        String url = "www.google.com try out";
        // Create SUT
        WCARest SUT = new WCARest(url);

        // ACT

        // ASSERT
        assertEquals("WCARest URL could contain type and keyword",url,SUT.getUrl() + " " + SUT.getType() + " " + SUT.getKeyword());
    }

    @Test
    public void shouldReturnJSONStringAsResponse() throws NoDataItemsException {
        // ARRANGE
        String url = "www.google.com";

        Crawler mockedCrawler = mock(Crawler.class);
        when(mockedCrawler.getOutput()).thenReturn("{\"id\": 4, \"time_elapsed\": \"2s\", \"pages_explored\": 5, \"search_depth\":3}");

        // Create SUT
        WCARest SUT = new WCARest(url);

        // ACT
        SUT.setResponse(mockedCrawler);

        // ASSERT
        verify(mockedCrawler).setBaseAddress(url);
        verify(mockedCrawler).getOutput();
//        assertEquals("Should return JSON string as response", mockedCrawler.getOutput(), SUT.getResponse());
    }

    @Test
    public void JSONStringOutputCouldBeEmpty() throws NoDataItemsException {
        // ARRANGE
        String url = "www.google.com";

        Crawler mockedCrawler = mock(Crawler.class);
        when(mockedCrawler.getOutput()).thenReturn("{}");

        // Create SUT
        WCARest SUT = new WCARest(url);

        // ACT
        SUT.setResponse(mockedCrawler);

        // ASSERT
        verify(mockedCrawler).setBaseAddress(url);
        verify(mockedCrawler).getOutput();
//        assertEquals("Should return JSON string as response", mockedCrawler.getOutput(), SUT.getResponse());
    }
}



