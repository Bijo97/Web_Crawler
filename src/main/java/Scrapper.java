import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scrapper {
    String page;
    String type;
    String keyword;
    Movie movie;
    Music music;
    Book book;

    public Scrapper(String page) throws NoDataItemsException {
        Boolean checkPage = page.trim().isEmpty() || page == null;

        if (checkPage) {
            throw new NoDataItemsException("Page can't be null or empty!");
        } else {
            this.page = page;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws WrongFormatException {
        if (type == null || type.equals("movies") || type.equals("musics") || type.equals("books")){
            this.type = type;
        } else {
            throw new WrongFormatException("Type can only be movies, musics, books, or null!");
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) throws WrongFormatException {
        if (this.type == null){
            if (keyword == null){
                this.keyword = keyword;
            } else {
                throw new WrongFormatException("Keyword for type: null can only be null!");
            }
        } else {
            this.keyword = keyword;
        }
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Object[] scrapping() {
        if (type == null && keyword == null){

            try {
                Document doc  = Jsoup.parse(new File(page), "ISO-8859-1");
                String _name = doc.select("div.media-details > h1").first().text();
                String _category = doc.select("div.media-details > table > tbody > tr").first().child(1).text();

                Elements datas = doc.select("div.media-details > table > tbody > tr");
                String _genre = datas.get(1).child(1).text();
                String _format = datas.get(2).child(1).text();
                String _year = datas.get(3).child(1).text();
                Media m = new Media(_genre,_format,_year,_name);

                if (_category.equals("Music")){
                    String _artist = datas.get(4).child(1).text();

                    this.music = new Music(m,_artist);
//                    System.out.println(this.music.getArtist());
                }else if (_category.equals("Books")){
                    String[] authors = datas.get(4).child(1).text().split(",");
                    List<String> _authors = new ArrayList<>();
                    for (String s:authors) {
                        _authors.add( s );
                    }

                    String _publisher = datas.get(5).child(1).text();
                    String _isbn = datas.get(6).child(1).text();

                    this.book = new Book(m,_authors,_publisher,_isbn);
//                    System.out.println(this.book.getAuthors());
                }else if (_category.equals("Movies")){
                    String _director = datas.get(4).child(1).text();
                    String[] writers = datas.get(5).child(1).text().split(",");
                    List<String> _writers = new ArrayList<>();
                    for (String s:writers) {
                        _writers.add( s );
                    }
                    String[] stars = datas.get(6).child(1).text().split(",");
                    List<String> _stars = new ArrayList<>();
                    for (String s:stars) {
                        _stars.add( s );
                    }

                    this.movie = new Movie(m,_director,_writers,_stars);
//                    System.out.println(this.movie.getStars());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongFormatException e) {
                e.printStackTrace();
            } catch (NoDataItemsException e) {
                e.printStackTrace();
            }

            return new Object[] {movie, music, book};
        }

        return new Object[] {};
    }
}
