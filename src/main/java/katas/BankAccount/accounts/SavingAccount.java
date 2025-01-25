package katas.BankAccount.accounts;

public class SavingAccount extends Account{
    public boolean active;

    public SavingAccount(float initialBalance, float annualRate){
        super(initialBalance, annualRate);
        active = initialBalance >= 10000;
    }

    @Override
    public void record(float amount){
        if (active){
            super.record(amount);
            active = balance >= 10000;
        }
        System.out.println("Non active account. Consignments can not be done.");
    }

    @Override
    public void withdraw(float withdrawMoney){
        if(active){
            super.withdraw(withdrawMoney);
            active = balance >= 10000;
        }
        System.out.println("Non active account. Withdrawals can not be performed.");
    }

    @Override
    public void monthlyStatement(){
        super.monthlyStatement();
    }

    public void print(){
        super.print();
        System.out.println("Account state: " + (active ? "Active" : "Inactive"));
    }
}
