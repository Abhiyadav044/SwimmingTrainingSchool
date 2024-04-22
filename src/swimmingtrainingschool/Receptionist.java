package swimmingtrainingschool;

import java.util.Scanner;

public class Receptionist {

    private Scanner scanner;
    private SchoolManagement management;
    private AquaticTimetable timetable;
    private AquaticEducator educator;
    private BookingHandler booking;
    private Feedback feedback;
    private Learner learner;
    private MonthlyRecord record;
    private final int minimum_level = 1;
    private final int maximum_level = 5;
    private final int minimum_age = 4;
    private final int maximum_age = 11;

    //initialize object
    public void intiObject(SchoolManagement management) {
        this.scanner = management.getScanner();
        this.management = management;
        this.timetable = management.getTimetable();
        timetable.initObject(management);
        this.educator = management.getEducator();
        this.learner = management.getLearner();
        this.booking = management.getBooking();
        this.feedback = management.getFeedback();
        this.record = management.getRecord();
        record.initObject(management);
        feedback.initObject(management);
    }

    public void receptionistMenu() {
        String option;
       
        do {
             System.out.println("\n\t\tReceptionist Menu\t\n");
        System.out.println("*********************************************************");
            System.out.println("1. Add Learner");
            System.out.println("2. View Learners");
            System.out.println("3. View Timetable");
            System.out.println("4. View Educator");
            System.out.println("5. Generate Monthly Report");
            System.out.println("6. All Bookings");
            System.out.println("7. All Feedback");
            System.out.println("8. Back");
            System.out.print("\nEnter one option from above list : ");
            option = scanner.nextLine().trim();
            switch (option) {
                case "1" -> {
                    addLearner();
                }
                case "2" -> {
                    learner.showLearnerInfo();
                }
                case "3" -> {
                    timetable.filterLessonList(0,"");
                }
                case "4" -> {
                    educator.educatorInformation();
                }
                case "5" -> {
                    record.generateMonthlyRecord();
                }
                case "6" -> {
                    booking.showLearnerBookingInformation(0,0);
                }
                case "7" -> {
                    feedback.showFeedbacks(0, 0);
                }
                default -> {

                }
            }

        } while (!option.equals("8"));

    }

    //add new learner information 
    public void addLearner() {
        String option = null;
        String learner_name = null;
        String learner_contact = null;
        String learner_email = null;
        String learner_gender = null;
        int learner_age = 0;
        int learner_level = 0;
        String level = null;
        String age = null;
        boolean learner_add = false;
        boolean correctAge = true;
        boolean correct_level = true;

        System.out.println("\n\t\tAdd new learner\n");
        do {
            if (learner_name == null) {
                System.out.print("Learner Name : ");
                learner_name = scanner.nextLine().trim();
                if (learner_name.isEmpty() || learner_name.matches("\\d+")) {
                    System.out.println("=> Error : you entered an incorrect learner name");
                    learner_add = true;
                } else {
                    if (duplicateName(learner_name)) {
                        System.out.println(learner_name + " has already been added.");
                        learner_name = null;
                    }

                }
            } else if (learner_contact == null) {
                System.out.print("Learner Phone : ");
                learner_contact = scanner.nextLine().trim();
                if (learner_contact == null || !learner_contact.matches("\\d+")) {
                    System.out.println("=> Error : you entered an incorrect learner phone number");
                    learner_add = true;
                    learner_contact = null;
                }
            } else if (learner_email == null) {
                System.out.print("Learner Email : ");
                learner_email = scanner.nextLine().trim();
                if (learner_email == null || !learner_email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    System.out.println("=> Error : you entered an incorrect learner email");
                    learner_add = true;
                    learner_email = null;
                } else {
                    if (duplicateEmail(learner_email)) {
                        System.out.println("=> " + learner_email + " already registered");
                        learner_email = null;
                    }
                }
            } else if (age == null) {
                System.out.print("Learner Age : ");
                age = scanner.nextLine().trim();
                if (age == null || !age.matches("\\d+")) {
                    System.out.println("=> Error : you entered an incorrect learner age. age should be 4 to 11 ");
                    learner_add = true;
                    age = null;
                } else {
                    learner_age = Integer.parseInt(age);
                    correctAge = correctAge(learner_age);
                    if (!correctAge) {
                        System.out.println("=> Error : you entered an incorrect learner age. age should be 4 to 11 ");
                        learner_add = true;
                        age = null;
                    }
                }
            } else if (level == null) {
                System.out.print("Learner Level : ");
                level = scanner.nextLine().trim();
                if (level == null || !level.matches("\\d+")) {
                    System.out.println("=> Error : you entered an incorrect learner level. level should be 1 to 5 ");
                    learner_add = true;
                    level = null;
                } else {
                    learner_level = Integer.parseInt(level);
                    correct_level = correctLevel(learner_level);
                    if (!correct_level) {
                        System.out.println("=> Error : you entered an incorrect learner level. level should be 1 to 5 ");
                        learner_add = true;
                        level = null;
                    }
                }
            } else if (learner_gender == null) {
                System.out.print("Learner Gender : ");
                learner_gender = scanner.nextLine().trim();
                if (learner_gender == null || learner_gender.matches("\\d+")) {
                    System.out.println("=> Error : you entered an incorrect learner gender ");
                    learner_add = true;
                    learner_gender = null;
                } else {
                    learner_gender = correctGender(learner_gender);
                }
            } else {
                //create foloater class object
                Learner learner = new Learner(0, learner_name, learner_email, learner_contact, learner_age, learner_level, learner_gender);
                //save learner information
                int registration_number = management.saveLearnerInformation(learner);
                if (registration_number != 0) {
                    System.out.println("\nNew learner added successfully");
                    System.out.println("Your Registration Number : " + registration_number);
                    learner_add = true;
                }

            }

        } while (!learner_add);
    }

    //check correct learner gender
    public String correctGender(String gender) {
        String learner_gender = null;
        if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("M")) {
            return "Male";
        } else if (gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("F")) {
            return "Female";
        }
        return learner_gender;
    }

    //chcek correct learner age
    public boolean correctAge(int age) {
        return age >= minimum_age && age <= maximum_age;
    }

    //check correct learner level
    public boolean correctLevel(int level) {
        return level >= minimum_level && level <= maximum_level;
    }

    //check duplicate learner name
    public boolean duplicateName(String name) {
        for (Learner learner1 : learner.learner_list) {
            if (learner1.getLearner_name().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    //check duplicate learner email
    public boolean duplicateEmail(String email) {
        for (Learner learner1 : learner.learner_list) {
            if (learner1.getLearner_email().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

}
