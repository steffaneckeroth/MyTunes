package src.DAL;

import src.BE.Song;

import java.util.List;

public interface ISongDataAccess {

    public List<Song> getAllSong() throws Exception;

    Song createSong(String title, String artist, String category) throws Exception;

    public void updateSongs(Song song) throws Exception;

    public void deleteSongs(Song song) throws Exception;

}
