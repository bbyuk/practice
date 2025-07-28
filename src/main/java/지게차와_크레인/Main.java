package 지게차와_크레인;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] storage = {"AZWQY", "CAABX", "BBDDA", "ACACA"};
        String[] requests= {"A", "BB", "A"};
        int answer = solution.solution(storage, requests);

        System.out.println(answer == 11);
    }
}
