package ex1;

import java.util.ArrayList;
import java.util.Collections;

public class MusicPlayer {
    private String name;
    private ArrayList<Album> albumList = new ArrayList<Album>();

    public MusicPlayer(String name) {
        this.name = name;
    }

    public void add(Album album) {
        albumList.add(album);
    }

    public void play() {
        System.out.println(name + "で全曲再生");
        for (Album album : albumList) {
            album.play();
        }
    }

    public void shufflePlay() {
        System.out.println(name + "でランダム再生");
        ArrayList<Music> musicList = new ArrayList<Music>();
        for (Album album : albumList) {
            musicList.addAll(album.getMusicList());
        }
        Collections.shuffle(musicList);
        for (var music : musicList) {
            music.play();
        }
        System.out.println("再生終了");
    }

    public static void main(String[] args) {
        MusicPlayer ipod = new MusicPlayer("ipod");
        Album album1, album2, album3;
        album1 = new Album("リテラチュア", "上田麗奈");
        album1.add(new Music("リテラチュア", "上田麗奈", 240, 5));
        album1.add(new Music("花の雨", "上田麗奈", 230, 4));
        album1.add(new Music("たより", "上田麗奈", 161, 4));
        album2 = new Album("One Last Kiss", "宇多田ヒカル");
        album2.add(new Music("One Last Kiss", "宇多田ヒカル", 252, 5));
        album2.add(new Music("Beautiful World", "宇多田ヒカル", 598, 5));
        album2.add(new Music("桜流し", "宇多田ヒカル", 282, 5));
        album3 = new Album("Shiro SAGISU Music FROM ``SHIN EVANGELION``", "鷺巣詩郎");
        album3.add(new Music("VOYAGER ~日付のない墓標~", "林原めぐみ", 295, 5));
        album3.add(new Music("born evil", "鷺巣詩郎", 612, 4));
        album3.add(new Music("激突!轟天対大魔艦", "鷺巣詩郎", 198, 5));
        album3.add(new Music("this is the dream, beyond belief", "鷺巣詩郎", 230, 5));
        ipod.add(album1);
        ipod.add(album2);
        ipod.add(album3);
        ipod.play();
        ipod.shufflePlay();
    }
}
