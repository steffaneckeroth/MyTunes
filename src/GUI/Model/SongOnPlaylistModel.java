package src.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.BE.Playlist;
import src.BE.Song;
import src.BLL.PlaylistManager;
import src.BE.SongOnPlaylist;
import src.BLL.SongOnPlaylistManager;

import java.util.ArrayList;

import static java.util.Collections.addAll;

public class SongOnPlaylistModel {
    private ObservableList<SongOnPlaylist> songOnPlaylistsToBeViewed;

    private SongOnPlaylistManager songOnPlaylistManager;

    private SongOnPlaylist selectedSongOnPlaylist;

    public SongOnPlaylistModel() throws Exception {

        songOnPlaylistManager = new SongOnPlaylistManager();
        songOnPlaylistsToBeViewed = FXCollections.observableArrayList();
        //songOnPlaylistsToBeViewed = addAll(songOnPlaylistManager.getSongsOnPlaylist());
    }



    public ObservableList<SongOnPlaylist> getObservableSongOnPlaylist() {
        return songOnPlaylistsToBeViewed;
    }

    public void addToPlaylist(Playlist playlist, Song song) throws Exception {
        Song mSong = songOnPlaylistManager.addToPlaylist(playlist, song);

        PlaylistManager.addToPlaylist(playlist, mSong);
    }


    public ArrayList<Song> getSongsOnPlaylist(Playlist playlist) {
        return songOnPlaylistManager.getSongsOnPlaylist(playlist);
    }


    public void deleteSongOnPlaylist(Playlist playlist, Song song) throws Exception {
        songOnPlaylistManager.deleteSongOnPlaylist(playlist, song);

    }


}


