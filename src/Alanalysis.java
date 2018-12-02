import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Alanalysis {

    private static int index = 0;
    // 存储数字的map集合
    private static HashMap<String, Integer> digHashMap = new HashMap<>();
    // 保留字
    private static HashMap<String, Integer> baoliu = new HashMap<>();
    //标识符
    private static HashMap<String, Integer> biaoshi = new HashMap<>();
    //特殊字符
    private static HashMap<String, Integer> parChar = new HashMap<>();


    public static void print() {
        int sindex = 0;
        // 表格所有行数据
        int line = digHashMap.size() + baoliu.size() + biaoshi.size() + parChar.size() + 1;
        Object[][] rowData = new Object[line][6];
        //输出数字
        Iterator<Map.Entry<String, Integer>> entries1 = digHashMap.entrySet().iterator();
        while (entries1.hasNext()) {
            Entry<String, Integer> entry = entries1.next();
            rowData[sindex][0] = "                      " + entry.getKey();
            rowData[sindex][1] = "                      √";
            rowData[sindex][2] = "                      ×";
            rowData[sindex][3] = "                      ×";
            rowData[sindex][4] = "                      ×";
            rowData[sindex][5] = "                      " + (entry.getValue() == -1 ? "越界" : entry.getValue());
            sindex++;
            //      System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        //输出标识符
        Iterator<Map.Entry<String, Integer>> entries3 = biaoshi.entrySet().iterator();
        while (entries3.hasNext()) {
            Entry<String, Integer> entry = entries3.next();
            rowData[sindex][0] = "                      " + entry.getKey();
            rowData[sindex][1] = "                      ×";
            rowData[sindex][2] = "                      √";
            rowData[sindex][3] = "                      ×";
            rowData[sindex][4] = "                      ×";
            rowData[sindex][5] = "                      " + (entry.getValue() == -1 ? "不合法" : entry.getValue());
            sindex++;
            // System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        //输出保留字
        Iterator<Map.Entry<String, Integer>> entries2 = baoliu.entrySet().iterator();
        while (entries2.hasNext()) {
            Entry<String, Integer> entry = entries2.next();
            rowData[sindex][0] = "                      " + entry.getKey();
            rowData[sindex][1] = "                      ×";
            rowData[sindex][2] = "                      ×";
            rowData[sindex][3] = "                      √";
            rowData[sindex][4] = "                      ×";
            rowData[sindex][5] = "                      " + entry.getValue();
            sindex++;
            //  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        //输出特殊字符
        Iterator<Map.Entry<String, Integer>> entries = parChar.entrySet().iterator();
        while (entries.hasNext()) {
            Entry<String, Integer> entry = entries.next();
            rowData[sindex][0] = "                      " + entry.getKey();
            rowData[sindex][1] = "                      ×";
            rowData[sindex][2] = "                      ×";
            rowData[sindex][3] = "                      ×";
            rowData[sindex][4] = "                      √";
            rowData[sindex][5] = "                      " + entry.getValue();
            sindex++;
            //  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        JFrame jf = new JFrame("词法分析器");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Object[] columnNames = {"词法名", "否数字", "标识符", "保留字", "特殊字符", "出现次数"};
        //创建一个表格
        JTable table = new JTable(rowData, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        jf.getContentPane().add(scrollPane, BorderLayout.CENTER);
        jf.setSize(1380, 2000);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        String readFile = Alanalysis.readFile(new File("src/cFile"));
        // System.out.println(readFile);
        String removeUseless = Alanalysis.removeUseless(readFile);
        System.out.println(removeUseless);
        toOperate(removeUseless);
        print();
    }

    private static void toOperate(String operateString) {
        int m;
        do {
            m = mainOperation(operateString);
            switch (m) {
                case 0:
                case 3:
                    index++;
            }
        } while (m != 0);

    }

    private static int mainOperation(String operateString) {
        //防止越界
        if (index == operateString.length() - 1)
            return 0;
        //保留字数组
        String[] pro = {"PROGRAM", "BEGIN", "END", "VAR", "INTEGER", "WHILE", "IF", "THEN", "ELSE", "DO", "PROCEDURE",
                "char", "int", "if", "else", "var", "return", "break", "do", "while", "for", "double", "float", "include",
                "short"};

        char operateChar = operateString.charAt(index);

        boolean falg = true;
        // 如果字符为数字
        if ((operateChar >= '0' && operateChar <= '9')) {
            //用来拼接数字
            StringBuilder dight = new StringBuilder();
            dight.append(operateString.charAt(index++));
            while ((operateString.charAt(index) >= '0' && operateString.charAt(index) <= '9') ||
                    (operateString.charAt(index) == '.' && falg)) {
                //数字中.只能出现一次  因此我们用falg 代表.是否出现过  出现过就不能再出现 ，否则不合法
                if (operateString.charAt(index) == '.')
                    falg = false;
                dight.append(operateString.charAt(index++));
            }
            index--;
            int dig = 0;
            //如果不是浮点数
            if (falg) {
                try {
                    //防止越界  如果越界返回  并标记为-1代表越界
                    dig = Integer.valueOf(dight.toString());
                } catch (Exception e) {
                    digHashMap.put(dight.toString(), -1);
                    return 3;
                }
                if (digHashMap.get(dig) == null) {
                    digHashMap.put(dig + "", 1);
                    //     System.out.println(dig + "第一次出现出现");
                } else {
                    digHashMap.put(dig + "", digHashMap.get(dig + "") + 1);
                }
                return 3;
            }
            //代表为浮点数
            else {
                double dig1 = 0;
                try {
                    //如果出现异常按 不合法 处理
                    dig1 = Double.valueOf(dight.toString());
                } catch (Exception e) {
                    digHashMap.put(dight.toString(), -1);
                    return 3;
                }
                if (digHashMap.get(dig1) == null) {
                    digHashMap.put(dig1 + "", 1);
                    //     System.out.println(dig + "第一次出现出现");
                } else {
                    digHashMap.put(dig1 + "", digHashMap.get(dig1 + "") + 1);
                }
            }

        }
        // 判断为标识符 或者保留字
        else if ((operateChar >= 'A' && operateChar <= 'Z') || (operateChar >= 'a' && operateChar <= 'z')
                || operateChar == '_') {

            StringBuilder Identification = new StringBuilder();
            Identification.append(operateString.charAt(index++));
            // 若字符为A~Z或0~9或者下划线，则继续读取
            while ((operateString.charAt(index) >= 'A' && operateString.charAt(index) <= 'Z')
                    || (operateString.charAt(index) >= '0' && operateString.charAt(index) <= '9')
                    || (operateString.charAt(index) >= 'a' && operateString.charAt(index) <= 'z')
                    || operateString.charAt(index) == '_') {
                Identification.append(operateString.charAt(index++));
            }
            index--;
            //判断是否是保留字  如果是记录并返回
            for (int i = 0; i < pro.length; i++) {
                if (pro[i].equals(Identification.toString())) {
                    if (baoliu.get(Identification.toString()) == null) {
                        baoliu.put(Identification.toString(), 1);
                    } else {
                        Integer a = baoliu.get(Identification.toString()) + 1;
                        baoliu.put(Identification.toString(), a);
                    }
                    //   System.out.println("保留字" + Identification.toString());

                    return 3;
                }
            }
            // 大于10 设置为wuxiao标识符
            if (Identification.toString().length() > 10) {
                biaoshi.put(Identification.toString(), -1);
                return 3;
            }
            if (biaoshi.get(Identification.toString()) == null) {
                biaoshi.put(Identification.toString(), 1);
            } else {
                biaoshi.put(Identification.toString(), biaoshi.get(Identification.toString()) + 1);
            }
            //    System.out.println("标识符" + Identification.toString());
            return 3;
        } else {
            switch (operateChar) {
                case ' ':
                    return 3;
                case '\r':
                    return 3;
                case '\n':
                    return 3;

                case '=': {
                    //  System.out.println("55");
                    if (parChar.get("=") == null)
                        parChar.put("=", 1);
                    else
                        parChar.put("=", parChar.get("=") + 1);
                }
                case '<': {
                    index++;
                    if (operateString.charAt(index) == '=') {
                        if (parChar.get("<=") == null)
                            parChar.put("<=", 1);
                        else
                            parChar.put("<=", parChar.get("<=") + 1);

                        return 3;
                    } else if (operateString.charAt(index) == '>') {
                        if (parChar.get("<>") == null)
                            parChar.put("<>", 1);
                        else
                            parChar.put("<>", parChar.get("<>") + 1);
                        return 3;
                    } else {
                        index--;
                        if (parChar.get("<") == null)
                            parChar.put("<", 1);
                        else
                            parChar.put("<", parChar.get("<") + 1);
                        return 3;
                    }
                }
                case '>': {
                    index++;
                    if (operateChar == '=') {
                        if (parChar.get(">=") == null)
                            parChar.put(">=", 1);
                        else
                            parChar.put(">=", parChar.get(">=") + 1);
                        return 3;
                    } else {
                        index--;
                        if (parChar.get(">") == null)
                            parChar.put(">", 1);
                        else
                            parChar.put(">", parChar.get(">") + 1);
                        return 3;
                    }
                }
                case '+': {
                    System.out.println("woshi +");
                    if (parChar.get("+") == null)
                        parChar.put("+", 1);
                    else
                        parChar.put("+", parChar.get("+") + 1);
                    return 3;
                }
                case '-': {
                    if (parChar.get("-") == null)
                        parChar.put("-", 1);
                    else
                        parChar.put("-", parChar.get("-") + 1);
                    return 3;
                }
                case '*': {
                    //     System.out.println("woshi *");
                    if (parChar.get("*") == null)
                        parChar.put("*", 1);
                    else
                        parChar.put("*", parChar.get("*") + 1);
                    return 3;
                }
                case '/': {

                    if (parChar.get("/") == null)
                        parChar.put("/", 1);
                    else
                        parChar.put("/", parChar.get("/") + 1);
                    return 3;

                }

                case ':': {
                    if (parChar.get(":") == null)
                        parChar.put(":", 1);
                    else
                        parChar.put(":", parChar.get(":") + 1);
                    return 3;
                }
                case ';': {
                    if (parChar.get(";") == null)
                        parChar.put(";", 1);
                    else
                        parChar.put(";", parChar.get(";") + 1);
                    return 3;
                }
                case '(': {
                    if (parChar.get("(") == null)
                        parChar.put("(", 1);
                    else
                        parChar.put("(", parChar.get("(") + 1);
                    return 3;
                }
                case ')': {
                    if (parChar.get(")") == null)
                        parChar.put(")", 1);
                    else
                        parChar.put(")", parChar.get(")") + 1);
                    return 3;
                }
                case '{': {
                    if (parChar.get("{") == null)
                        parChar.put("{", 1);
                    else
                        parChar.put("{", parChar.get("{") + 1);
                    return 3;
                }
                case '}': {
                    if (parChar.get("}") == null)
                        parChar.put("}", 1);
                    else
                        parChar.put("}", parChar.get("}") + 1);
                    return 3;
                }
                case '[': {
                    if (parChar.get("[") == null)
                        parChar.put("[", 1);
                    else
                        parChar.put("[", parChar.get("[") + 1);
                    return 3;
                }
                case ']': {
                    if (parChar.get("]") == null)
                        parChar.put("]", 1);
                    else
                        parChar.put("]", parChar.get("]") + 1);
                    return 3;
                }
                case '|': {
                    if (parChar.get("|") == null)
                        parChar.put("|", 1);
                    else
                        parChar.put("|", parChar.get("|") + 1);
                    return 3;
                }
                case '"': {
                    if (parChar.get("\"") == null)
                        parChar.put("\"", 1);
                    else
                        parChar.put("\"", parChar.get("\"") + 1);
                    return 3;
                }
                case ',': {
                    if (parChar.get(",") == null)
                        parChar.put(",", 1);
                    else
                        parChar.put(",", parChar.get(",") + 1);
                    return 3;
                }
                case '\'': {
                    if (parChar.get("\\") == null)
                        parChar.put("\\", 1);
                    else
                        parChar.put("\\", parChar.get("\\") + 1);
                    return 3;// 单引号
                }
                case '&': {
                    index++;
                    if (operateString.charAt(index) != '&') {
                        index--;
                        if (parChar.get("&") == null)
                            parChar.put("&", 1);
                        else
                            parChar.put("&", parChar.get("&") + 1);
                        return 3;
                    } else {
                        if (parChar.get("&&") == null)
                            parChar.put("&&", 1);
                        else
                            parChar.put("&&", parChar.get("&&") + 1);
                        return 3;
                    }
                }
            }
        }
        return 3;
    }

    private static String removeUseless(String readFile) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < readFile.length(); i++) {
            char c = readFile.charAt(i);
            if (c == '"') {
                sb.append(c);
                char cc = ' ';
                while (i < readFile.length() - 1 && (cc = readFile.charAt(++i)) != '"') {
                    sb.append(cc);
                }
                sb.append(cc);
            } else if (c == '/' && i + 1 < readFile.length() && readFile.charAt(i + 1) == '*') {
                i += 2;
                while (i + 1 < readFile.length()) {
                    if (readFile.charAt(i) == '*' && readFile.charAt(i + 1) == '/')
                        break;
                    i++;
                }
                if (i == readFile.length() - 1) {
                    return sb.toString();
                }
                i++;

            } else if (c == '/' && i + 1 < readFile.length() && readFile.charAt(i + 1) == '/') {
                i += 2;
                while (i < readFile.length() && readFile.charAt(i) != '\n') {
                    i++;
                }

            } else if (c == '\n' || c == '\t' || c == '\r' || c == ' ') {
                sb.append(' ');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();

    }

    public static String readFile(File file) {
        FileInputStream fi = null;
        BufferedInputStream bf = null;
        StringBuffer sb = new StringBuffer();
        try {
            fi = new FileInputStream(file);
            bf = new BufferedInputStream(fi);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[1024];
        int i = 0;
        try {
            while ((i = bf.read(buffer)) != -1) {
                sb.append(new String(buffer));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}