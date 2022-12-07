package src.GUI.Model;

public class MyTunesModel {

    private SongModel songModel;
    private PlaylistModel playlistModel;

    public MyTunesModel() throws Exception {
        songModel = new SongModel();
    }

    public SongModel getSongModel() {
        return songModel;
    }

    public void setSongModel(SongModel songModel) {
        this.songModel = songModel;
    }

    public PlaylistModel getPlaylistModel () {
        return playlistModel;
    }

    public void setPlaylistModel(PlaylistModel playlistModel) throws Exception {this.playlistModel = new PlaylistModel(); }
}
