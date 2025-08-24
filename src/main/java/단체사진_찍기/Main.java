package 단체사진_찍기;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int n, String[] data) {
        Set<String> photos = new HashSet<>();
        String[] friends = {"A", "C", "F", "J", "M", "N", "R", "T"};

        for (int i = 0; i < friends.length; i++) {
            boolean[] visit = new boolean[friends.length];
            dfs(photos, friends, visit, "", data);
        }

        return photos.size();
    }

    private boolean canTake(String photo, String[] data) {
        for (String checkData : data) {
            int distance = Integer.parseInt(String.valueOf(checkData.charAt(4)));
            char f1 = checkData.charAt(0);
            char f2 = checkData.charAt(2);
            char[] photoArray = photo.toCharArray();
            int f1Idx = -1;
            int f2Idx = -1;
            char condition = checkData.charAt(3);

            for (int i = 0; i < photoArray.length; i++) {
                if (photoArray[i] == f1) {
                    f1Idx = i;
                }
                if (photoArray[i] == f2) {
                    f2Idx = i;
                }
                if (f1Idx != -1 && f2Idx != -1) {
                    break;
                }
            }

            if (f1Idx == -1 || f2Idx == - 1) {
                return true;
            }

            int diff = Math.abs(f1Idx - f2Idx) - 1;
            switch(condition) {
                case '=':
                    if (diff != distance) {
                        return false;
                    }
                    break;
                case '<':
                    if (diff >= distance) {
                        return false;
                    }
                    break;
                case '>':
                    if (diff <= distance) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }

        return true;
    }

    private void dfs(Set<String> photos, String[] friends, boolean[] visit, String line, String[] data) {
        if (!canTake(line, data)) {
            return;
        }

        if (line.length() == friends.length) {
            photos.add(line);
            return;
        }

        for (int nextI = 0; nextI < friends.length; nextI++) {
            if (visit[nextI]) {
                continue;
            }
            visit[nextI] = true;
            dfs(photos, friends, visit,line + friends[nextI], data);
            visit[nextI] = false;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};
        int answer = sol.solution(n, data);

        System.out.println("answer = " + answer);
    }
}
