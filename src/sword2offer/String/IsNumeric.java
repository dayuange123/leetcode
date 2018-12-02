package sword2offer.String;

import javax.xml.stream.FactoryConfigurationError;
import java.util.ArrayList;
import java.util.List;

public class IsNumeric {

    /**
     * 数字的格式 A[.[B]][e|EC] ，其中A和C都是有符号整数，而B是无符号整数！
     *
     * @param str
     * @return
     */
    public boolean isNumber(String str) {
        if (str == null) throw new NullPointerException("str is null");
        List<Integer> list = new ArrayList<>();
        list.add(0);
        //先判断前面的无符号整数
        boolean flag = scanInteger(str, list);
        if (list.get(0) == str.length()) return flag;

        //接下来判断是否是.
        if (str.charAt(list.get(0)) == '.') {
            list.set(0, list.get(0) + 1);

            if (list.get(0) == str.length())
                return flag;


            flag = scanUnsignedInterger(str, list) || flag;
        }
        if (list.get(0) == str.length()) return flag;

        if (str.charAt(list.get(0)) == 'e' || str.charAt(list.get(0)) == 'E') {
            list.set(0, list.get(0) + 1);
            if (list.get(0) == str.length()) return false;
            flag = flag && scanInteger(str, list);
        }
        return flag && list.get(0) == str.length();
    }

    private boolean scanInteger(String str, List<Integer> list) {
        if (str.charAt(list.get(0)) == '+' || str.charAt(list.get(0)) == '-')
            list.set(0, list.get(0) + 1);
        if (list.get(0) == str.length()) return false;
        return scanUnsignedInterger(str, list);
    }

    private boolean scanUnsignedInterger(String str, List<Integer> list) {
        int oldIndex = list.get(0);
        while (list.get(0) < str.length() && str.charAt(list.get(0)) >= '0' && str.charAt(list.get(0)) < '9')
            list.set(0, list.get(0) + 1);

        return list.get(0) > oldIndex;
    }


    public static void main(String[] args) {
        IsNumeric isNumeric = new IsNumeric();
        boolean number = isNumeric.isNumber("12e+5.4");
        System.out.println(number);
    }
}