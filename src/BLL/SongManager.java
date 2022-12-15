package src.BLL;

import src.BE.Song;
import src.BLL.unit.SongSearcher;
import src.DAL.ISongDataAccess;
import src.DAL.db.SongDAO_DB;

import java.sql.Time;
import java.util.List;

public class SongManager {

    private SongSearcher songSearcher = new SongSearcher();

    private ISongDataAccess songDAO;

    public SongManager() {songDAO = new SongDAO_DB();}

    /**
     * This code is a method that returns a list of songs using the songDAO object.
     * @return songDAO.getAllSong()
     * @throws Exception
     */
    public List<Song> getAllSongs() throws Exception {return songDAO.getAllSong();}

    /**
     * This method searches for songs using the provided query string.
     * @param query
     * @return searchResult
     * @throws Exception
     */
    public List<Song> searchSongs(String query) throws Exception {
        List<Song> allSong = getAllSongs();
        List<Song> searchResult = songSearcher.search(allSong, query);
        return searchResult;
    }

    /**
     * It creates a new song with the all the parameters down below
     * @param title
     * @param artist
     * @param category
     * @param filepath
     * @param duration
     * @return songDAO.createSong(title, artist, category, filepath, duration)
     * @throws Exception
     */
    public Song createNewSong(String title, String artist, String category, String filepath, Time duration) throws Exception {
        return songDAO.createSong(title, artist, category, filepath, duration);
    }

    /**
     * This method updates the songs in the database using the songDAO.
     * @param updatedSongs
     * @throws Exception
     */
    public void updateSongs(Song updatedSongs) throws Exception {
        songDAO.updateSongs(updatedSongs);
    }

    /**
     * This method deletes the songs in the database using the songDAO.
     * @param deletedSongs
     * @throws Exception
     */
    public void deleteSongs(Song deletedSongs) throws Exception{
        songDAO.deleteSongs(deletedSongs);
    }

}
