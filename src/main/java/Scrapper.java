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

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
