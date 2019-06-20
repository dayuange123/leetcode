package from101to200;

import java.util.LinkedList;
import java.util.List;

/**
 * 动态规划，
 * 杨辉三角 求最短路径
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) return 0;
        if (triangle.size() == 1) return triangle.get(0).get(0);
        int[] res = new int[triangle.size() + 1];
        //从倒数第二层开始计算。只有一层直接返回
        //每一层的 i索引等于 下一层的 i索引  或者  i+1索引 取最小。
        for (int i = 0; i < triangle.size(); ++i)
            res[i] = triangle.get(triangle.size() - 1).get(i);
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                res[j] = res[j] < res[j + 1] ? res[j] + triangle.get(i).get(j) : res[j+1] + triangle.get(i).get(j);
            }
        }
        return res[0];
    }
}