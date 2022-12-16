package src.GUI.Model;

// Java imports

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.BE.Song;
import src.BLL.SongManager;

import java.sql.Time;
import java.util.List;

public class SongModel {

    private ObservableList<Song> songsToBeViewed;
    private SongManager songManager;
    private Song selectedSong;

    /**
     * This is the constructor for the SongModel class.
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

    /**
     * This method searches for songs.
     * @param query
     * @throws Exception
     */
    public void searchSong(String query) throws Exception {
        List<Song> searchResults = songManager.searchSongs(query);
        songsToBeViewed.clear();
        songsToBeViewed.addAll(searchResults);
    }

    /**
     * This method creates a new song object using the provided parameters and adds it to the songs to be viewed
     * @param title
     * @param artist
     * @param category
     * @param filepath
     * @param duration
     * @throws Exception
     */
    public void createNewSong(String title, String artist, String category, String filepath, Time duration) throws Exception
    {
        Song mSong = songManager.createNewSong(title, artist, category, filepath, duration);

        songsToBeViewed.add(mSong);
    }

    /**
     * This method updates a song in the song manager and then updates
     * the list of songs to be viewed to include all songs in the song manager.
     * @param updatedSongs
     * @throws Exception
     */
    public void updateSongs(Song updatedSongs) throws Exception {
        songManager.updateSongs(updatedSongs);
        songsToBeViewed.clear();
        songsToBeViewed.addAll(songManager.getAllSongs());
    }

    /**
     * This method is used to delete a song from the list of songs.
     * @param deletedSongs
     * @throws Exception
     */
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
