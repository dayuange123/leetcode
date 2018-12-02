package cn.xupt.String;

public class Searchrotatedsorting {
    /**
     * 假设按照升序排列的数组在预先未知的某个关键点上旋转。
     * （即[0,1,2,4,5,6,7]可能变成[4,5,6,7,0,1,2]）。
     * 你会得到一个目标值来搜索。如果在数组中发现它返回其索引，否则返回-1。
     * 您可能会认为数组中没有重复。
     * 算法的运行时复杂度必须按照O（log  n）的顺序
     * 例1：
     * 输入： nums = [ 4,5,6,7,0,1,2]，target = 0
     * 输出： 4
     * 例2：
     * 输入： nums = [ 4,5,6,7,0,1,2]，target = 3
     * 输出： -1
     * 二分查找中间值 如果左边有序 且要寻找的数在左边 就在左边进行二分
     * 如果要找的没在左边 start 赋值为middle    下次循环 继续用右边的进行重复操作
     * 同理 如果
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            //确定中间值
            int middle = (start + end) / 2;
            if (nums[middle] == target)
                return middle;
            //右边有序 左边无序
            if (nums[middle] <= nums[end]) {
                if (nums[middle] < target && target <= nums[end]) {
                    return Searchrotatedsorting.twofen(nums, middle + 1, end, target);
                } else {
                    end = middle - 1;
                }
            }
            //左边有序 右边边无序
            else {
                if (nums[middle] > target && target >= nums[start]) {
                    return Searchrotatedsorting.twofen(nums, start, middle - 1, target);
                } else
                    start = middle + 1;
            }
        }
        if (nums[start] == target)
            return start;
        return -1;
    }
    //二分 在数组中查找
    public static int twofen(int[] nums, int start, int end, int value) {
        if (end < start)
            return -1;
        if (start == end) {
            if (nums[start] == value) {
                return start;
            }
            return -1;
        }
        int middle = (start + end) / 2;
        if (nums[middle] == value)
            return middle;
        if (value > nums[middle])
            return twofen(nums, middle + 1, end, value);
        else return twofen(nums, start, middle, value);
    }

    public static void main(String[] args) {
        System.out.println(new Searchrotatedsorting().search(new int[]{3, 4, 5, 6, 1, 2}, 2));
    }

}
