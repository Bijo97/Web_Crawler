import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class SpiderLeg {
    URL url;
    int depth;
    private Set<URL> urlList = new HashSet<>();
    public  SpiderLeg(URL url, int depth) {
        setURLAndDepth(url,depth);
    }

    public void setURLAndDepth(URL url, int depth) {
        this.url = url;
        this.depth = depth;
    }

    public URL getURL() {
        return url;
    }

    public int getDepth() {
        return depth;
    }

    public Set<URL> getUrlList() {
        return urlList;
    }
}
