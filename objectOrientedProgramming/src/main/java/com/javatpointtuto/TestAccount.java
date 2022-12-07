package com.javatpointtuto;

//Banking-System example
class Account{
    int acc_no;
    String name;
    float amount;

    void insert(int a, String n, float amt){
        acc_no=a;
        name=n;
        amount=amt;
    }

    void deposit(float amt){
        amount += amt;
        System.out.println(amt + " $" + " deposited");
    }
    void withdraw(float amt){
        if (amount < amt){
            System.out.println("Insufficient Balance");
        }else{
            amount -= amt;
            System.out.println(amt + " $" +" withdrawn");
        }
    }
//method Check the account blance
    void checkBalance(){
        System.out.println("Balance is " + amount + " $");
    }
//method to diplay an object value
    void display(){
        System.out.println("Account information : " + acc_no + " " + name + " " + amount + " $");
    }
}


public class TestAccount {
    public static void main(String[] args) {
        Account account1 = new Account();
        account1.insert(20221002, "Dicken", 1150);
        account1.display();
        account1.checkBalance();
        account1.deposit(2000);
        account1.checkBalance();
        account1.withdraw(300);
//        account1.withdraw(40000);
        account1.checkBalance();
    }
}
