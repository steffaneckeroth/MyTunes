package src.DAL;

import src.BE.Song;
import src.DAL.SongDAO;

import java.util.List;

public interface ISongDataAccess {

    public List<Song> getAllSong() throws Exception;


    public Song createSong(String title, String artist, String category, String filepath, int duration) throws Exception;

    public void updateSongs(Song song) throws Exception;

    public void deleteSongs(Song song) throws Exception;

}
