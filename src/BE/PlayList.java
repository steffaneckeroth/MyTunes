
package src.BE;

import java.util.ArrayList;

public class PlayList {
    private int playlistid;
    private String playlistname;
    private ArrayList <Song> playlist = new ArrayList<>();

    public ArrayList<Song> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(ArrayList<Song> playlist) {
        this.playlist = playlist;
    }

    public PlayList(int id, String name) {
        this.playlistid = id;
        this.playlistname = name;
    }

        public void setPlayListId ( int playlistid){
            this.playlistid = playlistid;
        }
        public void setPlayListName (String playlistname){
            this.playlistname = playlistname;
        }
        public int getPlayListId () {
            return playlistid;
        }
        public String getPlayListName () {
            return playlistname;
        }

    @Override
    public String toString() {
        return playlistname;
    }


}

