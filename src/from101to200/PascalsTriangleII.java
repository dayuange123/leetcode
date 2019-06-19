package from101to200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Pascal's triangle.
 * 返回给索引的行。
 */
public class PascalsTriangleII {

    public List<Integer> getRow(int rowIndex) {
        Integer[] l = new Integer[rowIndex + 1];
        l[0] = 1;
        for (int i = 2; i <= rowIndex + 1; i++) {
            l[i-1]=1;
            Integer temp1 = 0;
            Integer temp2;
            for (int j = 1; j < i-1; j++) {
                if (j == 1)
                    temp1 = l[j - 1];
                temp2 = l[j];
                l[j] = temp1 + temp2;
                temp1 = temp2;
            }
        }
        return Arrays.asList(l);
    }

    public static void main(String[] args) {
        new PascalsTriangleII().getRow(3);
    }
}
