package src.GUI.Model;

import src.BLL.PlayListManager;

public class PlayListModel {

    private PlayListManager playlistManager;

    public PlayListModel() throws Exception {
        playlistManager = new PlayListManager();
       // songsToBeViewed = FXCollections.observableArrayList();
       // songsToBeViewed.addAll(songManager.getAllSongs());
    }

    public void createNewPlayList(String name) throws Exception {
/*
        PlayList mPlayList = playlistManager.createNewPlayList(name);
        mPlayList.add;
        playlistToBeViewed.add(mPlayList);
*/
    }

}
