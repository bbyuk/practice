package 아이템_줍기;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private Cell[][] map = new Cell[51][51];
    private int[] dx = {0, 1, 0, -1};
    private int[] dy = {1, 0, -1, 0};

    private static class Cell {
        private boolean cross;
        private boolean inner;
        private int x;
        private int y;
        private int id1;
        private int id2;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
            cross = false;
            inner = false;
        }
    }

    private static class State {
        private Cell cell;
        private int id; // rectangleId
        private int count;

        public State(Cell cell, int id, int count) {
            this.cell = cell;
            this.id = id;
            this.count = count;
        }
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        // 1. rectangle preprocessing
        preprocessingMap(rectangle);

        // 2. bfs
        answer = bfs(characterX, characterY, itemX, itemY);

        return answer;
    }

    private int bfs(int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        Queue<State> q = new LinkedList<>();
        State start = new State(map[characterY][characterX], map[characterY][characterX].id1, 0);
        boolean[][] visit = new boolean[51][51];

        q.add(start);

        while(!q.isEmpty()) {
            State currentState = q.remove();

            visit[currentState.cell.y][currentState.cell.x] = true;
            if (currentState.cell.x == itemX && currentState.cell.y == itemY)  {
                return currentState.count;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = currentState.cell.x + dx[i];
                int nextY = currentState.cell.y + dy[i];
                if (nextX < 0 || nextX == 51 || nextY < 0 || nextY == 51) {
                    continue;
                }
                if (visit[nextY][nextX]) {
                    continue;
                }
                if (map[nextY][nextX] == null || map[nextY][nextX].inner) {
                    continue;
                }

                int nextId;

                if (currentState.cell.cross) {
                    nextId = currentState.id == currentState.cell.id1 ? currentState.cell.id2 : currentState.cell.id1;
                    if (map[nextY][nextX].id1 != nextId && map[nextY][nextX].id2 != nextId) {
                        continue;
                    }

                    q.add(new State(map[nextY][nextX], nextId, currentState.count + 1));
                    break;
                }
                else {
                    nextId = currentState.id;
                }

                if (map[nextY][nextX].id1 != nextId && map[nextY][nextX].id2 != nextId) {
                    continue;
                }

                q.add(new State(map[nextY][nextX], nextId, currentState.count + 1));
            }
        }

        return answer;
    }

    private void preprocessingMap(int[][] rectangle) {
        // index + 1 각각의 id
        for (int idx = 0; idx < rectangle.length; idx++) {
            int[] rectangleInfo = rectangle[idx];

            int leftBottomX = rectangleInfo[0];
            int leftBottomY = rectangleInfo[1];
            int rightTopX = rectangleInfo[2];
            int rightTopY = rectangleInfo[3];

            putRectanglePoint(leftBottomX, leftBottomY, idx);
            putRectanglePoint(leftBottomX, rightTopY, idx);
            putRectanglePoint(rightTopX, leftBottomY, idx);
            putRectanglePoint(rightTopX, rightTopY, idx);

            for (int x = leftBottomX + 1; x < rightTopX; x++) {
                // 아래 가로
                putRectanglePoint(x, leftBottomY, idx);

                // 위 가로
                putRectanglePoint(x, rightTopY, idx);
            }
            for (int y = leftBottomY + 1; y < rightTopY; y++) {
                // 왼 세로
                putRectanglePoint(leftBottomX, y, idx);
                // 오른 세로
                putRectanglePoint(rightTopX, y, idx);
            }

            for (int y = leftBottomY + 1; y < rightTopY; y++) {
                for (int x = leftBottomX + 1; x < rightTopX; x++) {
                    putRectanglePoint(x, y, idx);
                    map[y][x].inner = true;
                }
            }
        }
    }

    private void putRectanglePoint(int x, int y, int idx) {
        if (map[y][x] == null) {
            Cell cell = new Cell(x, y);
            cell.id1 = idx + 1;
            map[y][x] = cell;
        }
        else {
            map[y][x].cross = true;
            map[y][x].id2 = idx + 1;
        }
    }

}

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] rectangle = {{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
        int characterX = 1;
        int characterY = 3;
        int itemX = 7;
        int itemY = 8;
        int answer = sol.solution(rectangle, characterX, characterY, itemX, itemY);

        System.out.println("answer = " + answer);
    }
}
