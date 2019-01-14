public class Test123 {
    public static void main(String[] args) {
        String src = "abcaabcded";
        String dest = "abc";
        int l = 0, k = 0;
        String[] strings = new String[20];
        boolean f=true;
        for (int i = 0; i < src.length(); i++) {
            if (src.charAt(i) == dest.charAt(l)) {
                if (l == 2) {
                    strings[k - 2] = "x";
                    strings[k - 1] = "x";
                    strings[k] = "x";
                    strings[k + 1] = "x";
                    l = 0;
                    k+=2;
                    f=false;
                }
                l++;
            } else {
                l = 0;
            }
            if(f){
                strings[k++] = src.charAt(i) + "";
            }
            f=true;
        }
        for (int i = 0; i < 15; i++)
            System.out.println(strings[i]);
    }
}