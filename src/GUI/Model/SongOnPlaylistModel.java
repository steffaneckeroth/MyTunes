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

    /**
     * This is the constructor for the SongOnPlaylistModel class.
     * @throws Exception
     */
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

    /**
     * This method adds a song to a playlist. If the song cannot be added, an exception is thrown.
     * @param playlist
     * @param song
     * @throws Exception
     */
    public void addToPlaylist(Playlist playlist, Song song) throws Exception
    {
        Song mSong = songOnPlaylistManager.addToPlaylist(playlist, song);
        System.out.println(mSong);
        songOnPlaylistsToBeViewed.add(mSong);
    }

    /**
     * This method deletes a song from a playlist.
     * @param playlist
     * @param song
     * @throws Exception
     */
    public void deleteSongOnPlaylist(Playlist playlist, Song song) throws Exception
    {
        songOnPlaylistManager.deleteSongOnPlaylist(playlist, song);
        songOnPlaylistsToBeViewed.remove(song);
    }

    /**
     * This method sets the selected playlist for the user to view.
     * @param playlist
     */
    public void setSelectedPlaylist(Playlist playlist)
    {
        songOnPlaylistsToBeViewed.clear();
        songOnPlaylistsToBeViewed.addAll(songOnPlaylistManager.getSongsOnPlaylist(playlist));
        selectedPlaylist = playlist;
    }
}


