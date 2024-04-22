package swimmingtrainingschool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Learner {

    private Scanner scanner;
    private BookingHandler booking;
    private Feedback feedback;
    private SchoolManagement management;
    private Receptionist receptionist;
    private int registration_number;
    private String learner_name;
    private String learner_email;
    private String learner_contact;
    private int learner_age;
    private int learner_level;
    private String learner_gender;
    public List<Learner> learner_list;

    public int getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(int registration_number) {
        this.registration_number = registration_number;
    }

    public String getLearner_name() {
        return learner_name;
    }

    public void setLearner_name(String learner_name) {
        this.learner_name = learner_name;
    }

    public String getLearner_email() {
        return learner_email;
    }

    public void setLearner_email(String learner_email) {
        this.learner_email = learner_email;
    }

    public String getLearner_contact() {
        return learner_contact;
    }

    public void setLearner_contact(String learner_contact) {
        this.learner_contact = learner_contact;
    }

    public int getLearner_age() {
        return learner_age;
    }

    public void setLearner_age(int learner_age) {
        this.learner_age = learner_age;
    }

    public int getLearner_level() {
        return learner_level;
    }

    public void setLearner_level(int learner_level) {
        this.learner_level = learner_level;
    }

    public String getLearner_gender() {
        return learner_gender;
    }

    public void setLearner_gender(String learner_gender) {
        this.learner_gender = learner_gender;
    }

    public List<Learner> getLearner_list() {
        return learner_list;
    }

    public void setLearner_list(List<Learner> learner_list) {
        this.learner_list = learner_list;
    }

    public Learner(int registration_number, String learner_name, String learner_email, String learner_contact, int learner_age, int learner_level, String learner_gender) {
        this.registration_number = registration_number;
        this.learner_name = learner_name;
        this.learner_email = learner_email;
        this.learner_contact = learner_contact;
        this.learner_age = learner_age;
        this.learner_level = learner_level;
        this.learner_gender = learner_gender;
    }

    public Learner() {
        learner_list = new ArrayList<>();
        preRegisteredLearners();
    }

    //initialize object 
    public void initObject(SchoolManagement management) {
        this.management = management;
        this.booking = management.getBooking();
        booking.initObject(management);
        this.receptionist = management.getReceptionist();
        this.feedback = management.getFeedback();
        feedback.initObject(management);
        this.scanner = management.getScanner();
    }

    public void learnerMenu() {
        String option;
        int registration_number = management.getRegistrationNumber();
        System.out.println("\n\t\t\tLearner Menu");
        System.out.println("*********************************************************\n");
        do {
            System.out.println("\n1. View/Update Profile");
            System.out.println("2. View Bookings");
            System.out.println("3. View Reviews");
            System.out.println("4. Booking Lesson");
            System.out.println("5. Change Lesson");
            System.out.println("6. Attend Lesson");
            System.out.println("7. Cancel Lesson");
            System.out.println("8. Back\n");
            System.out.print("Enter one option from above list : ");
            option = scanner.nextLine().trim();
            switch (option) {
                case "1" -> {
                    learnerProfile(registration_number);
                }
                case "2" -> {
                    booking.showLearnerBookingInformation(registration_number, 0);
                }
                case "3" -> {
                    feedback.showFeedbacks(0, 0);
                }
                case "4" -> {
                    if (booking.bookingLesson()) {
                        System.out.println("\nLesson successfully booked.");
                    }
                }
                case "5" -> {
                    if (booking.changeLesson()) {
                        System.out.println("Lesson successfully changed.");
                    }
                }
                case "6" -> {
                    if (booking.attendLesson()) {
                        System.out.println("\nLesson successfully attended");
                    }
                }
                case "7" -> {
                    if (booking.cancelLesson()) {
                        System.out.println("\nLesson successfully cancelled.");
                    }
                }
                default -> {
                    management.setRegistrationNumber(0);
                }
            }

        } while (!option.equals("8"));

    }

    //store pre_registered learner details
    public void preRegisteredLearners() {
        Arrays.asList(
                new Learner(16178, "John", "john@gmail.com", "04123412345", 9, 1, "Male"),
                new Learner(16278, "Robert", "robert@gmail.com", "04126789123", 7, 2, "Male"),
                new Learner(16378, "Alex", "alex@gmail.com", "04126789122", 5, 3, "Female"),
                new Learner(16478, "Ryan", "ryan@gmail.com", "04126789124", 8, 4, "Male"),
                new Learner(16578, "Morgan", "morgan@gmail.com", "04126789125", 10, 5, "Male"),
                new Learner(16678, "Jacob", "jacob@gmail.com", "04126789126", 11, 1, "Male"),
                new Learner(16778, "Wilson", "wilson@gmail.com", "04126789127", 6, 2, "Female"),
                new Learner(16878, "Davis", "dabis@gmail.com", "04126789128", 4, 3, "Male"),
                new Learner(16978, "Johnson", "johnson@gmail.com", "04126789129", 5, 4, "Female"),
                new Learner(16518, "William", "william@gmail.com", "04126789120", 9, 5, "Male"),
                new Learner(16528, "Brown", "brown@gmail.com", "04126789113", 7, 1, "Male"),
                new Learner(16538, "Robin", "robin@gmail.com", "04126789133", 4, 2, "Female")
        ).forEach(learner_list::add);
    }

    //display learner profile
    public void learnerProfile(int registration_number) {
        String option;
        for (Learner learner : learner_list) {
            if (learner.getRegistration_number() == registration_number) {
                System.out.println("\n\t\tLearner Profile\t\n");
                System.out.println("*********************************************************\n");
                System.out.println("Registration No  : " + learner.getRegistration_number());
                System.out.println("Learner Name     : " + learner.getLearner_name());
                System.out.println("Learner Email    : " + learner.getLearner_email());
                System.out.println("Learner Contact  : " + learner.getLearner_contact());
                System.out.println("Learner Gender   : " + learner.getLearner_gender());
                System.out.println("Learner Age      : " + learner.getLearner_age());
                System.out.println("Learner Level    : " + learner.getLearner_level());
                System.out.println("Total Bookings   : " + booking.learnerBookingCount(registration_number, 0).get("total_booking"));
                System.out.println("Total Attend     : " + booking.learnerBookingCount(registration_number, 0).get("total_attend"));
                System.out.println("Total cancel     : " + booking.learnerBookingCount(registration_number, 0).get("total_cancel"));
                System.out.println();
            }
        }
        System.out.print("Update Profile (YES/No) : ");
        option = scanner.nextLine().trim();
        if (option.equalsIgnoreCase("Yes") || option.equalsIgnoreCase("Y")) {
            updateProfile(registration_number);
        }
    }

    //update learner profilea
    public void updateProfile(int registration_number) {
        String name = null;
        String email = null;
        String contact = null;
        String gender = null;
        String get_age;
        String get_level;
        int age = 0;
        int level = 0;
        boolean update_profile;
        System.out.println("\n\t(\tUpdate Profile\t)\n");
        // take new name to update 
        do {
            System.out.print("Learner new name : ");
            name = scanner.nextLine().trim();
            if (name.matches("\\d+")) {
                System.out.println("=> Error : You entered an incorrect learner name");
                learnerMenu();
            } else if (name.isEmpty()) {

            }
        } while (!name.isEmpty() && name.matches("\\d+"));

        // take new email to update 
        do {
            System.out.print("Learner new email : ");
            email = scanner.nextLine().trim();
            if (email.isBlank()) {

            } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                System.out.println("=> Error : You entered an incorrect learner email");
                learnerMenu();
            }

        } while (!email.isEmpty() && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$"));

        // take new contact to update 
        do {
            System.out.print("Learner new contact : ");
            contact = scanner.nextLine().trim();
            if (contact.isEmpty()) {

            } else if (!contact.matches("\\d+")) {
                System.out.println("=> Error : You entered an incorrect learner contact");
                learnerMenu();
            }
        } while (!contact.isEmpty() && !contact.matches("\\d+"));

        // take new gender to update 
        do {
            System.out.print("Learner new gender : ");
            gender = scanner.nextLine().trim();
            if (gender.isEmpty()) {

            } else {
                if (receptionist.correctGender(gender) == null) {
                    System.out.println("=> Error : You entered an incorrect learner gender ");
                    learnerMenu();
                } else {

                    gender = receptionist.correctGender(gender);
                }
            }

        } while (!gender.isEmpty() && receptionist.correctGender(gender) == null);

        // take new age to update 
        do {
            System.out.print("Learner new age : ");
            get_age = scanner.nextLine().trim();
            if (get_age.isEmpty()) {

            } else {
                if (!get_age.matches("\\d+")) {
                    System.out.println("=> Error : you entered an incorrect learner age. age should be 4 to 11 ");
                    learnerMenu();
                } else {
                    age = Integer.parseInt(get_age);
                    if (!receptionist.correctAge(age)) {
                        System.out.println("=> Error : you entered an incorrect learner age. age should be 4 to 11 ");
                        get_age = "N";
                    }
                }
            }

        } while (!get_age.isEmpty() && !get_age.matches("\\d+"));

        // take new level to update
        do {
            System.out.print("Learner new level (1 to 5) : ");
            get_level = scanner.nextLine().trim();
            if (get_level.isEmpty()) {

            } else {
                if (!get_level.matches("[1-5]")) {
                    System.out.println("=> Error : you entered an incorrect learner level. level should be 1 to 5 ");
                    learnerMenu();
                } else {
                    level = Integer.parseInt(get_level);
                }
            }
        } while (!get_level.isEmpty() && !get_level.matches("[1-5]"));

        // Update profile
        update_profile = management.updateLearnerProfile(registration_number, name, email, contact, gender, age, level);
        if (update_profile) {
            System.out.println("Profile updated successfully");
        }
    }

    //get learner information by registration number
    public Learner getLearnerInfromation(int registration_number) {
        int index = 0;
        while (index < learner_list.size()) {
            Learner learner = learner_list.get(index);
            if (learner.getRegistration_number() == registration_number) {
                return learner;
            }
            index++;
        }
        return null;
    }

    //show all learner details
    public void showLearnerInfo() {
        System.out.println("***************************************************************************************************************************************************************************");
        System.out.printf("%-20s * %-20s * %-20s * %-20s * %-20s * %-20s * %-15s *\n",
                "\tRegistration No", "\tLearner Name", "\tLearner Email", "\tLearner Contact", "\tLearner Gender", "\tLearner Age", "\tLearner Level");

        System.out.println("***************************************************************************************************************************************************************************");
        for (Learner learner : learner_list) {

            System.out.printf("%-20s * %-20s * %-20s * %-20s * %-20s * %-20s *%-15s *\n",
                    "\t" + learner.getRegistration_number(), "\t" + learner.getLearner_name(), "\t" + learner.getLearner_email(),
                    "\t" + learner.getLearner_contact(), "\t" + learner.getLearner_gender(), "\t" + learner.getLearner_age(), "\tLevel " + learner.getLearner_level());

        }
        System.out.println("***************************************************************************************************************************************************************************");

    }

    //check correct registration id
    public boolean correctRegistrationId(int registration_number) {
        for (Learner learner : learner_list) {
            if (learner.getRegistration_number() == registration_number) {
                return true;
            }
        }
        return false;
    }

}
