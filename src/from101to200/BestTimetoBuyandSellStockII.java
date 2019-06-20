package from101to200;

/**
 * 假设您有一个数组，其中第i 个元素是第i天给定股票的价格。
 * <p>
 * 设计算法以找到最大利润。您可以根据需要完成尽可能多的交易（即，多次买入并卖出一股股票）。
 * <p>
 * 注意：您不能同时进行多笔交易（即，您必须在再次购买之前卖出股票）。
 * <p>
 * 例1：
 * <p>
 * 输入： [7,1,5,3,6,4]
 * 输出： 7
 * 说明：在第2天买入（价格= 1）并在第3天卖出（价格= 5），利润= 5-1 = 4。
 * 然后在第4天买入（价格= 3）并在第5天卖出（价格= 6），利润= 6-3 = 3。.
 *
 * 贪心算法
 */
public class BestTimetoBuyandSellStockII {

    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length - 1; ++i)
            max = prices[i] < prices[i + 1] ? max + prices[i + 1] - prices[i] : max;
        return max;
    }

    public static void main(String[] args) {
        int[] a = {7, 1, 5, 3, 6, 4};
        new BestTimetoBuyandSellStockII().maxProfit(a);
    }
}
