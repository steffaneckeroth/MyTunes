package src.DAL.db;

import src.BE.Playlist;

import java.util.List;

public interface IPlaylistDataAccess {
    public List<Playlist> getAllPlaylists () throws Exception;
    public Playlist createPlaylist(String playlistname)throws Exception;
    public void updatePlaylist(Playlist playlist) throws Exception;
    public Playlist deletePlaylist(Playlist playlist) throws Exception;


}
