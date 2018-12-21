public class WCARest {
    private String request;
    private String url;
    private String type;
    private String keyword;

    public WCARest(String request) {
        this.request = request;

        String[] data = request.split(" ");
        if (data.length == 3){
            url = data[0];
            type = data[1];
            keyword = data[2];
        } else if (data.length == 2){
            url = data[0];
            type = data[1];
        } else {
            url = data[0];
        }
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getKeyword() {
        return keyword;
    }
}
