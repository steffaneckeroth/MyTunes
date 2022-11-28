package src.DAL;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;

public class SongDAO implements ISongDataAccess{

    private static final String SONGS_FILE = "data/movie_titles.txt";
    private Path pathToFile = Path.of(SONGS_FILE);

    /**
     * Retrieve all songs from the data source
     * @return
     * @throws IOException
     */
    public List<Song> getAllSongs() throws Exception {

        try {
            // Read all lines from file
            List<String> lines = Files.readAllLines(pathToFile);
            List<Song> songs = new ArrayList<>();

            // Parse each line
            for (String line : lines) {
                String[] separatedLine = line.split(",");

                // Map each separated line to Movie object
                int id = Integer.parseInt(separatedLine[0]);
                int year = Integer.parseInt(separatedLine[1]);
                String title = separatedLine[2];

                // Create Movie object
                Song newSong = new Song(id, year, title);
                songs.add(newSong);

                //System.out.println(separatedLine);
            }
            songs.sort(Comparator.comparingInt(Song::getId));

            return songs;
        }
        catch (IOException e) {
            // Log specifics about IOException and re-throw up the layers...
            throw e;
        }
    }

    /**
     * Create a new movie
     * @param title
     * @param year
     * @return
     * @throws Exception
     */
    @Override
    public Song createSong(String title, int year) throws Exception {

        int nextId = getNextID();
        String newLine = nextId + "," + year + "," + title;

        // Append new line using Java NIO
        Files.write(pathToFile, ("\r\n" + newLine).getBytes(), APPEND);

        return new Song(nextId, year, title);
    }

    /**
     * Update a movie with param movie
     * @param song
     * @throws Exception
     */
    @Override
    public void updateSong(Song song) throws Exception {

    }

    /**
     * Delete a movie from the collection
     *
     * @param song
     * @return
     * @throws Exception
     */
    @Override
    public Song deleteMovie(Song song) throws Exception {
        return song;
    }


    /**
     * Get the next ID in our collection
     * @return
     */
    private int getNextID() throws Exception {
        List<Song> songs = getAllSongs();

        Song lastSong = songs.get(songs.size()- 1);
        return lastSong.getId() + 1;
    }



}
