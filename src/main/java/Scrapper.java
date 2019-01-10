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
            //Jsoup...
            return new Object[] {movie, music, book};
        }

        return new Object[] {};
    }
}
