package sword2offer;

/**
 * 二维数组中的查找
 */
public class LookupInTwoArrays {

    public boolean Find(int[][] matrix, int rows, int columns, int target) {

        int temp;
        int column = 0;
        int row = rows - 1;
        while (row >= 0 && column < columns) {
            temp = matrix[column][row];
            if (temp == target) return true;
            if (temp < target) {
                column++;
            } else row--;

        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new LookupInTwoArrays().Find(new int[][]{
                {1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}
        }, 4, 4, 7));
    }


}
