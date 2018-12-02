package from0to100;

public class TrappingRainWater {
    public int trap(int[] height) {
        int container = 0;
        if (height.length == 0) return container;
        for (int i = 0; i < height.length; ++i) {
            if (i < height.length - 1 && height[i] > height[i + 1]) {
                //记录当前i的值
                int start = i++;
                while (i < height.length) {
                    if (height[start] <= height[i])
                        break;
                    i++;
                }
                if (i != height.length) {
                    int end = i;
                    container += (end - start - 1) * height[start];
                    for (int j = start + 1; j < end; ++j) {
                        container -= height[j];
                    }
                    i--;
                }
            }
        }
        for (int i = height.length - 1; i >= 0; i--) {
            if (i >= 1 && height[i] > height[i - 1]) {
                //记录当前i的值
                int start = i--;
                while (i >= 0) {
                    if (height[start] < height[i])
                        break;
                    i--;
                }
                if (i != -1) {
                    int end = i;
                    container += (start - end - 1) * height[start];
                    for (int j = start - 1; j > end; j--) {
                        container -= height[j];
                    }
                    i++;
                }
            }
        }
        return container;
    }

    public static void main(String[] args) {
        System.out.println(new TrappingRainWater().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

}