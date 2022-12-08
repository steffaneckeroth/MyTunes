package src.GUI.Model;

import src.BE.Playlist;
import src.BE.Song;
import src.BLL.PlaylistManager;

public class SongOnPlaylistModel {


    public static void addToPlaylist(Playlist playlist, Song song) throws Exception {
        PlaylistManager.addToPlaylist(playlist, song);
    }




}
