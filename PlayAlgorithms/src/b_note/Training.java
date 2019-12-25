package b_note;

import e_stackAndQueue.Binary_Tree_Inorder_Traversal;

import java.lang.reflect.Array;
import java.util.*;

public class Training {

    //122 股票最佳投资机会
    private static int BestTimeBuy(int[] nums){
        int res = 0;

        for(int k =0; k< nums.length -1; k++){
            if(nums[k] < nums[k+1]){
                res += nums[k+1] - nums[k];
            }

        }
        return res;
    }

    //169 求最大众数
    private int majorityElement(int[] nums) {
        int res = 0, cnt = 0;
        for (int num : nums) {
            if (cnt == 0) {res = num; ++cnt;}
            else if (num == res) ++cnt;
            else --cnt;
        }
        return res;
    }

    //67-Binary Add  https://www.cnblogs.com/grandyang/p/4084971.html
    private String addBinary(String a, String b){
        String res = "";
        int m = a.length()-1; int n = b.length()-1;
        int carry = 0;
        while(m >= 0 || n >= 0){
            int p = m >= 0 ? a.charAt(m--) - '0' : 0;
            int q = n >= 0 ? b.charAt(n--) - '0' : 0;
            int sum = p + q + carry;
            res = (sum %2) + res;
            carry = sum /2;
        }

        return carry == 1 ? "1" + res : res;
    }

    //125 Valid Palindrome  https://www.cnblogs.com/grandyang/p/4030114.html
    public boolean isPalindrome(String s) {
            int left = 0, right = s.length() - 1 ;
            while (left < right) {
                if (!isAlphaNum(s.charAt(left)))
                    ++left;
                else if (!isAlphaNum(s.charAt(right)))
                    --right;
//                else if ((s.charAt(left) + 32 - 'a') %32 != (s.charAt(right) + 32 - 'a') % 32)
                else if(s.charAt(left) != s.charAt(right))
                    return false;
                else {
                    ++left; --right;
                }
            }
            return true;
    }

    boolean isAlphaNum(char ch) {
        if (ch >= 'a' && ch <= 'z') return true;
        if (ch >= 'A' && ch <= 'Z') return true;
        if (ch >= '0' && ch <= '9') return true;
        return false;
    }

    //345 Reverse Vowels of string  https://www.cnblogs.com/grandyang/p/5426682.html
    public String reverseVowels(String s) {
        char[] str = s.toCharArray();
        int left = 0, right= s.length() - 1;
        while (left < right) {
            if (isVowel(s.charAt(left)) && isVowel(s.charAt(right))) {
                swap(str, left++, right--);
            } else if (isVowel(s.charAt(left))) {
                --right;
            } else {
                ++left;
            }
        }
        return new String(str);
    }
    boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
    void swap(char[] s, int a, int b){
        char t = s[a];
        s[a] = s[b];
        s[b] = t;
    }

    //443 String Compression  https://www.cnblogs.com/grandyang/p/8742564.html
    private int compress(char[] chars){
        int n = chars.length;
        int cur = 0;
        for(int i = 0, j =0; i < n; i = j){
            while(j < n && chars[j] == chars[i])
                ++j;
            chars[cur++] = chars[i];
            if(j - i == 1) continue;
            for(char c: String.valueOf(j-i).toCharArray())
                chars[cur++] = c;
        }
        return cur;
    }

    public int compress1(char[] chars) {
        int t=0;//设置指针
        int i=0;
        while (i <chars.length && t<chars.length) {//遍历字符串
            chars[t++]=chars[i];//取相同字符序列的首字符存下
            int temp=i;//记录相同字符序列首元素位置
            while (i<chars.length &&chars[i]==chars[t-1])
                i++;//i指针滑动到相同字符序列末尾的下一个位置
            if(i-temp>1){//若相同字符序列长度大于1
                for(char c:String.valueOf(i-temp).toCharArray()){//向结果中加入相同字符序列的长度的字符形式
                    chars[t++]=c;
                }
            }
        }
        return t;//t即为已压缩的结果的长度
    }

    //459-repeated substring pattern
    public boolean repeatedSubstring(String str){
        int n = str.length();
        for(int i = n /2; i >= 1; --i){
            if(n % i == 0){
                int c = n/i;
                String t = "";
                for(int j = 0; j < c; ++j){
                    t += str.substring(0, i);
                }
//                System.out.println("c "+ c + " i " + i + " j "+j+" "+t);
                if(t.equals(str)) return true;
            }
        }
        return false;
    }

    //541-reverse string with k
    public String reverseStr(String s, int k){
        StringBuilder strBulter = new StringBuilder();

        for(int i =0; i < s.length(); i = i+ 2*k){
            if(s.length() >= (i+k)){
                strBulter.append(new StringBuilder(s.substring(i, i+k)).reverse());
            }else{
                strBulter.append(new StringBuilder(i).reverse());
                break;
            }
            if((i + 2*k) <= s.length()){
                strBulter.append(s.substring(i+k, i+2*k));
            }else{
                strBulter.append(s.substring(i+k));
            }
        }
        return strBulter.toString();
    }

    //557-reverse string III
    public String reverseStr3(String s){
        StringBuilder strBulter = new StringBuilder();
        int cur = 0;
        for(int i =0; i < s.length(); i++){
            if(i == s.length() -1){
                strBulter.append(new StringBuilder(s.substring(i-cur, i+1)).reverse());
            }else if(s.charAt(i) == ' '){
                strBulter.append(new StringBuilder(s.substring(i-cur, i)).reverse());
                strBulter.append(" ");
                cur = 0;
            }else{
                ++cur;
            }
        }
        return strBulter.toString();
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //606-Construct string from binary tree
    public String tree2str(TreeNode t){
        if(t == null) return "";
        if (t.left == null && t.right == null) {
            return String.valueOf(t.val);
        }
        if (t.left == null) {
            return String.valueOf(t.val) + "()" + "(" + tree2str(t.right) + ")";
        }
        if (t.right == null) {
            return String.valueOf(t.val) + "(" + tree2str(t.left) + ")";
        }
        return String.valueOf(t.val) + "(" + tree2str(t.left) + ")" + "(" + tree2str(t.right) + ")";
    }

    //680-valid Palindrome II 可以删除一个字符保持回文
    public boolean validPalindrome(String str){
        int left = 0;
        int right = str.length() -1;
        while(left < right) {
            if (str.charAt(left) != str.charAt(right))
                return idValid(str, left, right - 1) || idValid(str, left-1, right);
            ++left;
            --right;
        }
        return true;
    }

    private boolean idValid(String str, int left, int right) {
        while(left < right) {
            if (str.charAt(left) != str.charAt(right))
                return false;
            ++left;
            --right;
        }
        return true;
    }

    //686-repearted string match-- A叠加几次成为后B成为其子串
    private int repeatedString(String A, String B){
        int n1 = A.length();
        int n2 = B.length();
        int cnt = 1; String t = A;
        while(t.length() < n2){
            t += A;
            ++cnt;
        }
        if(t.contains(B)) return cnt;
        t += A;
        return (t.contains(B)) ? cnt +1: -1;
    }

    //696--count binary substring
    public int countBinarySubstring(String str){
        int zeros = 0; int ones = 0; int res = 0;
        for(int i =0; i < str.length(); ++i){
            if(i == 0) {
                if(str.charAt(i) == '1') ++ones; else ++zeros;
            }else {
                if(str.charAt(i) == '1'){
                    ones = (str.charAt(i-1) == '1') ? ones +1 :1;
                    if(zeros >= ones) ++res;
                }else if(str.charAt(i) == '0'){
                    zeros = (str.charAt(i-1) == '0') ? zeros + 1 :1;
                    if(ones >= zeros) ++res;
                }
            }
        }
        return res;
    }

    //804--Unique Morse Code Words
    public int uniqueMorse(String[] nums){
        String[] morse = {".-","-...","-.-.","-..",".","..-.","--.",
                "....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",
                ".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        HashSet set = new HashSet<String>();
        for(String w: nums){
            String t = "";
            for(char c: w.toCharArray()){
                t += morse[c - 'a'];
            }
            set.add(t);
        }
        return set.size();
    }

    //859--Buddy Strings
    private boolean buddyString(String A, String B){
        int n2 = B.length();
        int left = 0; int right= n2-1;
        char[] charA = A.toCharArray();
        int l = 0; int r = 0;
        ArrayList<Integer> cur = new ArrayList<>();
        while(left <= right){
            if (A.charAt(left) != B.charAt(left)) {
                cur.add(left);
                left++;
            }
        }
        l = cur.get(0);
        r = cur.get(1);
        if(cur.size() ==2){
            swap(charA, l, r);
            return (String.valueOf(charA).equals(B))? true : true;
        }
        return false;
    }

    //893--Groups of Special-Equivalent Strings 特殊字符串的群组
    private int groupSpecialString(String[] strs){
        Set<String> groups = new HashSet<>();
        for(String word: strs){
            char[] odd = new char[(word.length()+1) /2];
            char[] even = new char[(word.length()+1)/2];
            for(int i=0; i < word.length(); ++i){
                if(i % 2 ==0)
                    even[i/2] = word.charAt(i);
                else
                    odd[i/2] = word.charAt(i);
            }
            Arrays.sort(odd);
            Arrays.sort(even);
            String s = new String(odd) + new String(even);
            groups.add(s);
        }
        return groups.size();
    }

    public static void main(String[] args) {
        int[] nums = {7,1,5,3,0,4};
        System.out.println((new Training()).BestTimeBuy(nums));

        int[] nums1 = {2,1,2,2,1,2};
        System.out.println((new Training()).majorityElement(nums1));

        System.out.println((new Training()).addBinary("11", "1"));

        System.out.println((new Training()).isPalindrome("level"));

        System.out.println((new Training()).reverseVowels("abci"));

        char[] chars = {'a','a','b','c','b','b','b'};
        System.out.println((new Training()).compress(chars));

        System.out.println((new Training()).repeatedSubstring("abcabcabc"));

        //todo 有问题
        System.out.println((new Training()).reverseStr("abcdefghijklmn", 3));
        System.out.println((new Training()).reverseStr3("let us talk"));

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        TreeNode node4 = new TreeNode(4);
        node2.right = node4;
        System.out.println((new Training()).tree2str(node1));

        System.out.println((new Training()).validPalindrome("leveel"));

        System.out.println((new Training()).repeatedString("abcd", "cdabcdab"));
        //todo
        System.out.println((new Training()).countBinarySubstring("00110011"));

        char C = 'C';
        C+=32;  System.out.println(C);

        String[] words = {"gin", "zen"};
        System.out.println("unique morse words: " + (new Training()).uniqueMorse(words));

        System.out.println("buddy Strings:" + (new Training()).buddyString("ab","ba"));

        String[] str = {"abc","acb","bac","bca","cab","cba"};
        System.out.println("Groups special str: "+ (new Training()).groupSpecialString(str));
    }
}
