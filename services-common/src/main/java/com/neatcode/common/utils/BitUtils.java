package com.neatcode.common.utils;

public class BitUtils {
    public static boolean isOdd(int n) {
        return (n&1) == 1;
    }

    public static boolean isEven(int n) {
        return (n&1) == 0;
    }
}
