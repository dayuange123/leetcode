package from0to100;

/**
 * 合并两个有序数组
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * Output: [1,2,2,3,5,6]
 * <p>
 * num1有足够的空间。
 * 并且我们知道两个数组中元素个数
 * 对于这样得题目 我们只需要从num1后面开始，从尾部到头进行操作
 */
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int num1Index = m - 1;
        int end = m + n - 1;
        for (int i = m - 1; i >= 0; i--) {
            if (num1Index < 0 || nums2[i] >= nums1[num1Index]) {
                nums1[end] = nums2[i];
            } else {
                nums1[end] = nums1[num1Index];
                num1Index--;
                i++;
            }
            end--;
        }
    }
}
