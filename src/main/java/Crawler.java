import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Crawler {
    private String base_address, output;

    public Crawler(String base_address) throws NoDataItemsException, MalformedURLException, URISyntaxException {
        Boolean checkEmptyBaseAddress;
        checkEmptyBaseAddress = base_address.trim().isEmpty();
        URL url = new URL(base_address);
        url.toURI();
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
}
