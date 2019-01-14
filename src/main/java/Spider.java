
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Spider {
    private int max_depth, max_url;
    private SpiderLeg spiderLeg;
    private Set<URL> master_list = new HashSet<>();

    private List<Future<SpiderLeg>> futures = new ArrayList<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
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
            SpiderLeg spiderLeg_temp = new SpiderLeg(url, depth);
            Future<SpiderLeg> future = executorService.submit(spiderLeg_temp);
            master_list.add(url);
            futures.add(future);
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

    public int getFuturesSize() {
        return futures.size();
    }
}
