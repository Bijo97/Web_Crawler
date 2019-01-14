import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class SpiderTest {
    private int MAX_DEPTH = 5;
    private int MAX_URL = 30;
    private String url ="http://localhost/tci/";
    private int depth = 1;
    private Set<URL> urls = new HashSet<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

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
    public void canSubmitNewUrlToSpiderLegTaskListUsingUrlAndDepth() throws NoDataItemsException, MalformedURLException {
        URL urll = new URL(url);

        SpiderLeg mockSpiderLeg = mock(SpiderLeg.class);
        Spider spider = new Spider(MAX_DEPTH,MAX_URL);
        spider.setSpiderLeg(mockSpiderLeg);
        spider.submitNewURL(urll,depth);

        when(mockSpiderLeg.getDepth()).thenReturn(depth);
        when(mockSpiderLeg.getURL()).thenReturn(urll);

        Assert.assertEquals(url, mockSpiderLeg.getURL());
        Assert.assertEquals(depth, mockSpiderLeg.getDepth());
    }

    @Test
    public void canSubmitNewUrlToListIfUrlIsUnvisited() throws NoDataItemsException, MalformedURLException {
        URL urll = new URL(url);

        Spider spider = new Spider(MAX_DEPTH,MAX_URL);
        spider.submitNewURL(urll,depth);

        Assert.assertEquals(1,spider.countList());
    }

    @Test
    public void returnFalseIfURLisAlreadyVisitedIfContainsByMasterList() throws NoDataItemsException, MalformedURLException {
        URL urll = new URL(url);

        Spider spider = new Spider(MAX_DEPTH,MAX_URL);
        spider.submitNewURL(urll,depth);
        Assert.assertFalse(spider.shouldVisit(urll,depth));
    }

    @Test
    public void canAddNewURLsUsingSpiderLegUrlList() throws NoDataItemsException {
        Spider spider = new Spider(MAX_DEPTH,MAX_URL);
        SpiderLeg mockedSpiderLeg = mock(SpiderLeg.class);

        spider.addNewURLs(mockedSpiderLeg);

        verify(mockedSpiderLeg).getUrlList();
    }

    @Test
    public void canAddSpiderLegTasksToExecutorWhenSubmitNewUnvisitedURL() throws NoDataItemsException, MalformedURLException {
        Spider spider = new Spider(MAX_DEPTH,MAX_URL);
        URL urll = new URL(url);
        spider.submitNewURL(urll,depth);
        Assert.assertEquals(1,spider.getFuturesSize());
    }

    @Test
    public void spiderLegTasksIs0AfterCheckPageGrab() throws NoDataItemsException {
        Spider spider = new Spider(MAX_DEPTH, MAX_URL);
        SpiderLeg spiderLeg = mock(SpiderLeg.class);
        Assert.assertFalse(spider.checkPageGrab());
        Assert.assertEquals(0,spider.getFuturesSize());
    }


}
