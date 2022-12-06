
package src.BLL;

import src.BE.PlayList;
import src.DAL.db.IPlayListDataAccess;
import src.DAL.db.PlayListDAO_DB;

public class PlayListManager {
    private IPlayListDataAccess playlistDAO;

    public PlayListManager() {
        playlistDAO = new PlayListDAO_DB();
    }
    public PlayList createNewPlayList (String name) throws Exception {
        return playlistDAO.createPlayList(name);
    }

}
