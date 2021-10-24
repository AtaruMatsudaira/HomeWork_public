package ex1;

import java.util.ArrayList;

public class Album {
    private String title;
    private String artist;
    private ArrayList<Music> musicList = new ArrayList<Music>();

    public Album(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public ArrayList<Music> getMusicList() {
        return musicList;
    }

    public void add(Music music) {
        musicList.add(music);
    }

    public int getTotalTime() {
        int sumTIme = 0;
        for (var music : musicList) {
            sumTIme += music.getTime();
        }
        return sumTIme;
    }

    public double getRating() {
        double sumRating = 0;
        for (var music : musicList) {
            sumRating += music.getRating();
        }
        return sumRating / musicList.size();
    }

    public void play() {
        System.out.println("アルバム再生中:" + title + "by " + artist + "(" + musicList.size() + "曲 , " + getTotalTime()
                + "sec. 評価:" + getRating());
        for (var music : musicList) {
            music.play();
        }
        System.out.println("再生終了");
    }

    public String toString() {
        return "anAlbum(" + title + "," + artist + "," + musicList.size() + "," + getTotalTime() + "," + getRating()
                + ")";
    }

    public static void main(String[] args) {
        Album album = new Album("リテラチュア", "上田麗奈");
        album.add(new Music("リテラチュア", "上田麗奈", 240, 5));
        album.add(new Music("花の雨", "上田麗奈", 230, 4));
        album.add(new Music("たより", "上田麗奈", 161, 4));
        System.out.println(album);
        System.out.println("総再生時間:" + album.getTotalTime());
        System.out.println("アルバム評価:" + album.getRating());
        album.play();

    }
}
