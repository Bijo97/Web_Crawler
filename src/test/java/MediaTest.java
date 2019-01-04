import org.junit.Assert;
import org.junit.Test;

import java.security.MessageDigest;


public class MediaTest {
    @Test
    public void shouldHaveGenreFormatYearAndName() throws EmptyMediaException {
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

    @Test (expected = EmptyMediaException.class)
    public void genreFormatYearAndNameAreNotEmpty() throws EmptyMediaException {
        String genre = "comedy";
        String format = "Blu-ray";
        String year = "  ";
        String name = "Office Space";

        Media media = new Media(genre,format,year,name);
    }
}
