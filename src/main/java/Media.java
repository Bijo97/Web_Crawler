

public class Media {
    String genre, format, year, name;

    Media(String _genre, String _format, String _year, String _name) throws NoDataItemsException, WrongFormatException {
        Boolean checkGenre, checkFormat,checkYear,checkName, checkYearFormat;
        checkGenre = _genre.trim().isEmpty();
        checkFormat = _format == null || _format.trim().length() == 0;
        checkYear = _year == null || _year.trim().length() == 0;
        checkName = _name == null || _name.trim().length() == 0;
        checkYearFormat = _year.chars().allMatch(Character :: isDigit);

        if(!checkGenre){
            if(!checkFormat){
                if(!checkYear){
                    if(!checkName){
                        if(_year.length() == 4 && checkYearFormat){
                            this.genre = _genre;
                            this.format = _format;
                            this.name = _name;
                            this.year = _year;
                        } else{
                            throw new WrongFormatException("Wrong Year Format. Year format should be yyyy");
                        }
                    } else {
                        throw new NoDataItemsException("Name should not be null");
                    }
                } else {
                    throw new NoDataItemsException("Year should not be null");
                }
            } else{
                throw new NoDataItemsException("Format should not be null");
            }
        } else {
            throw new NoDataItemsException( "Genre should not be null");
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
