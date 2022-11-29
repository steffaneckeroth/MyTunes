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

    public Song createNewSong(String title, String artist, String category, String filepath, int duration) throws Exception {
        return songDAO.createSong(title, artist, category, filepath, duration);
    }

    public void updateSongs(Song updatedSongs) throws Exception {
        songDAO.updateSongs(updatedSongs);
    }

    public void deleteSongs(Song deletedSongs) throws Exception{
        songDAO.deleteSongs(deletedSongs);
    }
}
