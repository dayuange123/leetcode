package from0to100;

public class JumpGameII {
    public int jump(int[] nums) {
        int[] minStaps = new int[nums.length];

        if (nums.length < 2) return 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = 1; j <= nums[i] && i + j < nums.length; j++) {
                if (minStaps[i + j] == 0 && i + j != nums.length - 1) continue;
                if (minStaps[i] == 0)
                    minStaps[i] = minStaps[i + j] + 1;
                else {
                    if (minStaps[i] - 1 > minStaps[i + j])
                        minStaps[i] = minStaps[i + j] + 1;
                }
            }
        }
        return minStaps[0];
    }

    public int jump1(int[] nums) {
        if (nums.length <= 1) return 0;

        int curMax = 0; // to mark the last element in a level
        int level = 0, i = 0;
        while (i <= curMax) {
            //furthest其实是对应当前索引i能到达的最远的距离
            int furthest = curMax; // to mark the last element in the next level
            //从这些最远距离索引中选取一个能到的的更远的距离 赋值给furthest
            //其实就是在上一个可到达结合中选一个能到达的而且最远的索引，将这个索引赋值给furthest
            for (; i <= curMax; i++) {
                //这里就是获取一个能到达最远距离的
                //当i=0，能到达最远的就是nums[0] + 0.
                //然后再以nums[0] + 0尾索引，从这个范围内选出一个最远的。  通过此次循环i的值其实最后就是上一次能到达最远的即curMax。
                furthest = Math.max(furthest, nums[i] + i);
                if (furthest >= nums.length - 1) return level + 1;
            }
            //每次都需要跳一次。
            level++;
            curMax = furthest;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[25000];
        for (int i = 0; i < a.length; i++) {
            a[i] = 25000 - i;
        }
        System.out.println(new JumpGameII().jump1(a));
    }
}