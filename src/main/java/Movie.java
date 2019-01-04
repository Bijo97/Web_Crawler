import java.util.ArrayList;
import java.util.List;

public class Movie {
    private Media media;
    private String director;
    private List<String> writers = new ArrayList<>();
    private List<String> stars = new ArrayList<>();

    public Movie(Media media, String director, List<String> writers, List<String> stars) {
        this.media = media;
        this.director = director;
        this.writers = writers;
        this.stars = stars;
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
