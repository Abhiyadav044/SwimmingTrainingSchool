package swimmingtrainingschool;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.Scanner;

public class MonthlyRecord {

    private Learner learner;
    private BookingHandler booking;
    private AquaticEducator educator;
    private Feedback feedback;
    private Scanner scanner;
    private String[] month_name;

    public MonthlyRecord() {
        month_name = new DateFormatSymbols().getMonths();
    }

    //intialize object
    public void initObject(SchoolManagement management) {
        this.learner = management.getLearner();
        this.booking = management.getBooking();
        booking.initObject(management);
        this.feedback = management.getFeedback();
        this.educator = management.getEducator();
        this.scanner = management.getScanner();
    }

    //geneate monthly report
    public void monthlyRecord(int month) {
        String option;
        //get input from user to generate report
        System.out.println("\n1. Generate learner report");
        System.out.println("2. Generate aquatic educator report");
        System.out.print("Enter one option from above list : ");
        option = scanner.nextLine().trim();
        switch (option) {
            case "1" -> {
                learnerRecord(month);
            }
            case "2" -> {
                educatorRecord(month);
            }
            default -> {
                return;
            }
        }
    }

    public void generateMonthlyRecord() {
        String select_month;
        int month_number;
        System.out.printf("%-20s%-20s%-20s%-20s\n", month_name[0], month_name[1], month_name[2], month_name[3]);
        System.out.printf("%-20s%-20s%-20s%-20s\n", month_name[4], month_name[5], month_name[6], month_name[7]);
        System.out.printf("%-20s%-20s%-20s%-20s\n", month_name[8], month_name[9], month_name[10], month_name[11]);

        System.out.print("Enter month name to view report : ");
        select_month = scanner.nextLine().trim();
        if (select_month.isEmpty() || select_month.matches("\\d+")) {
            System.out.println("=> Error : You entered an incorrect month name");
        } else {
            month_number = getMonthNumber(select_month);
            if (month_number == 0) {
                System.out.println("=> Error : You entered an incorrect month name");
            } else {
                monthlyRecord(month_number);
            }
        }
    }

    public void learnerRecord(int month) {
        boolean report_available = false;
        // Iterate  learner_list
        learner.learner_list.forEach(learners -> {
            int registration_number = learners.getRegistration_number();
            if (!(booking.learnerBookingCount(registration_number, month).get("total_booking") == 0
                    && booking.learnerBookingCount(registration_number, month).get("total_attend") == 0
                    && booking.learnerBookingCount(registration_number, month).get("total_cancel") == 0)) {
                // Print learner information
                System.out.println("\nRegistration No  : " + learners.getRegistration_number());
                System.out.println("Learner Name     : " + learners.getLearner_name());
                System.out.println("Learner Email    : " + learners.getLearner_email());
                System.out.println("Learner Contact  : " + learners.getLearner_contact());
                System.out.println("Learner Gender   : " + learners.getLearner_gender());
                System.out.println("Learner Age      : " + learners.getLearner_age());
                System.out.println("Learner Label    : " + learners.getLearner_level());
                // Print booking information
                System.out.println("Total Bookings   : " + booking.learnerBookingCount(registration_number, month).get("total_booking"));
                System.out.println("Total Attend     : " + booking.learnerBookingCount(registration_number, month).get("total_attend"));
                System.out.println("Total cancel     : " + booking.learnerBookingCount(registration_number, month).get("total_cancel"));
                System.out.println();

                // Show detailed booking information
                booking.showLearnerBookingInformation(registration_number, month);
            }
        });
        for (Learner learner1 : learner.learner_list) {
            int registration_number = learner1.getRegistration_number();
            if (!(booking.learnerBookingCount(registration_number, month).get("total_booking") == 0
                    && booking.learnerBookingCount(registration_number, month).get("total_attend") == 0
                    && booking.learnerBookingCount(registration_number, month).get("total_cancel") == 0)) {
                report_available = true;
            }

        }
        if (!report_available) {
            System.out.println("\n=> Error : No learner report available in this month");
        }
    }

    public void educatorRecord(int month) {
        boolean report_available = false;
        educator.educator_list.forEach(educators -> {
            int educator_id = educators.getEducator_id();
            double average_ratings = feedback.calculateAverageRating(educator_id, month);
            //check educator average rating not 0
            if (average_ratings != 0) {
                String educator_name = educators.getEducator_name();
                String educator_email = educators.getEducator_email();
                String educator_contact = educators.getEducator_contact();
                String educator_gender = educators.getEducator_gender();
                String educator_experience = educators.getEducator_experience();
                String educator_age = educators.getEducator_age();
                //print educator details
                System.out.println("\nEducator Id      : " + educator_id);
                System.out.println("Educator Name    : " + educator_name);
                System.out.println("Average Rating   : " + new DecimalFormat("0.##").format(average_ratings));
                System.out.println("Experience       : " + educator_experience);
                System.out.println("Educator Email   : " + educator_email);
                System.out.println("Educator Contact : " + educator_contact);
                System.out.println("Educator Gender  : " + educator_gender);
                System.out.println("Educator Age     : " + educator_age);
                System.out.println();
                //print feedback details
                feedback.showFeedbacks(educator_id, month);
            }
        });
        for (AquaticEducator educator1 : educator.educator_list) {
            if (feedback.calculateAverageRating(educator1.getEducator_id(), month) != 0) {
                report_available = true;
            }

        }
        if (!report_available) {
            System.out.println("\n=> Error : No educator report available in this month");
        }
    }

    //check correct month name
    public int getMonthNumber(String select_month) {
        for (int i = 0; i < month_name.length - 1; i++) {
            if (month_name[i].equalsIgnoreCase(select_month) || month_name[i].substring(0, 3).equalsIgnoreCase(select_month)) {
                return i + 1;
            }
        }
        return 0;
    }
}
