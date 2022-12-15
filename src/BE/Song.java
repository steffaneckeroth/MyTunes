package src.BE;

import java.sql.Time;

public class Song {
    private int id;
    private String title;
    private String artist;
    private String category;
    private String filepath;
    private Time duration;

    /**
     *This is the constructor for the Song class.
     * @param id
     * @param title
     * @param artist
     * @param category
     * @param filepath
     * @param duration
     */
    public Song(int id, String title, String artist, String category, String filepath, Time duration) {
        this.id= id;
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.filepath = filepath;
        this.duration = duration;
    }

    /**
     *This code is a setter method.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     *This code is defining a method of Artist.
     * @return artist
     */
    public String getArtist() {
        return artist;
    }
    /**
     * This code is defining a method of category.
     * @return category
     */
    public String getCategory() {return category;}
    /**
     * It is a method which indicates that it is intended to set the artist attribute of the class.
     * @param artist
     * @return artist
     */
    public String setArtist(String artist) {
        this.artist = artist;
        return artist;
    }
    /**
     * It is a method which indicates that it is intended to set the artist attribute of the class.
     * @param category
     * @return category
     */
    public String setCategory(String category) {
        this.category = category;
        return category;
    }
    /**
     * This code is defining a method of id.
     * @return id
     */
    public int getId() {return id;}
    /**
     *This code is defining a method of title.
     * @return title
     */
    public String getTitle() {return title;}

    /**
     *This code is a setter method
     * @param title
     */
    public void setTitle(String title) {this.title = title;}

    /**
     * This code is defining a method of duration.
     * @return duration
     */
    public Time getDuration()
    {
        return duration;
    }

    /**
     * This code is a setter method
     * @param duration
     */
    public void setDuration(Time duration) {this.duration = duration;}

    /**
     * This code is defining a method of filepath.
     * @return filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * This code is a setter method
     * @param filepath
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * This code is defining a method of id and title.
     * @return id and title
     */
    @Override
    public String toString()
    {
        return id + ": " + title;
    }

    /**
     * This code is defining a method of id and length.
     * @return Length
     */
    public int getLength() {
        return 0;
    }

}

