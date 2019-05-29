package from0to100;

import java.util.Stack;

/**
 * 给定填充0和1的2D二进制矩阵，找到仅包含1的最大矩形并返回其区域。
 * <p>
 * 例：
 * <p>
 * 输入：
 * [
 * [ “1”， “0”， “1”， “0”， “0”]，
 * [“1”，“0”，“ 1 ”，“ 1 ”，“ 1 ”]，
 * [“1”，“1”，“ 1 ”，“ 1 ”，“ 1 ”]，
 * [ “1”， “0”， “0”， “1”， “0”]
 * ]
 * 产量： 6
 * <p>
 * <p>
 * 解决思路： 使用dp求出 每个位置对应的高度  我们这里从最底部开始
 * if(matrix[i][j]==0) matrix[i][j]=0
 * else matrix[i][j]=matrix[i-1][j]
 *
 * 然后问题就转化为，求一行中最 面积最大的。相当于 LargestRectangleinHistogram
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int max = 0;
        if (matrix == null || matrix.length == 0) return 0;
        int heights[][] = new int[matrix.length][matrix[0].length];
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (i == matrix.length - 1 || matrix[i][j] == '0') heights[i][j] = matrix[i][j] - '0';
                else
                    heights[i][j] = heights[i + 1][j] + 1;
            }
            max = Math.max(max, largestRectangleArea1(heights[i]));
        }
        return max;
    }

    /**
     *
     * 我们用一个栈来保存数据。
     * 相对来说我们并不会直接去每个都去进行向后遍历比较。
     *
     * 如果当前节点比栈顶大或者等于，就可以让其直接入栈。
     *  因为我们栈中保存的都是从大到小的几点。
     * 如果小于栈顶，说明栈顶这个节点存在得意义到这里就结束了，因为就算栈顶很大，后面小的节点覆盖掉他的大，木桶原则嘛（其实就是为了维护栈中元素永远都是从小到大）
     * 所以这个时候，我们需要让栈顶出栈，并计算以他为结尾的最大面积。其实就是他的高度*（前面比他大的元素个数。即I - S.PEEK()-1）因为栈中都是从小往大的，栈顶的肯定就是最后一个比他小的，减去最后一个比它小的即可。
     *
     * 计算一下面积，然后保存一下。
     * 最后我们需要计算栈中其他元素的最大大小。其实就是他*他到数组末尾的距离，因为之所以他能保存下来，就是他后面的元素都比他大。 比他小的在之前以及出栈了。
     * 因为我们后面可以计算这些栈中元素对应的面积，通过第二个while循环，
     * @param heights
     * @return
     */
    public int largestRectangleArea1(int[] heights) {
        Stack<Integer> s = new Stack<>();
        int max = 0;
        int tp;
        int area;
        int i = 0;
        while (i < heights.length) {
            if (s.isEmpty() || heights[s.peek()] <= heights[i])
                s.push(i++);
            else {
                tp = s.pop();
                area = heights[tp] * (s.empty() ? i : i - s.peek() - 1);
                if (max < area)
                    max = area;
            }
        }
        while (!s.isEmpty()) {
            tp=s.pop();
            area = heights[tp] * (s.empty() ? i : i - s.peek() - 1);
            if (max < area)
                max = area;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] h={1,3,5,9,100};
        new MaximalRectangle().largestRectangleArea1(h);
    }
}
