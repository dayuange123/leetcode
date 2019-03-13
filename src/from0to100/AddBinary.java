package from0to100;

public class AddBinary {
    public static void main(String[] args) {
        System.out.println(new AddBinary().addBinary("1010", "1011"));
    }

    public String addBinary(String a, String b) {
        int length = Math.min(a.length(), b.length());
        int max = Math.max(a.length(), b.length());
        char[] cha = new char[max + 1];
        boolean f = false;
        for (int i = 0; i < length; i++) {
            //进位
            if (a.charAt(a.length() - i - 1) == '1' && b.charAt(b.length() - i - 1) == '1') {
                if (f) {
                    cha[max - i] = '1';
                } else cha[max - i] = '0';
                f = true;
            } else if (a.charAt(a.length() - i - 1) == '1' || b.charAt(b.length() - i - 1) == '1') {
                if (f) {
                    cha[max - i] = '0';
                    f = true;
                } else cha[max - i] = '1';

            } else {
                if (f)
                    cha[max - i] = '1';
                else cha[max - i] = '0';
                f = false;
            }
        }
        if (a.length() >= b.length())
            return test(f, a, cha, length);
        else return test(f, b, cha, length);

    }

    public String test(boolean f, String s, char[] chars, int l) {

        if (f) {
            for (int i = s.length() - l - 1; i >= 0; i--) {
                if (f) {
                    if (s.charAt(i) == '0') {
                        chars[i + 1] = '1';
                        f = false;
                    } else
                        chars[i + 1] = '0';
                } else chars[i + 1] = s.charAt(i);

            }
        } else {
            for (int i = s.length() - l - 1; i >= 0; i--) {
                chars[i + 1] = s.charAt(i);
            }
        }
        if (f) {
            chars[0] = '1';
            return new String(chars);
        }
        return new String(chars).substring(1);
    }

}