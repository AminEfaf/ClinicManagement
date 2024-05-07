import java.util.ArrayList;
import java.io.Serializable;

public class Section implements Serializable {
    String sectionName;
    String sectionType;
    
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    public Section(String sectionName, String sectionType) {
        this.sectionName = sectionName;
        this.sectionType = sectionType;
    }

    // Method for checking if given section exists
    public int authentication(String sectionName, String sectionType){
        int index = -1;
        for (int i=0; i<Main.getSections().size(); i++) {
            if (Main.getSections().get(i).getSectionName().equals(sectionName)) {
                if (Main.getSections().get(i).getSectionType().equals(sectionType)){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }


    

}