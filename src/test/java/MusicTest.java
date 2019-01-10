import org.junit.Assert;
import org.junit.Test;

public class MusicTest {
    String genre = "Classic";
    String format = "CD";
    String year = "2012";
    String name = "Beethoven: Complete Symphonies";

    Media media;
    @Test
    public void shouldHaveArtist() throws WrongFormatException, NoDataItemsException {
        media = new Media(genre,format,year,name);

        String artist = "Ludwig van Beethoven";

        Music music = new Music(media, artist);

        Assert.assertEquals("Ludwig van Beethoven", music.getArtist());
    }

    @Test (expected = NoDataItemsException.class)
    public void artistIsNotEmpty() throws WrongFormatException, NoDataItemsException {
        media = new Media(genre,format,year,name);
        String artist = "";

        Music music = new Music(media, artist);
    }


}
