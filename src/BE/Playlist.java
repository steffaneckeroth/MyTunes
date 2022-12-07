
package src.BE;

import java.util.ArrayList;

public class Playlist {
    private int playlistid;
    public String playlistname;
    private ArrayList<Song> playlist = new ArrayList<>();

    public ArrayList<Song> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(ArrayList<Song> playlist) {
        this.playlist = playlist;
    }

    public Playlist(int id, String playlistname) {
        this.playlistid = id;
        this.playlistname = playlistname;
    }

        public void setPlaylistId ( int playlistid){
            this.playlistid = playlistid;
        }
        public void setPlaylistName (String playlistname){
            this.playlistname = playlistname;
        }
        public int getPlaylistId () {
            return playlistid;
        }
        public String getPlaylistName () {
            return playlistname;
        }

    @Override
    public String toString() {
        return playlistname;
    }


}

