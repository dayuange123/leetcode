package sword2offer.fourChapther;

public class Permutation {

    public void permutation(String s) {
        if (s == null)
            return;
        permutation(s.toCharArray(), 0);
    }

    private void permutation(char[] s, int index) {
        if (index == s.length) {
            System.out.println(s);
            return;
        }

        for (int i = index; i < s.length; ++i) {
            char temp = s[index];
            s[index] = s[i];
            s[i] = temp;

            permutation(s, index + 1);
            temp = s[i];
            s[i] = s[index];
            s[index] = temp;
        }

    }

    public static void main(String[] args) {
        new Permutation().permutation("abcd");
    }
}