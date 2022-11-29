package src.BE;

import java.util.Collection;

public class Artist {
    private int id;
    private String name;


    public Artist(int id,  String name) {
        this.id = id;
        this.name = name.toString();
    }
    public int getId() {
        return id;
    }
    public String getArtist() {
        return name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setArtist(String name) {
        this.name = name;
    }

    public Collection<Object> toLowerCase() {
        return null;
    }
}
