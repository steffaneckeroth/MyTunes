package src.BLL.unit;

import src.BE.Song;

import java.util.ArrayList;
import java.util.List;

public class SongSearcher {


    public List<Song> search(List<Song> searchBase, String query) {
        List<Song> searchResult = new ArrayList<>();

        for (Song songe : searchBase) {
            if(compareToSongTitle(query, songe) || compareToSongArtist(query, songe))
            {
                searchResult.add(songe);
            }
        }

        return searchResult;
    }

    private boolean compareToSongArtist(String query, Song song) {
        return song.getArtist().toLowerCase().contains(query.toLowerCase());
    }

    private boolean compareToSongTitle(String query, Song song) {
        return song.getTitle().toLowerCase().contains(query.toLowerCase());
    }

}
