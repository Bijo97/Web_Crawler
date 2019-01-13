

public class Media {
    private String genre, format, year, name;

    Media(String genre, String format, String year, String name) throws NoDataItemsException, WrongFormatException {
        Boolean checkGenre, checkFormat,checkYear,checkName, checkYearFormat;
        checkGenre = genre.trim().isEmpty();
        checkFormat = format == null || format.trim().length() == 0;
        checkYear = year == null || year.trim().length() == 0;
        checkName = name == null || name.trim().length() == 0;
        checkYearFormat = year.chars().allMatch(Character :: isDigit);

        if(!checkGenre){
            if(!checkFormat){
                if(!checkYear){
                    if(!checkName){
                        if(year.length() == 4 && checkYearFormat){
                            this.genre = genre;
                            this.format = format;
                            this.name = name;
                            this.year = year;
                        } else{
                            throw new WrongFormatException("Wrong Year Format. Year format should be yyyy");
                        }
                    } else {
                        throw new NoDataItemsException("Name should not be empty");
                    }
                } else {
                    throw new NoDataItemsException("Year should not be empty");
                }
            } else{
                throw new NoDataItemsException("Format should not be empty");
            }
        } else {
            throw new NoDataItemsException( "Genre should not be empty");
        }


    }

    Media(Object _object){
        this.genre = "DefaultGenre";
        this.format = "DefaultFormat";
        this.name = "DefaultName";
        this.year = "DefaultYear";
    }

    Media(){}

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
