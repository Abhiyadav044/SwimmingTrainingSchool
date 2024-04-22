package swimmingtrainingschool;

import java.util.Arrays;
import java.util.Scanner;

public class SchoolManagement {

    // Private static instance variable
    private static SchoolManagement instance;
    private Scanner scanner;
    private Learner learner;
    private AquaticTimetable timetable;
    private BookingHandler booking;
    private Receptionist receptionist;
    private AquaticEducator educator;
    private MonthlyRecord record;
    private Feedback feedback;
    private int registration_number;

    public Learner getLearner() {
        return learner;
    }

    public BookingHandler getBooking() {
        return booking;
    }

    public Receptionist getReceptionist() {
        return receptionist;
    }

    public AquaticEducator getEducator() {
        return educator;
    }

    public AquaticTimetable getTimetable() {
        return timetable;
    }

    public MonthlyRecord getRecord() {
        return record;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public int getRegistrationNumber() {
        return registration_number;
    }

    public void setRegistrationNumber(int registration_number) {
        this.registration_number = registration_number;
    }

    public Scanner getScanner() {
        return scanner;
    }

    // Public static method to get the instance
    public static SchoolManagement getInstance() {
        // Create the instance if instance is null
        if (instance == null) {
            instance = new SchoolManagement();
        }
        return instance;
    }

    //create constructor
    SchoolManagement() {
        scanner = new Scanner(System.in);
        learner = new Learner();
        educator = new AquaticEducator();
        receptionist = new Receptionist();
        booking = new BookingHandler();
        timetable = new AquaticTimetable();
        feedback = new Feedback();
        record = new MonthlyRecord();
    }

    //add new learner
    public int saveLearnerInformation(Learner learner1) {
        //get generate registration number and learner information
        int registration_number = generateRegistrationNumber();
        String learner_name = learner1.getLearner_name();
        String learner_contact = learner1.getLearner_contact();
        String learner_email = learner1.getLearner_email();
        String learner_gender = learner1.getLearner_gender();
        int learner_age = learner1.getLearner_age();
        int learner_level = learner1.getLearner_level();

        //add learner details in learner_list
        learner.learner_list.add(new Learner(registration_number, learner_name, learner_email, learner_contact, learner_age, learner_level, learner_gender));
        return registration_number;
    }

    //update learner profile
    public boolean updateLearnerProfile(int registration_number, String name, String email, String contact, String gender, int age, int level) {
        if (!(name.isEmpty() && email.isEmpty() && contact.isEmpty() && gender.isEmpty() && age == 0 && level == 0)) {
            for (Learner learner1 : learner.learner_list) {
                if (learner1.getRegistration_number() == registration_number) {
                    // update floarer name
                    if (name != null && !name.isEmpty()) {
                        learner1.setLearner_name(name);
                    }
                    //update learner email
                    if (email != null && !email.isEmpty()) {
                        learner1.setLearner_email(email);
                    }
                    //update learner contact
                    if (contact != null && !contact.isEmpty()) {
                        learner1.setLearner_contact(contact);
                    }
                    //update gender 
                    if (gender != null && !gender.isEmpty()) {
                        learner1.setLearner_gender(gender);
                    }
                    //update age if not 0
                    if (age != 0) {
                        learner1.setLearner_age(age);
                    }
                    //update level if not 0
                    if (level != 0) {
                        learner1.setLearner_level(level);
                    }
                    //return true after update 
                    return true;
                }
            }
        }
        return false;
    }

    //save learner booking information
    public void saveLearnerBookings(int registration_number, String lesson_id, String confirmation_status) {
        //generate booking_number of size 
        int id = booking.booking_list.size() + 1;
        String booking_number;
        if (id > 0 && id <= 9) {
            booking_number = "Book_0" + id;
        } else {
            booking_number = "Book_" + id;
        }
        //save learner booking infromations
        Arrays.asList(
                new BookingHandler(booking_number, registration_number, confirmation_status, lesson_id, "")
        ).forEach(booking.booking_list::add);
        //update lesson capacity
        for (AquaticTimetable aquatic : timetable.lesson_list) {
            if (aquatic.getLesson_id().equalsIgnoreCase(lesson_id)) {
                aquatic.setLesson_capacity(aquatic.getLesson_capacity() - 1);
                break;
            }
        }
    }

    //cancel learner booking
    public void cancelLearnerBookings(String booking_number, String reason_to_cancel_lesson) {
        //update booking status
        for (BookingHandler bookings : booking.booking_list) {
            if (bookings.getBooking_number().equalsIgnoreCase(booking_number)) {
                bookings.setConfirmation_status("Cancelled");
                bookings.setReason_to_cancel_lesson(reason_to_cancel_lesson);
                break;
            }
        }
        //get lesson_id from booking 
        String lesson_id = booking.getInfoByBookingNumber(booking_number).getLessonId();
        //update lesson capacity after cancel bookings
        for (AquaticTimetable aquatic : timetable.lesson_list) {
            if (aquatic.getLesson_id().equalsIgnoreCase(lesson_id)) {
                aquatic.setLesson_capacity(aquatic.getLesson_capacity() + 1);
                break;
            }
        }
    }

    //change learner booking
    public void changeLearnerBookings(String booking_number, String new_lesson_id) {
        String old_lesson_id = null;
        //change bookings
        for (BookingHandler bookings : booking.booking_list) {
            if (bookings.getBooking_number().equalsIgnoreCase(booking_number)) {
                old_lesson_id = bookings.getLessonId();
                bookings.setLesson_id(new_lesson_id);
                bookings.setConfirmation_status("Changed");
                break;
            }
        }
        // Update lesson capacities
        for (AquaticTimetable aquatic : timetable.lesson_list) {
            if (aquatic.getLesson_id().equalsIgnoreCase(old_lesson_id)) {
                aquatic.setLesson_capacity(aquatic.getLesson_capacity() + 1);
            } else if (aquatic.getLesson_id().equalsIgnoreCase(new_lesson_id)) {
                aquatic.setLesson_capacity(aquatic.getLesson_capacity() - 1);
            }
        }

    }

    //attend lesson by learner
    public boolean attendLearnerBookings(String booking_number, Feedback feedback1) {
        int registration_number = 0;
        int learner_level;
        int lesson_level;
        int feedback_id = feedback.feedback_list.size() + 1;
        int rating;
        String review;
        int educator_id;
        String lesson_id;
        if (feedback1 != null) {
            //update bookings status
            for (BookingHandler bookings : booking.booking_list) {
                if (bookings.getBooking_number().equalsIgnoreCase(booking_number)) {
                    registration_number = bookings.getRegistration_number();
                    bookings.setConfirmation_status("Attended");
                    break;
                }
            }

            //save learner feedback
            rating = feedback1.getRating();
            review = feedback1.getReview();
            //get lesson_id to update capacity
            lesson_id = booking.getInfoByBookingNumber(booking_number).getLessonId();
            educator_id = timetable.getLessonInfromation(lesson_id).getEducator_id();
            //save learner feedback
            Arrays.asList(
                    new Feedback(feedback_id, rating, review, booking_number, educator_id, registration_number, lesson_id)
            ).forEach(feedback.feedback_list::add);

            //update lesson capacity after attended lesson
            for (AquaticTimetable aquatic : timetable.lesson_list) {
                if (aquatic.getLesson_id().equalsIgnoreCase(lesson_id)) {
                    aquatic.setLesson_capacity(aquatic.getLesson_capacity() + 1);
                }
            }
            //get learner level
            learner_level = learner.getLearnerInfromation(registration_number).getLearner_level();
            //get lesson level
            lesson_level = timetable.getLessonInfromation(lesson_id).getLesson_level();
            //update learner level
            if (learner_level < lesson_level) {
                for (Learner learner1 : learner.learner_list) {
                    if (learner1.getRegistration_number() == registration_number) {
                        learner1.setLearner_level(lesson_level);
                        break;
                    }
                }
            }
            return true;
        }
        return false;
    }

    // Generate unique registration number
    public int generateRegistrationNumber() {
        int min = 10000;
        int max = 100000;
        int registration_number;
        do {
            registration_number = (int) (Math.random() * (max - min + 1) + min);
        } while (isRegistrationNumberExists(registration_number));

        return registration_number;
    }

    // Check registration number already exists
    public boolean isRegistrationNumberExists(int registrationNumber) {
        for (Learner learner1 : learner.learner_list) {
            if (learner1.getRegistration_number() == registration_number) {
                return true;
            }
        }
        return false;
    }

}
