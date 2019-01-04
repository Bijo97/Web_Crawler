import org.junit.Assert;
import org.junit.Test;

import java.security.MessageDigest;


public class MediaTest {
    @Test
    public void shouldHaveGenreFormatYearAndName(){
        String genre = "comedy";
        String format = "Blu-ray";
        String year = "";
        String name = "Office Space";

        Media media = new Media(genre,format,year,name);

        Assert.assertNotNull(media.getGenre());
        Assert.assertNotNull(media.getFormat());
        Assert.assertNotNull(media.getYear());
        Assert.assertNotNull(media.getName());
    }


}
