package logicLayer;

public class User {
    private String accNo, pin;
    private int ID, balance, overdraft;

    //constructors
    public User() {
    }
    public User(int id, String accNo, String pin, int balance, int overdraft) {
        this.ID = id;
        this.accNo = accNo;
        this.pin = pin;
        this.balance = balance;
        this.overdraft = overdraft;
    }

    //getters
    public int getID() {
        return ID;
    }
    public String getAccNo() {return accNo;}
    public int getBalance() {return balance;}
    public int getOverdraft() {return overdraft;}
    public String getPin() {return pin;}

    //setters
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setAccNo(String accNo) {this.accNo = accNo;}
    public void setBalance(int balance) {this.balance = balance;}
    public void setOverdraft(int overdraft) {this.overdraft = overdraft;}
    public void setPin(String pin) {this.pin = pin;}
}
