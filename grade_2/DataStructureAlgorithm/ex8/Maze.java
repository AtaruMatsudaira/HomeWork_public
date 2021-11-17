package ex8;

import java.util.*;

public class Maze {
    final static int INFINITY = Integer.MAX_VALUE; // これを無限大とする

    class Node {
        int distance = INFINITY;
        boolean isFixed = false;
        final char type; // '.' | '#' | 'S' | 'G'
        ArrayList<Edge> edges = new ArrayList<>();

        public Node(char type) {
            this.type = type;
        }
    }

    class Edge {
        final Node from;
        final Node to; // 先のノード

        public Edge(Node from, Node to) {
            this.to = to;
            this.from = from;
        }
    }

    Node start;
    Node[][] nodes;

    public Maze(String[] lines) {
        int h = lines.length;
        int w = lines[0].length();
        nodes = new Node[h][w];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Node node = new Node(lines[y].charAt(x));
                if (node.type == 'S') {
                    start = node;
                    start.distance = 0;
                }
                nodes[y][x] = node;
            }
        }

        // 右と下方向にエッジを登録する
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Node node = nodes[y][x];
                if (node.type == '#')
                    continue;
                if (y < h - 1) {
                    Node dNode = nodes[y + 1][x];
                    if (dNode.type != '#') {
                        node.edges.add(new Edge(node, dNode));
                        dNode.edges.add(new Edge(dNode, node));
                    }
                }
                if (x < w - 1) {
                    Node lNode = nodes[y][x + 1];
                    if (lNode.type != '#') {
                        node.edges.add(new Edge(node, lNode));
                        lNode.edges.add(new Edge(lNode, node));
                    }
                }
            }
        }
    }

    public int bfs_search() {
        // TODO: 幅優先探索で S から G までの距離を求めて返す
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        start.isFixed = true;
        while (!queue.isEmpty()) {
            // 処理するノードを取り出す
            Node node = queue.poll();
            int distance = node.distance + 1;
            // node.isFixed の更新
            for (Edge e : node.edges) {
                Node child = e.to;
                // child が訪問済み(isFixed)であればスキップ continue
                if (child.isFixed)
                    continue;
                // ノードがゴールなら終了する ,node.distance を使って return する
                if (child.type == 'G')
                    return node.distance + 1;
                // child.distance の設定
                child.distance = distance;
                child.isFixed = true;
                // queue に child を追加
                queue.offer(child);
            }
        }
        return 0;
    }

    public void printMatrix() {
        String red = "\u001b[00;31m";
        String green = "\u001b[00;32m";
        String yellow = "\u001b[00;33m";
        String purple = "\u001b[00;34m";
        String pink = "\u001b[00;35m";
        String cyan = "\u001b[00;36m";
        String end = "\u001b[00m";
        for (int y = 0; y < nodes.length; y++) {
            for (int x = 0; x < nodes[y].length; x++) {
                if (nodes[y][x].type != '.') {
                    System.out.print( red +"    " + nodes[y][x].type+end);
                } else {
                    System.out.printf("%5d", nodes[y][x].distance > 1000 ? -1 : nodes[y][x].distance);
                }
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String[] lines = new String[] { "..........#...", ".#.###..#...#.", ".#.#....#.##..", ".#.#.#.#..#...",
                ".#.#.#G.#.#...", ".#......#.#...", ".###.###...#.#", "....#....#....", "#..#.#.####.#.",
                "..S.........#.", };
        Maze graph = new Maze(lines);
        int cost = graph.bfs_search();
        System.out.println(cost);
        graph.printMatrix();
    }
}