package ex10;

public class MyLinkedList {
    private Element head; // 先頭
    private Element tail; // 末尾

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public int size() {
        // TODO: リストの要素数を数えて返す
        int count = 0;
        if (head != null) {
            Element element = this.head;
            count++;
            while (element.getNext() != null) {
                count++;
                element = element.getNext();
            }
        }
        return count;
    }

    public void addFirst(String str) {
        // 先頭に要素を追加
        // リストが空なら要素を作って先頭と末尾に
        Element newElem = new Element(str);
        if (this.head == null) {
            this.head = newElem;
            this.tail = this.head;
            return;
        }
        // TODO: リストが空でないなら先頭に追加
        newElem.setNext(this.head);
        this.head.setPrevious(newElem);
        this.head = newElem;
    }

    public void addLast(String str) {
        // 末尾に要素を追加
        // リストが空なら要素を作って先頭と末尾に
        Element newElem = new Element(str);
        if (this.head == null) {
            this.head = newElem;
            this.tail = this.head;
            return;
        }
        // TODO: リストが空でないなら末尾に追加
        if (this.tail == null) {
            this.tail = newElem;
            newElem.setPrevious(this.tail.getPrevious());
        } else {
            newElem.setPrevious(this.tail);
            this.tail.setNext(newElem);
            this.tail = newElem;

        }
    }

    public void add(int index, String str) {
        // index番目に要素を挿入
        // indexの範囲チェック
        if (index < 0 || this.size() < index) {
            System.out.println("インデックスが範囲外です。");
            return;
        }
        // TODO: indexがゼロなら先頭に追加
        if (index == 0) {
            this.addFirst(str);
        }
        // TODO: indexがサイズと同じなら末尾に追加
        else if (index == this.size()) {
            this.addLast(str);
        }
        // TODO: それ以外、i番目を見つけて、その前に追加
        else {
            // Element indexElem = this.getElement(index);
            // Element elem = new Element(str);
            // indexElem.setNext(elem);
            // Element preElem = this.getElement(index - 1);
            // elem.setNext(preElem);
            // preElem.setPrevious(elem);
            // elem.setPrevious(indexElem);
            Element indexElem = this.getElement(index);
            Element temp = indexElem.getPrevious();
            Element newElem = new Element(str);
            newElem.setNext(indexElem);
            newElem.setPrevious(temp);
            temp.setNext(newElem);
            indexElem.setPrevious(newElem);
        }
    }

    public Element getElement(int index) {
        if (index < 0 || this.size() < index) {
            return null;
        }
        int count = 0;
        Element element = this.head;
        while (count != index) {
            count++;
            element = element.getNext();
        }
        return element;
    }

    public String removeFirst() {
        // 先頭の要素を削除
        // 要素がない場合
        if (this.head == null) {
            System.out.println("削除する要素がありません。");
            return null;
        }
        // 要素が1つ以上
        Element next = this.head.getNext();
        Element target = this.head; // 削除する要素
        if (next != null) {
            // TODO: 要素が2つ以上
            target = this.head.getNext();
            next.setPrevious(null);
        }
        this.head = next;
        return target.getData();
    }

    public String removeLast() {
        // 末尾の要素を削除
        // 要素があるかチェック
        if (tail == null) {
            System.out.println("削除する要素がありません。");
            return null;
        }
        // 要素が1つ以上
        Element target = tail;
        Element prev = tail.getPrevious();
        if (prev != null) {
            // TODO: 要素が2つ以上
            // prev の next を更新する
            prev.setNext(null);
            this.tail.setPrevious(prev);
        } else {
            // TODO: 要素が1つ (削除で空になる場合)
            this.head = null;
            prev = null;
        }
        this.tail = prev;
        return target.getData();
    }

    public String remove(int index) {
        // index番目の要素を削除
        // indexの範囲チェック
        if (index < 0 || index >= this.size()) {
            System.out.println("インデックスが範囲外です。");
            return null;
        }
        // indexがゼロなら先頭を削除
        if (index == 0)
            return this.removeFirst();
        // indexがサイズ-1なら末尾を削除
        if (index == this.size() - 1)
            return this.removeLast();
        // TODO: それ以外
        var target = this.getElement(index);
        target.getPrevious().setNext(target.getNext());
        target.getNext().setPrevious(target.getPrevious());
        return target.getData();
    }

    public boolean contains(String str) {
        // 要素が含まれているかどうか返す
        // 要素がないならfalse
        if (this.head == null)
            return false;
        // TODO: 要素をたどってあるかないか探す
        var elem = this.head;
        boolean result = false;
        do {
            if (elem.getData().equals(str)) {
                result = true;
                break;
            }
            elem = elem.getNext();
        } while (elem != null);
        return result;
    }

    public void print() {
        // 要素を先頭から表示
        System.out.print("Forward: ");
        if (this.head == null) {
            System.out.println("null");
            return;
        }
        Element p = this.head;
        while (p != null) {
            System.out.print(p + " -> ");
            p = p.getNext();
        }
        System.out.println("null");
    }

    public void printReverse() {
        // 要素を末尾から表示
        System.out.print("Reverse: ");
        if (this.tail == null) {
            System.out.println("null");
            return;
        }
        Element p = this.tail;
        while (p != null) {
            System.out.print(p + " -> ");
            p = p.getPrevious();
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // いろいろと試す
        System.out.println("リストの生成");
        MyLinkedList list = new MyLinkedList();
        list.print();
        System.out.println("サイズ: " + list.size());
        System.out.println("dを先頭に追加");
        list.addFirst("d");
        list.print();
        System.out.println("サイズ: " + list.size());
        System.out.println("cを先頭に追加");
        list.addFirst("c");
        list.print();
        System.out.println("eを最後に追加");
        list.addLast("e");
        list.print();
        list.printReverse();
        System.out.println("bを先頭に追加");
        list.addFirst("b");
        list.print();
        System.out.println("fを最後に追加");
        list.addLast("f");
        list.print();
        list.printReverse();
        System.out.println("fが含まれているか?: " + list.contains("f"));
        System.out.println("bが含まれているか?: " + list.contains("b"));
        System.out.println("dが含まれているか?: " + list.contains("d"));
        System.out.println("zが含まれているか?: " + list.contains("z"));
        list.print();
        System.out.println("先頭を削除");
        list.removeFirst();
        list.print();
        System.out.println("最後を削除");
        list.removeLast();
        list.print();
        System.out.println("先頭を削除");
        list.removeFirst();
        list.print();
        System.out.println("最後を削除");
        list.removeLast();
        list.print();
        System.out.println("先頭を削除");
        list.removeFirst();
        list.print();
        System.out.println("0番目にdを追加");
        list.add(0, "d");
        list.print();
        System.out.println("0番目にbを追加");
        list.add(0, "b");
        list.print();
        System.out.println("2番目にfを追加");
        list.add(2, "f");
        list.print();
        System.out.println("1番目にcを追加");
        list.add(1, "c");
        list.print();
        System.out.println("3番目にeを追加");
        list.add(3, "e");
        list.print();
        System.out.println("5番目にgを追加");
        list.add(5, "g");
        list.print();
        list.printReverse();
        System.out.println("0番目を削除");
        list.remove(0);
        list.print();
        System.out.println("4番目を削除");
        list.remove(4);
        list.print();
        System.out.println("1番目を削除");
        list.remove(1);
        list.print();
        System.out.println("2番目を削除");
        list.remove(2);
        list.print();
        list.printReverse();
        System.out.println("1番目を削除");
        list.remove(1);
        list.print();
        System.out.println("0番目を削除");
        list.remove(0);
        list.print();
        System.out.println("end.");
    }
}
