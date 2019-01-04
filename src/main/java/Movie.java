

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private Media media;
    private String director;
    private List<String> writers = new ArrayList<>();
    private List<String> stars = new ArrayList<>();

    public Movie(Media media, String director, List<String> writers, List<String> stars) throws NoDataItemsException {
        Boolean checkDirector, checkWriters, checkStars;
        checkDirector = director.trim().isEmpty();
        checkWriters = writers.isEmpty();
        checkStars = stars.isEmpty();

        if(!checkDirector){
            if(!checkWriters){
                if(!checkStars){
                    this.media = media;
                    this.director = director;
                    this.writers = writers;
                    this.stars = stars;
                } else {
                    throw new NoDataItemsException("List of Stars should not be empty.");
                }
            } else {
                throw new NoDataItemsException("List of Writers should not be empty.");
            }
        } else {
            throw new NoDataItemsException("Director should not be empty.");
        }

    }

    public String getDirector() {
        return director;
    }


    public List<String> getWriters() {
        return writers;
    }

    public List<String> getStars() {
        return stars;
    }
}
