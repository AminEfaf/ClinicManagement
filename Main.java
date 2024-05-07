import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public abstract class Main implements Services{ 
    static ArrayList<Manager> managers = new ArrayList<Manager>();
    static ArrayList<Patient> patients= new ArrayList<Patient>();
    static ArrayList<Section> sections = new ArrayList<Section>();
    static ArrayList<Receptionist> receptionists = new ArrayList<Receptionist>();
    static ArrayList<Doctor> doctors = new ArrayList<Doctor>();
    static ArrayList<Clinic> clinics = new ArrayList<Clinic>();
    static Vector<String> vector = new Vector<String>();
    static int count = 0;
    
    public static ArrayList<Clinic> getClinics() {
        return clinics;
    }

    public static ArrayList<Manager> getManagers() {
        return managers;
    }

    public static ArrayList<Patient> getPatients() {
        return patients;
    }

    public static ArrayList<Section> getSections() {
        return sections;
    }

    public static ArrayList<Receptionist> getReceptionists() {
        return receptionists;
    }

    public static ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public static int clinicAuthentication(Clinic c){
        return c.authentication(c.getClinicName(), c.getClinicType());
    }

    public static int doctorAuthentication(Doctor d){
        return d.authentication(d.getDoctorUser(), d.getDoctorPass());
    }

    public static int receptionistAuthentication(Receptionist r){
        return r.authentication(r.getReceptionistUser(), r.getReceptionistPass());
    }
    

    public static int sectionAuthentication(Section s){
        return s.authentication(s.getSectionName(), s.getSectionType());
    }

    public static int patientAuthentication(Patient p){
        return p.authentication(p.getPatientUser(), p.getPatientPass());
    }

    public static int managerAuthentication(Manager m){
        return m.authentication(m.getManagerUser(), m.getManagerPass());
    }
    public static void main(String[] args) {
        // Read arraylist from file
        // Deserialize
        try {
            FileInputStream inputStream = new FileInputStream("save.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            managers = (ArrayList<Manager>) objectInputStream.readObject();
            doctors = (ArrayList<Doctor>) objectInputStream.readObject();
            sections = (ArrayList<Section>) objectInputStream.readObject();
            receptionists = (ArrayList<Receptionist>) objectInputStream.readObject();
            patients = (ArrayList<Patient>) objectInputStream.readObject();

            objectInputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        
        Scanner input = new Scanner(System.in);

        int selection = 1;
        int secondSelection = 1;
        int thirdSelection = 1;  
        
        // Menu

        while (selection != 3) {
            clear_screen();

            System.out.println("welcome!"+"\n");
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Exit and save");            

            selection = input.nextInt();

            clear_screen();

            if (selection == 1) { // Sign up
                System.out.println("You want to sign in as a manager or patient?");
                System.out.println("1. Manager");
                System.out.println("2. Patient");
                
                secondSelection = input.nextInt();

                clear_screen();

                if (secondSelection == 1) { // Manager sign up
                    System.out.println("Enter your name: ");
                    String name = input.next();
    
                    System.out.println("Enter your username: ");
                    String username = input.next();
    
                    System.out.println("Enter your password: ");
                    String password = input.next();
    
                    // create manager
                    Manager e = new Manager(name, username, password);
                    // add manger to the arraylist
                    managers.add(e);
                } else if (secondSelection == 2) { //Patient sign up
                    System.out.println("Enter your name: ");
                    String name = input.next();
    
                    System.out.println("Enter your username: ");
                    String username = input.next();
    
                    System.out.println("Enter your password: ");
                    String password = input.next();

                    System.out.println("Enter your accountBalance: ");
                    int accountBalance = input.nextInt();
    
                    // create patient
                    Patient p = new Patient(name, username, password);
                    p.setAccountBalance(accountBalance);
                    // add patient to the arraylist
                    patients.add(p);
                }
            } else if (selection == 2) { // sign in
                System.out.println("Do you want to sign in as what?");
                System.out.println("1. Manager");
                System.out.println("2. Patient");
                System.out.println("3. Receptionist");
                System.out.println("4. Doctor");
                System.out.println("5. Exit");

                thirdSelection = input.nextInt();

                clear_screen();

                if (thirdSelection == 1){ // Manager sign in
                    System.out.println("Enter your name: ");
                    String name = input.next();

                    System.out.println("Enter your username: ");
                    String username = input.next();

                    System.out.println("Enter your password: ");
                    String password = input.next();

                    Manager e = new Manager(name, username, password);
                    int mangerIndex = managerAuthentication(e);
                    Manager currentManager;

                    if (mangerIndex != -1) {
                        // manager can access to his panel
                        currentManager = managers.get(mangerIndex);
                        // go to manager panel
                        managerPanel(currentManager);

                    } else {
                        System.out.println("username or password is not correct!");
                        System.out.println("press any key to exit ...");
                        String exit = input.next(); 
                    }
                } else if (thirdSelection == 2){ // Patient sign in
                    System.out.println("Enter your name: ");
                    String name = input.next();

                    System.out.println("Enter your username: ");
                    String username = input.next();

                    System.out.println("Enter your password: ");
                    String password = input.next();

                    Patient p = new Patient(name, username, password);
                    int patientIndex = patientAuthentication(p);
                    Patient currentPatient;

                    if (patientIndex != -1) {
                        // patient can access to his panel 
                        currentPatient = patients.get(patientIndex);
                        // go to patient panel
                        patientPanel(currentPatient);

                    } else {
                        System.out.println("username or password is not correct!");
                        System.out.println("press any key to exit ...");
                        String exit = input.next();
                    }
                } else if (thirdSelection == 3){ // Receptionist sign in
                    System.out.println("Enter your name: ");
                    String name = input.next();

                    System.out.println("Enter your username: ");
                    String username = input.next();

                    System.out.println("Enter your password: ");
                    String password = input.next();

                    Receptionist r = new Receptionist(name);
                    r.setReceptionistUser(username);
                    r.setReceptionistPass(password);
                    int receptionistIndex = receptionistAuthentication(r);
                    Receptionist currentReceptionist;

                    if (receptionistIndex != -1) {
                        // Receptionist can access to his panel 
                        currentReceptionist = receptionists.get(receptionistIndex);
                        // go to Receptionist panel
                        ReceptionistPanel(currentReceptionist);

                    } else {
                        System.out.println("username or password is not correct!");
                        System.out.println("press any key to exit ...");
                        String exit = input.next();
                    }
                } else if (thirdSelection == 4){ // Doctor sign in
                    System.out.println("Enter your name: ");
                    String name = input.next();

                    System.out.println("Enter your username: ");
                    String username = input.next();

                    System.out.println("Enter your password: ");
                    String password = input.next();

                    Doctor d = new Doctor(name);
                    d.setDoctorUser(username);
                    d.setDoctorPass(password);
                    int doctorIndex = doctorAuthentication(d);
                    Doctor currentDoctor;

                    if (doctorIndex != -1) {
                        // doctor can access to his panel 
                        currentDoctor = doctors.get(doctorIndex);
                        // go to doctor panel
                        doctorPanel(currentDoctor);

                    } else {
                        System.out.println("username or password is not correct!");
                        System.out.println("press any key to exit ...");
                        String exit = input.next();
                    }
                } else if (thirdSelection == 5){ // Exit
                    selection = 3;
                }
            } else if (selection == 3){ // Exit
                // Save mangers to file
                // Serialize
                try {
                    FileOutputStream outputStream = new FileOutputStream("save.txt");
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                    objectOutputStream.writeObject(managers);
                    objectOutputStream.writeObject(doctors);
                    objectOutputStream.writeObject(sections);
                    objectOutputStream.writeObject(receptionists);
                    objectOutputStream.writeObject(patients);

                    objectOutputStream.close();
                    outputStream.close();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } else {
                System.out.println("please enter valid number!");
            }
        }
	}
    public static void managerPanel(Manager manager) {

        Scanner input = new Scanner(System.in);

        // manger panel
        int selection = 1;
        while (selection !=8 ) {
            clear_screen();

            System.out.println("1. Add doctor");
            System.out.println("2. About doctors");
            System.out.println("3. Add receptionist");
            System.out.println("4. About receptionist");
            System.out.println("5. Add clinic");
            System.out.println("6. Add Section");
            System.out.println("7. History");
            System.out.println("8. Exit");

            selection = input.nextInt();

            clear_screen();

            if (selection == 1){ // Add doctor
                System.out.println("Enter doctor's name: ");
                String name = input.next();

                System.out.println("Enter doctor's username: ");
                String doctorUser = input.next();

                System.out.println("Enter doctor's password: ");
                String doctorPass = input.next();

                System.out.println("Enter doctor's visit price: ");
                int doctorVisit = input.nextInt();

                System.out.println("Enter doctor salary: ");
                int doctorSalary = input.nextInt();

                System.out.println("Enter doctor's type: ");
                String doctorType = input.next();

                System.out.println("Enter doctor's section: ");
                String doctorSection = input.next();

                System.out.println("Enter doctor's section type: ");
                String doctorSectionType = input.next();

                Section s = new Section(doctorSection, doctorSectionType);
                int sectionIndex = sectionAuthentication(s);

                // Check if there is a section with this name or not
                if (sectionIndex != -1){
                    // create doctor with top information and add it to doctors's arraylist
                    Doctor d = new Doctor (name);
                    d.setDoctorUser(doctorUser);
                    d.setDoctorPass(doctorPass);
                    d.setDoctorSalary(doctorSalary);
                    d.setDoctorType(doctorType);
                    d.setDoctorVisit(doctorVisit);
                    d.setSection(doctorSection);
                    doctors.add(d);
                }
                else{
                    clear_screen();
                    System.out.println("There is no section with this name!");
                    System.out.println("press any key to exit ...");
                    String exit = input.next();
                }

            } else if (selection == 2){ // About doctors
                System.out.println("Enter doctor's name: ");
                String name = input.next();

                System.out.println("Enter doctor's username: ");
                String username = input.next();

                System.out.println("Enter doctor's password: ");
                String password = input.next();

                Doctor d = new Doctor(name);
                d.setDoctorUser(username);
                d.setDoctorPass(password);
                int doctorIndex = doctorAuthentication(d);
                Doctor currentDoctor;

                // Check if there is a doctor with this name or not
                if (doctorIndex != -1) {
                    currentDoctor = doctors.get(doctorIndex);
                    
                    clear_screen();

                    int choice = 1;
                    while (choice != 4){
                        clear_screen();
                        System.out.println("1. Fire doctor");
                        System.out.println("2. Pay salary");
                        System.out.println("3. See salary amount");
                        System.out.println("4. Exit");
    
                        choice = input.nextInt();
    
                        clear_screen();
    
                        if (choice == 1){ // Fire doctor
                            doctors.remove(currentDoctor);
    
                            System.out.println("Done!");
                            System.out.println("press any key to exit ...");
                            choice = 4;
                            String exit = input.next();
                        } else if (choice == 2){ // Pay salary
                            System.out.println("How much do you wanna pay? ");
                            int doctorSalaray = input.nextInt();
    
                            currentDoctor.setDoctorSalary(doctorSalaray);
    
                            System.out.println("Done!");
                            System.out.println("press any key to exit ...");
                            String exit = input.next();
                        } else if (choice == 3){ // See salary amount
                            System.out.println(currentDoctor.getDoctorSalary());
    
                            System.out.println("press any key to exit ...");
                            String exit = input.next();
                        }
                    }

                } else {
                    System.out.println("username or password is not correct!");
                    System.out.println("press any key to exit ...");
                    String exit = input.next();
                }
            } else if (selection == 3){ // Add receptionist
                System.out.println("Enter receptionist's name: ");
                String name = input.next();

                System.out.println("Enter receptionist's username: ");
                String receptionistUser = input.next();

                System.out.println("Enter receptionist's password: ");
                String receptionistPass = input.next();

                System.out.println("Enter receptionist's salary: ");
                int receptionistSalary = input.nextInt();

                System.out.println("Enter receptionist's doctor: ");
                String receptionistDoctor = input.next();

                
                // Check if there is doctor with this name or not
                int index = -1;
                for (int i=0; i<doctors.size(); i++) {
                    if (doctors.get(i).getName().equals(receptionistDoctor)) {
                        index = i;
                        break;
                    }
                }

                if (index != -1){
                    // create receptionist with top information and add it to manager's arraylist
                    Receptionist r = new Receptionist(name);
                    r.setReceptionistUser(receptionistUser);
                    r.setReceptionistPass(receptionistPass);
                    r.setReceptionistSalary(receptionistSalary);
                    r.setReceptionistDoctor(receptionistDoctor);
                    receptionists.add(r);
                } else{
                    clear_screen();
                    System.out.println("there is no doctor with this name!");
                    System.out.println("Press any key to exit ...");
                    String exit = input.next();
                }

            } else if (selection == 4){ // About receptionist
                System.out.println("Enter your name: ");
                String name = input.next();

                System.out.println("Enter your username: ");
                String username = input.next();

                System.out.println("Enter your password: ");
                String password = input.next();

                Receptionist r = new Receptionist(name);
                r.setReceptionistUser(username);
                r.setReceptionistPass(password);
                int receptionistIndex = receptionistAuthentication(r);
                Receptionist currentReceptionist;

                // Check if the information was right or not
                if (receptionistIndex != -1) {

                    currentReceptionist = receptionists.get(receptionistIndex);

                    int secondChoice = 1;

                    while (secondChoice != 4){
                        clear_screen();
                        System.out.println("1. Fire receptionist");
                        System.out.println("2. Pay salary");
                        System.out.println("3. See salary amount");
                        System.out.println("4. Exit");

                        secondChoice = input.nextInt();

                        if (secondChoice == 1){ // Fire receptionist
                            receptionists.remove(currentReceptionist);

                            System.out.println("Done!");
                            System.out.println("press any key to exit ...");
                            secondChoice = 4;
                            String exit = input.next();

                        } else if (secondChoice == 2){ // Pay salary
                            System.out.println("How much do you wanna pay? ");
                            int receptionistSalaray = input.nextInt();

                            currentReceptionist.setReceptionistSalary(receptionistSalaray);
                            System.out.println("Done!");
                            System.out.println("press any key to exit ...");
                            String exit = input.next();

                        } else if (secondChoice == 3){ // See salary amount
                            System.out.println(currentReceptionist.getReceptionistSalary());

                            System.out.println("press any key to exit ...");
                            String exit = input.next();
                        }
                    }
                } else{
                    System.out.println("username or password is not correct!");
                    System.out.println("press any key to exit ...");
                    String exit = input.next();
                }
            } else if (selection == 5){ // Add clinic
                System.out.println("Enter clinic's name: ");
                String clinicName = input.next();

                System.out.println("Enter clinic's type: ");
                String clinicType = input.next();

                // create clinic with top information and add it to clinic's arraylist
                Clinic c = new Clinic(clinicName, clinicType);
                clinics.add(c);
            } else if (selection == 6){ // Add section
                System.out.println("Enter clinic's name: ");
                String clinicName = input.next();

                System.out.println("Enter clinic's type: ");
                String clinicType = input.next();

                int index = -1;
                for (int i=0; i<clinics.size(); i++) {
                    if (clinics.get(i).getClinicName().equals(clinicName)) 
                    if (clinics.get(i).getClinicType().equals(clinicType)){
                        index = i;
                        break;
                    }
                }

                // Check if there is clinic with this name or not
                if (index != -1){
                    System.out.println("Enter section's name: ");
                    String sectionName = input.next();
    
                    System.out.println("Enter section's type: ");
                    String sectionType = input.next();
    
                    // create section with top information and add it to section's arraylist
                    Section s = new Section(sectionName, sectionType);
                    sections.add(s);
                } else{
                    clear_screen();
                    System.out.println("there is no clinic with this name!");
                    System.out.println("Press any key to exit ...");
                    String exit = input.next();
                }

            } else if (selection == 7){ // History
                for (int i = 0;i<count;i += 2){
                    System.out.print(vector.get(i));
                    System.out.println(vector.get(i+1));
                }
                    

                System.out.println("press any key to exit ...");
                String exit = input.next();
            } 
            
        }
    }
    public static void patientPanel(Patient patient){

        Scanner input = new Scanner(System.in);

        // patient panel
        int selection = 1;
        while (selection != 4){
            clear_screen();

            System.out.println("1. Visit Doctor");
            System.out.println("2. Account blance");
            System.out.println("3. Add money to credit card");
            System.out.println("4. Exit");

            selection = input.nextInt();

            clear_screen();

            if (selection == 1){ // Visit Doctor
                System.out.println("Which doctor Do you wanna visit?");
                String doctorName = input.next();

                int index = -1;
                for (int i=0; i<doctors.size(); i++) {

                    if (doctors.get(i).getName().equals(doctorName)) {
                        index = i;
                        break;
                    }
                }

                // Check if there is a doctor with this name or not
                if (index != -1){

                    Doctor d;
                    
                    d = doctors.get(index);

                    System.out.println("You have to pay "+d.getDoctorVisit()+"$ for visit(type (pay) for paying)");
                    String pay = input.next();
                    if (patient.getAccountBalance() < d.getDoctorVisit()){
                        System.out.println("You do not have enough money!");
                    } else{
                        patient.setAccountBalance(patient.getAccountBalance() - d.getDoctorVisit());
                        vector.add(d.getName());
                        vector.add(" have visited "+patient.getName());
                        count += 2;
                        System.out.println("Done!");
                        System.out.println("Press any key to exit ...");
                        String exit = input.next();

                    }
                } else{
                    clear_screen();
                    System.out.println("There is no doctor with this name");
                    System.out.println("press any key to exit ...");
                    String exit = input.next();
                }
            } else if (selection == 2){ // Account balance
                System.out.println("Account balance: "+patient.getAccountBalance()+"$"+"\n");

                System.out.println("There is no doctor with this name");
                System.out.println("press any key to exit ...");
                String exit = input.next();

            } else if (selection == 3){ // Add money
                System.out.println("How much money do wanna add? ");
                int accountBalance = input.nextInt();
            
                patient.setAccountBalance(patient.getAccountBalance()+accountBalance);
            }
        }
    }
    public static void ReceptionistPanel(Receptionist receptionist){
        Scanner input = new Scanner(System.in);

        // Receptionist panel
        int selection = 1;
        while (selection != 3){
            clear_screen();

            System.out.println("1. History");
            System.out.println("2. See salary");
            System.out.println("3. Exit");

            selection = input.nextInt();

            clear_screen();

            if (selection == 1){ // History
                for (int i = 0; i < count; i += 2){
                    if (vector.get(i) == receptionist.getReceptionistDoctor()){
                        System.out.print(vector.get(i));
                        System.out.println(vector.get(i+1));
                    }
                }
                System.out.println("press any key to exit ...");
                String exit = input.next();
            } else if (selection == 2){ // See salary
                System.out.println("Salary = "+receptionist.getReceptionistSalary()+"$");
                System.out.println("press any key to exit ...");
                String exit = input.next();
            }
        }
    }
    public static void doctorPanel(Doctor doctor){
                
        Scanner input = new Scanner(System.in);

        // Doctor panel
        int selection = 1;

        while (selection != 3){
            clear_screen();
            System.out.println("1. History");
            System.out.println("2. See salary and visit price");
            System.out.println("3. Exit");

            selection = input.nextInt();

            clear_screen();

            if (selection == 1){ // History
                for (int i = 0;i<count;i += 2){
                    if (vector.get(i) == doctor.getName()){
                        System.out.print(vector.get(i));
                        System.out.println(vector.get(i+1));
                    }
                }
                System.out.println("Press any key to exit ...");
                String exit = input.next();
            } else if (selection == 2){ // Salary and visit price
                System.out.println("Salary = "+doctor.getDoctorSalary()+"$");
                System.out.println("Visit price = "+doctor.getDoctorVisit()+"$");
                System.out.println("Press any key to exit ...");
                String exit = input.next();
            }

        }
    }

    // Clearing the screen
    public static void clear_screen() {
        System.out.print("\033[H\033[2J");
    }

}

