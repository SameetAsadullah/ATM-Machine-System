package dataLayer;

import logicLayer.Amount;
import logicLayer.User;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class writerAndReaderTest {
    private writerAndReader readAndWrite;

    private writerAndReaderTest() {
        readAndWrite = new writerAndReader();
    }

    @Test
    void readUsersFromFile() {
        Vector<User> users = new Vector<>();
        readAndWrite.readUsersFromFile(users);
        assertEquals(2, users.size());
    }

    @Test
    void readAtmData() {
        Amount amt = readAndWrite.readAtmData();
        assertEquals(1500, amt.getTotalAmount());
    }
}