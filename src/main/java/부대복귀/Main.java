package 부대복귀;

import java.util.*;

class Solution {

    private static class Node {
        private int id;
        private List<Node> adjList;

        public Node(int id) {
            this.id = id;
            this.adjList = new ArrayList<>();
        }
    }

    private static class State {
        private int id;
        private int length;

        public State(int id, int length) {
            this.id = id;
            this.length = length;
        }
    }

    private int[] table;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        Node[] graph = new Node[n + 1];
        table = new int[n + 1];
        Arrays.fill(table, -1);

        for (int i = 1; i <= n; i++) {
            graph[i] = new Node(i);
        }

        for (int[] road : roads) {
            graph[road[0]].adjList.add(graph[road[1]]);
            graph[road[1]].adjList.add(graph[road[0]]);
        }
        bfs(graph, destination, n);

        for (int i = 0; i < sources.length; i++) {
            answer[i] = table[sources[i]];
        }

        return answer;
    }

    private void bfs(Node[] graph, int source, int n) {
        Queue<State> q = new LinkedList<>();
        boolean[] visit = new boolean[n + 1];

        visit[source] = true;
        State start = new State(source, 0);
        q.add(start);
        while (!q.isEmpty()) {
            State currentState = q.remove();
            table[currentState.id] = currentState.length;

            Node currentNode = graph[currentState.id];
            for (Node adj : currentNode.adjList) {
                if (visit[adj.id]) {
                    continue;
                }

                visit[adj.id] = true;
                q.add(new State(adj.id, currentState.length + 1));
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 5;
        int[][] roads = {{1, 2},{1, 4}, {2, 4}, {2, 5}, {4, 5}};
        int[] sources = {1, 3, 5};
        int destination = 5;
        int[] answer = sol.solution(n, roads, sources, destination);

        for (int i : answer) {
            System.out.println("i = " + i);
        }
    }
}
