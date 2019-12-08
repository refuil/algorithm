package array;

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

    public static void main(String[] args) {
        int[] nums = {7,1,5,3,0,4};
        System.out.println((new Training()).BestTimeBuy(nums));

        int[] nums1 = {2,1,2,2,1,2};
        System.out.println((new Training()).majorityElement(nums1));
    }
}
