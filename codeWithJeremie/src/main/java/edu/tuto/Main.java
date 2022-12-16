package edu.tuto;


import java.util.Scanner;

public class Main {
    static Scanner myScanner = new Scanner(System.in);
    static float numb1;
    static int numb2;
    public static void main(String[] args) {
        System.out.println("** I'm a program comparing values **");
        System.out.println("Enter first number : ");
        numb1 = myScanner.nextFloat();
        System.out.println("Enter the second number :");
        numb2 = myScanner.nextInt();
        System.out.println("number 1 equals number 2 : " + (numb1 == numb2));
        System.out.println("number 1 inferior to number 2 : " + (numb1 < numb2));
        System.out.println("number 1 superior to number 2 : " + (numb1 > numb2));
    }
}