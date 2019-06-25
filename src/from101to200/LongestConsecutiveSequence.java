package from101to200;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定未排序的整数数组，找到最长连续元素序列的长度。
 * <p>
 * 您的算法应该以O（n）复杂度运行。
 * <p>
 * 例：
 * <p>
 * 输入：  [100,4,200,1,3,2]
 * 输出： 4
 * 说明：最长的连续元素序列是[1, 2, 3, 4]。因此它的长度是4。
 * <p>
 * 使用并查集可以解决问题，但是前提是先要构造并查集。可以通过map。
 * 还有一种思路。就是通过判断查找一个数，然后分别向前后判断。其实复杂度挺高的一个算法
 */
public class LongestConsecutiveSequence {
    //7ms
    public int longestConsecutive(int[] nums) {
        int max = -1;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int i = 0;
        while (!set.isEmpty()) {
            int len = 1;
            int left = nums[i] - 1;
            int right = nums[i] + 1;
            while (true) {
                if (set.contains(left)) {
                    len++;
                    set.remove(left--);
                } else if (set.contains(right)) {
                    len++;
                    set.remove(right++);
                } else break;
            }
            max = Math.max(max, len);
            set.remove(nums[i]);
            i++;
        }
        return max;
    }

    //这个想法
    //其实就是先构建并查集。对于每一个数 ，判断是否有父亲和孩子，这个过程其实是非常慢的。 其实构建并查集的思想还是可以借鉴的
    //后面就是对并查集的操作。
    //迭代对应的节点，然后向后遍历。计算出对应的长度，下面这两行代码很关键
    //                map.put(prev, prev); //避免该节点再进行while循环。也就是操作过的节点，一般不会再管了。
    //                map.put(key, next);  //这句其实最后保存这个节点后面的最大长度，以便他前面的节点使用。
    //9ms
    public int longestConsecutive1(int[] nums) {
        int res = 0;
        if (nums == null || nums.length == 0) return res;
        //build graph pair
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (map.containsKey(n - 1)) map.put(n - 1, n);
            if (map.containsKey(n + 1)) map.put(n, n + 1);
            else map.put(n, n);
        }
        for (int key : map.keySet()) {
            Integer prev = key, next = map.get(key);
            while (!next.equals(prev)) {
                map.put(prev, prev); //discard used pair
                prev = next;
                next = map.get(next);
                if(next.equals(prev))
                    map.put(key, prev);
            }
            res = Math.max(res, prev - key + 1);
        }
        return res;
    }
}
