package from0to100;

/**
 * 给定m x n网格填充非负数，找到从左上到右下的路径，这最小化了沿其路径的所有数字的总和。
 * 注意：您只能在任何时间点向下或向右移动。
 * 输入：
 * [
 * [1,3,1]，
 * [1,5,1]，
 * [4,2,1]
 * ] 输出： 7
 * 说明：因为路径1→3→1→1→1最小化总和。
 * <p>
 * 解决方法：
 * 这是一道动态规划
 * 我们不能直接从(0,0)位置开始递归回溯，我们应该从终点开始，分别计算上下到终点的最小距离，最后我们就可以得到最短距离。。
 * <p>
 * grid[i][j]=min(grid[i+1][j],grid[i][j+1])+grid[i][j].
 * 通过一个队列，将每个
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int[][] route = new int[grid.length][grid[0].length];
        for (int[] ints : route) {
            for (int i = 0; i < ints.length; i++) {
                ints[i] = -1;
            }
        }
        rescure(route, grid, 0, 0);
        return route[0][0];

    }
    public void rescure(int[][] route, int[][] grid, int i, int j) {
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            route[i][j] = grid[i][j];
            return;
        }
        if (i < grid.length - 1 && j < grid[0].length - 1) {
            if (route[i + 1][j] == -1)
                rescure(route, grid, i + 1, j);
            if (route[i][j + 1] == -1)
                rescure(route, grid, i, j + 1);
            route[i][j] = grid[i][j] + Math.min(route[i + 1][j], route[i][j + 1]);
            return;
        }
        if (i < grid.length - 1) {
            if (route[i + 1][j] == -1)
                rescure(route, grid, i + 1, j);
            route[i][j] = grid[i][j] + route[i + 1][j];
        } else if (j < grid[0].length - 1) {
            if (route[i][j + 1] == -1)
                rescure(route, grid, i, j + 1);
            route[i][j] = grid[i][j] + route[i][j + 1];
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}};

        int i = new MinimumPathSum().minPathSum(grid);
        System.out.println(i);
    }
}
