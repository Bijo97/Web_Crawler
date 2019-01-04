public class Scrapper {
    String page;
    String type;

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

    public void setType(String type) {
        this.type = type;
    }
}
