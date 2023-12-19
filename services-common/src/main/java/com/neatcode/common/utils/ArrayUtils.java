package com.neatcode.common.utils;

public class ArrayUtils {
    public static void reverse(int[] arr) {
        if(arr == null || arr.length == 0) {
            return;
        }
        for(int i = 0, j = arr.length-1; i<j; i++, j--) {
            BitUtils.swap(arr, i, j);
        }
    }
}
