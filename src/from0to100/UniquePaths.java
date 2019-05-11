package from0to100;

public class UniquePaths {

    //每个节点到终点的路径等于 他右边和下边的之和
    //动态规划
    //从 m-1 n-1   m代表列 ，n代表行
    //cout为 每个位置到终点的路径书
    //如果 n=边界-1，cout(n,m)=   (n,m+1)
    //如果 m=边界-1， cout=  (n+1,m)
    //其他位置 等于 (n,m+1)+(n+1,m)
    //count(m-1,n-1)=1
    public int uniquePaths(int m, int n) {
        int[][] s=new int[n][m];
        s[n-1][m-1]=1;
        return recure(s,0, 0, n, m);
    }
    public int recure(int[][] s,int n, int m, int maxRow, int maxCoulmn) {
        if(s[n][m]!=0) return s[n][m];
        if (n == maxRow - 1){
            s[n][m]=recure(s,n, m + 1, maxRow, maxCoulmn);
        }else if (m == maxCoulmn - 1){
            s[n][m]=recure(s,n+1, m, maxRow, maxCoulmn);
        } else s[n][m]=recure(s,n + 1, m, maxRow, maxCoulmn) + recure(s,n, m + 1, maxRow, maxCoulmn);
        return s[n][m];
    }
    public static void main(String[] args) {
        System.out.println(new UniquePaths().uniquePaths(51,9));
    }
}

