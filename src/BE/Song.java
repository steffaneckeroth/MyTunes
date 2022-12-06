package src.BE;

import java.sql.Time;

public class Song {
    private int id;
    private String title;
    private String artist;
    private String category;
    private String filepath;
    private Time duration;

    public Song(int id, String title, String artist, String category, String filepath, Time duration) {
        this.id= id;
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.filepath = filepath;
        this.duration = duration;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getArtist() {
        return artist;
    }

    public String getCategory() {
        return category;
    }

    public String setArtist(String artist) {
        this.artist = artist;
        return artist;
    }

    public String setCategory(String category) {
        this.category = category;
        return category;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {this.title = title;}

    public Time getDuration() {
        return duration;
    }
    public void setDuration(Time duration) {
        this.duration = duration;
    }
    public String getFilepath() {
        return filepath;
    }
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
    @Override
    public String toString()
    {
        return id + ": " + title + "(" +artist+ ")" + "(" +category+ ")" + "(" +filepath+ ")" + "(" +duration+")";
    }

    public int getLength() {
        return 0;
    }
}

