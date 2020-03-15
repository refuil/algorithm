package array;

import java.util.*;

public class Solutions {
    //6 字形变换
    public String convert1(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }


    //12 整数转罗马数字
    public String intToRoman(int num) {
        // 把阿拉伯数字与罗马数字可能出现的所有情况和对应关系，放在两个数组中
        // 并且按照阿拉伯数字的大小降序排列，这是贪心选择思想
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while (index < 13) {
            // 特别注意：这里是等号
            while (num >= nums[index]) {
                // 注意：这里是等于号，表示尽量使用大的"面值"
                stringBuilder.append(romans[index]);
                num -= nums[index];
            }
            index++;
        }
        return stringBuilder.toString();
    }

    //15 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for(int i=0; i < len-2; i++){
            if(nums[i] > 0) break;
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int target = 0 - nums[i], left = i+1, right = nums.length -1;
            while(left < right){
                if(nums[left] + nums[right] == target){
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    while(left < right && nums[left] == nums[left+1]) left++;
                    while(left < right && nums[right] == nums[right-1]) right--;
                    left++;
                    right--;
                }else if(nums[left] + nums[right] < target){
                    left++;
                }else right--;
            }
        }
        return res;
    }

    //16
    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        int close = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        int diff = Math.abs(target - close);
        for(int i =0; i < len-2; i++){
            int left = i +1, right = nums.length -1;
            while(left < right){
                int sum = nums[left] + nums[right] + nums[i];
                int newDiff = Math.abs(sum -target);
                close = diff < newDiff ? sum : close;
                if(sum < target) left++;
                else right--;
            }
        }
        return close;
    }



    //43 字符串相乘
    public String multiply(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int m = num1.length(), n = num2.length();
        int[] vals = new int[m+n];
        for(int i = m-1; i >=0; i--){
            for(int j = n-1; j >=0; j--){
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i+j+1, sum = mul + vals[p2];
                vals[p1] += sum /10;
                vals[p2] = sum %10;
            }
        }
        for(int val: vals){
            if(!res.equals("") || val != 0) res.append(val + '0');
        }
        return res.equals("") ? "0" : res.toString();
    }

    public String multiply1(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        int l1 = num1.length(), l2 = num2.length(), l = l1 + l2;
        char[] ans = new char[l];
        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();

        for (int i = l1 - 1; i >= 0; --i) {
            int c = c1[i] - '0';
            for (int j = l2 - 1; j >= 0; --j) {
                int cc = c2[j] - '0';
                ans[i + j + 1] +=  c * cc;   //模拟数学乘法
            }
        }
        for (int i = l - 1; i > 0; --i) {
            if (ans[i] > 9) {
                ans[i - 1] += ans[i] / 10;   //大于10了就要进位
                ans[i] %= 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; ; ++i) if (ans[i] != 0) break;
        for (; i < ans.length; ++i) sb.append((char) (ans[i] + '0'));
        return sb.toString();
    }

    //137. 只出现一次的数字 II
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int i =0; i < 32; i++){
            int sum =0;
            for(int j =0; j < nums.length; j++)
                sum += (nums[j] >> i) & 1;
            res |= (sum % 3) << i;
        }
        return res;
    }


    //209 长度最小的子数组
    //O(n)
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while (right < n) {
            sum += nums[right];
            right++;
            while (sum >= s) {
                min = Math.min(min, right - left);
                sum -= nums[left];
                left++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    //165 比较版本号
    public int compareVersion(String version1, String version2) {
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");
        int i = 0, j = 0;
        while (i < nums1.length || j < nums2.length) {
            //这个技巧经常用到，当一个已经遍历结束的话，我们将其赋值为 0
            String num1 = i < nums1.length ? nums1[i] : "0";
            String num2 = j < nums2.length ? nums2[j] : "0";
            int res = compare(num1, num2);
            if (res == 0) {
                i++;
                j++;
            } else {
                return res;
            }
        }
        return 0;
    }

    private int compare(String num1, String num2) {
        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);
        if (n1 > n2) {
            return 1;
        } else if (n1 < n2) {
            return -1;
        } else {
            return 0;
        }
    }

    //179 最大数
    //总定义排序
    private class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }

    public String largestNumber(int[] nums) {
        // Get input integers as strings.
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }

        // Sort strings according to custom comparator.
        Arrays.sort(asStrs, new LargerNumberComparator());

        // If, after being sorted, the largest number is `0`, the entire number
        // is zero.
        if (asStrs[0].equals("0")) {
            return "0";
        }

        // Build largest number from sorted array.
        String largestNumberStr = new String();
        for (String numAsStr : asStrs) {
            largestNumberStr += numAsStr;
        }

        return largestNumberStr;
    }

    public static void main(String[] args) {
        int[] res = {5,7,7,8,8,10};
        System.out.println((new Solutions()).intToRoman(8));

        int[] a1 = {2,2,3,2};
        System.out.println((new Solutions()).singleNumber(a1));

        String s = "1";
        System.out.println(Integer.valueOf(s));
    }

}
