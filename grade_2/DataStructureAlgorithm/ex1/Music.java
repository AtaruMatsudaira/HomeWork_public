package ex1;

public class Music {
    private String title;
    private String artist;
    private int time;
    private int rating;

    public Music(String title, String artist, int time, int rating) {
        this.title = title;
        this.artist = artist;
        this.time = time;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getTime() {
        return time;
    }

    public int getRating() {
        return rating;
    }

    public void play() {
        String message = "再生中: " + title + " by " + artist + " " + time + "sec.. Fav:" + rating;
        System.out.println(message);
        // try {
        // Desktop.getDesktop().browse(new URI("https://twitter.com/intent/tweet?text="
        // + message));
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
    }

    public String toString() {
        return "aMusic(" + title + ", " + artist + ", " + time + ", " + rating + ")";
    }

    public static void main(String[] args) {
        Music music = new Music("なんだこれくしょん", "きゃりーぱみゅぱみゅ", 47, 3);
        System.out.println(music);
        music.play();
    }
}
