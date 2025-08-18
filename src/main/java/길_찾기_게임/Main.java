package 길_찾기_게임;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {

    private static class Node implements Comparable<Node> {
        private int id;
        private int x;
        private int y;

        private Node left;
        private Node right;

        public Node(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        public void addChild(Node child) {
            if (this.x > child.x) {
                if (this.left == null) {
                    this.left = child;
                } else {
                    this.left.addChild(child);
                }
            } else {
                if (this.right == null) {
                    this.right = child;
                } else {
                    this.right.addChild(child);
                }
            }
        }

        public void preOrder(List<Integer> answer) {
            answer.add(id);
            if (left != null) {
                left.preOrder(answer);
            }
            if (right != null) {
                right.preOrder(answer);
            }
        }

        public void postOrder(List<Integer> answer) {
            if (left != null) {
                left.postOrder(answer);
            }
            if (right != null) {
                right.postOrder(answer);
            }
            answer.add(id);
        }

        @Override
        public int compareTo(Node opponent) {
            return Integer.compare(opponent.y, this.y);
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];

        PriorityQueue<Node> pq = new PriorityQueue<>();

        /**
         * pq 구성
         */
        for (int i = 1; i <= nodeinfo.length; i++) {
            int[] n = nodeinfo[i - 1];
            Node node = new Node(i, n[0], n[1]);
            pq.add(node);
        }

        Node root = pq.remove();

        while (!pq.isEmpty()) {
            Node cur = pq.remove();
            root.addChild(cur);
        }

        List<Integer> preOrderAnswer = new ArrayList<>();
        List<Integer> postOrderAnswer = new ArrayList<>();

        root.preOrder(preOrderAnswer);
        root.postOrder(postOrderAnswer);

        for (int i = 0; i < preOrderAnswer.size(); i++) {
            answer[0][i] = preOrderAnswer.get(i);
        }
        for (int i = 0; i < postOrderAnswer.size(); i++) {
            answer[1][i] = postOrderAnswer.get(i);
        }

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};

        int[][] answer = sol.solution(nodeinfo);
        for (int[] a : answer) {
            System.out.print("[");
            for (int id : a) {
                System.out.print(id + ", ");
            }
            System.out.print("]");
        }
    }
}
