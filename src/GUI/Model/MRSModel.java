package src.GUI.Model;

public class MRSModel {

    private SongModel songModel;

    public MRSModel() throws Exception {
        songModel = new SongModel();
    }

    public SongModel getSongModel() {
        return songModel;
    }

    public void setSongModel(SongModel songModel) {
        this.songModel = songModel;
    }
}
