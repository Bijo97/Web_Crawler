
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class Spider {
    private int max_depth, max_url;
    private SpiderLeg spiderLeg;
    private Set<URL> master_list = new HashSet<>();

    public Spider(SpiderLeg spiderLeg){
        this.spiderLeg = spiderLeg;
    }

    public Spider(int max_depth, int max_url) throws NoDataItemsException{
        if(max_depth==0){
            throw new NoDataItemsException("Max Url should not be 0!");
        }
        if(max_url==0){
            throw new NoDataItemsException("Max Depth should not be 0!");
        }
        if(max_url<0){
            throw new IllegalArgumentException("Max Url should not be below 0");
        }
        if(max_depth<0){
            throw new IllegalArgumentException("Max Depth should not be below 0");
        }
        this.max_depth = max_depth;
        this.max_url = max_url;
    }

    public int getMaxDepth() {
        return max_depth;
    }

    public int getMaxURL() {
        return max_url;
    }

    public int getSpiderLegDepth(){
        return spiderLeg.getDepth();
    }

    public void submitNewURL(URL url, int depth) {
        if(shouldVisit(url,depth)) {
            spiderLeg = new SpiderLeg(url, depth);
            master_list.add(url);
        }
    }

    public boolean shouldVisit(URL url, int depth) {
        if(master_list.contains(url)){
            return false;
        }
        return true;
    }

    public void setSpiderLeg(SpiderLeg spiderLeg) {
        spiderLeg = spiderLeg;
    }

    public int countList() {
        return master_list.size();
    }

    public void addNewURLs(SpiderLeg spiderLeg) {
        for(URL url : spiderLeg.getUrlList()){
            submitNewURL(url, spiderLeg.getDepth() + 1);
        }

    }
}
