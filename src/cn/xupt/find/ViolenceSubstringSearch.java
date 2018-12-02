package cn.xupt.find;

public class ViolenceSubstringSearch {

    public static int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        for (int i = 0; i <= N - M; ++i) {
            int j;
            for (j = 0; j < M; j++)
                if (pat.charAt(j) != txt.charAt(i + j))
                    break;
            if (j == M) return i;
        }
        return N;
    }

    //另一种实现：
    public static int search1(String pat, String txt) {
        int i, j, M = pat.length(), N = txt.length();

        for (i = 0, j = 0; i < N && j < M; i++) {
            if (txt.charAt(i) != pat.charAt(j)) {
                i -= j;
                j = 0;
            } else j++;
        }
        if (j == M) return i - M;
        else return N;
    }
}