package com.neatcode.common.utils;

public class MathUtils {

    public static int gcd(int a, int b) {
        if(b == 0) return a;  // or, if(a%b == 0) return b;
        return gcd(b, a%b);
    }

    public static long gcd(long a, long b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }

    public static int gcd(int... arr) {
        if(arr == null) return -1;
        int res = arr[0];
        for(int n: arr) {
            res = gcd(res, n);
            if(res == 1) return 1;
        }
        return res;
    }

    public static long lcm(int a, int b) {
        long aa = a;
        return (aa * b) / gcd(a, b);
    }

    public static long lcm(int... arr) {
        if(arr == null) return -1;
        long res = arr[0];
        for(int n: arr) {
            res = lcm(res, n);
        }
        return res;
    }

    /**
     * privately used as helper for lcm function.
     */
    private static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }
}
