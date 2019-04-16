package from0to100;

/**
 * 爬楼梯
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        int[] result = new int[n+1];
        result[0]=1;
        result[1]=1;
        for(int i=2;i<=n;i++){
            result[i]=result[i-1]+result[i-2];
        }
        return result[n];
    }

    public static void main(String[] args) {
        System.out.println(new ClimbingStairs().climbStairs(44));
    }
}
