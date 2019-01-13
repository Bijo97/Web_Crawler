
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Crawler implements Runnable {
    private String base_address,output;
    private URI uri;
    List<String> urls = new ArrayList<>();

    public Crawler(String base_address) throws NoDataItemsException, MalformedURLException, URISyntaxException {
        Boolean checkEmptyBaseAddress;
        checkEmptyBaseAddress = base_address.trim().isEmpty();
        URL url = new URL(base_address);
        url.toURI();

        this.uri = URI.create(base_address);
        if(!checkEmptyBaseAddress){
            this.base_address = base_address;
        } else{
            throw new NoDataItemsException("Base Address shoud not be Empty!");
        }

    }

    public String getOutput() {
        return output;
    }

    public void setBaseAddress(String base_address) {
        this.base_address = base_address;
    }

    public String getBaseAddress() {
        return base_address;
    }

    public boolean visitPage(String url){

        List<String> linksOnthisPage = new ArrayList<>();

        if(!url.contains("javascript") && !url.contains("#")){

            try{
                Document doc = Jsoup.connect(url).timeout(0).get();
                Elements linkTags = doc.select("a[href]");

                for(Element e : linkTags){
                    String link = e.attr("href");
                    if(!link.contains("#") && !link.contains("javascript") && !link.equals(url)){
                        if(link.startsWith("http") || link.startsWith("www")) {
                            if (link.contains(uri.getHost())) {
                                linksOnthisPage.add(link);
                            } else {
                                System.out.println("SOME OTHER WEBSITE -- " + link);
                            }
                        }else if(link.contains("php")){
                            linksOnthisPage.add(link);
                        }
                        else if(link.startsWith("/")){
                            link = url + link.substring(1, link.length());
                            linksOnthisPage.add(link);
                        }else{
                            System.out.println("LINK IGNORED DUE TO  -- " + url);
                        }
                    }else{
                        System.out.println("LINK IGNORED -- " + url);
                    }
                }
                System.out.println("\n\nLinks found in \"" + url+ "\" : " + linksOnthisPage.size());

            }catch(Exception e){
                System.out.println("EXCEPTION -- " + url);
            }
        }else{
            System.out.println("UNWANTED URL -- " + url);
        }
        this.urls=linksOnthisPage;
        return(linksOnthisPage.size()>0);
    }


    @Override
    public void run() {

    }

    public void printUrl() {
    }
}
