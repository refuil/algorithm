package c_search_hashTable;

import java.util.*;

//170 Two_Sum

public class Two_Sum_III {
  private HashMap<Integer, Integer> countsMap;

  /** Initialize your data structure here. */
  public Two_Sum_III() {
    countsMap = new HashMap<Integer, Integer>();
  }

  /** Add the number to an internal data structure.. */
  public void add(int number) {
    if (countsMap.containsKey(number))
      countsMap.replace(number, countsMap.get(number) + 1);
    else
      countsMap.put(number, 1);
  }

  /** Find if there exists any pair of numbers which sum is equal to the value. */
  public boolean find(int value) {
    for (Map.Entry<Integer, Integer> entry : countsMap.entrySet()) {
      int key = value - entry.getKey();
      if ((key == entry.getKey() && entry.getValue() > 1)
              || (key != entry.getKey() && countsMap.containsKey(key))) {
        return true;
      }
    }
    return false;
  }



  public boolean isIsomorphicHelper(String s, String t) {
    HashMap<Character, Character> map = new HashMap<>();
    for(int i = 0; i < s.length(); i++){
      char ch1 = s.charAt(i);
      char ch2 = t.charAt(i);
      if(map.containsKey(ch1)){
        if(map.get(ch1) != ch2)
          return false;
      }else{
        map.put(ch1, ch2);
      }
    }
    return true;

  }

//  public int maxSubArray(int[] nums) {
//    int res = Integer.MIN_VALUE;
//    for(int i =0; i < nums.length; i++){
//      for(int j=0; j <= i; j++) {
//        int sum = 0;
//        for(int k = j; k <= i; k++) {
//          sum += nums[k];
//        }
//        if(res < sum) res = sum;
//      }
//
//    }
//    return res;
//  }


  public String toString(char[] str){
    StringBuilder res = new StringBuilder();
    for(int i = 0 ; i < str.length ; i ++){
      res.append(str[i]);
    }
    return res.toString();
  }

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public LinkedList<TreeNode> generate_trees(int start, int end) {
    LinkedList<TreeNode> all_trees = new LinkedList<TreeNode>();
    if (start > end) {
      all_trees.add(null);
      return all_trees;
    }

    // pick up a root
    for (int i = start; i <= end; i++) {
      // all possible left subtrees if i is choosen to be a root
      LinkedList<TreeNode> left_trees = generate_trees(start, i - 1);

      // all possible right subtrees if i is choosen to be a root
      LinkedList<TreeNode> right_trees = generate_trees(i + 1, end);

      // connect left and right trees to the root i
      for (TreeNode l : left_trees) {
        for (TreeNode r : right_trees) {
          TreeNode current_tree = new TreeNode(i);
          current_tree.left = l;
          current_tree.right = r;
          all_trees.add(current_tree);
        }
      }
    }
    return all_trees;
  }

  public int lengthOfLongestSubstring(String s) {
    int n = s.length();
    Set<Character> set = new HashSet<>();
    int ans = 0, i = 0, j = 0;
    while (i < n && j < n) {
      // try to extend the range [i, j]
      if (!set.contains(s.charAt(j))){
        set.add(s.charAt(j++));
        ans = Math.max(ans, j - i);
      }
      else {
        set.remove(s.charAt(i++));
      }
    }
    return ans;
  }

  public List<List<String>> groupAnagrams(String[] strs) {
    //返回值是List List里面是List 装的String  定义一个HashMap 值为List
    Map<String, List> map = new HashMap<String, List>();
    for (String i : strs) {
      //将字符串转换成数组
      char[] arr = i.toCharArray();
      //将字符数组排序 eg："tea" -> "aet"
      Arrays.sort(arr);
      //再次将arr化为String
      String str = String.valueOf(arr);
      if (!map.containsKey(str)) {
        //若不存在建立映射关系 排序后的字符串—>新的List集合（装未排序的异位词）
        map.put(str, new ArrayList());
      }
      //建立映射关系户后添加 以及存在映射关系后添加单词
      map.get(str).add(i);
    }
    //返回值是List集合 通过构造器 构造一个包含指定 collection 的元素的列表
    return new ArrayList(map.values());
  }

  public String fractionToDecimal(int numerator, int denominator) {
    if (numerator == 0) {
      return "0";
    }
    StringBuilder fraction = new StringBuilder();
    // If either one is negative (not both)
    if (numerator < 0 ^ denominator < 0) {
      fraction.append("-");
    }
    // Convert to Long or else abs(-2147483648) overflows
    long dividend = Math.abs(Long.valueOf(numerator));
    long divisor = Math.abs(Long.valueOf(denominator));
    fraction.append(String.valueOf(dividend / divisor));
    long remainder = dividend % divisor;
    if (remainder == 0) {
      return fraction.toString();
    }
    fraction.append(".");
    Map<Long, Integer> map = new HashMap<>();
    while (remainder != 0) {
      if (map.containsKey(remainder)) {
        fraction.insert(map.get(remainder), "(");
        fraction.append(")");
        break;
      }
      map.put(remainder, fraction.length());
      remainder *= 10;
      fraction.append(String.valueOf(remainder / divisor));
      remainder %= divisor;
    }
    return fraction.toString();
  }

  public void sortColors(int[] nums) {
    int red =0, blue = nums.length-1;
    for(int i=0; i<= blue; i++){
      System.out.println("i:"+i + "red:"+red+"blue:"+blue);
      if(nums[i] ==0){
        swap(nums, i, red++);
      }else if(nums[i] == 2){
        swap(nums, i--, blue--);
      }
      System.out.println("i:"+i + "red:"+red+"blue:"+blue);
    }
    return;
  }

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> res = new ArrayList<>();
    int len = candidates.length;

    // 排序是为了提前终止搜索
    Arrays.sort(candidates);

    dfs(candidates, len, target, 0, new ArrayDeque<>(), res);
    return res;
  }

  /**
   * @param candidates 数组输入
   * @param len        输入数组的长度，冗余变量
   * @param residue    剩余数值
   * @param begin      本轮搜索的起点下标
   * @param path       从根结点到任意结点的路径
   * @param res        结果集变量
   */
  private void dfs(int[] candidates,
                   int len,
                   int residue,
                   int begin,
                   Deque<Integer> path,
                   List<List<Integer>> res) {
    if (residue == 0) {
      // 由于 path 全局只使用一份，到叶子结点的时候需要做一个拷贝
      res.add(new ArrayList<>(path));
      return;
    }

    for (int i = begin; i < len; i++) {

      // 在数组有序的前提下，剪枝
      if (residue - candidates[i] < 0) {
        break;
      }

      path.addLast(candidates[i]);
      dfs(candidates, len, residue - candidates[i], i, path, res);
      path.removeLast();

    }
  }

  public void swap(int[] nums, int n1, int n2){
    int temp = nums[n1];
    nums[n1] = nums[n2];
    nums[n2] = temp;
  }

  public static void main(String[] args) {
    int[] a = {-2,1,-3,4,-1,2,1,-5,4};
    HashMap<Character, Integer> map = new HashMap<>();
    int[] a1 = {2,0,2,1,1,0};

    (new Two_Sum_III()).sortColors(a1);
    List<List<Integer>> res = new ArrayList<>();
    res.remove(res.size());

  }


}




