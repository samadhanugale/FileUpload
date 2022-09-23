package com.neo.fileupload.dataStructures;

public class Array2d {
    public static void main(String[] args){
      //
        int[][] accounts = {
                {3,4,5},
                {23,24,25},
                {11,22,33}
            };
        System.out.println(maximumWealth(accounts));

    }
    static int maximumWealth(int[][] accounts){
        int ans = Integer.MIN_VALUE;
        for(int row = 0; row < accounts.length;row++) {
            int rowSum = 0;
            for(int column = 0; column < accounts[row].length; column++) {
              rowSum+=accounts[row][column];
            }
            System.out.println("Ans : "+rowSum);
            if(ans < rowSum){
                ans = rowSum;
            }
          //
        }
        return ans;
    }
}
