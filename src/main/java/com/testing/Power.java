package com.testing;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class Power {
    public static void main (String[] args)
    {
        BigDecimal y2 = new BigDecimal("0.999999999999999");
        BigDecimal x1 = new BigDecimal("7");
        BigDecimal y1 = new BigDecimal("-0.888888889999999");
        BigDecimal x2 = new BigDecimal("-2653");
        BigDecimal res = (x2.subtract(x1).pow(2).add(y2.subtract(y1).pow(2)));
        System.out.println(res);

//        double x1 = res;
//        double y1 = 1d;
//        double x2 = 1d;
//        double y2 = 2d;
//        double ans = ((x2-x1)*(x2-x1)) + ((y2-y1)*(y2-y1));

//        System.out.println(Double.MAX_VALUE*(-1)*2 + " - " + Double.MAX_VALUE);
//
//        System.out.println((1-res)*(1-res)+);
//        System.out.println(ans);

    }

    private void old() {
        int LB = 0;
        int UB = 0;
        int P = 0;
        int pow = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the lower bound of range");
        LB = sc.nextInt();
        System.out.println("Enter the upper bound of range");
        UB = sc.nextInt();
        System.out.println("Enter the Power for the Entered Number");
        P = sc.nextInt();
        int j = LB;
        if (P == 0) {
            pow = 1;
            System.out.println(pow);
        } else {
            while (j <= UB) {
                for (int i = 1; i <= P; i++)
                    pow *= j;
                System.out.println("The result is " + pow);
                pow = 1;
                j++;
            }


        }
    }

}
