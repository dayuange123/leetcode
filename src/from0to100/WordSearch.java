package from0to100;

/**
 * 79
 * Word Search
 *
 * 这道题其实很容易。类似于之前的迷宫问题
 * 可以上下左右移动，但是不能重复计算。所以通过flag数组记录那个位置已经被计算了就可以直接返回。
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {

        if (board == null || board[0].length == 0) return false;
        int[][] route = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int[][] flag = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                boolean res = recuser(board, word, 0, i, j, route, flag);
                if (res) return true;
            }
        }
        return false;
    }

    public boolean recuser(char[][] board, String word, int index, int i, int j, int[][] route, int[][] flag) {
        if (index == word.length() - 1) {
            if (board[i][j] == word.charAt(index))
                return true;
            else return false;
        } else {
            if (board[i][j] != word.charAt(index)) return false;
        }
        flag[i][j] = 1;
        for (int k = 0; k < 4; k++) {
            int newI = i + route[k][0];
            int newJ = j + route[k][1];
            if (newI >= board.length || newJ >= board[0].length || newI < 0 || newJ < 0)
                continue;
            if (flag[newI][newJ] == 1) continue;
            boolean res = recuser(board, word, index + 1, newI, newJ, route, flag);
            if (res) return res;
        }
        flag[i][j] = 0;
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'},
                         {'S', 'F', 'C', 'S'},
                         {'A', 'D', 'E', 'E'}};


        boolean res = new WordSearch().exist(board, "ABCCED");
        System.out.println(res);
    }
}