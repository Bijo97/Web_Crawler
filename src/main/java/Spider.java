public class Spider {
    private int max_depth, max_url;

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
}
