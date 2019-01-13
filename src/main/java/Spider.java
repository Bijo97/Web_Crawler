public class Spider {
    private Integer max_depth, max_url;

    public Spider(Integer max_depth, Integer max_url) throws NoDataItemsException{
        if(max_depth!=0 || max_depth == null){
            if(max_url!=0 || max_url == null){
                this.max_depth = max_depth;
                this.max_url = max_url;
            } else {
                throw new NoDataItemsException("Max Url should not be 0!");
            }
        } else {
            throw new NoDataItemsException("Max Depth should not be 0!");
        }

    }

    public Integer getMaxDepth() {
        return max_depth;
    }

    public Integer getMaxURL() {
        return max_url;
    }
}
