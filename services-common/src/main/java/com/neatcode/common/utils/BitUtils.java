package com.neatcode.common.utils;

public class BitUtils {
    public static boolean isOdd(int n) {
        return (n&1) == 1;
    }

    public static boolean isEven(int n) {
        return (n&1) == 0;
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * multiply n by 2
     */
    public static long twice(int n) {
        return n << 1;
    }

    /**
     * divide n by 2
     */
    public static long half(int n) {
        return n >> 1;
    }

    /**
     * is Kth bit (from The Least Significant Bit LSB)set in N
     */
    public static boolean isKthBitSet(int n, int k) {
        return !((n & (1 << (k - 1))) == 0);
    }

    /**
     * clears Kth bit in N
     */
    public static int clearKthBit(int n, int k) {
        n = n & (~(1 << (k - 1)));
        return n;
    }

    /**
     * sets Kth bit in N
     */
    public static int setKthBit(int n, int k) {
        n = n | (1 << (k - 1));
        return n;
    }

    /**
     * toggles Kth bit in N
     */
    public static int toggleKthBit(int n, int k) {
        n ^= 1 << (k - 1);
        return n;
    }

    /**
     * clears rightmost set bit
     */
    public static int clearRightMostSetBit(int n) {
        return n & (n - 1);
    }

    /**
     * clears all set bit except right most set bit
     */

    public static int clearAllButFirstSetBit(int n) {
        return n & (-n);
    }

    /**
     * returns true if n is power of 2
     */
    public static boolean isPowerOf2(int n) {
        return ((n & (~(n & (n - 1)))) == n);
    }

    /**
     * returns number of set bits in n
     */
    public static int bitCount(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count+=1;
        }
        return count;
    }
}
