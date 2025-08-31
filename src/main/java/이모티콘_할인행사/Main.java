package 이모티콘_할인행사;

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    private int[] discountPercentage = {10, 20, 30, 40};
    private PriorityQueue<Pair> pq = new PriorityQueue<>();
    private Set<String> set = new HashSet<>();

    public class Pair implements Comparable<Pair> {
        int emoticonPlus = 0;
        int totalPrice = 0;

        @Override
        public int compareTo(Pair opponent) {
            if (opponent.emoticonPlus == this.emoticonPlus) {
                return Integer.compare(opponent.totalPrice, this.totalPrice);
            }
            return Integer.compare(opponent.emoticonPlus, this.emoticonPlus);
        }
    }

    private static class User {
        int discountPercentage;
        long maximumPrice;

        public User(int p, int m) {
            this.discountPercentage = p;
            this.maximumPrice = m;
        }

        public boolean buy(Emoticon emoticon) {
            return discountPercentage <= emoticon.discountPercentage;
        }
    }

    private static class Emoticon {
        int originalPrice;
        int discountPercentage;

        public Emoticon(int price) {
            this.originalPrice = price;
            discountPercentage = 10;
        }

        public int getDiscountPrice() {
            return originalPrice * (100 - discountPercentage) / 100;
        }
    }

    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        User[] userArr = Arrays.stream(users).map(user -> new User(user[0], user[1])).collect(Collectors.toList()).toArray(new User[0]);
        Emoticon[] emoticonArr = Arrays.stream(emoticons).mapToObj(Emoticon::new).collect(Collectors.toList()).toArray(new Emoticon[0]);

        for (int i = 0; i < 4; i++) {
            dfs(0, i, userArr, emoticonArr);
        }

        Pair best = pq.remove();

        answer[0] = best.emoticonPlus;
        answer[1] = best.totalPrice;

        return answer;
    }

    private void dfs(int e, int d, User[] users, Emoticon[] emoticons) {
        if (e == emoticons.length) {
            // 순회 ㄱ
            Pair pair = new Pair();
            for (User user : users) {
                int userPrice = 0;
                for (Emoticon emoticon : emoticons) {
                    if (user.buy(emoticon)) {
                        userPrice += emoticon.getDiscountPrice();
                    }
                }
                if (userPrice >= user.maximumPrice) {
                    pair.emoticonPlus++;
                }
                else {
                    pair.totalPrice += userPrice;
                }
            }
            String key = pair.emoticonPlus + "_" + pair.totalPrice;
            if (!set.contains(key)) {
                pq.add(pair);
                set.add(key);
            }
            return;
        }

        int beforePercentage = emoticons[e].discountPercentage;
        for (int i = 0; i < 4; i++) {
            emoticons[e].discountPercentage = discountPercentage[d];
            dfs(e + 1, i, users, emoticons);
            emoticons[e].discountPercentage = beforePercentage;
        }
    }
}

public class Main {
    public static void main(String[] args) {
//        int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
//        int[] emoticons = {1300, 1500, 1600, 4900};

        int[][] users = {{40, 10000}};
        int[] emoticons = {7000, 9000};

        Solution s = new Solution();
        int[] answer = s.solution(users, emoticons);

        for (int a : answer) {
            System.out.print(a + ", ");
        }
    }
}
