package from0to100;

import java.util.Stack;

/**
 * 给定n个非负整数表示直方图的条形高度，其中每个条形的宽度为1，找到直方图中最大矩形的区域。
 * <p>
 * 自己的思路
 * 类似于冒泡。维护一个最大值
 */
public class LargestRectangleinHistogram {

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int max = 0;
        int currentHeight;
        int minHeight = 0;
        for (int i = 0; i < heights.length; i++) {
            if (minHeight >= heights[i]) {
                minHeight = heights[i];
                continue;
            }
            currentHeight = heights[i];
            for (int j = i; j < heights.length; j++) {
                currentHeight = currentHeight < heights[j] ? currentHeight : heights[j];
                max = max < (currentHeight * (j - i + 1)) ? (currentHeight * (j - i + 1)) : max;
            }

        }
        return max;
    }

//    public int largestRectangleArea1(int[] heights) {
//        Stack<Integer> s = new Stack<>();
//
//        int max = 0;
//        int tp;
//        int area_with_top;
//        int i = 0;
//        while (i < heights.length) {
//            if (s.isEmpty() || heights[s.peek()] <= heights[i])
//                s.push(i++);
//            else {
//                tp = s.pop();
//                area_with_top = heights[tp] * (s.empty() ? i : i - s.peek() - 1);
//                if (max < area_with_top)
//                    max = area_with_top;
//            }
//        }
//        while (s.isEmpty() == false) {
//            tp = s.peek();
//            s.pop();
//            area_with_top = heights[tp] * (s.empty() ? i : i - s.peek() - 1);
//            if (max < area_with_top)
//                max = area_with_top;
//        }
//        return max;
//    }

    public static void main(String[] args) {
        int[] a = {2, 1, 5, 6, 2, 3};
        System.out.println(new LargestRectangleinHistogram().largestRectangleArea(a));
    }

}
