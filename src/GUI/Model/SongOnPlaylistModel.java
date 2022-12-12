package src.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.BE.Playlist;
import src.BE.Song;
import src.BLL.PlaylistManager;
import src.BE.SongOnPlaylist;
import src.BLL.SongOnPlaylistManager;

import java.util.ArrayList;

public class SongOnPlaylistModel {

    private SongOnPlaylistManager songOnPlaylistManager;

    private SongOnPlaylist selectedSongOnPlaylist;

    public SongOnPlaylistModel() throws Exception {
        songOnPlaylistManager = new SongOnPlaylistManager();
        songOnPlaylistsToBeViewed = FXCollections.observableArrayList();
        songOnPlaylistsToBeViewed.addAll((SongOnPlaylist) songOnPlaylistManager.getAllSongOnPlaylists());
    }



    private ObservableList<SongOnPlaylist> songOnPlaylistsToBeViewed;


    public void addToPlaylist(Playlist playlist, Song song) throws Exception {
        Song mSong = songOnPlaylistManager.addToPlaylist(playlist, song);

        PlaylistManager.addToPlaylist(playlist, mSong);
    }


    public ArrayList<Song> getSongsOnPlaylist(Playlist playlist) {
        return songOnPlaylistManager.getSongsOnPlaylist(playlist);
    }
}


