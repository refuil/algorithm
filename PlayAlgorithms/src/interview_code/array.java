package interview_code;

import java.util.HashSet;
import java.util.Stack;

public class array {
    //3
    public int findRepeatNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int i : nums){
            if(set.contains(i))
                return i;
            else set.add(i);
        }
        return set.iterator().next();
    }

    //5 替换空格
    public String replaceSpace(String s) {
        return s.replaceAll(" ", "%20");
    }

    //10 斐波那契
    class Solution {
        public int fib(int n) {
            if (n <= 1){
                return n;
            }
            int[] dp = new int[n+1];
            dp[0] = 0;
            dp[1] = 1;

            for (int i = 2; i <= n; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2])%1000000007;
            }

            return dp[n];
        }
    }

    public static void main(String[] args) {
        int[] res = {5,7,7,8,8,10};

        Stack<Integer> stack = new Stack<>();
        int[] a1 = {2,2,3,2};

    }
}
