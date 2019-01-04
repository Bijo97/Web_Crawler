

public class Media {
    String genre, format, year, name;

    Media(String _genre, String _format, String _year, String _name) throws EmptyMediaException {
        Boolean checkGenre, checkFormat,checkYear,checkName;
        checkGenre = _genre.trim().isEmpty();
        checkFormat = _format == null || _format.trim().length() == 0;
        checkYear = _year == null || _year.trim().length() == 0;
        checkName = _name == null || _name.trim().length() == 0;



        if(!checkGenre){
            System.out.println("Check Genre = true ");
            if(!checkFormat){
                if(!checkYear){
                    if(!checkName){
                        this.genre = _genre;
                        this.format = _format;
                        this.year = _year;
                        this.name = _name;
                    } else {
                        throw new EmptyMediaException("Name should not be null");
                    }
                } else {
                    throw new EmptyMediaException("Year should not be null");
                }
            } else{
                throw new EmptyMediaException("Format should not be null");
            }
        } else {
            throw new EmptyMediaException( "Genre should not be null");
        }


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
