package array;


//思路就是找打 峰谷要不是在右边，要不就是在右边
public class FindPeak {

    public int findPeakElement(int[] nums) {
        int len = nums.length;
        int left = 0; int right = len-1;
        while(left < right){
            int mid = left + (right-left)/2;
            if(nums[mid] < nums[mid+1]){
                left = mid+1;
            }else{
                right = mid;
            }

        }
        return right;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1, 1,2,3,5,4};
        FindPeak f = new FindPeak();

        System.out.println((new FindPeak()).findPeakElement(nums));
    }

}
