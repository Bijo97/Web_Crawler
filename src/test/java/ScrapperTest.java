import org.junit.Assert;
import org.junit.Test;

public class ScrapperTest {
    @Test
    public void pageIsNotNull() throws NoDataItemsException {
        String page = "<html>Something</html>";
        Scrapper SUT = new Scrapper(page);
    }

    @Test(expected = NoDataItemsException.class)
    public void ThrowExceptionWhenPageISEmpty() throws NoDataItemsException {
        String page = "";
        Scrapper SUT = new Scrapper(page);
    }

    @Test
    public void typeCanBeNull() throws NoDataItemsException{
        //ARRANGE
        String type = null;
        String page = "<html>Something</html>";
        Scrapper SUT = new Scrapper(page);

        //ACT
        SUT.setType(type);

        //ASSERT
        Assert.assertEquals("Type can be null!", null, SUT.getType());
    }

    @Test(expected = WrongFormatException.class)
    public void ThrowExceptionWhenTypeIsNotCorrect() throws NoDataItemsException {
        //ARRANGE
        String type = null;
        String page = "<html>Something</html>";
        Scrapper SUT = new Scrapper(page);

        //ACT
        SUT.setType(type);
    }
}
