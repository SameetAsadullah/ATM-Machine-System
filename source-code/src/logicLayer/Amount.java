package logicLayer;

public class Amount {
    private int totalAmount, fiftyNotes, twentyNotes, tenNotes, fiveNotes;

    // constructor
    public Amount() {}

    public Amount(int totalAmount, int fiftyNotes, int twentyNotes, int tenNotes, int fiveNotes) {
        this.totalAmount = totalAmount;
        this.fiftyNotes = fiftyNotes;
        this.twentyNotes = twentyNotes;
        this.tenNotes = tenNotes;
        this.fiveNotes = fiveNotes;
    }

    // getters
    public int getFiftyNotes() {return fiftyNotes;}
    public int getFiveNotes() {return fiveNotes;}
    public int getTenNotes() {return tenNotes;}
    public int getTotalAmount() {return totalAmount;}
    public int getTwentyNotes() {return twentyNotes;}

    // setters
    public void setFiftyNotes(int fiftyNotes) {this.fiftyNotes = fiftyNotes;}
    public void setFiveNotes(int fiveNotes) {this.fiveNotes = fiveNotes;}
    public void setTenNotes(int tenNotes) {this.tenNotes = tenNotes;}
    public void setTotalAmount(int totalAmount) {this.totalAmount = totalAmount;}
    public void setTwentyNotes(int twentyNotes) {this.twentyNotes = twentyNotes;}
}
