package src.BE;

public class Song {
    private int id;
    private String title;
    private Artist artist;
    private Category category;
    private String filepath;
    private int duration;

    public Song(int id, String title, Artist artist, Category category, String filepath, int duration) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.filepath = filepath;
        this.duration = duration;

    }

    public Artist getArtist() {
        return artist;
    }

    public Category getCategory() {
        return category;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {this.title = title;}

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getFilepath() {
        return filepath;
    }
    @Override
    public String toString()
    {
        return id + ": " + title + "(" +artist+ ")" + "(" +category+ ")" + "(" +filepath+ ")" + "(" +duration+")";
    }
}

