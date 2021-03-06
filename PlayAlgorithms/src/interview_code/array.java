package interview_code;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.lang.reflect.Array;
import java.util.*;

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

    //15 二进制中1的个数
    public int hammingWeight(int n) {
        int res = 0;
        while(n != 0) {
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }

    //16 数值的整数次方
    public double myPow(double x, int n) {
        double ans = 1, temp = x;
        int exp = n;
        while (exp != 0) {
            if ((exp % 2) != 0) {
                ans = ans * temp;
            }
            temp = temp * temp;
            exp /= 2;
        }
        return n > 0 ? ans : 1 / ans;
    }


    //19 正则表达式匹配
    boolean isMatch(String s, String p) {
        if(p.isEmpty()) return s.isEmpty();
        if(p.charAt(1) == '*'){
            return isMatch(s, p.substring(2))
                    || (!s.isEmpty() && (s.charAt(0) == s.charAt(0) || p.charAt(0) == '.'))
                    && isMatch(s.substring(1), p);
        }
        else{
            return !s.isEmpty() && (s.charAt(0) == s.charAt(0) || p.charAt(0) == '.')
                    && (isMatch(s.substring(1), p.substring(1)));
        }
    }

    //20 表示数值的字符串
    /*
        核心: 有效数字的模式只有两种:
        1)A[.[B]][e|EC]  比如: +100   -67.0  29.    3.14E5
        2).B[e|EC]       比如: .3     .4E6
        其中,A、C是整数，B是正整数; [e|EC]表示[eC]或者[EC]
        原则: 有A的话,有没有B都可以; 没有A的话, 必须有B
    */
    //扫描字符串时的索引
    int i = 0;
    public boolean isNumber(String s) {
        //input check
        if (s == null || s.length() == 0)
            return false;
        //去掉首尾的空字符
        s = s.trim();
        //判断是否有A; 同时将B,C初始化为false
        boolean A = scanInteger(s), B = false, C = false;
        //判断是否有B; 使用索引时要确保索引不越界
        if (i < s.length() && s.charAt(i) == '.') {
            //i当前指向'.', 所以需要i++
            i++;
            B = scanUnsignedInteger(s);
        }
        //判断是否有C
        if (i < s.length() && (s.charAt(i) == 'e' || s.charAt(i) == 'E')) {
            i++;
            C = scanInteger(s);
            //如果存在e|E, 但是没有C, 说明不是数字
            if (C == false)
                return false;
        }
        //here, 说明C是合格的, 只需判断A和B的情况
        //i必须扫描完整个字符串 && (有A的话,有没有B都可以; 没有A的话, 必须有B)
        return i == s.length() && (A || B);

    }

    //扫描整数
    private boolean scanInteger(String s) {
        //判断是否有'+'或者'-'
        if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-'))
            i++;
        //扫描正整数
        return scanUnsignedInteger(s);
    }

    //扫描正整数
    private boolean scanUnsignedInteger(String s) {
        //起始索引
        int start = i;
        while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            i++;
        }
        //i>start说明扫描到了数字;
        //i<=start说明没有扫描到数字, 此种情况说明要么start越界, 要么s.charAt(start)不是数字
        return i > start;
    }


    //21. 调整数组顺序使奇数位于偶数前面
    public int[] exchange(int[] nums) {
        int left =0, right = nums.length-1;
        while(left < right){
            if((nums[left] & 1) != 0){
                left++;
                continue;
            }
            if((nums[right] & 1) != 1){
                right--;
                continue;
            }
            swap(nums, left, right);
        }
        return nums;
    }

    public int[] exchange1(int[] nums) {
        int fast =0, slow = 0;
        while(fast < nums.length){
            if((nums[fast] & 1) != 0){
                swap(nums, fast, slow);
                slow++;
            }
            fast++;
        }
        return nums;
    }

    private void swap(int[] nums, int l, int r){
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    //40 最小的k个数
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 最后一个参数表示我们要找的是下标为k-1的数
        return quickSearch(arr, 0, arr.length - 1, k - 1);
    }

    private int[] quickSearch(int[] nums, int lo, int hi, int k) {
        // 每快排切分1次，找到排序后下标为j的元素，如果j恰好等于k就返回j以及j左边所有的数；
        int j = partition(nums, lo, hi);
        if (j == k) {
            return Arrays.copyOf(nums, j + 1);
        }
        // 否则根据下标j与k的大小关系来决定继续切分左段还是右段。
        return j > k? quickSearch(nums, lo, j - 1, k): quickSearch(nums, j + 1, hi, k);
    }

    // 快排切分，返回下标j，使得比nums[j]小的数都在j的左边，比nums[j]大的数都在j的右边。
    private int partition(int[] nums, int lo, int hi) {
        int v = nums[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (++i <= hi && nums[i] < v);
            while (--j >= lo && nums[j] > v);
            if (i >= j) {
                break;
            }
            int t = nums[j];
            nums[j] = nums[i];
            nums[i] = t;
        }
        nums[lo] = nums[j];
        nums[j] = v;
        return j;
    }

    //40
    // 保持堆的大小为K，然后遍历数组中的数字，遍历的时候做如下判断：
    // 1. 若目前堆的大小小于K，将当前数字放入堆中。
    // 2. 否则判断当前数字与大根堆堆顶元素的大小关系，如果当前数字比大根堆堆顶还大，这个数就直接跳过；
    //    反之如果当前数字比大根堆堆顶小，先poll掉堆顶，再将该数字放入堆中。
    public int[] getLeastNumbers1(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 默认是小根堆，实现大根堆需要重写一下比较器。
        Queue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int num: arr) {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (num < pq.peek()) {
                pq.poll();
                pq.offer(num);
            }
        }

        // 返回堆中的元素
        int[] res = new int[pq.size()];
        int idx = 0;
        for(int num: pq) {
            res[idx++] = num;
        }
        return res;
    }


    //42 连续子数组的最大值
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        for(int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    public int maxSubArray1(int[] nums) {
        //设置sum的初值为分解点；
        int sum = 0;
        //从nums[0]开始更新max;
        int max = nums[0];
        for(int i=0; i < nums.length; i++){
            if(sum <= 0){
                sum = nums[i];
            }else{
                sum += nums[i];
            }
            if(sum > max){
                max = sum;
            }
        }
        return max;
    }

    //43. 1-n整数中1出现的次数
    // 递归求解
    public int countDigitOne(int n) {
        return dfs(n);
    }

    private int dfs(int n) {
        if (n <= 0) {
            return 0;
        }

        String numStr = String.valueOf(n);
        int high = numStr.charAt(0) - '0';
        int pow = (int) Math.pow(10, numStr.length() - 1);
        int last = n - high * pow;

        if (high == 1) {
            // 最高位是1，如1234, 此时pow = 1000,那么结果由以下三部分构成：
            // (1) dfs(pow - 1)代表[0,999]中1的个数;
            // (2) dfs(last)代表234中1出现的个数;
            // (3) last+1代表固定高位1有多少种情况。
            return dfs(pow - 1) + dfs(last) + last + 1;
        } else {
            // 最高位不为1，如2234，那么结果也分成以下三部分构成：
            // (1) pow代表固定高位1，有多少种情况;
            // (2) high * dfs(pow - 1)代表999以内和1999以内低三位1出现的个数;
            // (3) dfs(last)同上。
            return pow + high * dfs(pow - 1) + dfs(last);
        }
    }

    //44
//    public:
//    int findNthDigit(int n) {
//        // 计算该数字由几位数字组成，由1位：digits = 1；2位：digits = 2...
//        long base = 9,digits = 1;
//        while (n - base * digits > 0){
//            n -= base * digits;
//            base *= 10;
//            digits ++;
//        }
//
//        // 计算真实代表的数字是多少
//        int idx = n % digits;  // 注意由于上面的计算，n现在表示digits位数的第n个数字
//        if (idx == 0)idx = digits;
//        long number = 1;
//        for (int i = 1;i < digits;i++)
//            number *= 10;
//        number += (idx == digits)? n/digits - 1:n/digits;
//
//        // 从真实的数字中找到我们想要的那个数字
//        for (int i=idx;i<digits;i++) number /= 10;
//        return number % 10;
//    }

    //46 把数字翻译成字符串
    public int translateNum(int num) {
        char[] sc = String.valueOf(num).toCharArray();
        int n = sc.length;
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            //if (sc[i - 1] >= '0' && sc[i - 1] <= '9') {
            f[i] += f[i - 1];
            //}
            if (i > 1) {
                int a = (sc[i - 2] - '0') * 10 + (sc[i - 1] - '0');
                if (a >= 10 && a <= 25) {
                    f[i] += f[i - 2];
                }
            }
        }
        return f[n];
    }

    public int translateNum1(int num) {
        String str = String.valueOf(num);
        int[] dp = new int[11];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i < str.length(); i ++) {
            if (str.charAt(i-1) == '0' ||
                    Integer.valueOf(str.substring(i-1, 2)) > 25) {
                dp[i+1] = dp[i];
            } else {
                dp[i+1] = dp[i] + dp[i-1];
            }
        }
        return dp[str.length()];
    }

    //48 最长不含重复字符的子字符串
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        Set<Character> set = new HashSet<>();
        for(int l = 0, r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            while(set.contains(c)) {
                set.remove(s.charAt(l++));
            }
            set.add(c);
            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    //49 丑数
    public int nthUglyNumber(int n) {
        int p2=0,p3=0,p5=0;
        int[] dp=new int[n];
        dp[0]=1;
        for(int i=1;i<n;i++){
            dp[i]=Math.min(dp[p2]*2,Math.min(dp[p3]*3,dp[p5]*5));
            if(dp[i]==dp[p2]*2) p2++;
            if(dp[i]==dp[p3]*3) p3++;
            if(dp[i]==dp[p5]*5) p5++;
        }
        return dp[n-1];
    }

    //50.第一个只出现一次的字符
    public char firstUniqChar(String s) {
        HashMap<Character, Integer> dic = new HashMap<>();
        char[] sc = s.toCharArray();
        for(char c : sc) {
            if(!dic.containsKey(c)) dic.put(c, 1);
            else dic.put(c, dic.get(c) + 1);
        }
        for(char c : sc) {
            if(dic.get(c) == 1) return c;
        }
        return ' ';
    }

    //51. 数组中的逆序对
    // 后有序数组中元素出列的时候，计算逆序个数

    public int reversePairs(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }
        int[] temp = new int[len];
        return reversePairs(nums, 0, len - 1, temp);
    }

    /**
     * 计算在数组 nums 的索引区间 [left, right] 内统计逆序对
     *
     * @param nums  待统计的数组
     * @param left  待统计数组的左边界，可以取到
     * @param right 待统计数组的右边界，可以取到
     * @return
     */
    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        // 极端情况下，就是只有 1 个元素的时候，这里只要写 == 就可以了，不必写大于
        if (left == right) {
            return 0;
        }

        int mid = (left + right) >>> 1;

        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);

        int reversePairs = leftPairs + rightPairs;
        if (nums[mid] <= nums[mid + 1]) {
            return reversePairs;
        }

        int reverseCrossPairs = mergeAndCount(nums, left, mid, right, temp);
        return reversePairs + reverseCrossPairs;

    }

    /**
     * [left, mid] 有序，[mid + 1, right] 有序
     * @param nums
     * @param left
     * @param mid
     * @param right
     * @param temp
     * @return
     */
    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        // 复制到辅助数组里，帮助我们完成统计
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;
        int res = 0;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                // i 用完了，只能用 j
                nums[k] = temp[j];
                j++;
            } else if (j > right) {
                // j 用完了，只能用 i
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                // 此时前数组元素出列，不统计逆序对
                nums[k] = temp[i];
                i++;
            } else {
                // 此时后数组元素出列，统计逆序对，快就快在这里，一次可以统计出一个区间的个数的逆序对
                nums[k] = temp[j];
                j++;
                res += (mid - i + 1);
            }
        }
        return res;
    }

    //57 和为s的连续正数序列
    public int[][] findContinuousSequence(int target) {
        int i = 1; // 滑动窗口的左边界
        int j = 1; // 滑动窗口的右边界
        int sum = 0; // 滑动窗口中数字的和
        List<int[]> res = new ArrayList<>();

        while (i <= target / 2) {
            if (sum < target) {
                // 右边界向右移动
                sum += j;
                j++;
            } else if (sum > target) {
                // 左边界向右移动
                sum -= i;
                i++;
            } else {
                // 记录结果
                int[] arr = new int[j-i];
                for (int k = i; k < j; k++) {
                    arr[k-i] = k;
                }
                res.add(arr);
                // 左边界向右移动
                sum -= i;
                i++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }


    //58 翻转单词顺序
    public String reverseWords(String s){
        String[] a = s.trim().split(" +");
        StringBuilder sb = new StringBuilder();
        for(int i = a.length-1;i>=0;i--){
            sb.append(a[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    //59-I. 滑动窗口的最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        int left=0, right = left + k;
        int max = nums[0];
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0; i < nums.length-k; i++){
            max = Math.max(nums[left], nums[right]);
            res.add(max);
            left++;
            right++;
        }
        int[] ans = new int[res.size()];
        for(int i=0; i< res.size(); i++) ans[i] = res.get(i);
        return ans;
    }

    //59-II. 队列的最大值

    //60. n个骰子的点数
    public double[] twoSum(int n) {
        int [][]dp = new int[n+1][6*n+1];
        //边界条件
        for(int s=1;s<=6;s++)dp[1][s]=1;
        for(int i=2;i<=n;i++){
            for(int s=i;s<=6*i;s++){
                //求dp[i][s]
                for(int d=1;d<=6;d++){
                    if(s-d<i-1)break;//为0了
                    dp[i][s]+=dp[i-1][s-d];
                }
            }
        }
        double total = Math.pow((double)6,(double)n);
        double[] ans = new double[5*n+1];
        for(int i=n;i<=6*n;i++){
            ans[i-n]=((double)dp[n][i])/total;
        }
        return ans;
    }


    //61. 扑克牌中的顺子

    //62. 圆圈中最后剩下的数字

    //63. 股票最大收益

    public static void main(String[] args) {
        int[] res = {3,4,5,1,2};
        System.out.println((new array()).firstUniqChar("leetcode"));

        Queue<Integer> queue = new ArrayDeque<>();

    }
}
