package from101to200;


import java.util.LinkedList;
import java.util.Map;

/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * <p>
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * 示例:
 * <p>
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * <p>
 * <p>
 * 首先把四周的o都保存起来，然后逐个进行深入优先或者广度优先。
 * 赋值为一个特殊字符R。最后将所有O填充成X
 */
public class SurroundedRegions {
    class Pair<K, V> {
        K k;
        V v;

        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }

        Pair(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }

    /**
     * 这里 可以优化的一点就是不需要遍历所有边界，而是在遍历的时候判断。
     * @param board
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        LinkedList<Pair<Integer, Integer>> list = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O')
                list.add(new Pair<>(i, 0));
            if (board[i][board[0].length - 1] == 'O')
                list.add(new Pair<>(i, board[0].length - 1));
        }
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'O')
                list.add(new Pair<>(0, i));
            if (board[board.length - 1][i] == 'O')
                list.add(new Pair<>(board.length - 1, i));
        }
        int[][] rout = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (!list.isEmpty()) {
            Pair<Integer, Integer> pair = list.removeFirst();
            char ch = board[pair.getKey()][pair.getValue()];
            if (ch != 'R') {
                //对ch开始进行广度优先.使用一个
                LinkedList<Pair<Integer, Integer>> linkedList = new LinkedList<>();
                board[pair.getKey()][pair.getValue()] = 'R';
                linkedList.add(pair);
                while (!linkedList.isEmpty()) {
                    Pair<Integer, Integer> pair1 = linkedList.removeFirst();
                    int row = pair1.getKey();
                    int column = pair1.getValue();
                    for (int i = 0; i < 4; i++) {
                        int newRow = row + rout[i][0];
                        int newColumn = column + rout[i][1];
                        if (newRow >= 0 && newRow < board.length &&
                                newColumn >= 0 && newColumn < board[0].length &&
                                board[newRow][newColumn] == 'O') {
                            board[newRow][newColumn] = 'R';
                            linkedList.add(new Pair<>(newRow, newColumn));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == 'R')
                    board[i][j] = 'O';
            }
    }
    public static void main(String[] args) {
        char[][] s = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        new SurroundedRegions().solve(s);
    }
}
