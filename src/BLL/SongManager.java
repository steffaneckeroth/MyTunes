package src.BLL;

import src.BE.Song;
import src.BLL.unit.SongSearcher;
import src.DAL.ISongDataAccess;
import src.DAL.db.SongDAO_DB;
import java.util.List;

public class SongManager {

    private SongSearcher songSearcher = new SongSearcher();

    private ISongDataAccess songDAO;

    public SongManager() {
        songDAO = new SongDAO_DB();
    }

    public List<Song> getAllSongs() throws Exception {
        return songDAO.getAllSong();
    }

    public List<Song> searchSongs(String query) throws Exception {
        List<Song> allSong = getAllSongs();
        List<Song> searchResult = songSearcher.search(allSong, query);
        return searchResult;
    }

    public Song createNewSong(String title, String artist, String category) throws Exception {
        return songDAO.createSong(title, artist, category);
    }

    public void updateSong(Song updatedSong) throws Exception
    {
        songDAO.updateSong(updatedSong);
    }

    public void deleteSong(Song deletedSong) throws Exception
    {
        songDAO.deleteSong(deletedSong);
    }
}
