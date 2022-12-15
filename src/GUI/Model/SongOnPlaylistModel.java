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
    private final ObservableList<Song> songOnPlaylistsToBeViewed;

    private SongOnPlaylistManager songOnPlaylistManager;

    private Playlist selectedPlaylist;

    public SongOnPlaylistModel() throws Exception
    {
        songOnPlaylistManager = new SongOnPlaylistManager();
        songOnPlaylistsToBeViewed = FXCollections.observableArrayList();
    }

    public void getASongFromAPlaylist()
    {

    }

    public ObservableList<Song> getObservableSongOnPlaylist()
    {
        return songOnPlaylistsToBeViewed;
    }

    public void addToPlaylist(Playlist playlist, Song song) throws Exception
    {
        Song mSong = songOnPlaylistManager.addToPlaylist(playlist, song);
        System.out.println(mSong);
        songOnPlaylistsToBeViewed.add(mSong);
    }

    public void deleteSongOnPlaylist(Playlist playlist, Song song) throws Exception
    {
        songOnPlaylistManager.deleteSongOnPlaylist(playlist, song);
        songOnPlaylistsToBeViewed.remove(song);
    }

    public void setSelectedPlaylist(Playlist playlist)
    {
        songOnPlaylistsToBeViewed.clear();
        songOnPlaylistsToBeViewed.addAll(songOnPlaylistManager.getSongsOnPlaylist(playlist));
        selectedPlaylist = playlist;
    }
}


