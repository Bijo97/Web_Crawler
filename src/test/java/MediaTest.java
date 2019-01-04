import org.junit.Assert;
import org.junit.Test;


public class MediaTest {
    @Test
    public void shouldHaveGenreFormatYearAndName() throws NoDataItemsException, WrongFormatException {
        String genre = "comedy";
        String format = "Blu-ray";
        String year = "1999";
        String name = "Office Space";

        Media media = new Media(genre,format,year,name);

        Assert.assertNotNull(media.getGenre());
        Assert.assertNotNull(media.getFormat());
        Assert.assertNotNull(media.getYear());
        Assert.assertNotNull(media.getName());
    }

    @Test (expected = NoDataItemsException.class)
    public void genreFormatYearAndNameAreNotEmpty() throws NoDataItemsException, WrongFormatException {
        String genre = "comedy";
        String format = "Blu-ray";
        String year = "  ";
        String name = "Office Space";

        Media media = new Media(genre,format,year,name);
    }

    @Test (expected = WrongFormatException.class)
    public void yearShouldBeStringOf4Number() throws NoDataItemsException, WrongFormatException {
        String genre = "comedy";
        String format = "Blu-ray";
        String year = "1997a";
        String name = "Office Space";

        Media media = new Media(genre,format,year,name);
    }
}
