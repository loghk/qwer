package leetcode;
/*
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba"也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution3 {
    public String longestPalindrome(String s) {
        StringBuilder temp = new StringBuilder(s);
        temp = temp.reverse();
        //System.out.println(temp);
        int[] str1 = new int[s.length()];
        int[] str2 = new int[s.length()];
        for (int i = 0;i<s.length();i++){
            str1[i] = s.charAt(i);
            str2[i] = temp.charAt(i);
        }

        return ""+temp;
    }
}

public class Work3 {
    public static String stringToString(String input) {
        if (input == null) {
            return "null";
        }
        return input.substring(1, input.length()-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = stringToString(line);

            String ret = new Solution3().longestPalindrome(s);

            String out = (ret);

            System.out.print(out);
        }
    }
}