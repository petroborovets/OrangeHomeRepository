package com.boro.black.utils;


import com.boro.black.component.CrawlerUtil;

import java.sql.Timestamp;

/**
 * Created by petroborovets on 4/12/15.
 */
public class Main {
    public static void main(String[] args) {
        int[][] multiplyTab  = new int[11][11];
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                multiplyTab[i][j] = i*j;
                System.out.print(multiplyTab[i][j] + " ");
            }
            System.out.println();
        }
        double maxValue = Math.pow(multiplyTab.length-1, 2);
        System.out.println(maxValue);
    }
}
