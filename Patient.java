import java.util.ArrayList;
import java.io.Serializable;

public class Patient implements Serializable {
    String name;
    String typeOfSickness;
    int accountBalance;
    String patientUser;
    String patientPass;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfSickness() {
        return typeOfSickness;
    }

    public void setTypeOfSickness(String typeOfSickness) {
        this.typeOfSickness = typeOfSickness;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getPatientUser() {
        return patientUser;
    }

    public void setPatientUser(String patientUser) {
        this.patientUser = patientUser;
    }

    public String getPatientPass() {
        return patientPass;
    }

    public void setPatientPass(String patientPass) {
        this.patientPass = patientPass;
    }


    public Patient(String name, String patientUser, String patientPass) {
        this.name = name;
        this.patientUser = patientUser;
        this.patientPass = patientPass;
    }
    
    // Method for checking if username or password correct
    public int authentication(String username, String password){
        int index = -1;
        for (int i=0; i<Main.getPatients().size(); i++) {
            if (Main.getPatients().get(i).getPatientUser().equals(username)) {
                if (Main.getPatients().get(i).getPatientPass().equals(password)){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
    
    

}