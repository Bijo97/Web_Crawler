import org.junit.Assert;
import org.junit.Test;


public class MediaTest {
    @Test
    public void shouldHaveGenreFormatYearAndName() throws NoDataItemsException, WrongFormatException {
        //ARRANGE
        String genre = "comedy";
        String format = "Blu-ray";
        String year = "1999";
        String name = "Office Space";

        //ACT
        Media media = new Media(genre,format,year,name);

        //ASSERT
        Assert.assertNotNull(media.getGenre());
        Assert.assertNotNull(media.getFormat());
        Assert.assertNotNull(media.getYear());
        Assert.assertNotNull(media.getName());
    }

    @Test (expected = NoDataItemsException.class)
    public void genreFormatYearAndNameAreNotEmpty() throws NoDataItemsException, WrongFormatException {
        //ARRANGE
        String genre = "comedy";
        String format = "Blu-ray";
        String year = "  ";
        String name = "Office Space";

        //ACT
        Media media = new Media(genre,format,year,name);

        //ASSERT
    }

    @Test (expected = WrongFormatException.class)
    public void yearShouldBeStringOf4Number() throws NoDataItemsException, WrongFormatException {
        //ARRANGE
        String genre = "comedy";
        String format = "Blu-ray";
        String year = "1997a";
        String name = "Office Space";

        //ACT
        Media media = new Media(genre,format,year,name);

        //ASSERT
    }
}
