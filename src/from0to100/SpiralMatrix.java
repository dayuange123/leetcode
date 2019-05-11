package from0to100;


public class SpiralMatrix {

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int mid=1;
        int l=0,r=n-1,t=0,b=n-1;
        while (mid<=n*n){
            for(int i=l;i<=r;i++ ){
                res[t][i]=mid++;
            }
            t++;
            if(mid>n*n) break;
            for(int i=t;i<=b;i++){
                res[i][r]=mid++;
            }
            r--;
            if(mid>n*n) break;
            for(int i=r;i>=l;i--){
                res[b][i]=mid++;
            }
            b--;
            if(mid>n*n) break;
            for(int i=b;i>=t;i--){
                res[i][l]=mid++;
            }
            l++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] ints = new SpiralMatrix().generateMatrix(6);
        for (int[] anInt : ints) {
            for (int i : anInt) {
                System.out.print(i+"\t");
            }
            System.out.println();
        }
    }

}