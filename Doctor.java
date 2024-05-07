import java.util.ArrayList;
import java.io.Serializable;

public class Doctor implements Serializable {
    String name;
    String doctorUser;
    String doctorPass;
    int doctorSalary;
    int doctorVisit;
    String doctorType;
    String section;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoctorUser() {
        return doctorUser;
    }

    public void setDoctorUser(String doctorUser) {
        this.doctorUser = doctorUser;
    }

    public String getDoctorPass() {
        return doctorPass;
    }

    public void setDoctorPass(String doctorPass) {
        this.doctorPass = doctorPass;
    }

    public int getDoctorSalary() {
        return doctorSalary;
    }

    public void setDoctorSalary(int doctorSalary) {
        this.doctorSalary = doctorSalary;
    }

    public int getDoctorVisit() {
        return doctorVisit;
    }

    public void setDoctorVisit(int doctorVisit) {
        this.doctorVisit = doctorVisit;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Doctor(String name) {
        this.name = name;
    }

    // Method for checking if username or password correct
    public int authentication(String username, String password){
        int index = -1;
        for (int i=0; i<Main.getDoctors().size(); i++) {
            if (Main.getDoctors().get(i).getDoctorUser().equals(username)) {
                if (Main.getDoctors().get(i).getDoctorPass().equals(password)){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
    

    
}