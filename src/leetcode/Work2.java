package leetcode;
/*
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:

输入: [7,1,5,3,6,4]
输出: 7
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
示例 2:

输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
示例 3:

输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution2 {
    public boolean isUp(int[] prices){
        for(int i = 0;i<prices.length-1;i++){
            for(int j = i+1;j<prices.length;j++){
                if(prices[j]<prices[i]) return false;
            }
        }
        return true;
    }

    public boolean isDown(int[] prices){
        for(int i = 0;i<prices.length-1;i++){
            for(int j = i+1;j<prices.length;j++){
                if(prices[j]>prices[i]) return false;
            }
        }
        return true;
    }

    public int maxProfit(int[] prices) {
        if(prices.length == 0||isDown(prices)) return 0;
        if(isUp(prices)){
            return prices[prices.length-1]-prices[0];
        }

        int i = 0;
        int j = 0;
        int count = 0;
        for(j = 1;j<prices.length;j++){
            i = j-1;
            while(prices[j-1]<prices[j]){
                j++;
                if(j == prices.length) break;
            }
            count += prices[j-1]-prices[i];

        }
        return count;
    }
}

public class Work2 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] prices = stringToIntegerArray(line);

            int ret = new Solution2().maxProfit(prices);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}