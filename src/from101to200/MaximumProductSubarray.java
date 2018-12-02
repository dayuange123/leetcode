package from101to200;

import com.sun.org.apache.regexp.internal.RE;

public class MaximumProductSubarray {
    public int maxProduct1(int[] nums) {
        if (nums.length == 0) return 0;
        int[] result = new int[nums.length];
        int maxResult = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i];
            maxResult = maxResult < result[i] ? result[i] : maxResult;
            for (int j = i; j < nums.length - 1; j++) {
                result[j + 1] = result[j] * nums[j + 1];
                maxResult = maxResult < result[j + 1] ? result[j + 1] : maxResult;
            }
        }
        return maxResult;
    }

    //动态规划
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int maxResult = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            max = nums[i] * max < nums[i] ? nums[i] : nums[i] * max;
            min = nums[i] * min < nums[i] ? nums[i] * min : nums[i];
            maxResult = maxResult > max ? maxResult : max;
        }
        return maxResult;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumProductSubarray().maxProduct(new int[]{-2}));
    }
}