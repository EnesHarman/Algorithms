package com.enesharman.algorithms;

import java.util.Arrays;

public class Sorting {
    public static void main(String[] args) {
        int[] arr = new int[]{75, 13, 42, 25, 68, 23, 56, 34, 76, 45, 11, 9};
        insertionSort(arr.clone());
        mergeSort(arr, 0, arr.length - 1);
        quickSort(arr, 0, arr.length -1);
        printArr(arr);
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int partitionIndex = partition(arr, left, right);
        quickSort(arr, left, partitionIndex - 1);
        quickSort(arr, partitionIndex + 1,  right);
    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1; // -1 at first
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i ,j);
            }
        }
        i++;
        swap(arr, i, right);
        return i;
    }

    private static void swap(int[] arr, int inx1, int inx2) {
        int temp = arr[inx1];
        arr[inx1] = arr[inx2];
        arr[inx2] = temp;
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int key = arr[i];
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
        printArr(arr);
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int original = left;
        int leftIndex = 0;
        int rightIndex = 0;
        int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);
        while (leftIndex < leftArr.length && rightIndex < rightArr.length) {
            if (leftArr[leftIndex] < rightArr[rightIndex]) {
                arr[original] = leftArr[leftIndex];
                leftIndex++;
            } else {
                arr[original] = rightArr[rightIndex];
                rightIndex++;
            }
            original++;
        }

        while (leftIndex < leftArr.length) {
            arr[original] = leftArr[leftIndex];
            original++;
            leftIndex++;
        }
        while (rightIndex < rightArr.length) {
            arr[original] = rightArr[rightIndex];
            original++;
            rightIndex++;
        }
    }

    public static void printArr(int[] arr) {
        for (int e : arr) {
            System.out.print(" " + e);
        }
        System.out.println();
    }
}
