package sword2offer.backmethod;

public class RangeOfMotion {

    public int gerRange(int threshold, int rows, int cols) {
        boolean[] visted = new boolean[rows * cols];
        if (rows < 1 || cols < 1 || threshold < 0)
            return 0;
        return getRangeCore(threshold, rows, cols, 0, 0, visted);

    }

    public int getRangeCore(int threshold, int rows, int cols, int row, int col, boolean[] visted) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || visted[row * cols + col])
            return 0;
        int thisThreshold = 0;
        int row1 = row, col1 = col;
        while (row1 > 0) {
            thisThreshold += row1 % 10;
            row1 /= 10;
        }
        while (col1 > 0) {
            thisThreshold += col1 % 10;
            col1 /= 10;
        }
        if (thisThreshold > threshold)
            return 0;
        int number = 1;
        visted[row * cols + col] = true;
        number += getRangeCore(threshold, rows, cols, row - 1, col, visted) +
                getRangeCore(threshold, rows, cols, row, col - 1, visted) +
                getRangeCore(threshold, rows, cols, row + 1, col, visted) +
                getRangeCore(threshold, rows, cols, row, col + 1, visted);

        return number;
    }

    public static void main(String[] args) {
        System.out.println(new RangeOfMotion().gerRange(1, 2, 2));
    }
}