package katas.BankAccount.accounts;

public class Account {
    protected Float balance;
    protected Integer numConsignments;
    protected Integer numWithdrawals;
    protected Float annualRate;
    protected Float monthlyCommission;

    public Account(Float initialBalance, Integer numConsignments, Float annualRate) {
        this.balance = initialBalance;
        this.numConsignments = numConsignments;
        this.numWithdrawals = 0;
        this.annualRate = annualRate;
        this.monthlyCommission = 0f;
    }

    public Account(float initialBalance, float annualRate) {
        this.balance = initialBalance;
        this.numConsignments = 0;
        this.numWithdrawals = 0;
        this.annualRate = annualRate;
        this.monthlyCommission = 0f;
    }


    public void record(float amount){
        if(amount > 0){
            balance += amount;
            numConsignments ++;
        }
    }

    public void withdraw(float withdrawMoney){
        if(withdrawMoney <= balance) {
            balance -= withdrawMoney;
            numWithdrawals++;
        }else {
            System.out.println("Insufficient available quantity in your account. Your current balance is " + balance);
        }
    }

    public void monthlyInterest(){
        balance += balance * (annualRate/12)/100;
    }

    protected void monthlyStatement(){
        balance -= monthlyCommission;
        monthlyInterest();
    }


    public void print(){
        System.out.println("Current balance: $" + balance);
        System.out.println("Number of consignments: " + numConsignments);
        System.out.println("Number of withdrawals " + numWithdrawals);
        System.out.println("Annual rate~" + annualRate);
        System.out.println("Monthly commission $" + monthlyCommission);
    }

    //getters and setters

    public Float getBalance() {
        return balance;
    }

    public Integer getNumConsignments() {
        return numConsignments;
    }

    public Integer getNumWithdrawals() {
        return numWithdrawals;
    }

    public Float getAnnualRate() {
        return annualRate;
    }

    public Float getMonthlyCommission() {
        return monthlyCommission;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public void setNumConsignments(Integer numConsignments) {
        this.numConsignments = numConsignments;
    }

    public void setNumWithdrawals(Integer numWithdrawals) {
        this.numWithdrawals = numWithdrawals;
    }

    public void setAnnualRate(Float annualRate) {
        this.annualRate = annualRate;
    }

    public void setMonthlyCommission(Float monthlyCommission) {
        this.monthlyCommission = monthlyCommission;
    }
}


