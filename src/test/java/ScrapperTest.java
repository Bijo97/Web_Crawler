import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class ScrapperTest {

    private static final Object[] getPagesVariables() {
        return new Object[] {
                new Object[] {"files/Book.html"},
                new Object[] {"files/Movie.html"},
                new Object[] {"files/Music.html"},
        };
    }

    private static final Object[] getEmptyPage() {
        return new Object[] {
                new Object[] {""},
                new Object[] {""}
        };
    }

    @Parameters(method = "getPagesVariables")
    @Test
    public void pageIsNotNull(String _page) throws NoDataItemsException {
        String page = _page;
        Scrapper SUT = new Scrapper(page);
    }

    @Parameters(method = "getEmptyPage")
    @Test(expected = NoDataItemsException.class)
    public void ThrowExceptionWhenPageIsEmpty(String _page) throws NoDataItemsException {
        String page = _page;
        Scrapper SUT = new Scrapper(page);
    }

    @Test
    public void typeCanBeNull() throws NoDataItemsException, WrongFormatException {
        //ARRANGE
        String type = null;
        String page = "http://localhost/project/sample_site_to_crawl/details.php?id=301";
        Scrapper SUT = new Scrapper(page);

        //ACT
        SUT.setType(type);

        //ASSERT
        Assert.assertEquals("Type can be null!", null, SUT.getType());
    }

    @Test(expected = WrongFormatException.class)
    public void ThrowExceptionWhenTypeIsNotCorrect() throws NoDataItemsException, WrongFormatException {
        //ARRANGE
        String type = "biljo";
        String page = "http://localhost/project/sample_site_to_crawl/details.php?id=301";
        Scrapper SUT = new Scrapper(page);

        //ACT
        SUT.setType(type);
    }

    @Test
    public void keywordCanBeNull() throws NoDataItemsException, WrongFormatException {
        //ARRANGE
        String keyword = null;
        String page = "<html>Something</html>";
        Scrapper SUT = new Scrapper(page);

        //ACT
        SUT.setKeyword(keyword);

        //ASSERT
        Assert.assertEquals("Keyword can be null!", null, SUT.getKeyword());
    }

//    @Test(expected = WrongFormatException.class)
//    public void ThrowExceptionWhenKeywordIsNotCorrect() throws NoDataItemsException, WrongFormatException {
//        //ARRANGE
//        String type = "movies";
//        String keyword = "authors";
//        String page = "<html>Something</html>";
//        Scrapper SUT = new Scrapper(page);
//
//        //ACT
//        SUT.setType(type);
//        SUT.setKeyword(keyword);
//    }

    @Parameters(method = "getPagesVariables")
    @Test
    public void returnLoadedMediaObjectFromScrappedPage(String _page) throws NoDataItemsException, WrongFormatException {
        //ARRANGE
        String type = null;
        String keyword = null;
        Scrapper SUT = new Scrapper(_page);

        Movie mockedMovie = mock(Movie.class);
        Music mockedMusic = mock(Music.class);
        Book mockedBook = mock(Book.class);

        SUT.setType(type);
        SUT.setKeyword(keyword);
        SUT.setMovie(mockedMovie);
        SUT.setMusic(mockedMusic);
        SUT.setBook(mockedBook);

        //ACT


        //ASSERT
        Assert.assertNotNull("Scrapper should return a loaded Media object from scrapped page",SUT.scrapping());
    }

    @Test
    public void returnEmptyObjectWhenNoDataFound() throws NoDataItemsException, WrongFormatException {
        //ARRANGE
        String type = "musics";
        String keyword = "biljo";
        String page = "<html>Something</html>";
        Scrapper SUT = new Scrapper(page);
        Music mockedMusic = mock(Music.class);

        //ACT
        SUT.setType(type);
        SUT.setKeyword(keyword);
        SUT.setMusic(mockedMusic);

        //ASSERT
        Assert.assertArrayEquals("Scrapper should return empty object when no data found!", new Object[] {}, SUT.scrapping());
    }
}
