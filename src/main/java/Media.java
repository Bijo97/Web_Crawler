public class Media {
    String genre, format, year, name;

    Media(){

    }

    Media(String _genre, String _format, String _year, String _name){
        this.genre = _genre;
        this.format = _format;
        this.year = _year;
        this.name = _name;
    }

    public String getGenre() {
        return genre;
    }

    public String getFormat() {
        return format;
    }

    public String getYear() {
        return year;
    }

    public String getName() {
        return name;
    }
}
