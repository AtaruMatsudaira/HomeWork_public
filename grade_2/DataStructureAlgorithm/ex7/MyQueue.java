package ex7;

public class MyQueue {
    private Node head;

    public MyQueue() {
        head = null; // 最初は空っぽ
    }

    public Node getTail() {

        // 先頭が null の場合は null を返す
        if (this.head == null)
            return null;
        Node hoge = head;
        while (hoge.getNext() != null) {
            // System.out.print(hoge + " -> ");
            hoge = hoge.getNext();
        }
        // System.out.println();
        return hoge;
    }

    public void offer(String str) {
        if (this.head == null) {
            this.head = new Node(str);
        } else {
            this.getTail().setNext(new Node(str));
        }
    }

    public String peekFirst() {
        // 先頭が null の場合は null を返す
        if (head == null)
            return null;
        return this.head.getData();
    }

    public String poll() {
        // 先頭が null の場合は null を返す
        if (head == null)
            return null;
        String headStr = head.getData();
        head = head.getNext();
        return headStr;
    }

    public String toString() {
        return "キューの中身: " + toString(head);
    }

    public String toString(Node p) {
        if (p == null) {
            return "null";
        }
        return p.toString() + " -> " + toString(p.getNext());
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();

        System.out.println(queue);
        queue.offer("abc");
        System.out.println(queue);
        queue.offer("def");
        System.out.println(queue);
        queue.offer("ghi");
        System.out.println(queue);
        queue.offer("jkl");
        System.out.println(queue);
        queue.offer("mno");
        System.out.println(queue);
        System.out.println("peek: " + queue.peekFirst());
        System.out.println(queue);
        System.out.println("poll: " + queue.poll());
        System.out.println(queue);
        System.out.println("poll: " + queue.poll());
        System.out.println(queue);
        System.out.println("poll: " + queue.poll());
        System.out.println(queue);
        System.out.println("peek: " + queue.peekFirst());
        System.out.println(queue);
        System.out.println("poll: " + queue.poll());
        System.out.println(queue);
        System.out.println("poll: " + queue.poll());
        System.out.println(queue);
        System.out.println("poll: " + queue.poll());
        System.out.println(queue);
        System.out.println("peek: " + queue.peekFirst());
        System.out.println(queue);
    }
}
