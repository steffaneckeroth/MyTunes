package src.DAL.db;

import src.BE.Playlist;
import src.BE.Song;
import src.BE.SongOnPlaylist;

import java.util.ArrayList;
import java.util.List;

public interface ISongOnPlaylistDataAccess {

    public Song addToPlaylist(Playlist playlist, Song song)throws Exception;


    ArrayList<Song> getSongsOnPlaylist(Playlist playlist);

    public void deleteSongOnPlaylist(Playlist playlist, Song song);

}
