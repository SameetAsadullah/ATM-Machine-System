package logicLayer;

import dataLayer.writerAndReader;

import java.util.Vector;
import java.io.*;

public class ATM {
    private Vector<User> users;
    private writerAndReader readAndWrite;
    private static ATM instance;
    private Amount amountInATM;

    //constructor
    private ATM() {
        //initializing data members
        users = new Vector<>();
        readAndWrite = new writerAndReader();

        //initializing users from file
        try {
            File myObj = new File("./src/dataLayer/users.csv");
            if (!myObj.createNewFile()) {   //if file has already created
                readAndWrite.readUsersFromFile(users);
            } else {
                readAndWrite.writeHeadersInFile();
            }
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

        //initializing ATM data from file
        try {
            File myObj = new File("./src/dataLayer/atm_data.csv");
            if (!myObj.createNewFile()) {   //if file has already created
                amountInATM = readAndWrite.readAtmData();
            } else {
                readAndWrite.writeAtmHeadersInFile();
            }
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    //getters
    public Vector<User> getUsers() {
        return users;
    }
    public writerAndReader getReadAndWrite() {
        return readAndWrite;
    }
    public Amount getAmountInATM() {return amountInATM;}

    //setters
    public void setUsers(Vector<User> users) {
        this.users = users;
    }
    public void setReadAndWrite(writerAndReader readAndWrite) {
        this.readAndWrite = readAndWrite;
    }
    public void setAmountInATM(Amount amountInATM) {this.amountInATM = amountInATM;}

    //function to get instance of this class
    public static ATM getInstance(){
        if (instance == null) {
            instance = new ATM();
        }
        return instance;
    }

    //function to check if the account number and pin is valid or not
    public boolean validateAccount(String accNo, String pin) {
        for (User user : users) {
            if (accNo.equals(user.getAccNo()) && pin.equals(user.getPin())) {
                return true;
            }
        }
        return false;
    }

    // function to check whether the money is withdrawable or not
    public int canWithdrawMoney(String accNo, int money) {
        for (User user : users) {
            if (accNo.equals(user.getAccNo())) {
                if (user.getBalance() >= money || user.getBalance() + user.getOverdraft() >= money) {
                    if (amountInATM.getTotalAmount() >= money) {
                        return 1;   // good to go
                    } else {
                        return 2;   // not enough money in ATM
                    }

                } else {
                    return 0;   // not enough money in account
                }
            }
        }
        return 0;   // accNo didn't match
    }

    // function to get balance
    public int getBalance(String accNo) {
        for (User user : users) {
            if (accNo.equals(user.getAccNo())) {
                return user.getBalance();
            }
        }
        return -1;
    }

    // function to get maximum withdraw amount
    public int getMaximumWithdrawAmount(String accNo) {
        for (User user : users) {
            if (accNo.equals(user.getAccNo())) {
                return user.getBalance() + user.getOverdraft();
            }
        }
        return -1;
    }

    //function to withdraw money
    public Amount withdrawMoney(String accNo, int money) {
        // counting and deducting notes for withdrawing money
        int money_copy = money, note = 50, fiftyNotes = 0, twentyNotes = 0, tenNotes = 0, fiveNotes = 0;
        int notes = money_copy / note;

        if (notes == 0 && note > money_copy)
        {
            note = 20;
            notes = money_copy / note;
        }
        if (notes == 0 && note > money_copy)
        {
            note = 10;
            notes = money_copy / note;
        }
        if (notes == 0 && note > money_copy)
        {
            note = 5;
            notes = money_copy / note;
        }

        while (notes != 0 && money_copy != 0) {
            if (note == 50) {
                if (amountInATM.getFiftyNotes() >= notes) {
                    fiftyNotes = notes;
                    amountInATM.setFiftyNotes(amountInATM.getFiftyNotes() - notes);
                } else {
                    fiftyNotes = amountInATM.getFiftyNotes();
                    amountInATM.setFiftyNotes(0);
                }

                money_copy -= fiftyNotes * 50;
                note = 20;
            }
            else if (note == 20) {
                if (note <= money_copy) {
                    if (amountInATM.getTwentyNotes() >= notes) {
                        twentyNotes = notes;
                        amountInATM.setTwentyNotes((amountInATM.getTwentyNotes() - notes));
                    } else {
                        twentyNotes = amountInATM.getTwentyNotes();
                        amountInATM.setTwentyNotes(0);
                    }

                    money_copy -= twentyNotes * 20;
                }

                note = 10;
            }
            else if (note == 10) {
                if (note <= money_copy) {
                    if (amountInATM.getTenNotes() >= notes) {
                        tenNotes = notes;
                        amountInATM.setTenNotes((amountInATM.getTenNotes() - notes));
                    } else {
                        tenNotes = amountInATM.getTenNotes();
                        amountInATM.setTenNotes(0);
                    }

                    money_copy -= tenNotes * 10;
                }

                note = 5;
            }
            else if (note == 5) {
                if (amountInATM.getFiveNotes() >= notes) {
                    fiveNotes = notes;
                    amountInATM.setFiveNotes((amountInATM.getFiveNotes() - notes));
                } else {
                    fiveNotes = amountInATM.getFiveNotes();
                    amountInATM.setFiveNotes(0);
                }

                note = -1;
                money_copy -= fiveNotes * 5;
            }

            if (note <= money_copy)
                notes = money_copy / note;
        }
        Amount amountWithdrawn = new Amount(money, fiftyNotes, twentyNotes, tenNotes, fiveNotes);

        // deducting amount that has been withdrawn
        amountInATM.setTotalAmount(amountInATM.getTotalAmount() - money);
        for (int i = 0; i < users.size(); ++i) {
            if (accNo.equals(users.get(i).getAccNo())) {
                if (users.get(i).getBalance() >= money) {
                    users.get(i).setBalance(users.get(i).getBalance() - money);
                }
                else if (users.get(i).getBalance() + users.get(i).getOverdraft() >= money) {
                    money -= users.get(i).getBalance();
                    users.get(i).setBalance(0);
                    users.get(i).setOverdraft(users.get(i).getOverdraft() - money);
                }
            }
        }

        readAndWrite.truncateAFile("./src/dataLayer/users.csv");
        readAndWrite.writeHeadersInFile();
        for (User user : users) {
            readAndWrite.writeUserIntoFile(user);
        }

        readAndWrite.truncateAFile("./src/dataLayer/atm_data.csv");
        readAndWrite.writeAtmHeadersInFile();
        readAndWrite.writeAtmDataIntoFile(amountInATM);


        return amountWithdrawn;
    }
}
