package src.BE;

public class Song {


    private int id;
    private String title;
    private String artist;
    private String category;
    private String filepath;
    private int duration;






    public Song(int id, String title, String artist, String category, String filepath, int duration) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.filepath = filepath;
        this.duration = duration;

    }

    public int getId() {
        return id;
    }
    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {this.title = title;}

    public String getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist.toString();
    }
    public void setCategory(Category category) {
        this.category = category.toString();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
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
        return id + ": " + title +" ("+artist+")"+ " ("+category+")"+ "("+filepath+")"+ "("+duration+")";
    }
}

