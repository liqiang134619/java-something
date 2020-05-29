package com.luni724.ag;

/**
 * @author Liq
 * @date 2020/5/28
 */
public class StringDemo {



    private static void basic() {
        // initialize
        String s1 = "Hello World";
        System.out.println("s1 is \"" + s1 + "\"");
        String s2 = s1;
        System.out.println("s2 is another reference to s1.");
        String s3 = new String(s1);
        System.out.println("s3 is a copy of s1.");
        // compare using '=='
        System.out.println("Compared by '==':");
        // true since string is immutable and s1 is binded to "Hello World"
        System.out.println("s1 and \"Hello World\": " + (s1 == "Hello World"));
        // true since s1 and s2 is the reference of the same object
        System.out.println("s1 and s2: " + (s1 == s2));
        // false since s3 is refered to another new object
        System.out.println("s1 and s3: " + (s1 == s3));
        // compare using 'equals'
        System.out.println("Compared by 'equals':");
        System.out.println("s1 and \"Hello World\": " + s1.equals("Hello World"));
        System.out.println("s1 and s2: " + s1.equals(s2));
        System.out.println("s1 and s3: " + s1.equals(s3));
        // compare using 'compareTo'
        System.out.println("Compared by 'compareTo':");
        System.out.println("s1 and \"Hello World\": " + (s1.compareTo("Hello World") == 0));
        System.out.println("s1 and s2: " + (s1.compareTo(s2) == 0));
        System.out.println("s1 and s3: " + (s1.compareTo(s3) == 0));

    }


    /**
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
     * 如果不存在，则返回  -1。
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        int l = needle.length(), n = haystack.length();
        if (l == 0) {
            return 0;
        }

        int pn = 0;
        while (pn < n - l + 1) {
            // find the position of the first needle character
            // in the haystack string
            while (pn < n - l + 1 && haystack.charAt(pn) != needle.charAt(0)) {
                ++pn;
            }

            // compute the max match string
            int currLen = 0, pL = 0;
            while (pL < l && pn < n && haystack.charAt(pn) == needle.charAt(pL)) {
                ++pn;
                ++pL;
                ++currLen;
            }

            // if the whole needle string is found,
            // return its start position
            if (currLen == l) {
                return pn - l;
            }

            // otherwise, backtrack
            pn = pn - currLen + 1;
        }
        return -1;
    }


    // 滑动窗口
    private static int strStr2(String haystack, String needle) {
        int i = haystack.length();
        int j = needle.length();
        for (int k = 0; k < i - j + 1; k++) {
            if (haystack.substring(k, j + k).equals(needle)) {
                return k;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";
        System.out.println(strStr2(haystack, needle));
    }
}
