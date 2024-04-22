package swimmingtrainingschool;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Feedback {

    private Scanner scanner;
    private SchoolManagement management;
    private AquaticTimetable timetable;
    private int feedback_id;
    private int rating;
    private String review;
    private String booking_id;
    private int educator_id;
    private int registration_number;
    private String lesson_id;
    public List<Feedback> feedback_list;

    public int getFeedback_id() {
        return feedback_id;
    }

    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public int getEducator_id() {
        return educator_id;
    }

    public int getRegistration_number() {
        return registration_number;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public List<Feedback> getFeedback_list() {
        return feedback_list;
    }

    public Feedback(int feedback_id, int rating, String review, String booking_id, int educator_id, int registration_number, String lesson_id) {
        this.feedback_id = feedback_id;
        this.rating = rating;
        this.review = review;
        this.booking_id = booking_id;
        this.educator_id = educator_id;
        this.registration_number = registration_number;
        this.lesson_id = lesson_id;
    }

    public Feedback() {
        feedback_list = new ArrayList<>();
    }

    //initialize object
    public void initObject(SchoolManagement management) {
        this.scanner = management.getScanner();
        this.management = management;
        this.timetable = management.getTimetable();
        
    }

    //get ratings and review from learner
    public Feedback learnerFeedback(String booking_number) {
        String get_rating = null;
        int rating = 0;
        String reviews = null;
        Feedback feedback = null;
        System.out.println("\n1: Very dissatisfied\n2: Dissatisfied\n3: Ok\n4: Satisfied\n5: Very Satisfied\n");
        do {
            if (reviews == null) {
                System.out.print("Enter review for attended lesson : ");
                reviews = scanner.nextLine().trim();
                if (reviews.isEmpty()) {
                    System.out.println("=> Please give correct review for this lesson ");
                    reviews = null;
                }
            } else if (get_rating == null) {
                System.out.print("Enter rating from above list : ");
                get_rating = scanner.nextLine().trim();
                if (get_rating.isEmpty() || !get_rating.matches("[1-5]")) {
                    System.out.println("=> Please give correct rating for this lesson 1 to 5");
                    get_rating = null;
                }
            } else {
                //create object
                rating = Integer.parseInt(get_rating);
                feedback = new Feedback(0, rating, reviews, booking_number, 0, 0, "");
                return feedback;
            }

        } while (feedback == null);
        return feedback;
    }

    //show feedbacks
    public void showFeedbacks(int educator_id,int month) {
        int month_number;
        if (!feedback_list.isEmpty()) {
            System.out.println("*******************************************************************************************************************************************************************");
            System.out.printf("%-10s * %-10s * %-12s * %-10s * %-10s * %-10s * %-10s * %-10s * %-15s * %-10s *\n",
                    "Feedback Id", "\tLesson Id", "Lesson", "\tLearner", "Lesson Day", "Lesson Duration", "\tLesson Date", "\tEducator", "\tReview", "\tRatings");
            System.out.println("*******************************************************************************************************************************************************************");
            for (Feedback feedback : feedback_list) {
                 month_number = Integer.parseInt(timetable.getLessonInfromation(feedback.getLesson_id()).getLesson_date().substring(3, 5));
                if ((feedback.getEducator_id() == educator_id || educator_id == 0)||feedback.getEducator_id() == educator_id&&month==0) {
                    int feedback_id = feedback.getFeedback_id();
                    String lesson_id = feedback.getLesson_id();
                    int registration_number = feedback.getRegistration_number();
                    //create lesson object to get lesson details
                    AquaticTimetable aquatic = timetable.getLessonInfromation(lesson_id);
                    String lesson_name = aquatic.getLesson_name();
                    String learner_name = management.getLearner().getLearnerInfromation(registration_number).getLearner_name();
                    String lesson_day = aquatic.getLesson_day();
                    String lesson_date = aquatic.getLesson_date();
                    String lesson_duration = aquatic.getLesson_duration();
                    String educator_name = management.getEducator().getEducatorInfromation(feedback.getEducator_id()).getEducator_name();
                    String review = feedback.getReview();
                    int rating = feedback.getRating();
                    System.out.printf("%-10s * %-10s * %-12s * %-10s * %-10s * %-10s * %-10s * %-10s * %-15s * %-10s *\n",
                            "REVIEW_0" + feedback_id, "\t" + lesson_id, lesson_name, "\t" + learner_name, lesson_day, lesson_duration, "\t" + lesson_date, "\t" + educator_name, "\t" + review, "\t" + rating);
                }
            }
            System.out.println("*******************************************************************************************************************************************************************");
        }else{
            System.out.println("=> Error : No reviews available at the moment");
        }
    }

    public double calculateAverageRating(int educator_id, int month) {
        // Calculate average rating for each educator
        int month_number;
        double sum = 0;
        int count = 0;
        for (Feedback feedback : feedback_list) {
            month_number = Integer.parseInt(timetable.getLessonInfromation(feedback.getLesson_id()).getLesson_date().substring(3, 5));
            if (feedback.getEducator_id() == educator_id && month_number == month) {
                sum += feedback.getRating();
                count++;
            }
        }
        // get average rating
        double avgRating = (count == 0) ? 0 : sum / count;
        //return average ratig
        return avgRating;
    }

}
