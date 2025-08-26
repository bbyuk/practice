package 다단계_칫솔_판매;

import java.util.*;

class Solution {

    private final int PRICE = 100;

    private static class Node {
        private String name;
        private Node parent;
        private List<Node> children;
        private int profit;

        public Node(String name) {
            this.name = name;
            this.children = new ArrayList<>();
            this.profit = 0;
        }

        public void earn(int amount) {
            if (parent == null) {
                return;
            }
            // 10% 빼서 부모주기
            int parentEarn = amount / 10;
            int myEarn = amount - parentEarn;
            this.profit += myEarn;
            parent.earn(parentEarn);
        }

        public void addChild(Node child) {
            child.parent = this;
            this.children.add(child);
        }
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        Map<String, Node> tree = new HashMap<>();
        final String CENTER = "-";

        Node root = new Node(CENTER);
        tree.put(CENTER, root);

        for (int i = 0; i < enroll.length; i++) {
            Node newNode = new Node(enroll[i]);
            tree.put(enroll[i], newNode);

            Node existNode = tree.get(referral[i]);
            existNode.addChild(newNode);
        }

        for (int i = 0; i < seller.length; i++) {
            Node sellerNode = tree.get(seller[i]);
            sellerNode.earn(amount[i] * PRICE);
        }

        for (int i = 0; i < enroll.length; i++) {
            Node node = tree.get(enroll[i]);
            answer[i] = node.profit;
        }

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {

        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        Solution sol = new Solution();
        int[] answer = sol.solution(enroll, referral, seller, amount);

        for (int i : answer) {
            System.out.println("i = " + i);
        }
    }
}
