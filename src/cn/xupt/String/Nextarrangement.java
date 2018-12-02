package cn.xupt.String;

import java.util.Arrays;

public class Nextarrangement {
    /**
     * 实施下一个排列，将数字重新排列成按字母顺序排列的下一个更大的数字排列。
     * 如果这种安排不可行，则必须将其重新排列为尽可能最低的顺序（即按升序排序）。
     * 更换必须在原地并只使用恒定的额外内存。
     * 这里有些例子。输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3→交通1,3,2
     * 3,2,1→交通1,2,3
     * 1,1,5→交通1,5,1
     * 以前在真正的采访中看过这个问题吗？
     * 难度：中等
     * 总共接受：153.6K
     * 提交总数：527.5K
     * 贡献者：LeetCode
     * 订阅 ，看看哪些公司问这个问题。
     * 相关话题
     * 类似的问题
     * 排列组合排列II 排列顺序回文排列II
     *
     *
     * 思想
     * 特殊情况：如果 这个数组全是逆序 则说明下一个肯定就是正序排列
     * 正常情况 从数组尾部开始查找  直到 那个数字打乱了逆序 记录该数对应索引
     * 然后从尾部找出 一个比他的大第一个数  进行交换  然后把 索引后的数字排序
     *
     */
    public void nextPermutation(int[] nums) {
        int flag = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                flag = 1;//代表不是全降序
                break;
            }
        }
        if (flag == 0) {
            for (int i = 0; i < (nums.length) / 2; i++) {
                int index = nums[i];
                nums[i] = nums[nums.length - i - 1];
                nums[nums.length - i - 1] = index;
            }
            for (Integer integer : nums)
                System.out.println(integer);
            return;
        }
        //132
        int index = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i + 1] > nums[i]) {
                index = i;//记录换位的索引
                break;
            }
        }
      //  System.out.println("index"+index);
        //找到交换
        for (int i = nums.length - 1; i > index; i--) {
            if (nums[index] < nums[i]) {
                int temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
                break;
            }
        }
        //将index后面的逆序
       Arrays.sort(nums,index+1,nums.length);
        for (Integer integer : nums)
            System.out.println(integer);
    }
    public static void main(String[] args) {
        new Nextarrangement().nextPermutation(new int[]{1,3,2});

    }

}
