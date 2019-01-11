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
                new Object[] {"http://localhost/project/sample_site_to_crawl/details.php?id=301"},
                new Object[] {""},
                new Object[] {null}
        };
    }

    @Parameters(method = "getPagesVariables")
    @Test
    public void pageIsNotNull(String _page) throws NoDataItemsException {
        String page = _page;
        Scrapper SUT = new Scrapper(page);

    }

    @Parameters(method = "getPagesVariables")
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

    @Test
    public void returnAllFoundDataWhenTypeAndKeywordAreNull() throws NoDataItemsException, WrongFormatException {
        //ARRANGE
        String type = null;
        String keyword = null;
        String page = "http://localhost/project/sample_site_to_crawl/details.php?id=203";
        Scrapper SUT = new Scrapper(page);

//        String genre = "Comedy";
//        String format = "Blu-ray";
//        String year = "1997";
//        String name = "Office Space";
//        Media media = new Media(genre,format,year,name);
//        String director = "Mike Judge";
//        List<String> writers = new ArrayList<>();
//        List<String> stars = new ArrayList<>();]]]]]]]]]]]]]]]]]]]]]
//        Movie movie = new Movie(media, director, writers, stars);
//
//        genre = "Classic";
//        format = "CD";
//        year = "2012";
//        name = "Beethoven: Complete Symphonies";
//        media = new Media(genre,format,year,name);
//        String artist = "Ludwig van Beethoven";
//        Music music = new Music(media, artist);
//
//        genre = "Tech";
//        format = "Audio";
//        year = "2011";
//        name = "The Clean Coder: A Code of Conduct for Professional Programmers";
//        media = new Media(genre,format,year,name);
//        List<String> authors = new ArrayList<>();
//        String publisher = "Prentice Hall";
//        String isbn = "007-60920469811";
//        Book book = new Book(media, authors, publisher, isbn);

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
        Assert.assertArrayEquals("Scrapper should return all data when type and keyword are null!", new Object[] {mockedMovie, mockedMusic, mockedBook}, SUT.scrapping());
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
