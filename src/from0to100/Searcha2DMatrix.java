package from0to100;

public class Searcha2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null) return false;
        //先定位利列。在定位行。
        if (matrix.length == 0) return false;
        if (matrix[0].length == 0) return false;
        //二分定位行
        int l = 0;
        int r = matrix.length;
        int index = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (matrix[mid][0] == target)
                return true;
            if (matrix[mid][0] > target) {
                if (mid - 1 < 0) return false;
                if (matrix[mid - 1][0] <= target) {
                    if (matrix[mid - 1][0] == target) return true;
                    index = mid - 1;
                    break;
                }
                r = mid - 1;
            }
            if (matrix[mid][0] < target) {
                if (mid + 1 >= matrix.length) {
                    index = matrix.length - 1;
                    break;
                }
                if (mid + 1 < matrix.length && matrix[mid + 1][0] >= target) {
                    if (matrix[mid + 1][0] == target) return true;
                    index = mid;
                    break;
                }
                l = mid + 1;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[index][i] == target) {
                return true;
            }
        }
        return false;
    }

}