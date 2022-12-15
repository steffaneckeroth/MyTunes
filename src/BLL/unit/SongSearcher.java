package src.BLL.unit;

import src.BE.Song;

import java.util.ArrayList;
import java.util.List;

public class SongSearcher {


    /**
     * This code searches a given list of songs based on a query string, and returns a list of matching songs.
     * @param searchBase
     * @param query
     * @return searchResult
     */
    public List<Song> search(List<Song> searchBase, String query) {
        List<Song> searchResult = new ArrayList<>();
        // checks each song against the provided "query".
        for (Song song : searchBase) {
            if(compareToSongTitle(query, song) || compareToSongArtist(query, song))
            {
                searchResult.add(song);
            }
        }

        return searchResult;
    }

    /**
     * This method compares the query string to the artist of the given Song object.
     * @param query
     * @param song
     * @return song.getArtist().toLowerCase().contains(query.toLowerCase())
     */
    private boolean compareToSongArtist(String query, Song song) {
        return song.getArtist().toLowerCase().contains(query.toLowerCase());
    }

    /**
     * This method compares the query string to the artist of the given Song object.
     * @param query
     * @param song
     * @return song.getTitle().toLowerCase().contains(query.toLowerCase());
     */
    private boolean compareToSongTitle(String query, Song song) {
        return song.getTitle().toLowerCase().contains(query.toLowerCase());
    }

}
