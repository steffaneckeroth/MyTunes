package src.BE;

public class Song {


    private int id;
    private String title;
    private String artist;
    private String category;


    public Song(int id, String title, String artist, String category) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.category = category;
    }

    public Song(int id, int i, String artist) {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist.toString();
    }
    public void setCategory(Category category) {
        this.category = category.toString();
    }

    @Override
    public String toString()
    {
        return id + ": " + title +" ("+artist+")"+ " ("+category+")";
    }
}
