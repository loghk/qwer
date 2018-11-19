package leetcode;

/*
给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。

请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。

你可以假设 nums1 和 nums2 不同时为空。

示例 1:

nums1 = [1, 3]
nums2 = [2]

中位数是 2.0
示例 2:

nums1 = [1, 2]
nums2 = [3, 4]

中位数是 (2 + 3)/2 = 2.5

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        ArrayList nums = new ArrayList();
        for(int num : nums1){
            nums.add(num);
        }
        for (int num : nums2){
            nums.add(num);
        }
        int[] new_nums = new int[nums.size()];
        for (int i = 0;i<nums.size();i++){
            new_nums[i] = (int)nums.get(i);
        }
        Arrays.sort(new_nums);
        for (int i : new_nums){
            System.out.println(i);
        }
        if(new_nums.length%2==0){
            double ret = (new_nums[new_nums.length/2]+new_nums[new_nums.length/2-1])/2.0;
            return ret;
        }
        else{
            return new_nums[(new_nums.length-1)/2];
        }
    }
}

public class Work1 {
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

    public static String doubleToString(double input) {
        return new DecimalFormat("0.00000").format(input);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums1 = stringToIntegerArray(line);
            line = in.readLine();
            int[] nums2 = stringToIntegerArray(line);

            double ret = new Solution().findMedianSortedArrays(nums1, nums2);

            String out = doubleToString(ret);

            System.out.print(out);
        }
    }
}