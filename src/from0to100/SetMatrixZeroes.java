package from0to100;

import java.util.*;

/**
 * 给定m × n矩阵，如果元素为0，则将其整个行和列设置为0.
 *
 * Input:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * Output:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 *
 *
 */
public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        Set<Integer> conlum = new HashSet<>();
        Set<Integer> row = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    conlum.add(j);
                    for (int h = 0; h < j; h++) {
                        matrix[i][h] = 0;
                    }
                    for (int h = j; h < matrix[0].length; h++) {
                        if (matrix[i][h] == 0) {
                            for (int q = i - 1; q >= 0; q--) {
                                if (!row.contains(q) && matrix[q][h] == 0)
                                    break;
                                matrix[q][h] = 0;
                            }
                            conlum.add(h);
                        } else matrix[i][h] = 0;
                    }
                    break;
                }
                if (conlum.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void setZeroes1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, k = 0;
        while (k < n && matrix[0][k] != 0) ++k;
        for (int i = 1; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (matrix[i][j] == 0)
                    matrix[0][j] = matrix[i][0] = 0;
        for (int i = 1; i < m; ++i)
            for (int j = n - 1; j >= 0; --j)
                if (matrix[0][j] == 0 || matrix[i][0] == 0)
                    matrix[i][j] = 0;
        if (k < n) Arrays.fill(matrix[0], 0);
    }

    public static void main(String[] args) {
        int[][] a = new int[2][3];
        System.out.println(a.length);
        System.out.println(a[0].length);
    }

}
