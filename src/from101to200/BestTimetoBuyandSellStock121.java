package from101to200;

/**
 * 输入： [7,1,5,3,6,4]
 * 输出： 5
 * 说明：在第2天买入（价格= 1）并在第5天卖出（价格= 6），利润= 6-1 = 5。
 * 不是7-1 = 6，因为售价需要大于购买价格。
 * <p>
 * 我们从后面开始操作
 */
public class BestTimetoBuyandSellStock121 {
    public int maxProfit(int[] prices) {
        byte[] a = new byte[0];
        synchronized (a) {

        }
        if (prices.length == 0) return 0;
        int max = 0;
        int currnetMax = prices[prices.length - 1];
        for (int i = prices.length - 1; i >= 0; --i) {
            if (currnetMax > prices[i]) {
                //保存大的
                max = max > currnetMax - prices[i] ? max : currnetMax - prices[i];
            } else {
                currnetMax = prices[i];
            }
        }
        return max;
    }
}
