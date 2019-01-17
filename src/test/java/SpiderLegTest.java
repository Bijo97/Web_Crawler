import org.junit.Test;

import java.net.URL;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SpiderLegTest {
    @Test
    public void spiderLegIsCallable(){
        Spider mockSpider = mock(Spider.class);
        SpiderLeg spiderLeg = new SpiderLeg(mockSpider);
//        when(mockSpider.getAddress()).thenReturn("http://localhost/tci");

    }
}
