package src.DAL.db;

import src.BE.PlayList;

public interface IPlayListDataAccess {

    public PlayList createPlayList(String name)throws Exception;

}
