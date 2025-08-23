package 메뉴_리뉴얼;

import java.util.*;

class Solution {

    private static class Course implements Comparable<Course> {
        private String menus;
        private int orderCount;

        public Course(String menus, int orderCount) {
            this.menus = menus;
            this.orderCount = orderCount;
        }

        @Override
        public int compareTo(Course opponent) {
            if (this.orderCount == opponent.orderCount) {
                return this.menus.compareTo(opponent.menus);
            }
            return Integer.compare(opponent.orderCount, this.orderCount);
        }
    }

    private Map<String, Integer> orderMap = new LinkedHashMap<>();
    private List<Course>[] courses;

    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        courses = new ArrayList[course.length];

        for (int l : course) {
            for (String order : orders) {
                char[] charArray = order.toCharArray();
                for (int i = 0; i < charArray.length - l + 1; i++) {
                    dfs(i, String.valueOf(charArray[i]), l, order);
                }
            }
        }

        for (int i = 0; i < course.length; i++) {
            int length = course[i];
            courses[i] = new ArrayList<>();

            for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
                if (entry.getKey().length() == length && entry.getValue() >= 2) {
                    courses[i].add(new Course(entry.getKey(), entry.getValue()));
                }
            }

            if (!courses[i].isEmpty()) {
                Collections.sort(courses[i]);
                int max = courses[i].get(0).orderCount;
                for (Course c : courses[i]) {
                    if (c.orderCount == max) {
                        answer.add(c.menus);
                        continue;
                    }
                    break;
                }
            }
        }

        Collections.sort(answer);

        return answer.toArray(new String[0]);
    }

    private void dfs(int i, String menu, int length, String order) {
        if (menu.length() == length) {
            String sortedMenu = sort(menu);
            orderMap.putIfAbsent(sortedMenu, 0);
            Integer cnt = orderMap.get(sortedMenu);
            orderMap.replace(sortedMenu, cnt + 1);
            return;
        }

        for (int j = i + 1; j < order.length(); j++) {
            dfs(j, menu + order.charAt(j), length, order);
        }
    }

    private String sort(String menu) {
        char[] charArray = menu.toCharArray();
        Arrays.sort(charArray);

        return new String(charArray);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();

        String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course = {2, 3, 5};
        String[] answer = s.solution(orders, course);

        for (String a : answer) {
            System.out.println("a = " + a);
        }
    }
}
