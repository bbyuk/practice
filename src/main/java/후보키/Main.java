package 후보키;

import java.util.*;
import java.util.stream.Collectors;

class Solution {

    private List<String> keys = new ArrayList<>();

    public int solution(String[][] relation) {

        // "0,1,3" : [ "100_ryan"... ]
        /**
         * {
         *     "0,1,2" : ["100_music_2"]
         * }
         */

        for (int i = 0; i < relation[0].length; i++) {
            Map<String, Set<String>> table = new HashMap<>();
            traverse(String.valueOf(i), relation, table);
        }

        // 1) 인덱스 "개수" 기준으로 정렬 (최소성 필터를 정확히 적용하기 위해)
        keys.sort(Comparator.comparingInt(k -> k.split(",").length));

        // 2) 최소성 필터: 앞(더 작은) 키가 부분집합이면 뒤(큰) 키 제거
        boolean[] remove = new boolean[keys.size()];
        for (int i = 0; i < keys.size(); i++) {
            Set<Integer> small = toSet(keys.get(i));
            for (int j = i + 1; j < keys.size(); j++) {
                if (remove[j]) continue;
                Set<Integer> big = toSet(keys.get(j));
                if (big.containsAll(small)) {
                    remove[j] = true; // 최소성 위반: 큰 키 제거
                }
            }
        }
        List<String> filtered = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            if (!remove[i]) filtered.add(keys.get(i));
        }
        keys = filtered;

        return keys.size();
    }


    private Set<Integer> toSet(String key) {
        return Arrays.stream(key.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }


    private void traverse(String idx, String[][] relation, Map<String, Set<String>> table) {

        table.putIfAbsent(idx, new HashSet<>());
        boolean correct = true;
        int[] colIdxArr = Arrays.stream(idx.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int row = 0; row < relation.length; row++) {
            StringBuilder sb = new StringBuilder();
            Set<String> values = table.get(idx);

            for (int col : colIdxArr) {
                sb.append(relation[row][col]);
            }
            String currentRowValue = sb.toString();

            if (values.contains(currentRowValue)) {
                correct = false;
                break;
            }

            values.add(currentRowValue);
        }

        if (correct) {
            keys.add(idx);
        }
        else {
            int nextIdx = colIdxArr[colIdxArr.length - 1] + 1;
            for (int i = nextIdx; i < relation[0].length; i++) {
                traverse(idx + "," +i, relation, table);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[][] relation = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}};

        int answer = sol.solution(relation);
        System.out.println("answer = " + answer);
    }
}
