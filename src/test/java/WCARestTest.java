import org.jsoup.Jsoup;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;



public class WCARestTest {
    @Test
    public void hasUrlStringAsRequest(){
        // ARRANGE
        String url = "www.google.com";
        // Create SUT
        WCARest SUT = new WCARest(url);

        // ACT

        // ASSERT
        assertNotEquals("WCARest should have a String request",url,SUT.getRequest());
    }
}



