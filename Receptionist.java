import java.util.ArrayList;
import java.io.Serializable;

public class Receptionist implements Serializable {
    String name;
    int receptionistSalary;
    String receptionistUser;
    String receptionistPass;
    String receptionistDoctor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReceptionistSalary() {
        return receptionistSalary;
    }
    
    public void setReceptionistSalary(int receptionistSalary) {
        this.receptionistSalary = receptionistSalary;
    }

    public String getReceptionistUser() {
        return receptionistUser;
    }

    public void setReceptionistUser(String receptionistUser) {
        this.receptionistUser = receptionistUser;
    }

    public String getReceptionistPass() {
        return receptionistPass;
    }

    public void setReceptionistPass(String receptionistPass) {
        this.receptionistPass = receptionistPass;
    }

    public String getReceptionistDoctor() {
        return receptionistDoctor;
    }

    public void setReceptionistDoctor(String receptionistDoctor) {
        this.receptionistDoctor = receptionistDoctor;
    }


    public Receptionist(String name) {
        this.name = name;
    }

    // Method for checking if username or password correct
    public int authentication(String username, String password){
        int index = -1;
        for (int i=0; i<Main.getReceptionists().size(); i++) {
            if (Main.getReceptionists().get(i).getReceptionistUser().equals(username)) {
                if (Main.getReceptionists().get(i).getReceptionistPass().equals(password)){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
    
}