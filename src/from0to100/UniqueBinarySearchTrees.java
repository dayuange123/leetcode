package from0to100;

/**
 * 分享
 * 给定n，有多少结构上唯一的BST（二叉搜索树）存储值1 ...  n？
 *
 * 例：
 *
 * 输入： 3
 *  输出： 5
 *  说明：
 * 给定n = 3，总共有5个唯一的BST：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 个数等于左×右
 *
 */

public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
            //setup the dp array to store different combination number
        int[] dp = new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            for(int j=0; j<i;j++){
                //Possible number equals to the possible left tree*right tree
                dp[i]+=dp[j]*dp[i-j-1];
            }
        }
        return dp[n];
    }
}
