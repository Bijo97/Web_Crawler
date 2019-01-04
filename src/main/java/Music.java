public class Music {
    private Media media;
    private String artist;
    public Music(Media media, String artist) throws NoDataItemsException {
        Boolean checkArtist;
        checkArtist = artist.trim().isEmpty();
        if(!checkArtist){
            this.media = media;
            this.artist = artist;
        } else {
            throw new NoDataItemsException("Artist should not be empty.");
        }

    }

    public String getArtist() {
        return artist;
    }
}
