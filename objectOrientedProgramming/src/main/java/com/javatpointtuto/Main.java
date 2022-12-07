package com.javatpointtuto;

public class Main {
        public static void main(String[] args) {
            Rectangle R1 = new Rectangle(), R2 = new Rectangle();
            R1.insert(12, 10);
            R2.insert(14, 2);
            R1.calculateArea();
            R2.calculateArea();
    }
}
class Rectangle {
    int length, width;

    void insert(int l, int w){
        length = l;
        width = w;
    }
    void calculateArea(){
        System.out.println(length*width);
    }
}