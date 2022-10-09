package dataLayer;

import logicLayer.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Vector;

public class writerAndReader {

    //function for writing headings in newly created file
    public void writeHeadersInFile() {
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter("./src/dataLayer/users.csv", true);
            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
            bufferedWriter.append("ID,AccNo,Pin,Balance,Overdraft\n");
            bufferedWriter.close();
        } catch (Exception error) {
            System.err.println("Error: " + error.getMessage());
        }
    }

    //function for writing headings in newly created atm file
    public void writeAtmHeadersInFile() {
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter("./src/dataLayer/atm_data.csv", true);
            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
            bufferedWriter.append("AtmBalance,Fifties,Twenties,Tens,Fives\n");
            bufferedWriter.close();
        } catch (Exception error) {
            System.err.println("Error: " + error.getMessage());
        }
    }

    //function to truncate a file
    public void truncateAFile(String fileName){
        try {
            FileWriter fileTruncate = new FileWriter(fileName);
            fileTruncate.close();
        }
        catch (Exception error) {
            System.err.println("Error: " + error.getMessage());
        }
    }

    //function for writing data into users file
    public void writeUserIntoFile(User i) {
        try {
            FileWriter fileWriter =
                    new FileWriter("./src/dataLayer/users.csv", true);
            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
            bufferedWriter.append(i.getID() + "," + i.getAccNo() + "," + i.getPin() + "," + i.getBalance() + ",");
            bufferedWriter.append(i.getOverdraft() + "\n");
            bufferedWriter.close();
        } catch (Exception error) {
            System.err.println("Error: " + error.getMessage());
        }
    }

    //function for writing data into atm file
    public void writeAtmDataIntoFile(Amount i) {
        try {
            FileWriter fileWriter =
                    new FileWriter("./src/dataLayer/atm_data.csv", true);
            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
            bufferedWriter.append(i.getTotalAmount() + "," + i.getFiftyNotes() + "," + i.getTwentyNotes() + "," + i.getTenNotes() + ",");
            bufferedWriter.append(i.getFiveNotes() + "\n");
            bufferedWriter.close();
        } catch (Exception error) {
            System.err.println("Error: " + error.getMessage());
        }
    }

    //function for reading data from user file
    public void readUsersFromFile(Vector<User> users) {
        User c = new User();
        int value, temp = 1;
        String strLine = "";
        boolean check = false;

        try {
            FileInputStream fileStream = new FileInputStream("./src/dataLayer/users.csv");
            DataInputStream dataStream = new DataInputStream(fileStream);
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(dataStream));
            while ((value = bufferReader.read()) != -1) {
                char character = (char) value;
                if (character != ',' && character != '\n') {
                    strLine += character;
                } else {
                    //if the first line which consists headers is being read
                    if (check == false) {
                        if (character == '\n') {
                            check = true;
                            strLine = "";
                        }
                        continue;
                    }

                    //Saving data because now it's not the first line of headers
                    if (temp == 1) {
                        c.setID(Integer.parseInt(strLine));
                    } else if (temp == 2) {
                        c.setAccNo(strLine);
                    } else if (temp == 3) {
                        c.setPin(strLine);
                    } else if (temp == 4) {
                        c.setBalance(Integer.parseInt(strLine));
                    } else if (temp == 5) {
                        c.setOverdraft(Integer.parseInt(strLine));
                        temp = 0;
                        users.add(c);
                        c = new User();
                    }
                    temp++;
                    strLine = "";
                }
            }
            dataStream.close();
        }
        catch (Exception error) {
            System.err.println("Error: " + error.getMessage());
        }
    }

    //function for reading data from user file
    public Amount readAtmData() {
        Amount amt = new Amount();
        int value, temp = 1;
        String strLine = "";
        boolean check = false;

        try {
            FileInputStream fileStream = new FileInputStream("./src/dataLayer/atm_data.csv");
            DataInputStream dataStream = new DataInputStream(fileStream);
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(dataStream));
            while ((value = bufferReader.read()) != -1) {
                char character = (char) value;
                if (character != ',' && character != '\n') {
                    strLine += character;
                } else {
                    //if the first line which consists headers is being read
                    if (check == false) {
                        if (character == '\n') {
                            check = true;
                            strLine = "";
                        }
                        continue;
                    }

                    //Saving data because now it's not the first line of headers
                    if (temp == 1) {
                        amt.setTotalAmount(Integer.parseInt(strLine));
                    } else if (temp == 2) {
                        amt.setFiftyNotes(Integer.parseInt(strLine));
                    } else if (temp == 3) {
                        amt.setTwentyNotes(Integer.parseInt(strLine));
                    } else if (temp == 4) {
                        amt.setTenNotes(Integer.parseInt(strLine));
                    } else if (temp == 5) {
                        amt.setFiveNotes(Integer.parseInt(strLine));
                        temp = 0;
                        return amt;
                    }
                    temp++;
                    strLine = "";
                }
            }
            dataStream.close();
        }
        catch (Exception error) {
            System.err.println("Error: " + error.getMessage());
        }

        return amt;
    }
}