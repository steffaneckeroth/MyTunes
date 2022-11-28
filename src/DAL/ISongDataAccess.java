package src.DAL;




import src.BE.Song;

import java.util.List;

public interface ISongDataAccess {

    public List<Song> getAllSong() throws Exception;

    Song createSong(String title, String artist, String category) throws Exception;

    public void updateSong(Song song) throws Exception;

    public void deleteSong(Song song) throws Exception;

}
