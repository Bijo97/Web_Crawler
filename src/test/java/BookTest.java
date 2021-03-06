import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BookTest {
    String genre = "Tech";
    String format = "Audio";
    String year = "2011";
    String name = "The Clean Coder: A Code of Conduct for Professional Programmers";

    Media media;

    @Test
    public void shouldHaveAuthorsPublisherAndISBN() throws WrongFormatException, NoDataItemsException {
        //ARRANGE
        media = new Media(genre,format,year,name);
        List<String> authors = new ArrayList<>();
        List<String> authors_temp = new ArrayList<>();

        String publisher = "Prentice Hall";
        String isbn = "007-6092046981";
        authors.add("Robert C. Martin");
        authors_temp.add("Robert C. Martin");

        //ACT
        Book book = new Book(media, authors, publisher, isbn);

        //ASSERT
        Assert.assertTrue(compareList(authors_temp,book.getAuthors()));
        Assert.assertEquals("Prentice Hall",book.getPublisher());
        Assert.assertEquals("007-6092046981",book.getISBN());
    }

    @Test (expected = NoDataItemsException.class)
    public void artistsPublisherAndISBNAreNotEmpty() throws WrongFormatException, NoDataItemsException {
        //ARRANGE
        media = new Media(genre,format,year,name);
        List<String> authors = new ArrayList<>();

        //ACT
        authors.add("Robert C. Martin");
        String publisher = "Prentice Hall";
        String isbn = "";

        Book book = new Book(media,authors,publisher,isbn);

        //ASSERT
    }

    @Test (expected = WrongFormatException.class)
    public void ISBNFormatIsCorrect() throws WrongFormatException, NoDataItemsException {
        //ARRANGE
        media = new Media(genre,format,year,name);
        List<String> authors = new ArrayList<>();

        //ACT
        authors.add("Robert C. Martin");
        String publisher = "Prentice Hall";
        String isbn = "007-60920469811";
        Book book = new Book(media,authors,publisher,isbn);

        //ASSERT
    }

    public static boolean compareList(List ls1,List ls2){
        System.out.println(ls1.toString());
        System.out.println(ls2.toString());
        return ls1.toString().contentEquals(ls2.toString())?true:false;
    }
}
