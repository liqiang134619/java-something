package com.luni724.ag;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 * @author Liq
 * @date 2020/5/26
 */
public class Demo01 {


    private static void merge(int[] nums1, int m, int[] nums2, int n) {

//        System.arraycopy(nums2,0,nums1,m,nums2.length);
//        Arrays.sort(nums1);
//        System.out.println(Arrays.toString(nums1));

        // Make a copy of nums1.
        int[] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        // Two get pointers for nums1_copy and nums2.
        int p1 = 0;
        int p2 = 0;

        // Set pointer for nums1
        int p = 0;

        // Compare elements from nums1_copy and nums2
        // and add the smallest one into nums1.
        while ((p1 < m) && (p2 < n)) {
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];
        }

        // if there are still elements to add
        if (p1 < m) {
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        }
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
        }
    }

    private static void merge2(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;

        // set pointer for nums1
        int p = m + n - 1;

        // while there are still elements to compare
        while ((p1 >= 0) && (p2 >= 0))
        // compare two elements from nums1 and nums2
        // and add the largest one in nums1
        {
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }

        // add missing elements from nums2
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    private static boolean isBadVersion(int version) {
        return false;
    }

    private static int firstBadVersion(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    public int maxProfit(int[] prices) {
        int sum = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                sum += prices[i + 1] - prices[i];
            }
        }
        return sum;
    }


    private static List<String> fizzBuzz(int n) {
        List<String> s = new ArrayList<>(n);
        for (int i = 1; i < n + 1; i++) {
            s.add((i % 15 == 0) ? "FizzBuzz" : (i % 5 == 0) ? "Buzz" : (i % 3 == 0) ? "Fizz" : String.valueOf(i));
        }
        return s;

    }


    private static int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrim(i)) {
                System.out.println(i);
                count++;
            }
        }

        return count;
    }

    private static boolean isPrim(int i) {
        for (int j = 2; j * j <= i; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }


    public static boolean isPowerOfThree(int n) {
        while (n != 1) {
            System.out.println(n);
            n = n / 3;
        }
        return true;
    }

    public static int countPrimes1(int n) {
        Stream<Integer> integerStream = IntStream.rangeClosed(2, n).mapToObj(i->isPrim(i)).map(b->{
            if(b) {
                return 1;
            }
            return 0;
        });
        return integerStream.reduce(0, Integer::sum);
    }

    public static boolean isPrime(int n) {
        int can = (int)Math.sqrt(n);
        return IntStream.rangeClosed(2,can).noneMatch( i -> can % i ==0);
    }


    public static void main(String[] args) {
        System.out.println(countPrimes1(20));

    }
}
