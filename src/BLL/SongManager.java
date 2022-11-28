package src.BLL;

import src.BE.Song;
import src.BLL.unit.SongSearcher;
import src.DAL.ISongDataAccess;
import src.DAL.db.SongDAO_DB;

import java.util.List;

public class SongManager {

    private src.BLL.unit.SongSearcher songSearcher = new src.BLL.unit.SongSearcher();

    private ISongDataAccess myTunesDAO;

    public SongManager() {
        myTunesDAO = new SongDAO_DB();
    }

    public List<Song> getAllSongs() throws Exception {
        return myTunesDAO.getAllSong();
    }

    public List<Song> searchSongs(String query) throws Exception {
        List<Song> allSong = getAllSongs();
        List<Song> searchResult = songSearcher.search(allSong, query);
        return searchResult;
    }

    public Song createNewSong(String title, String artist, String category) throws Exception {
        return myTunesDAO.createSong(title, artist, category);
    }

    public void updateSongs(Song updatedSong) {
    }

    public void deleteSongs(Song deletedSong) {
    }
}
