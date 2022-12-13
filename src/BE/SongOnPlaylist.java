package src.BE;

import java.util.ArrayList;

public class SongOnPlaylist {

    private int songonplaylistid;
    public String songonplaylistname;


    private ArrayList<Song> songonplaylist = new ArrayList<>();

    public int getSongonplaylistid() {
        return songonplaylistid;
    }

    public void setSongonplaylistid(int songonplaylistid) {
        this.songonplaylistid = songonplaylistid;
    }

    public String getSongonplaylistname() {
        return songonplaylistname;
    }

    public void setSongonplaylistname(String songonplaylistname) {
        this.songonplaylistname = songonplaylistname;
    }

    public ArrayList<Song> getSongOnPlaylist()
    {
        return songonplaylist;
    }
    public void setSongOnPlaylist(ArrayList<Song> songonplaylist)
    {
     this.songonplaylist = songonplaylist;
    }

    public SongOnPlaylist(int id, String songonplaylistname){
        this.songonplaylistid = id;
        this.songonplaylistname = songonplaylistname;
    }
    @Override
    public String toString() {
        return songonplaylistname;
    }

}
