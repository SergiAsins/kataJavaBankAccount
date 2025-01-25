package katas.BankAccount.accounts;

public class CurrentAccount extends Account{
    protected float overdraft;

    public CurrentAccount(float initialBalance, float annualRate){
        super(initialBalance, annualRate);
        overdraft = 0;
    }

    @Override
    public void withdraw(float withdrawMoney){
        if (withdrawMoney <= balance) {
            balance -= withdrawMoney;
        } else {
            overdraft += withdrawMoney - balance;
            balance = 0f;
        }
        numWithdrawals++;
    }

    @Override
    public void record(float amount){
        if (overdraft > 0) {
            if (amount >= overdraft) {
                balance = amount - overdraft;
                overdraft = 0;
            } else {
                overdraft -= amount;
            }
        } else {
            super.record(amount);
        }
    }

    public void print(){
        super.print();
        System.out.println("Ei buddy, watch out! Your overdraft is -$" + balance);
    }

    public void setOverdraft(float overdraft){
        this.overdraft = overdraft;
    }


}
