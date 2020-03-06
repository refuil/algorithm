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

   public class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
  }

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

  //////////////////////////////////////

  List<List<String>> res;
  public List<List<String>> partition(String s) {
    res = new ArrayList<>();
    List<String> out = new ArrayList<>();
    helper(s, out, 0);
    return res;
  }

  //131 aab => a a b  / aa b
  public void helper(String s, List<String> out, int index){
    if(index == s.length()){
      res.add(out);
      return;
    }
    for(int i = index; i < s.length(); i++){
      if(!isPalindrome(s, index, i)) continue;
      out.add(s.substring(index, i-index+1));  //begin2 end 1 length3 outofbound
      helper(s, out, i+1);
      out.remove(out.size()-1);
    }
    return;
  }

  private boolean isPalindrome(String s, int start, int end){
    while(start < end){
      if(s.charAt(start) != s.charAt(end)) return false;
      start++;
      end--;
    }
    return true;
  }

  //格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
  //给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头
  //用到格雷码和二进制数之间的相互转化
  public List<Integer> grayCode(int n) {
    List<Integer> res = new ArrayList<>();
    for(int i =0; i< Math.pow(2,n); i++){
      res.add((i >>1) ^ 1);
    }
    return res;
  }

  //143 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
  //将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
  //你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换
  //分为以下三个小问题：
  //1. 使用快慢指针来找到链表的中点，并将链表从中点处断开，形成两个独立的链表。
  //2. 将第二个链翻转。
  //3. 将第二个链表的元素间隔地插入第一个链表中。

  public void reorderList(ListNode head) {
    if(head == null || head.next==null || head.next.next == null) return;
    ListNode fast =head;
    ListNode slow = head;
    while(fast.next !=null && fast.next.next !=null){
      slow = slow.next;
      fast = fast.next.next;
    }
    ListNode mid = slow.next;  //找到中点
    slow.next = null;
    ListNode last = mid, pre = null;
    while(last != null){      //第二个链翻转
      ListNode next = last.next;
      last.next = pre;
      pre = last;
      last = next;
    }
    while(head !=null && pre != null){  //第二个链表间隔插入第一个链表
      ListNode next = head.next;
      head.next = pre;
      pre = pre.next;
      head.next.next = next;
      head = next;
    }
  }

  //19
  //只遍历一次就完成，需要用两个指针cur pre
  //首先 cur 指针先向前走N步，如果此时 cur 指向空，说明N为链表的长度，则需要移除的为首元素，那么此时返回 head->nex即可，
  //如果 cur 存在，再继续往下走，此时 pre 指针也跟着走，
  // 直到 cur 为最后一个元素时停止，此时 pre 指向要移除元素的前一个元素，
  // 再修改指针跳过需要移除的元素即可
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if(head == null) return head;
    ListNode cur = head;
    ListNode pre = head;
    for(int i=0; i<n; i++) cur = cur.next;
    if(cur ==null) return head.next;
    //cur存在,继续走
    while(cur.next != null) {
      cur = cur.next;
      pre = pre.next;
    }
    pre.next = pre.next.next;
    return head;
  }

  //61 旋转链表
  //输入: 1->2->3->4->5->NULL, k = 2
  //输出: 4->5->1->2->3->NULL
  //一个指针的解法：遍历得到长度n，
  // 把链表头和尾链接起来，在往后走n - k % n个节点就到达新链表的头结点前一个点，这时断开链表
  public ListNode rotateRight(ListNode head, int k) {
    if(head == null) return head;
    ListNode cur = head;
    int n =1;
    while(cur.next != null) {
      cur = cur.next;
      n++;
    }
    cur.next = head;
    int m = n - k % n;
    for(int i=0; i <m; i++){
      cur = cur.next;
    }
    ListNode newhead = cur.next;
    cur.next = null;  //断开链表
    return newhead;
  }


  public static void main(String[] args) {
    int[] a = {-2,1,-3,4,-1,2,1,-5,4};
    HashMap<Character, Integer> map = new HashMap<>();
    int[] a1 = {2,0,2,1,1,0};
    ArrayList<Integer> res = new ArrayList<>();


  }


}




