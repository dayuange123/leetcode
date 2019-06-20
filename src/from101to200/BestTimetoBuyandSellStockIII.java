package from101to200;

/**
 * 假设您有一个数组，其中第i 个元素是第i天给定股票的价格。
 * <p>
 * 设计算法以找到最大利润。您最多可以完成两笔交易。
 * <p>
 * 注意： 您不能同时进行多笔交易（即，您必须在再次购买之前卖出股票）。
 * <p>
 * 例1：
 * <p>
 * 输入： [3,3,5,0,0,3,1,4]
 * 输出： 6
 * 说明：第4天买入（价格= 0）并在第6天卖出（价格= 3），利润= 3-0 = 3。
 * 然后在第7天买入（价格= 1）并在第8天卖出（价格= 4），利润= 4-1 = 3。
 * <p>
 * 动态规划
 * 来回两趟
 * 第一趟先从前往后
 */
public class BestTimetoBuyandSellStockIII {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int[] dp = new int[prices.length];
        int cunrrent = prices[0];
        int res = 0;
        //第一趟
        for (int i = 0; i < prices.length - 1; ++i) {
            if (cunrrent < prices[i]) {
                dp[i] = prices[i] - cunrrent;
            } else {
                cunrrent = prices[i];
            }
        }
        //第二趟 从尾部计算
        int max = 0;
        cunrrent = prices[prices.length - 1];
        for (int i = prices.length - 1; i >= 0; i--) {
            if (cunrrent > prices[i]) {
                max = Math.max(cunrrent - prices[i], max);
            } else {
                cunrrent = prices[i];
            }
            res=Math.max(max+dp[i],res);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {3,3,5,0,0,3,1,4};
        new BestTimetoBuyandSellStockIII().maxProfit(a);
    }
}
