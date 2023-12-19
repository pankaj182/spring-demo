package com.neatcode.common.algos;

public class Searching {

    public static int search(int[] arr, int key) {
        if(arr == null || arr.length == 0) {
            return -1;
        }
        int i = 0;
        for(int n: arr) {
            if(n == key) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int binarySearch(int[] sortedArray, int key) {
        int low = 0;
        int high = sortedArray.length-1;
        int mid;
        while(low <= high) {
            mid = low + (high-low)/2;
            if(sortedArray[mid] == key) {
                return mid;
            } else if(sortedArray[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -low-1;
    }
}
