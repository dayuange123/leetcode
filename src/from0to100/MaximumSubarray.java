package from0to100;

import com.sun.org.apache.regexp.internal.RE;

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0)
            return 0;

        int returnMax = nums[0];

        for (int i = 0; i < nums.length; i++) {
            int max = nums[i];
            for (int j = i + 1; j < nums.length; ++j) {
                max += nums[j];
                returnMax = max > returnMax ? max : returnMax;
            }
        }
        return returnMax;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSubarray().maxSubArray3(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public int maxSubArray1(int[] nums) {
        int max = Integer.MIN_VALUE;
        int maxending = 0;

        for (int i = 0; i < nums.length; i++) {
            maxending += nums[i];
            if (max < maxending) {
                max = maxending;
            }
            if (maxending < 0) {
                maxending = 0;
            }
        }
        return max;
    }

    public int maxSubArray3(int[] nums) {

        return maxRescure(nums, 0, nums.length - 1);

    }

    private int maxRescure(int[] nums, int l, int r) {
        if (l >= r)
            return nums[l];
        int center = l + (r - l) / 2;
        int lmax = maxRescure(nums, l, center - 1);
        int rmax = maxRescure(nums, center + 1, r);
        int cmax = nums[center], ct = cmax;
        for (int i = center - 1; i >= l; --i) {
            ct += nums[i];
            cmax = ct > cmax ? ct : cmax;
        }
        ct = cmax;
        for (int i = center + 1; i <= r; ++i) {
            ct += nums[i];
            cmax = ct > cmax ? ct : cmax;
        }
        return Math.max(lmax, Math.max(rmax, cmax));
    }
}