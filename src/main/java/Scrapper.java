public class Scrapper {
    String page;
    String type;
    String keyword;

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
        } else if (this.type.equals("movies")){
            if (keyword.equals("genre") || keyword.equals("format") || keyword.equals("year") || keyword.equals("director") || keyword.equals("stars") || keyword.equals("writers")){
                this.keyword = keyword;
            } else {
                throw new WrongFormatException("Keyword for type: movies can only be genre, format, year, director, stars, or writers!");
            }
        } else if (this.type.equals("musics")){
            if (keyword.equals("genre") || keyword.equals("format") || keyword.equals("year") || keyword.equals("artist")){
                this.keyword = keyword;
            } else {
                throw new WrongFormatException("Keyword for type: musics can only be genre, forma, year, or artist!");
            }
        } else if (this.type.equals("books")){
            if (keyword.equals("genre") || keyword.equals("format") || keyword.equals("year") || keyword.equals("authors") || keyword.equals("ISBN") || keyword.equals("publisher")){
                this.keyword = keyword;
            } else {
                throw new WrongFormatException("Keyword for type: books can only be genre, format, year, authors, ISBN, or publisher!");
            }
        }
    }
}
