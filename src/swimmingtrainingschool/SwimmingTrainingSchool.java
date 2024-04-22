package swimmingtrainingschool;

import java.util.Scanner;

public class SwimmingTrainingSchool {
    private Learner learner;
    private Receptionist receptionist;
    private final int receptionist_registration_number = 12345;
    private Scanner scanner;

    //initialize object
    public void initObject(SchoolManagement management) {
        this.scanner = management.getScanner();
        this.learner = management.getLearner();
        learner.initObject(management);
        this.receptionist = management.getReceptionist();
        receptionist.intiObject(management);
    }

    public void schoolInfo() {
        System.out.println("\t\tSwimming Training School\n");
    }

    //main menu
    public void mainMenu(SchoolManagement management) {
        String id;
        int registration_number;
        boolean correct_registration_number;
        System.out.println("\n\t\t\tMain Menu\t\n");
        System.out.println("*********************************************************");
        System.out.println("=> Enter Q to quit this program");
        do {
            //enter registration id from user
            System.out.print("Enter your registration id : ");
            id = scanner.nextLine().trim();
            //validation of registration id
            if (id.matches("\\d+")) {
                registration_number = Integer.parseInt(id);
                correct_registration_number = learner.correctRegistrationId(registration_number);
                if (correct_registration_number) {
                    management.setRegistrationNumber(registration_number);
                    //open learner menu
                    learner.learnerMenu();
                } else if (id.equals("12345")) {
                    //open receptionist menu
                    receptionist.receptionistMenu();
                } else {
                    System.out.println("=> Registration number " + registration_number + " does not exist ");
                }
            } else if (id.equalsIgnoreCase("Q")) {
                System.out.println("Close Program");
            } else {
                System.out.println("=> Please enter your correct registration number");
            }
        } while (!id.equals("q"));
    }

    public static void main(String[] args) {
        SchoolManagement management = SchoolManagement.getInstance();
        SwimmingTrainingSchool school = new SwimmingTrainingSchool();
        school.initObject(management);
        school.schoolInfo();
        school.mainMenu(management);
    }

}
