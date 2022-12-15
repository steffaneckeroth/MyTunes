
package src.BE;


public class Playlist {
    private int playlistid;
    public String playlistname;

    /**
     * This code creates a constructor for the Playlist class
     * @param id
     * @param playlistname
     */
    public Playlist(int id, String playlistname) {
        this.playlistid = id;
        this.playlistname = playlistname;
    }

    /**
     * this sets the playlistid
     * @param playlistid
     */
        public void setPlaylistId ( int playlistid){
            this.playlistid = playlistid;
        }

    /**
     * this sets the playlistname
     * @param playlistname
     */
    public void setPlaylistName (String playlistname){
            this.playlistname = playlistname;
        }

    /**
     *
     * @return playlistid
     */
    public int getPlaylistId () {
            return playlistid;
        }

    /**
     *
     * @return playlistname
     */
    public String getPlaylistName () {
            return playlistname;
        }

    /**
     *
     * @return playlistname
     */
    @Override
    public String toString() {
        return playlistname;
    }
}

