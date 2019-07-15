package from101to200;


public class PalindromePartitioningII {
    private int min = Integer.MAX_VALUE;
    private int[][] minFun;

    public int minCut(String s) {
        minFun = new int[s.length() - 1][s.length() - 1];
        partition(s, 0, 0, s.length() - 1);
        return min;
    }

    public void partition(String s, int index, int i, int j) {
        if (index >= min) return;
        if (i == j || isHuiWen(s.substring(i, j + 1))) minFun[i][i] = 0;
        while (i < j) {
            if (isHuiWen(s.substring(i, j + 1))) {
                minFun[i][j] = 0;
                return;
            } else {


            }

        }
    }

    private boolean isHuiWen(String s) {
        if (s.length() == 0) return false;
        int mid = s.length() / 2 - 1;
        while (mid >= 0) {
            if (s.charAt(mid) != s.charAt(s.length() - mid - 1))
                return false;
            mid--;
        }
        return true;
    }

}
