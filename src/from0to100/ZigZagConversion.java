package from0to100;

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion {

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        List<String> list = new ArrayList<>();
        int index = 0;
        int thisIndex = 0;
        boolean plus = true;
        while (thisIndex < s.length()) {
            if (list.size() < index + 1) list.add("");
            list.set(index, list.get(index) + s.charAt(thisIndex++));
            if (plus) {
                if (index == numRows - 1) {
                    plus = false;
                    index--;
                } else index++;
            } else {
                if (index == 0) {
                    plus = true;
                    index++;
                } else index--;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s1 : list) {
            stringBuilder.append(s1);
        }
        return stringBuilder.toString();
    }
}