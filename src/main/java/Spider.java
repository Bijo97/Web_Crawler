
import org.apache.commons.lang3.time.StopWatch;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Spider {
    private int max_depth, max_url;
    private String pageAddress;
    private SpiderLeg spiderLeg;
    private Set<URL> master_list = new HashSet<>();
    private Crawler crawler = new Crawler("http://localhost/tci/");
    private List<Future<SpiderLeg>> futures = new ArrayList<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    public Spider(SpiderLeg spiderLeg) throws URISyntaxException, NoDataItemsException, MalformedURLException {
        this.spiderLeg = spiderLeg;
    }

    public Spider(int max_depth, int max_url) throws NoDataItemsException, URISyntaxException, MalformedURLException {
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

    public void setAddress(){
        this.pageAddress = crawler.getBaseAddress();
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

    public boolean checkPageGrabs() {
        Set<SpiderLeg> spiderLegSet = new HashSet<>();
        Iterator<Future<SpiderLeg>> iterator = futures.iterator();

        while(iterator.hasNext()){
            Future<SpiderLeg> future = iterator.next();
            try{
                spiderLegSet.add(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        for (SpiderLeg spiderLeg : spiderLegSet){
            addNewURLs(spiderLeg);
        }
        return futures.size()>0;
    }

    public void go() throws MalformedURLException {
        StopWatch stopWatch = new StopWatch();
        String start = crawler.getBaseAddress();
        URL url = new URL(start);
        submitNewURL(url, 0);

        while (checkPageGrabs()) ;
        stopWatch.stop();
    }
}
