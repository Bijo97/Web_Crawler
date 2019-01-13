import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import sun.security.provider.ConfigFile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class SpiderTest {
    private int MAX_DEPTH = 5;
    private int MAX_URL = 30;
    private static final Object[] getSpider(){
        return new Object[]{
                new Object[] {1,2},
                new Object[] {2,4}
        };
    }

    private static final Object[] getSpiderIAE(){
        return new Object[]{
          new Object[] {null,1},
          new Object[]{1,null},
          new Object[] {-1,1},
                new Object[] {1,-1}
        };
    }

    private static final Object[] getSpider0(){
        return new Object[]{
                new Object[] {0,1},
                new Object[]{1,0}
        };
    }


    @Test
    public void shouldHaveMaxDepthAndMaxUrls() throws NoDataItemsException {
        Spider spider = new Spider(MAX_DEPTH,MAX_DEPTH);
        assertEquals(MAX_DEPTH,spider.getMaxDepth());
    }

    @Test
    @Parameters(method = "getSpider")
    public void constructorShouldSetMaxDepthAndMaxUrl(int max_depth, int max_url) throws NoDataItemsException {
        Spider spider = new Spider(max_depth,max_url);

        assertEquals(max_depth,spider.getMaxDepth());
        assertEquals(max_url,spider.getMaxURL());
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getSpiderIAE")
    public void constructorShouldThrowIAEIfMaxDepthAndMaxUrlIsNullOrBelow0(int max_depth, int max_url) throws NoDataItemsException {
        Spider spider = new Spider(max_depth, max_url);

    }

    @Test(expected = NoDataItemsException.class)
    @Parameters(method = "getSpider0")
    public void constructorShouldThrowNDIEIfMaxDepthAndMaxUrlIs0(int max_depth, int max_url) throws NoDataItemsException {
        Spider spider = new Spider(max_depth,max_url);
    }

    @Test
    public void getBaseAddressFromCrawlerBeforeDoingFunctionRun() throws NoDataItemsException {
        Crawler crawler = mock(Crawler.class);
        when(crawler.getBaseAddress()).thenReturn("http://localhost/tci/");
        Spider spider = new Spider(MAX_DEPTH,MAX_URL);

    }

    @Test
    public void canSubmitNewUrlIfUrlIsUnvisited() throws NoDataItemsException {
        Spider spider = new Spider(MAX_DEPTH,MAX_URL);

    }



}
