public class Crawler {
    private String base_address, output;

    public Crawler(String base_address) throws NoDataItemsException {
        Boolean checkBaseAddress;
        checkBaseAddress = base_address.trim().isEmpty();

        if(!checkBaseAddress){
            this.base_address = base_address;
        } else{
            throw new NoDataItemsException("Base Address shoud not be Empty!");
        }

    }

    public String getOutput() {
        return output;
    }

    public String getBaseAddress() {
        return base_address;
    }
}
