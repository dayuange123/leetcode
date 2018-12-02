package sword2offer.backmethod;

public class PathInMatrix {
    public boolean hasPath(char[] matrix, int rows, int cols, String str) {

        if (matrix == null || rows < 1 || cols < 1 || str == null)
            return false;
        boolean[] visted = new boolean[matrix.length];
        int pathLength = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (hasPathCore(matrix, rows, cols, row, col, str, pathLength, visted)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasPathCore(char[] matrix, int rows, int cols, int row, int col, String str, int pathLength, boolean[] visted) {

        if (pathLength == str.length())
            return true;

        boolean hasPath = false;
        if (row >= 0 && row < rows && col >= 0 && col < cols &&
                matrix[row * cols + col] == str.charAt(pathLength) && !visted[row * cols + col]) {
            pathLength++;
            visted[row * cols + col] = true;
            //递归分别向其他四个方向遍历
            hasPath = hasPathCore(matrix, rows, cols, row, col - 1, str, pathLength, visted) ||
                    hasPathCore(matrix, rows, cols, row - 1, col, str, pathLength, visted) ||
                    hasPathCore(matrix, rows, cols, row, col + 1, str, pathLength, visted) ||
                    hasPathCore(matrix, rows, cols, row + 1, col, str, pathLength, visted);

            if (!hasPath) {
                --pathLength;
                visted[row * cols + col] = false;
            }
        }
        return hasPath;
    }

    public static void main(String[] args) {
        System.out.println(new PathInMatrix().hasPath(new char[]{'a', 'b', 't', 'g', 'c', 'f', 'c', 's', 'j', 'd', 'e', 'h'},
                3, 4, "ab"));
    }
}