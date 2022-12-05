package src.DAL;

import src.BE.Song;

import java.sql.Time;
import java.util.List;


public interface ISongDataAccess {

    public List<Song> getAllSong() throws Exception;

    public Song createSong(String title, String artist, String category, String filepath, Time duration) throws Exception;

    public void updateSongs(Song song) throws Exception;

    public Song deleteSongs(Song song) throws Exception;

}
