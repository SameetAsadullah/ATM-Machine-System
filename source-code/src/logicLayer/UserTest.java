package logicLayer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    private UserTest() {
        user = new User(1, "123456789", "1234", 800, 200);
    }

    @Test
    void getID() {
        assertEquals(1, user.getID());
    }

    @Test
    void getAccNo() {
        assertEquals("123456789", user.getAccNo());
    }

    @Test
    void getBalance() {
        assertEquals(800, user.getBalance());
    }

    @Test
    void setID() {
        user.setID(2);
        assertEquals(2, user.getID());
    }

    @Test
    void setAccNo() {
        user.setAccNo("987654321");
        assertEquals("987654321", user.getAccNo());
    }

    @Test
    void setBalance() {
        user.setBalance(1500);
        assertEquals(1500, user.getBalance());
    }
}