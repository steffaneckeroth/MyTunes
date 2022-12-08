package src.DAL.db;

import src.BE.Song;

public interface ISongOnPlaylistDataAccess {

    public Song addToPlaylist(String name, Song song)throws Exception;


}
