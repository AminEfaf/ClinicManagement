import java.util.ArrayList;
import java.io.Serializable;
 
public class Clinic {
    String clinicName;
    String clinicType;

    public String getClinicName() {
        return clinicName;
    }
    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
    public String getClinicType() {
        return clinicType;
    }
    public void setClinicType(String clinicType) {
        this.clinicType = clinicType;
    }

    public Clinic(String clinicName, String clinicType) {
        this.clinicName = clinicName;
        this.clinicType = clinicType;
    }

        // Method for checking if given clinic exists
        public int authentication(String clinicName, String clinicType){
        int index = -1;
        for (int i=0; i<Main.getClinics().size(); i++) {
            if (Main.getClinics().get(i).getClinicName().equals(clinicName)) {
                if (Main.getClinics().get(i).getClinicType().equals(clinicType)){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
    
    
    
}