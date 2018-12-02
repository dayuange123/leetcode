package from0to100;

/**
 * 确定9x9数独板是否有效。只需要根据以下规则验证已填充的单元格  ：
 * 1.
 * 每行必须包含数字  1-9而不重复。
 * 2.
 * 每列必须包含数字  1-9 而不重复。
 * 3.
 * 3x3网格的9 个子框中的每一个必须包含1-9 不重复的数字  。
 * 部分填充的数独有效。可以部分填充数独板，其中空单元格填充该字符'.'。
 */
public class ValidSudoku {

    public static void main(String[] args) {
        char[][] cahr = {{
                '7', '.', '.', '.', '4', '.', '.', '.', '.'
        }, {
                '.', '.', '.', '8', '6', '5', '.', '.', '.'
        }, {
                '.', '1', '.', '2', '.', '.', '.', '.', '.'
        }, {
                '.', '.', '.', '.', '.', '9', '.', '.', '.'
        }, {
                '.', '.', '.', '.', '5', '.', '5', '.', '.'
        }, {
                '.', '.', '.', '.', '.', '.', '.', '.', '.'
        }, {
                '.', '.', '.', '.', '.', '.', '2', '.', '.'
        }, {
                '.', '.', '.', '.', '.', '.', '.', '.', '.'
        }, {
                '.', '.', '.', '.', '.', '.', '.', '.', '.'
        }};
        System.out.println(new ValidSudoku().isValidSudoku(cahr));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("123123");
        String s = null;
        stringBuilder.append(s);
        System.out.println(stringBuilder.toString());

    }

    public boolean isValidSudoku(char[][] board) {
        int[][] flage = new int[9][9];
        int[][] flage1 = new int[9][9];
        int[][] flage2 = new int[23][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    flage[i][board[i][j] - '1']++;
                    if (flage[i][board[i][j] - '1'] > 1) return false;
                }
                if (board[j][i] != '.') {
                    flage1[i][board[j][i] - '1']++;
                    if (flage1[i][board[j][i] - '1'] > 1) return false;
                }
                if (board[i][j] != '.') {
                    flage2[i / 3 * 10 + j / 3][board[i][j] - '1']++;
                    if (flage2[i / 3 * 10 + j / 3][board[i][j] - '1'] > 1) return false;
                }
            }
        return true;
    }

}