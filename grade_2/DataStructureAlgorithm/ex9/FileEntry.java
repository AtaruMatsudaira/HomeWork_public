package ex9;

import java.util.*;

public class FileEntry {
    private String name;
    private ArrayList<FileEntry> list;
    private HashMap<String, FileEntry> map;

    public FileEntry(String name) {
        this.name = name;
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public FileEntry add(FileEntry entry) {
        list.add(entry);
        map.put(entry.name, entry);
        return this;
    }

    public void printList(String prefix) {
        // TODO:
        System.out.println(prefix + this.name);
        for (var child : list) {
            child.printList(prefix + this.name + "/");
        }
    }

    public void printList() {
        printList("/");
    }

    public FileEntry find(String path) {
        // TODO:

        FileEntry result = this; // 検索結果
        for (String filename : path.split("/")) {
            if (!result.map.containsKey(filename)) { // result.map に filename が存在しない場合
                return null;
            }
            result = result.map.get(filename);
            // result.map から filename を取り出す
        }

        return result;
    }

    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        FileEntry rootDir = new FileEntry("root");
        FileEntry binDir = new FileEntry("bin");
        FileEntry tmpDir = new FileEntry("tmp");
        FileEntry usrDir = new FileEntry("usr");
        rootDir.add(binDir);
        rootDir.add(tmpDir);
        rootDir.add(usrDir);
        binDir.add(new FileEntry("vi"));
        binDir.add(new FileEntry("latex"));
        FileEntry yuki = new FileEntry("yuki");
        FileEntry hanako = new FileEntry("hanako");
        FileEntry tomura = new FileEntry("tomura");
        usrDir.add(yuki);
        usrDir.add(hanako);
        usrDir.add(tomura);
        yuki.add(new FileEntry("diary.html"));
        yuki.add(new FileEntry("Composite.java"));
        hanako.add(new FileEntry("memo.tex"));
        tomura.add(new FileEntry("game.doc"));
        tomura.add(new FileEntry("junk.mail"));
        rootDir.printList();
        System.out.println("find: root -> usr/tomura -> game.doc");
        System.out.println(rootDir.find("usr/tomura").find("game.doc"));
        System.out.println("find: root -> bin/zsh");
        System.out.println(rootDir.find("bin/zsh"));
    }
}
