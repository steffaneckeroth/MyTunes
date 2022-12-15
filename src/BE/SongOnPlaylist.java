package src.BE;

import java.util.ArrayList;

public class SongOnPlaylist {

    private int songonplaylistid; // id on the song
    public String songonplaylistname; // name on the song


    private ArrayList<Song> songonplaylist = new ArrayList<>();

    /**
     * This code is defining a method of songonplaylistid.
     * @return songonplaylistid
     */
    public int getSongonplaylistid() {
        return songonplaylistid;
    }

    /**
     * This method is a setter. 
     * @param songonplaylistid
     */
    public void setSongonplaylistid(int songonplaylistid) {
        this.songonplaylistid = songonplaylistid;
    }

    /**
     * This code is defining a method of songonplaylistname.
     * @return songonplaylistname
     */
    public String getSongonplaylistname() {
        return songonplaylistname;
    }

    /**
     * This code is a setter method.
     * @param songonplaylistname
     */
    public void setSongonplaylistname(String songonplaylistname) {
        this.songonplaylistname = songonplaylistname;
    }

    /**
     * This method returns an ArrayList of Song objects that are on the playlist.
     * @return songonplaylist
     */
    public ArrayList<Song> getSongOnPlaylist()
    {
        return songonplaylist;
    }

    /**
     * This method sets the list of songs on a playlist.
     * @param songonplaylist
     */
    public void setSongOnPlaylist(ArrayList<Song> songonplaylist)
    {
     this.songonplaylist = songonplaylist;
    }

    /**
     * This code defines a constructor for the SongOnPlaylist class.
     * @param id
     * @param songonplaylistname
     */
    public SongOnPlaylist(int id, String songonplaylistname){
        this.songonplaylistid = id;
        this.songonplaylistname = songonplaylistname;
    }

    /**
     * This code is defining the toString() method for a class that has a member variable named "songonplaylistname".
     * @return songonplaylistname
     */
    @Override
    public String toString() {
        return songonplaylistname;
    }

}
