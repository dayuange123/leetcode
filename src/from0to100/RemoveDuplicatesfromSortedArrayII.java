package from0to100;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1:
 * <p>
 * 给定 nums = [1,1,1,2,2,3],
 * <p>
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 思路：
 * 前后指针。
 */
public class RemoveDuplicatesfromSortedArrayII {


    public int removeDuplicates(int[] nums) {

        int current = 0;
        int waite = 0;
        int fuzhi = 0;
        while (current <= nums.length) {
            if (current != nums.length && nums[current] == nums[waite]) {
                current++;
            } else {
                if (current - waite == 1) {
                    nums[fuzhi++] = nums[waite];
                } else if (current - waite >= 2) {
                    nums[fuzhi++] = nums[waite];
                    nums[fuzhi++] = nums[waite];
                }


                waite = current;
                current++;
            }
        }
        for (int num : nums) {
            System.out.println(num);
        }
        return fuzhi;
    }

    public static void main(String[] args) {
        int[] a = {1, 1, 1, 2, 2, 2, 3, 3, 4,};
        new RemoveDuplicatesfromSortedArrayII().removeDuplicates(a);
    }

    public int removeDuplicates1(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;
        for (int num : nums) {
            System.out.println(num);
        }
        return i;
    }

}
