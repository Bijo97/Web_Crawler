import java.util.List;

public class Book {
    private Media media;
    private List<String> authors;
    private String publisher;
    private String isbn;

    public Book(Media media, List<String> authors, String publisher, String isbn) {
        this.media = media;
        this.authors = authors;
        this.publisher = publisher;
        this.isbn = isbn;
    }

    public List getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getISBN() {
        return isbn;
    }
}
