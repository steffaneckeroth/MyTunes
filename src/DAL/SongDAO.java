package src.DAL;

import src.BE.Song;
import src.DAL.ISongDataAccess;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SongDAO implements ISongDataAccess {

    private static final String SONG_FILE = "MyTunes/lib/music";
    private Path pathToFile = Path.of(SONG_FILE);

    /**
     * Retrieve all movies from the data source
     * @return
     * @throws IOException
     */
    public List<Song> getAllSong() throws Exception {

        try {
            // Read all lines from file
            List<String> lines = Files.readAllLines(pathToFile);
            List<Song> songs = new ArrayList<>();

            // Parse each line
            for (String line : lines) {
                String[] separatedLine = line.split(",");

                // Map each separated line to Movie object
                int id = Integer.parseInt(separatedLine[0]);
                String title = separatedLine[1];
                String artist = separatedLine[2];
                String category = separatedLine[3];

                // Create Movie object

                Song newSong = new Song(id, title, artist, category);
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
     * @param artist
     * @return
     * @throws Exception
     */

    @Override
    public Song createSong(String title, String artist, String category) throws Exception {

        int nextId = getNextID();
        String newLine = nextId + "," + title + "," + artist + "," + category;

        // Append new line using Java NIO
        //Files.write(pathToFile, ("\r\n" + newLine).getBytes(), APPEND);

        // Append new line using BufferedWriter
        try (BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardOpenOption.SYNC, StandardOpenOption.APPEND, StandardOpenOption.WRITE)) {
            bw.newLine();
            bw.write(nextId + "," + title + "," + "," +artist + "," + category);
        }

        return new Song(nextId, title, artist, category);
    }

    /**
     * Update a movie with param movie
     * @param song
     * @throws Exception
     */
    @Override
    public void updateSong(Song song) throws Exception {
        try {
            File tmp = new File(song.hashCode() + ".txt"); //Creates a temp file for writing to.
            List<Song> allSongs = getAllSong();
            allSongs.removeIf((Song t) -> t.getId() == song.getId());
            allSongs.add(song);

            //I'll sort the movies by their ID's
            allSongs.sort(Comparator.comparingInt(Song::getId));

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp))) {
                for (Song mov : allSongs) {
                    bw.write(mov.getId() + "," + mov.getTitle() + "," + mov.getArtist() + "," + mov.getCategory());
                    bw.newLine();
                }
            }

            //Overwrite the movie file wit the tmp one.
            Files.copy(tmp.toPath(), new File(SONG_FILE).toPath(), StandardCopyOption.REPLACE_EXISTING);
            //Clean up after the operation is done (Remove tmp)
            Files.delete(tmp.toPath());

        } catch (IOException ex) {
            throw new Exception("Could not update movie.", ex);
        }
    }

    /**
     * Delete a movie from the collection
     * @param song
     * @throws Exception
     */
    @Override
    public void deleteSong(Song song) throws Exception {

        try {
            File file = new File(SONG_FILE);
            if (!file.canWrite()) {
                throw new Exception("Can't write to song storage");
            }
            List<Song> songs = getAllSong();
            songs.remove(song);
            OutputStream os = Files.newOutputStream(file.toPath());
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os))) {
                for (Song mov : songs) {
                    String line = mov.getId() + "," + mov.getTitle() + "," + mov.getArtist() + "," + mov.getCategory();
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            throw new Exception("Could not delete song.", ex);
        }
    }


    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Song getSong(int id) throws Exception {
        List<Song> all = getAllSong();

        int index = Collections.binarySearch(all, new Song(id, 0, ""), Comparator.comparingInt(Song::getId));

        if (index >= 0) {
            return all.get(index);
        } else {
            throw new IllegalArgumentException("No movie with ID: " + id + " is found.");
        }
    }



    /**
     * Get the next ID in our collection
     * @return
     */
    private int getNextID() throws Exception {
        List<Song> songs = getAllSong();

        Song lastSong = songs.get(songs.size()- 1);
        return lastSong.getId() + 1;
    }





    /*
    public List<Movie> getAllMovies() {
        List<Movie> allMovieList = new ArrayList<>();

        File moviesFile = new File(MOVIES_FILE);


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(moviesFile))) {
            boolean hasLines = true;
            while (hasLines) {
                String line = bufferedReader.readLine();
                hasLines = (line != null);
                if (hasLines && !line.isBlank())
                {
                    String[] separatedLine = line.split(",");

                    int id = Integer.parseInt(separatedLine[0]);
                    int year = Integer.parseInt(separatedLine[1]);
                    String title = separatedLine[2];
                    if(separatedLine.length > 3)
                    {
                        for(int i = 3; i < separatedLine.length; i++)
                        {
                            title += "," + separatedLine[i];
                        }
                    }
                    Movie movie = new Movie(id, title, year);
                    allMovieList.add(movie);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allMovieList;
    }
    */


}