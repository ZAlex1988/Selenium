package com.testing;

import java.util.Scanner;

public class Power {
    public static void main (String[] args)
    {
        int LB = 0;
        int UB = 0;
        int P = 0;
        int pow =1;

        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the lower bound of range");
        LB=sc.nextInt();
        System.out.println("Enter the upper bound of range");
        UB=sc.nextInt();
        System.out.println("Enter the Power for the Entered Number");
        P=sc.nextInt();
        int j= LB;
        if(P==0)
        {
            pow = 1;
            System.out.println(pow);
        }
        else
        {
            while(j<=UB)
            {
                for (int i = 1; i <= P; i++)
                    pow *= j;
                System.out.println("The result is " +pow);
                pow=1;
                j++;
            }


        }
    }

}
