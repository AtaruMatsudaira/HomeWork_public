package ex7;

public class Item {
    private String title;
    private String link;
    private String description;

    public Item(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public String toString() {
        return "title : " + title + "\nlink : " + link + "\ndescription : " + description;
    }
}