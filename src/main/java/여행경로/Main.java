package 여행경로;

import java.util.*;

class Solution {

    private static class Node {
        String name;
        List<Edge> adjList;

        Node(String name) {
            this.name = name;
            adjList = new ArrayList<>();
        }
    }

    private static class Edge {
        Node source;
        Node target;
        boolean used;

        Edge(Node source, Node target) {
            this.source = source;
            this.target = target;
            this.used = false;
        }
    }

    private Map<String, Node> cities;
    List<List<String>> answerList;
    private int ticketCount;

    public String[] solution(String[][] tickets) {
        String[] answer = {};

        answerList = new ArrayList<>();
        ticketCount = tickets.length;
        cities = new HashMap<>();

        for (String[] ticket : tickets) {
            cities.putIfAbsent(ticket[0], new Node(ticket[0]));
            cities.putIfAbsent(ticket[1], new Node(ticket[1]));

            Node source = cities.get(ticket[0]);
            Node target = cities.get(ticket[1]);
            source.adjList.add(new Edge(source, target));
        }

        List<String> log = new ArrayList<>();
        dfs(log, cities.get("ICN"));

        answer = answerList.stream().min(Comparator.comparing(a -> String.join("", a))).orElseThrow().toArray(String[]::new);

        return answer;
    }

    public void dfs(List<String> log, Node currentCity) {
        log.add(currentCity.name);

        if (log.size() == ticketCount + 1) {
            answerList.add(new ArrayList<>(log));
            log.remove(log.size() - 1);
            return;
        }

        for (Edge ticket : currentCity.adjList) {
            if (ticket.used) {
                continue;
            }

            ticket.used = true;
            dfs(log, ticket.target);
            ticket.used = false;
        }
        log.remove(log.size() - 1);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
        String[] answer = sol.solution(tickets);
        for (String s : answer) {
            System.out.println("s = " + s);
        }
    }
}
