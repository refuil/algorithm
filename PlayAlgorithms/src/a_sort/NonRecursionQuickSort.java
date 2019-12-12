package a_sort;

import java.util.Arrays;
import java.util.Stack;

public class NonRecursionQuickSort {
    // 我们的算法类不允许产生任何实例
    private NonRecursionQuickSort(){}

    // 对arr[l...r]部分进行partition操作
    // 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
    private static int partition(int[] arr, int l, int r){

        int v = arr[l];

        int j = l; // arr[l+1...j] < v ; arr[j+1...i) > v
        for( int i = l + 1 ; i <= r ; i ++ )
            if( arr[i] < v ){
                j ++;
                swap(arr, j, i);
            }

        swap(arr, l, j);

        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // start和end为前闭后闭
    private static void nonRec_quickSort(int[] a, int start, int end) {
        // 用栈模拟
        Stack<Integer> stack = new Stack<>();
        if (start < end) {
            stack.push(end);
            stack.push(start);
            while (!stack.isEmpty()) {
                int l = stack.pop();
                int r = stack.pop();
                int index = partition(a, l, r);
                if (l < index - 1) {
                    stack.push(index - 1);
                    stack.push(l);
                }
                if (r > index + 1) {
                    stack.push(r);
                    stack.push(index + 1);
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }

    // 测试 QuickSort
    public static void main(String[] args) {
        int[] nums = {3,4,1,2,5};
        (new NonRecursionQuickSort()).nonRec_quickSort(nums, 0 ,nums.length-1);

        return;
    }



}
