package from0to100;

/**
 * 将只包含 0 1 2的数组进行排序。这里采用类似于三向快排的思想，将1做为中间，然后把02进行排序。时间复杂度为O(logn)
 */
public class SortColors {
    public void sortColors(int[] nums) {
        int l = 0, r = nums.length - 1;
        int lt = l;
        int temp;
        while (l <= r) {
            if (nums[l] < 1) {
                temp = nums[l];
                nums[l] = nums[lt];
                nums[lt] = temp;
                l++;
                lt++;
            } else if (nums[l] == 1) {
                l++;
            } else if (nums[l] > 1) {
                temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
                r--;
            }
        }
    }
}

