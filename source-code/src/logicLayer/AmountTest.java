package logicLayer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {
    private Amount amt;

    private AmountTest() {
        amt = new Amount(1500, 10, 30, 30, 20);
    }

    @Test
    void getFiftyNotes() {
        assertEquals(10, amt.getFiftyNotes());
    }

    @Test
    void getFiveNotes() {
        assertEquals(20, amt.getFiveNotes());
    }

    @Test
    void getTenNotes() {
        assertEquals(30, amt.getTenNotes());
    }

    @Test
    void setFiftyNotes() {
        amt.setFiftyNotes(50);
        assertEquals(50, amt.getFiftyNotes());
    }

    @Test
    void setFiveNotes() {
        amt.setFiveNotes(5);
        assertEquals(5, amt.getFiveNotes());
    }

    @Test
    void setTenNotes() {
        amt.setTenNotes(10);
        assertEquals(10, amt.getTenNotes());
    }
}