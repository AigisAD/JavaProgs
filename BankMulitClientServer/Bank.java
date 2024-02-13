/**
 * Author: Jacques Gueye
 * Assignment: BankMulitClientServer 
 * Date: 05/29/21
 * Course: CS56 Adv Java (1791)
 * Description: Program implements an server
 * for multiple clients to simulate bank transactions
 * such as withdraws and deposits.
 */


package BankMultiClientServer;

public class Bank implements java.io.Serializable{
    private String accountNumber;
    private double balance;
    Bank(String accountNumber,double balance){
        this.accountNumber=accountNumber;
        this.balance=balance;
    }
    public void deposit(double addBal){
        balance+=addBal;
    }
    public boolean withdraw(double subBal){
        if (balance-subBal>=0){
            balance-=subBal;
            return true;
        }
        return false;
    }
    public double getBalance(){
        return balance;
    }    
    public String getAccountNumber(){
        return accountNumber;
    }  
}
