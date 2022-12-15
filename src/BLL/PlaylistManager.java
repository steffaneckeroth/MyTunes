
package src.BLL;

import src.BE.Playlist;
import src.DAL.db.IPlaylistDataAccess;
import src.DAL.db.PlaylistDAO_DB;

import java.util.List;

public class PlaylistManager {
    private IPlaylistDataAccess playlistDAO;

    public PlaylistManager() {playlistDAO = new PlaylistDAO_DB();}
    /**
     *This code creates a new playlist with the specified name using the
     * createPlaylist method from the playlistDAO object.
     * @param playlistname
     * @return playlistDAO.createPlaylist(playlistname)
     * @throws Exception
     */
    public Playlist createNewPlaylist (String playlistname) throws Exception
    {return playlistDAO.createPlaylist(playlistname);}

    /**
     * This method returns a list of playlists from the playlistDAO
     * @return playlistDAO.getAllPlaylists()
     * @throws Exception
     */
    public List<Playlist> getAllPlaylists() throws Exception
    {return playlistDAO.getAllPlaylists();}

    /**
     * This method updates a playlist using the provided updated playlist object.
     * @param updatedPlaylist
     * @throws Exception
     */
    public void updatePlaylist(Playlist updatedPlaylist) throws Exception
    {playlistDAO.updatePlaylist(updatedPlaylist);}

    /**
     * This method deletes a playlist using the deletePlaylist method from the playlistDAO object.
     * @param deletedPlaylist
     * @throws Exception
     */
    public void deletePlaylist(Playlist deletedPlaylist) throws Exception
    {playlistDAO.deletePlaylist(deletedPlaylist);}
}
