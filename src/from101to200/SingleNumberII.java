package from101to200;

/**
 * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
 *
 * Note:
 *
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 *
 * Input: [2,2,3,2]
 * Output: 3
 * Example 2:
 *
 * Input: [0,1,0,1,0,1,99]
 * Output: 99
 *
 *
 * 如果是两次 可以使用异或 直接操作。
 * 如果是三次就不行了。
 *
 * 下面这个算法的思想，其实很简单，就是对每一位进行操作。
 * 因为出现三次的数我们忽略，所以将每一个位的为1的加起来，然后对三取余，如果结果不为0，那么说明  这一位就是要找的这个数对应的位，我们通过左移和或操作给它累积上
 */
public class SingleNumberII {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for(int i = 0; i < 32; i++) {
            int sum = 0;
            for(int j = 0; j < nums.length; j++) {
                if(((nums[j] >> i) & 1) == 1) {
                    sum++;
                }
            }
            sum %= 3;
            if(sum != 0) {
                ans |= sum << i;
            }
        }
        return ans;
    }
}
