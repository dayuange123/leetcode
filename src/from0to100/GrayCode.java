package from0to100;

import java.util.ArrayList;
import java.util.List;

/**
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * <p>
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * <p>
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 * <p>
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * <p>
 */
public class GrayCode {

    public static void main(String[] args) {
        List<Integer> list = new GrayCode().grayCode(2);
        list.forEach(integer -> {
            System.out.println(integer);
        });
    }

    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<=n;i++){
            if(i==0) list.add(0);
            else {
                int siez = list.size();
                int v = 1 << (i - 1);
                for (int j = siez - 1; j >= 0; j--) {
                    list.add(v | list.get(j));
                }
            }
        }
        return list;
    }

//    public void recue(List<Integer> list, int n) {
//        if (n == 0) {
//            list.add(0);
//            return;
//        }
//        recue(list, n - 1);
//        int siez = list.size();
//        int v = 1 << (n - 1);
//        for (int i = siez - 1; i >= 0; i--) {
//            list.add(v | list.get(i));
//        }
//    }
}

