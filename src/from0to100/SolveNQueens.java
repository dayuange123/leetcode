package from0to100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolveNQueens {
    public List<List<String>> solveNQueens(int n) {

        List<List<String>> lists = new ArrayList<>();
        List<String> l = new ArrayList<>();
        if (n == 1) {
            l.add("Q");
            lists.add(l);
            return lists;
        }
        for (int i = 0; i < n; i++) {
            String s = "";
            for (int j = 0; j < n; j++)
                s += ".";
            l.add(s);
        }
        boolean[] fl = new boolean[n];
        recure(0, n, lists, fl, l);

        return lists;
    }

    private boolean recure(int rows, int n, List<List<String>> lists, boolean[] f, List<String> list) {
        if (rows == n) {
            List<String> list1 = new ArrayList<>(list);
            lists.add(list1);
        }

        for (int i = 0; i < n; i++) {
            //判断list周围是否有皇后，我们只需要判断上面的。因为下面还没有操作
            boolean flag = false;
            if (rows == 0) ;
            else {
                if (f[i]) continue;
                for (int h = rows - 1; h >= 0; h--) {
                    if (i - rows + h >= 0) {
                        if (list.get(h).charAt(i - rows + h) == 'Q') {
                            flag = true;
                            break;
                        }
                    }
                    if (i + rows - h < n) {
                        if (list.get(h).charAt(i + rows - h) == 'Q') {
                            flag = true;
                            break;
                        }
                    }
                }
            }
            if (flag) continue;
            StringBuilder s = new StringBuilder();
            for (int i1 = 0; i1 < n; i1++) {
                if (i1 == i)
                    s.append("Q");
                else s.append(".");
            }
            list.set(rows, s.toString());
            f[i] = true;
            recure(rows + 1, n, lists, f, list);
            f[i] = false;
            // list.set(rows, ".");
        }
        return false;
    }
}