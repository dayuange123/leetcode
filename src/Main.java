


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i <=n; i++) {
            long number = 1;
            //打印空格字符串

            for (int j = 0; j <= i; j++) {
                if(i==n){
                    System.out.printf(number+" ");
                }
                number = number * (i - j) / (j + 1);
            }
        }
    }

}