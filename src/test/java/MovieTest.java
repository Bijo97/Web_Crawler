import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieTest {
    String genre = "Comedy";
    String format = "Blu-ray";
    String year = "1997";
    String name = "Office Space";

    Media media;
    @Test
    public void shouldHaveDirectorWritersAndStars() throws WrongFormatException, NoDataItemsException {
        Media mockedMedia = mock(Media.class);
        List<String> writers = new ArrayList<>();
        List<String> stars = new ArrayList<>();
        List<String> stars1 = new ArrayList<>();

        media = new Media(genre,format,year,name);



        String director = "Mike Judge";
        writers.add("William Goldman");
        stars.add("Ron Livingston");
        stars.add("Jennifer Aniston");
        stars.add("David Herman");
        stars.add("Ajay Naidu");
        stars.add("Diedrich Bader");
        stars.add("Stephen Root");

        Movie movie  = new Movie(media, director, writers, stars);

        Assert.assertEquals("Mike Judge", movie.getDirector());
        Assert.assertTrue(compareList(writers,movie.getWriters()));
        Assert.assertTrue(compareList(stars,movie.getStars()));

    }

    @Test (expected = NoDataItemsException.class)
    public void directorWritersAndStarsAreNotEmpty() throws WrongFormatException, NoDataItemsException {
        String director = "Mike Judge";
        List<String> writers = new ArrayList<>();
        List<String> stars = new ArrayList<>();

        writers.add("William Goldman");

        media = new Media(genre,format,year,name);

        Movie movie = new Movie(media, director, writers, stars);
    }

    public static boolean compareList(List ls1,List ls2){
        System.out.println(ls1.toString());
        System.out.println(ls2.toString());
        return ls1.toString().contentEquals(ls2.toString())?true:false;
    }
}
