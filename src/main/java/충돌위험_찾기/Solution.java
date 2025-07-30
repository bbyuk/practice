package 충돌위험_찾기;

import javax.swing.plaf.nimbus.State;
import java.util.*;

public class Solution {

    private final int[] di = {-1, 1, 0, 0};
    private final int[] dj = {0, 0, 1, -1};


    class Point {
        int i;
        int j;

        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        int getValue() {
            return 100 * i + j;
        }
    }

    class RobotState {
        Point pos;
        List<Point> path = new ArrayList<>();

        public RobotState(int i, int j) {
            pos = new Point(i, j);
        }

        public void addPathFromPrevState(RobotState prevState) {
            if (prevState != null) {
                path.addAll(prevState.path);
            }
            path.add(pos);
        }
    }

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        // 1. bfs로 각 경로로 최단경로 찍기
        // 2. 시간마다 경로 상태를 저장
        // 2. 충돌지점을 저장할 때 이 충돌시점이 최단경로임을 어떻게 보장?
        int maxTime = 0;
        List<List<Point>> paths = new ArrayList<>();
        for (int[] route : routes) {
            List<Point> path = new ArrayList<>();

            boolean isFirst = true;
            for (int i = 0; i < route.length - 1; i++) {
                int sourceId = route[i] - 1;
                int destinationId = route[i + 1] - 1;

                int[] source = points[sourceId];
                int[] destination = points[destinationId];

                List<Point> foundPath = bfs(source, destination);
                if (!isFirst) {
                    path.addAll(foundPath.subList(1, foundPath.size()));
                }
                else {
                    path.addAll(foundPath);
                }
                isFirst = false;
            }
            maxTime = Math.max(maxTime, path.size());
            paths.add(path);
        }

        for (int time = 0; time < maxTime; time++) {
            int[] pos = new int[10000];
            Set<Integer> collision = new HashSet<>();
            for (List<Point> path : paths) {
                if (path.size() <= time) {
                    continue;
                }

                pos[path.get(time).getValue()]++;
                if (pos[path.get(time).getValue()] > 1) {
                    collision.add(path.get(time).getValue());
                }
            }
            answer += collision.size();
        }


        return answer;
    }

    public List<Point> bfs(int[] source, int[] destination) {

        RobotState initState = new RobotState(source[0] - 1, source[1] - 1);
        initState.addPathFromPrevState(null);
        boolean[][] visitied = new boolean[100][100];
        visitied[source[0] - 1][source[1] - 1] = true;

        Queue<RobotState> queue = new LinkedList<>();
        queue.add(initState);

        while(!queue.isEmpty()) {
            RobotState currentState = queue.remove();
            if (currentState.pos.i == destination[0] - 1 && currentState.pos.j == destination[1] - 1) {
                return currentState.path;
            }

            for (int w = 0; w < 4; w++) {
                int nextI = currentState.pos.i + di[w];
                int nextJ = currentState.pos.j + dj[w];

                if (nextI < 0 || nextI == 100 || nextJ < 0 || nextJ == 100 || visitied[nextI][nextJ]) {
                    continue;
                }
                visitied[nextI][nextJ] = true;
                RobotState nextState = new RobotState(nextI, nextJ);
                nextState.addPathFromPrevState(currentState);

                queue.add(nextState);
            }
        }

        return null;
    }
}
