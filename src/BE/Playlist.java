
package src.BE;


import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private int playlistid;
    public String playlistname;
    private List<Song> songs = new ArrayList<>();

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

    public void setSongs(ArrayList<Song> songsOnPlaylist)
    {
        songs = songsOnPlaylist;
    }
    public List<Song> getSongs()
    {
        return songs;
    }



    public Time getTotalDuration()
    {
        // Calculate the total duration in seconds
        int totalCount = 0;
        for (Song x: songs) {
            totalCount += x.getDuration().getTime();
        }
        // Calculate the number of hours, minutes, and seconds
        int hours = (totalCount / 60 / 60);
        int minutes = ((totalCount - (hours * 60 * 60)) / 60);
        int seconds = (totalCount % 60);

        // Create a new Time object using the calculated values
        Time totalDuration = new Time(hours, minutes, seconds);
        return totalDuration;
    }
}

