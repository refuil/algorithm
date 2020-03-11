package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Solutions {
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

    public static void main(String[] args) {
        int[] res = {5,7,7,8,8,10};
        System.out.println((new Solutions()).intToRoman(8));

        int[] a1 = {2,2,3,2};
        System.out.println((new Solutions()).singleNumber(a1));
    }

}
