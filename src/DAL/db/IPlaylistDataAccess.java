package src.DAL.db;

import src.BE.Playlist;

import java.util.List;

public interface IPlaylistDataAccess {

    public Playlist createPlaylist(String playlistname)throws Exception;
    public List<Playlist> getAllPlaylists () throws Exception;
    public Playlist updatePlaylist(Playlist playlist) throws Exception;
    public Playlist deletePlaylist(Playlist playList) throws Exception;


}
