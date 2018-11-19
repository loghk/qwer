package qwe;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
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
        int j = 1;
        int count = 0;
        for(j = 1;j<prices.length;j++){
            i = j-1;
            while(prices[j-1]<prices[j]){
                j++;
                if (j==prices.length) break;
            }
            count += prices[j-1]-prices[i];

        }
        return count;
    }
}

public class Test2 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        //input = input.substring(1, input.length() - 1);
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

            int ret = new Solution().maxProfit(prices);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}