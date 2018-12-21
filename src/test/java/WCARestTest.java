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
        assertEquals("WCARest should have a String request",url,SUT.getRequest());
    }

//    @Test
////    public void urlContainsBaseAddress(){
////        // ARRANGE
////        String url = "www.google.com";
////        // Create SUT
////        WCARest SUT = new WCARest(url);
////
////        // ACT
////
////        // ASSERT
////        assertEquals("WCARest URL should contain base address",url,SUT.getRequest());
////    }

    @Test
    public void urlCouldContainTypeAndKeyword(){
        // ARRANGE
        String url = "www.google.com try out";
        // Create SUT
        WCARest SUT = new WCARest(url);

        // ACT

        // ASSERT
        assertEquals("WCARest URL could contain type and keyword",url,SUT.getUrl() + " " + SUT.getType() + " " + SUT.getKeyword());
    }
}



