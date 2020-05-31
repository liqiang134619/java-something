package com.luni724.ag;

import java.util.Arrays;
import java.util.OptionalInt;

/**
 * @author Liq
 * @date 2020/5/28
 */
public class ArrayDemo {


    /**
     * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
     * <p>
     * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
     * <p>
     * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
     *
     * @param nums
     * @return
     */
    private static int pivoIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int sum1 = 0;
            int sum2 = 0;
            for (int j = 0; j < i; j++) {
                sum1 += nums[j];
            }
            for (int j = i + 1, size = nums.length; j < size; j++) {
                sum2 += nums[j];
            }
            if (sum1 == sum2) {
                return i;
            }
        }
        return -1;
    }

    private static int pivoIndex2(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 左边元素 == 右边元素
            if (leftSum == sum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;

    }


    /**
     * 在一个给定的数组nums中，总是存在一个最大元素 。
     * <p>
     * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
     * <p>
     * 如果是，则返回最大元素的索引，否则返回-1。
     * <p>
     * 输入: nums = [3, 6, 1, 0]
     * 输出: 1
     * 解释: 6是最大的整数, 对于数组中的其他整数,
     * 6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
     * <p>
     * <p>
     * 输入: nums = [1, 2, 3, 4]
     * 输出: -1
     * 解释: 4没有超过3的两倍大, 所以我们返回 -1.
     *
     * @param nums given Array
     * @return xx
     */
    private static int dominantIndex(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            boolean flag = false;
            for (int i1 = 0; i1 < nums.length; i1++) {
                if (i1 == i) {
                    continue;
                }
                if (nums[i1] * 2 <= nums[i]) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }

        }
        return -1;
    }


    private static int dominantIndex2(int[] nums) {
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (maxIndex != i && nums[maxIndex] < nums[i] * 2) {
                return -1;
            }
        }
        return maxIndex;
    }


    private static int[] calculateMaxMatchLengths(String pattern) {
        int[] maxMatchLengths = new int[pattern.length()];
        int maxLength = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (maxLength > 0 && pattern.charAt(maxLength) != pattern.charAt(i)) {
                maxLength = maxMatchLengths[maxLength - 1]; // ①
            }
            if (pattern.charAt(maxLength) == pattern.charAt(i)) {
                maxLength++; // ②
            }
            maxMatchLengths[i] = maxLength;
        }
        return maxMatchLengths;
    }



    public static int searchInsert(int[] nums, int target) {




        for (int i = 0; i < nums.length; i++) {
            if (target <= nums[i]) {
                return i;
            }

        }
        return nums.length ;

    }

    public static void main(String[] args) {

        int[] nums = {1,3,5,6};
        int i = searchInsert(nums, 7);
        System.out.println(i);


    }
}
