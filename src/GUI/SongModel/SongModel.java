package src.GUI.SongModel;

// Java imports
import src.BE.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

// Project imports

import src.BLL.SongManager;
import src.BE.Song;

/**
 * @author smsj
 */
public class SongModel {

    private ObservableList<Song> songsToBeViewed;
    private SongManager songManager;

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

    public void createNewSong(String title, String artist, String category) throws Exception{
        Song m = songManager.createNewSong(title, artist, category);

        songsToBeViewed.add(m);
    }
    public void updateSong(Song updatedSong) throws Exception {
        //Call BLL
        // Update song in DB
        songManager.updateSong(updatedSong);
        //UPDATE listView
        songsToBeViewed.clear();
        songsToBeViewed.addAll(songManager.getAllSongs());
    }

    public void deleteSong(Song deletedSong) throws Exception {
        songManager.deleteSong(deletedSong);
        songsToBeViewed.clear();
        songsToBeViewed.addAll(songManager.getAllSongs());
    }
}
