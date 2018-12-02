package from0to100;

public class Rotateimage {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int j = 0; j < (n + 1) / 2; j++) {
            int temp;
            for (int i = j; i < n - j - 1; i++) {
                temp = matrix[j][i];
                matrix[j][i] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = matrix[n - j - 1][n - i - 1];
                matrix[n - j - 1][n - i - 1] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }
}