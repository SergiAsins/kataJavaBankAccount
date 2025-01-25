package katas.BankAccount.accounts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    public void testRecordUpdatesBalance() {
        Account account = new Account(1000f, 0, 5f);
        account.record(500f);
        assertEquals(1500f, account.getBalance(), 0.001);
    }

    @Test
    public void testRecordUpdatesNumConsignments() {
        Account account = new Account(1000f, 0, 5f);
        account.record(500f);
        assertEquals(1, account.getNumConsignments());
    }

    @Test
    public void testRecordNegativeAmountDoesNotChangeBalance() {
        Account account = new Account(1000f, 0, 5f);
        account.record(-500f);
        assertEquals(1000f, account.getBalance(), 0.001);
        assertEquals(1, account.getNumConsignments());
    }

    @Test
    public void testSuccessfulWithdrawal() {
        Account account = new Account(1000f, 0, 5f);
        account.withdraw(500f);
        assertEquals(500f, account.getBalance(), 0.001);
        assertEquals(1, account.getNumWithdrawals());
    }

    @Test
    public void testInsufficientFundsWithdrawal() {
        Account account = new Account(500f, 0, 5f);
        account.withdraw(1000f);
        assertEquals(500f, account.getBalance(), 0.001);
        assertEquals(0, account.getNumWithdrawals());
    }

    @Test
    public void testMonthlyInterestCalculation() {
        Account account = new Account(1000f, 0, 12f); // Annual rate 12%
        account.monthlyInterest();
        float expectedInterest = 1000f * (12f / 12f) / 100;
        assertEquals(1000f + expectedInterest, account.getBalance(), 0.001);
    }

    @Test
    public void testSavingsAccountActiveState() {
        SavingAccount account = new SavingAccount(15000f, 5.0f);
        assertTrue(account.active, "Account should be active for balance >= 10000");
    }

    @Test
    public void testSavingsAccountInactiveAfterWithdrawal() {
        SavingAccount account = new SavingAccount(15000f, 5.0f);
        account.withdraw(12000f); // Balance now 3000, should become inactive
        assertFalse(account.active, "Account should become inactive for balance < 10000");
    }

    @Test
    public void testCurrentAccountOverdraft() {
        CurrentAccount account = new CurrentAccount(0f, 5.0f);
        account.withdraw(500f); // Overdraft should be 500
        assertEquals(500f, account.overdraft, 0.001);
        assertEquals(0f, account.getBalance(), 0.001);
    }

    @Test
    public void testCurrentAccountOverdraftRepayment() {
        CurrentAccount account = new CurrentAccount(0f, 5.0f);
        account.withdraw(500f); // Overdraft 500
        account.record(300f);  // Partial repayment
        assertEquals(200f, account.overdraft, 0.001);
        assertEquals(0f, account.getBalance(), 0.001);
    }

    @Test
    public void testMonthlyStatementUpdatesBalance() {
        Account account = new Account(1000f, 0, 5f);
        account.setMonthlyCommission(50f);
        account.monthlyInterest();
        account.monthlyStatement();
        float expectedInterest = 1000f * (5f / 12f) / 100;
        assertEquals(1000f - 50f + expectedInterest, account.getBalance(), 0.01);
    }

    @Test
    public void testSavingsAccountConsignmentOnInactive() {
        SavingAccount account = new SavingAccount(8000f, 5.0f);
        account.record(500f); // Should not change balance as account is inactive
        assertEquals(8000f, account.getBalance(), 0.001);
        assertFalse(account.active);
    }
}
