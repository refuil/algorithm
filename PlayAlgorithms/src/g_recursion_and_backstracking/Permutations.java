package g_recursion_and_backstracking;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

//全排列
//开辟三个空间 ArrayList<List<Integer>> res 用于存储最后的数组结果
//临时空间  LinkedList<Integer> p  借用链表的结构，增删最后一个元素，遍历一次复制给res
//临时空间  boolean[] used 用于标记元素在上一层中已经用过
public class Permutations {

    private ArrayList<List<Integer>> res;
    private boolean[] used;

    public List<List<Integer>> permute(int[] nums) {

        res = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0)
            return res;

        used = new boolean[nums.length];
        LinkedList<Integer> p = new LinkedList<Integer>();
        generatePermutation(nums, 0, p);

        return res;
    }

    // p中保存了一个有index-1个元素的排列。
    // 向这个排列的末尾添加第index个元素, 获得一个有index个元素的排列
    private void generatePermutation(int[] nums, int index, LinkedList<Integer> p){

        if(index == nums.length){
            res.add((LinkedList<Integer>)p.clone());
            return;
        }

        for(int i = 0 ; i < nums.length ; i ++)
            if(!used[i]){
                used[i] = true;
                p.addLast(nums[i]);
                generatePermutation(nums, index + 1, p );
                p.removeLast();   //回溯
                used[i] = false;
            }

        return;
    }

    private static void printList(List<Integer> list){
        for(Integer e: list)
            System.out.print(e + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3};
        List<List<Integer>> res = (new Permutations()).permute(nums);
        for(List<Integer> list: res)
            printList(list);

    }
}
