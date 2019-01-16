package sword2offer.fourChapther;

/**
 * 顺时针打印矩阵
 */
public class PrintMatrixICirecle {
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9},{10,11,12}};

        new PrintMatrixICirecle().printMatrixICirecle(a, 3, 4);
    }

    public void printMatrixICirecle(int[][] num, int col, int row) {
        int left = 0, right = col - 1, top = 0, bottom = row - 1;
        int j = 0;
        while (j < col * row) {
            for (int i = left; i <= right; i++) {
                //打印第一行
                System.out.println(num[top][i]);
                j++;
            }
            top++;
            if (j < col * row) {
                for (int i = top; i <= bottom; i++) {
                    j++;
                    System.out.println(num[i][right]);
                }
                right--;
            }
            if (j < col * row) {
                for (int i = right; i >= left; i--) {
                    j++;
                    System.out.println(num[bottom][i]);
                }
                bottom--;
            }
            if (j < col * row) {
                for (int i = bottom; i >= top; i--) {
                    System.out.println(num[i][left]);
                    j++;
                }
                left++;
            }
        }
    }
}