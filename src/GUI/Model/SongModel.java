package src.GUI.Model;

// Java imports

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.BE.Artist;
import src.BE.Category;
import src.BE.Song;
import src.BLL.SongManager;

import java.sql.Time;
import java.util.List;

/**
 * @author smsj
 */
public class SongModel {

    private ObservableList<Song> songsToBeViewed;
    private SongManager songManager;

    private Song selectedSong;

    /**
     * Constructor
     * @throws Exception
     */
    public SongModel() throws Exception {
        songManager = new SongManager();
        songsToBeViewed = FXCollections.observableArrayList();
        songsToBeViewed.addAll(songManager.getAllSongs());
    }

    public ObservableList<Song> getObservableSongs() {
        return songsToBeViewed;
    }

    public void searchSong(String query) throws Exception {
        List<Song> searchResults = songManager.searchSongs(query);
        songsToBeViewed.clear();
        songsToBeViewed.addAll(searchResults);
    }


    public void createNewSong(String title, Artist artist, Category category, String filepath, Time duration) throws Exception{
        Song m = songManager.createNewSong(title, artist, category, filepath, duration);

        songsToBeViewed.add(m);
    }
    public void updateSongs(Song updatedSongs) throws Exception {
        //Call BLL
        // Update movie in DB
        songManager.updateSongs(updatedSongs);
        //UPDATE listView
        songsToBeViewed.clear();
        songsToBeViewed.addAll(songManager.getAllSongs());
    }

    public void deleteSong(Song deletedSongs) throws Exception {
        songManager.deleteSongs(deletedSongs);
        songsToBeViewed.clear();
        songsToBeViewed.addAll(songManager.getAllSongs());
    }
    public Song getSelectedSong() {
        return selectedSong;
    }
    public void setSelectedSong(Song selectedSong) {
        this.selectedSong = selectedSong;
    }
}
