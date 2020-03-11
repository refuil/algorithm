package h_dynamic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solutions {

//5. 最长回文子串
    public String longestPalindrome(String s) {
        if (s.equals("")) return "";
        String origin = s;
        String reverse = new StringBuffer(s).reverse().toString();
        int length = s.length();
        int[][] dp = new int[length][length];
        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++) {
                if (origin.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                }
                /**********修改的地方*******************/
                if (dp[i][j] > maxLen) {
                    int beforeRev = length - 1 - j;
                    if (beforeRev + dp[i][j] - 1 == i) { //判断下标是否对应
                        maxLen = dp[i][j];
                        maxEnd = i;
                    }
                }
            }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
    }

    //62 不同路径
    public int uniquePaths(int m, int n) {
        int [][] dp = new int[m+1][n+1];
        //列
        for(int i = 1;i<m+1;i++){
            //行
            for(int j = 1;j<n+1;j++){
                if(i == 1 && j == 1){
                    dp[1][1] = 1;
                }else{
                    //状态转移方程
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }

    public int uniquePaths2(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (j > 0 ) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }

    //63 不同路径II
    public int uniquePathsWithObstacles(int[][] obstacleGrid){
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int [][] dp = new int[m+1][n+1];
        //列
        for(int i = 1;i<m+1;i++){
            //行
            for(int j = 1;j<n+1;j++){
                if(obstacleGrid[i-1][j-1] != 0) continue;
                if(i == 1 && j == 1){
                    dp[1][1] = 1;
                }else{
                    //状态转移方程
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if(obstacleGrid[i-1][j-1] != 0) continue;
                if (j > 0 ) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }

    //64 最小路径和
    // dp(j)=grid(i,j)+min(dp(j),dp(j+1))
    public int minPathSum(int[][] grid) {
        if(grid == null) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){

                if(j == 0){ //只能从上边
                    dp[j] = dp[j];
                } else if(i == 0){ //只能从左边
                    dp[j] = dp[j-1];
                }else{
                    dp[j] = Math.min(dp[j], dp[j-1]);
                }
                dp[j] += grid[i][j];
            }
        }
        return dp[n-1];
    }

    //91 解码方法
    //dp[i] = dp[i-1] + dp[i-2]
    public int numDecodings(String s) {
        final int length = s.length();
        int[] dp = new int[length+1];
        dp[0] = 1;

        for(int i=0;i<length;i++){
            dp[i+1] = s.charAt(i)=='0'?0:dp[i];
            if(i > 0 && (s.charAt(i-1) == '1' || (s.charAt(i-1) == '2' && s.charAt(i) <= '6'))){
                dp[i+1] += dp[i-1];
            }
        }

        return dp[length];
    }

    //120 三角形最小路径和
    //triangle[i][j] = min(triangle[i - 1][j - 1], triangle[i - 1][j])
    public int minimumTotal(List<List<Integer>> triangle) {
        // 特判
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int row = triangle.size();
        int column = triangle.get(row - 1).size();

        int[][] dp = new int[row][column];
        dp[0][0] = triangle.get(0).get(0);
        int res = Integer.MAX_VALUE;

        for (int i = 1; i < row; i++) {
            //对每一行的元素进行推导
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    // 最左端特殊处理
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                } else if (j == i) {
                    // 最右端特殊处理
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                }
            }
        }

        // dp最后一行记录了最小路径
        for (int i = 0; i < column; i++) {
            res = Math.min(res, dp[row - 1][i]);
        }
        return res;
    }

    //139 单词拆分
    public class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDictSet=new HashSet(wordDict);
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[s.length()];
        }
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        return word_Break(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
    }
    public boolean word_Break(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }


    //152. 乘积最大子序列
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for(int i=0; i<nums.length; i++){
            if(nums[i] < 0){
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax*nums[i], nums[i]);
            imin = Math.min(imin*nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }


    public static void main(String[] args) {

    }

}
