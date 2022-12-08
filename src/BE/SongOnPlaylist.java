package src.BE;

import java.util.ArrayList;

public class SongOnPlaylist {

    private int songplaylistid;

    public String songonplaylistname;

    private ArrayList<Song> songonplaylist = new ArrayList<>();

    public ArrayList<Song> getSongOnPlaylist()
    {
        return songonplaylist;
    }
    public void setSongOnPlaylist(ArrayList<Song> songonplaylist)
    {
     this.songonplaylist = songonplaylist;
    }

    public SongOnPlaylist(int id, String songonplaylistname){
        this.songplaylistid = id;
        this.songonplaylistname = songonplaylistname;
    }

}
