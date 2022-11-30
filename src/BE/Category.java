package src.BE;

public class Category {
    private int id;
    private String name;

    public Category(int id,  String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getCategory() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setCategory(String name) {
        this.name = name;
    }
}
