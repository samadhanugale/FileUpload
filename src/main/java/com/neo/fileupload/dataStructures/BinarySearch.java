package com.neo.fileupload.dataStructures;

public class BinarySearch {
    public static void main(String[] args){
      //
        int[] arr = {12,23,34,45,56,67,78,89,90,93};
        int target = 566;
        int ans = binarySearch(arr,target);
        if(ans == -1){
            System.out.println("Target not found!");
        }else{
            System.out.println("Target found at index : "+ans+" which is : "+arr[ans]);
        }
    }

    static int binarySearch(int[] arr,int target){
        int start = 0;
        int end = arr.length-1;

        while(start<=end){

            int mid = start + (end-start)/2;
            if(target<arr[mid]){
                //present at left side
                 end = mid -1;
            }else if(target > arr[mid]){
                //present at right side
                start = mid+1;
            }else{
                //ans found
                return mid;
            }
        }
        return -1;
    }
}
