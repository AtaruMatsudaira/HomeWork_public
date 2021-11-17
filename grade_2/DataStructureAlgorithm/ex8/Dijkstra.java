package ex8;

import java.util.*;
import java.util.stream.*;

public class Dijkstra {
    final static int INFINITY = Integer.MAX_VALUE; // これを無限大とする

    class Node {
        final int key;
        int distance = INFINITY;
        boolean isFixed = false;
        ArrayList<Edge> edges = new ArrayList<>();

        public Node(int key) {
            this.key = key;
        }
    }

    class Edge {
        final Node from;
        final Node to; // 先のノード
        final int weight; // エッジの重み

        public Edge(Node from, Node to, int weight) {
            this.to = to;
            this.from = from;
            this.weight = weight;
        }
    }

    Node start;
    List<Node> nodes;

    public Dijkstra(int[] nodeKeys, int[][] edgeInfo) {
        nodes = Arrays.stream(nodeKeys).mapToObj(Node::new).collect(Collectors.toList());
        // or
        // nodes = new ArrayList<>();
        // for (int key : nodeKeys) {
        // nodes.add(new Node(key));
        // }
        for (int[] abw : edgeInfo) {
            int ai = abw[0] - 1;
            int bi = abw[1] - 1;
            int weight = abw[2];
            Node aNode = nodes.get(ai);
            Node bNode = nodes.get(bi);

            aNode.edges.add(new Edge(aNode, bNode, weight));
            bNode.edges.add(new Edge(bNode, aNode, weight));
        }

        // スタート位置は 0 番目に固定
        start = nodes.get(0);
        start = nodes.get(0);
        start.distance = 0;
    }

    public void calc() {
        // TODO: ダイクストラ法のアルゴリズムを実装する
        // 全てのノードを確定ラベルにする
        start.distance = 0;
        start.isFixed = true;
        moveNode(start);
    }

    void moveNode(Node node) {
        for (Edge e : node.edges) {
            if (e.to.distance > node.distance + e.weight) {
                e.to.distance = node.distance + e.weight;
                e.to.isFixed = true;
                moveNode(e.to);
            }
        }
    }

    public static void main(String[] args) {
        int[] nodeKeys = new int[] { 1, 2, 3, 4, 5 };
        int[][] edges = new int[][] { { 1, 2, 20 }, // from, to, weight (双方向)
                { 1, 3, 10 }, { 1, 4, 15 }, { 2, 4, 10 }, { 3, 4, 20 }, { 3, 5, 10 }, { 4, 5, 20 }, };

        Dijkstra graph = new Dijkstra(nodeKeys, edges);
        graph.calc();

        for (Node node : graph.nodes) {
            System.out.println("Node" + node.key + ": " + node.distance);
        }
    }
}