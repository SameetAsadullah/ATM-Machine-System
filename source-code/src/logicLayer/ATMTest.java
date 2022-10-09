package logicLayer;

import static org.junit.jupiter.api.Assertions.*;

class ATMTest {
    private ATM atm;

    private ATMTest() {
        atm = ATM.getInstance();
    }

    @org.junit.jupiter.api.Test
    void validateAccount() {
        assertTrue(atm.validateAccount("123456789", "1234"));
    }

    @org.junit.jupiter.api.Test
    void canWithdrawMoney() {
        assertEquals(1, atm.canWithdrawMoney("123456789", 5));
    }

    @org.junit.jupiter.api.Test
    void getBalance() {
        assertEquals(800, atm.getBalance("123456789"));
    }

    @org.junit.jupiter.api.Test
    void getMaximumWithdrawAmount() {
        assertEquals(1000, atm.getMaximumWithdrawAmount("123456789"));
    }

    @org.junit.jupiter.api.Test
    void getUsers() {
        assertEquals(2, atm.getUsers().size());
    }

    @org.junit.jupiter.api.Test
    void getAmountInATM() {
        assertEquals(1500, atm.getAmountInATM().getTotalAmount());
    }
}