package 전력망을_둘로_나누기;

import java.util.*;

class Solution {

    private class Node {
        private int id;
        private List<Node> adjList = new ArrayList<>();

        private boolean visit = false;

        public Node(int id) {
            this.id = id;
        }
    }

    private class Edge {
        private Node v1;
        private Node v2;

        public Edge(Node v1, Node v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }
    private Map<Integer, Node> tree = new HashMap<>();
    private List<Edge> edges = new ArrayList<>();

    private int cnt = 0;
    private PriorityQueue<Integer> pq = new PriorityQueue<>();

    public int solution(int n, int[][] wires) {
        int answer = -1;

        for (int i = 0; i < n; i++) {
            tree.put(i + 1, new Node(i + 1));
        }

        for (int[] wire : wires) {
            Node v1 = tree.get(wire[0]);
            Node v2 = tree.get(wire[1]);
            v1.adjList.add(v2);
            v2.adjList.add(v1);

            if (wire[0] > wire[1]) {
                edges.add(new Edge(v2, v1));
            }
            else {
                edges.add(new Edge(v1, v2));
            }
        }

        for (Edge edge : edges) {
            Node v1 = edge.v1;
            Node v2 = edge.v2;

            int cnt1 = 0;
            int cnt2 = 0;

            clear();
            cnt = 0;
            v2.visit = true;
            dfs(v1);
            cnt1 = cnt;

            clear();
            cnt = 0;
            v1.visit = true;
            dfs(v2);
            cnt2 = cnt;

            pq.add(Math.abs(cnt1 - cnt2));
        }

        answer = pq.remove();
        return answer;
    }

    private void clear() {
        for (Map.Entry<Integer, Node> entry : tree.entrySet()) {
            entry.getValue().visit = false;
        }
    }


    private void dfs(Node node) {
        node.visit = true;
        cnt++;

        for (Node adj : node.adjList) {
            if (!adj.visit) {
                dfs(adj);
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 9;
        int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        int answer = s.solution(n, wires);
        System.out.println("answer = " + answer);
    }
}
