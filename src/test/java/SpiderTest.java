import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnitParamsRunner.class)
public class SpiderTest {

    private static final Object[] getSpider(){
        return new Object[]{
                new Object[] {1,2},
                new Object[] {2,4}
        };
    }

    private static final Object[] getSpiderNull(){
        return new Object[]{
          new Object[] {null,1},
          new Object[]{1,null}
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
        Integer max_depth = 5;
        Integer max_url = 30;
        Spider spider = new Spider(max_depth,max_url);
        assertEquals(max_depth,spider.getMaxDepth());
    }

    @Test
    @Parameters(method = "getSpider")
    public void constructorShouldSetMaxDepthAndMaxUrl(Integer max_depth, Integer max_url) throws NoDataItemsException {
        Spider spider = new Spider(max_depth,max_url);

        assertEquals(max_depth,spider.getMaxDepth());
        assertEquals(max_url,spider.getMaxURL());
    }

    @Test(expected = NullPointerException.class)
    @Parameters(method = "getSpiderNull")
    public void constructorShouldNotSetMaxDepthAndMaxUrlIfNull(Integer max_depth, Integer max_url) throws NoDataItemsException {
        Spider spider = new Spider(max_depth, max_url);

    }

    @Test(expected = NoDataItemsException.class)
    @Parameters(method = "getSpider0")
    public void constructorShouldNotSetMaxDepthAndMaxUrlIf0(Integer max_depth, Integer max_url) throws NoDataItemsException {
        Spider spider = new Spider(max_depth,max_url);
    }

}
