
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
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
    private Scrapper scrapper;
    private Converter converter;

    private List<Object> medias = new ArrayList<>();
    private List<String> urls = new ArrayList<>();

    public Crawler(String base_address) throws NoDataItemsException, MalformedURLException, URISyntaxException {
        Boolean checkEmptyBaseAddress;
        checkEmptyBaseAddress = base_address.trim().isEmpty();
//        URL url = new URL(base_address);
//        url.toURI();
//
//        this.uri = URI.create(base_address);
        if(!checkEmptyBaseAddress){
            this.base_address = base_address;
        } else{
            throw new NoDataItemsException("Base Address shoud not be Empty!");
        }
    }

    public String getOutput() {
        return output;
    }

    public void setScrapper(Scrapper scrapper) {
        this.scrapper = scrapper;
    }

    public void setBaseAddress(String base_address) {
        this.base_address = base_address;
    }

    public String getBaseAddress() {
        return base_address;
    }

    public boolean visitPage(String url){

        List<String> linksOnthisPage = new ArrayList<>();
        //Base address is added to list of pages to be scrapped
        linksOnthisPage.add(url);

        if(!url.contains("javascript") && !url.contains("#")){

            try{
                Document doc  = Jsoup.parse(new File(base_address), "ISO-8859-1");
//                Document doc = Jsoup.connect(url).timeout(0).get();
                Elements linkTags = doc.select("a[href]");

                for(Element e : linkTags){
                    String link = e.attr("href");
                    System.out.println(link);
                    //Check if link is not directed to the same page itself
                    if(!link.contains("#") && !link.contains("javascript") && !link.equals(url)){

                        if(link.startsWith("http") || link.startsWith("www")) {
                            linksOnthisPage.add(link);
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
                System.out.println("Links found in \"" + url+ "\" : " + linksOnthisPage.size());

            }catch(Exception e){
//                System.out.println("EXCEPTION -- " + url);
            }
        }else{
            System.out.println("UNWANTED URL -- " + url);
        }
        this.urls=linksOnthisPage;
        return(linksOnthisPage.size()>0);
    }

    public int getPagesFound(){
        return this.urls.size();
    }

    @Override
    public void run() {
        this.visitPage(base_address);
        for (int i=0; i<this.urls.size() ; i++){
            this.scrap(this.urls.get(i));
        }
    }

    public void scrap(String url){
        this.scrapper.setPage(url);
        this.scrapper.scrapping();
    }

    public void convertMedia(Media m){
        this.converter.convertMedia(m);
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }
}
