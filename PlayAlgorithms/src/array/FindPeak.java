package array;

//一个数组先递增后递减，找到峰顶的元素下标

//思路就是找打 峰顶 元素的特点， 这个元素 左边小 右边大，二分查找
public class FindPeak {
    int findPeak(int[] nums){
        if (nums != null && nums.length > 0) {
            if (nums.length == 1) {
                return 0;
            }
            if (nums[0] > nums[1]) {//数组单调递减
                return 0;
            }
            int index = nums.length-1;
            if (nums[index] > nums[index-1]) {//数组单调递增
                return index;
            }
            int i = 0, j = index;
            int mid = 0;
            while (i < j) {//二分查找
                mid = (i + j) / 2;
                if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                    return mid;
                } else if (nums[mid] > nums[mid + 1]) {//处于下坡段, 即递减段
                    j = mid - 1;
                } else if (nums[mid] > nums[mid - 1]) {//处于上坡段, 即递增段
                    i = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,4,5, 4,3,0};
        FindPeak f = new FindPeak();
        System.out.println(f.findPeak(nums));
    }

}
