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

    //11 旋转数组的最小数字
    public int minArray(int[] numbers) {
        int len = numbers.length;
        int left = 0, right = len-1;
        while(left < right){
            int mid = (right + left)/2;
            if(numbers[mid] > numbers[right])
                left = mid+1;
            else if(numbers[mid] < numbers[right])
                right = mid;
            else right--;
        }
        return numbers[left];
    }

    //15
    public int hammingWeight(int n) {
        
    }

    //16 数值的整数次方
    public double myPow(double x, int n) {

    }



    public static void main(String[] args) {
        int[] res = {3,4,5,1,2};
        System.out.println((new array()).minArray(res));


    }
}
