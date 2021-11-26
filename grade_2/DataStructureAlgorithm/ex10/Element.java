package ex10;

public class Element {
    private Element next; // 次の要素
    private Element previous; // 前の要素
    private String data; // 保持する文字列

    public Element(String data) {
        this.data = data;
    }

    public Element setNext(Element next) {
        this.next = next;
        return next;
    }

    public Element getNext() {
        return next;
    }

    public Element setPrevious(Element previous) {
        this.previous = previous;
        return previous;
    }

    public Element getPrevious() {
        return previous;
    }

    public String setData(String data) {
        this.data = data;
        return data;
    }

    public String getData() {
        return data;
    }

    public String toString() {
        return "[" + data + "]";
    }
}