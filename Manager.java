import java.util.ArrayList;
import java.io.Serializable;

public class Manager implements Serializable {
    String name;
    String managerUser;
    String managerPass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerUser() {
        return managerUser;
    }

    public void setManagerUser(String managerUser) {
        this.managerUser = managerUser;
    }

    public String getManagerPass() {
        return managerPass;
    }

    public void setManagerPass(String managerPass) {
        this.managerPass = managerPass;
    }


    public Manager(String name, String managerUser, String managerPass) {
        this.name = name;
        this.managerUser = managerUser;
        this.managerPass = managerPass;
    }

    // Method for checking if username or password correct
    public int authentication(String username, String password){
        int index = -1;
        for (int i=0; i<Main.getManagers().size(); i++) {
            if (Main.getManagers().get(i).getManagerUser().equals(username)) {
                if (Main.getManagers().get(i).getManagerPass().equals(password)){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }



    
}