import java.util.List;
import java.util.regex.Pattern;

public class Book {
    private Media media;
    private List<String> authors;
    private String publisher;
    private String isbn;

    public Book(Media media, List<String> authors, String publisher, String isbn) throws NoDataItemsException, WrongFormatException {
        Pattern p = Pattern.compile("\\d{3}-\\d{10}");

        Boolean checkAuthors, checkPublisher, checkISBN, checkISBNFormat;
        checkAuthors = authors.isEmpty();
        checkPublisher = publisher.trim().isEmpty();
        checkISBN = isbn.trim().isEmpty();
        checkISBNFormat = p.matcher(isbn).matches();

        if(!checkAuthors){
            if(!checkPublisher){
                if(!checkISBN){
                    if(checkISBNFormat){
                        this.media = media;
                        this.authors = authors;
                        this.publisher = publisher;
                        this.isbn = isbn;
                    } else {
                        throw new WrongFormatException("ISBN Format should be ddd-dddddddddd  (3)-(10)");
                    }

                } else {
                    throw new NoDataItemsException("ISBN should not be empty");
                }
            } else {
                throw new NoDataItemsException("Publisher should not be empty.");
            }
        } else {
            throw new NoDataItemsException("Author should not be empty.");
        }

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
