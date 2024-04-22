package swimmingtrainingschool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BookingHandler {

    private Learner learner;
    private SchoolManagement management;
    private AquaticTimetable timetable;
    private AquaticEducator educator;
    private Scanner scanner;
    private Feedback feedback;
    private String booking_number;
    private int registration_number;
    private String confirmation_status;
    private String lesson_id;
    private final int min_capacity = 0;
    private final int max_capacity = 5;
    private String reason_to_cancel_lesson;
    List<BookingHandler> booking_list;

    public Learner getLearner() {
        return learner;
    }

    public String getBooking_number() {
        return booking_number;
    }

    public int getRegistration_number() {
        return registration_number;
    }

    public String getConfirmation_status() {
        return confirmation_status;
    }

    public String getLessonId() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public void setConfirmation_status(String confirmation_status) {
        this.confirmation_status = confirmation_status;
    }

    public String getReason_to_cancel_lesson() {
        return reason_to_cancel_lesson;
    }

    public void setReason_to_cancel_lesson(String reason_to_cancel_lesson) {
        this.reason_to_cancel_lesson = reason_to_cancel_lesson;
    }

    public List<BookingHandler> getBooking_list() {
        return booking_list;
    }

    public BookingHandler(String booking_number, int registration_number, String confirmation_status, String lesson_id, String reason_to_cancel_lesson) {
        this.booking_number = booking_number;
        this.registration_number = registration_number;
        this.confirmation_status = confirmation_status;
        this.lesson_id = lesson_id;
        this.reason_to_cancel_lesson = reason_to_cancel_lesson;
    }

    public BookingHandler() {

        booking_list = new ArrayList<>();
    }

    //intialize object
    public void initObject(SchoolManagement management) {
        this.management = management;
        this.scanner = management.getScanner();
        this.learner = management.getLearner();
        this.timetable = management.getTimetable();
        timetable.initObject(management);
        this.educator = management.getEducator();
        this.feedback = management.getFeedback();
        feedback.initObject(management);
    }

    //booking clas
    public boolean bookingLesson() {
        String lesson_id;
        String status = "Booked";
        int lesson_capacity = 0;
        //get learner registration number
        int registration_number = management.getRegistrationNumber();
        //show timetable to select lesson for booking
        String action = timetable.filterLessonList(registration_number,"Book");
        if (action != null) {
            System.out.print("Enter lesson id to book lesson : ");
            lesson_id = scanner.nextLine().trim();
            if (lesson_id.isEmpty() || !correctLessonId(lesson_id)) {
                System.out.println("=> Please enter correct lesson id to book lesson");
            } else {
                //check not duplicate booking
                if (duplicateBooking(registration_number, lesson_id)) {
                    System.out.println("=> This lesson id " + lesson_id + " already booked by you");
                } else {
                    //get current lesson capacity
                    lesson_capacity = timetable.getLessonInfromation(lesson_id).getLesson_capacity();
                    if (!(lesson_capacity > min_capacity && lesson_capacity < max_capacity)) {
                        System.out.println("=> This lesson id " + lesson_id + " is fully booked.");
                    } else {
                        //get lesson level
                        int lesson_level = timetable.getLessonInfromation(lesson_id).getLesson_level();
                        //get learner level
                        int learner_level = learner.getLearnerInfromation(registration_number).getLearner_level();
                        if (!(learner_level == lesson_level || (learner_level + 1) == lesson_level)) {
                            System.out.println("=> You can book only level " + learner_level + " & " + (learner_level + 1) + " lessons");
                        } else {
                            management.saveLearnerBookings(registration_number, lesson_id, status);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    //cancel lesson
    public boolean cancelLesson() {
        String reason;
        boolean correct_reason = false;
        int registration_number = management.getRegistrationNumber();
        String booking_number = null;
        String previous_menu;
        //show learner booking details to select for cancel
        showLearnerBookingInformation(registration_number,0);
        if (!getLearnerBookings(registration_number,0).isEmpty()) {
            System.out.print("Enter one booking id from above to cancel lesson : ");
            booking_number = scanner.nextLine().trim();
            if (booking_number.isEmpty() || booking_number.matches("\\d+")) {
                System.out.println("=> Please enter correct booking id to cancel lesson");
            } else {
                //chcek learner enter correct booking numer 
                if (!correctBookingId(booking_number, registration_number)) {
                    System.out.println("=> This booking number " + booking_number + " does not exist");
                } else {
                    //check selected booking number not attended or cancel
                    String booking_status = getInfoByBookingNumber(booking_number).getConfirmation_status();
                    if (booking_status.equalsIgnoreCase("Attended")) {
                        System.out.println("=> You have already attended this booking id " + booking_number);
                    } else if (booking_status.equalsIgnoreCase("Cancelled")) {
                        System.out.println("=> You have already cancelled this booking id " + booking_number);
                    } else {
                        //show selected booking details
                        selectedBookingInfo(booking_number);
                        //get reason for cancel class by learner 
                        do {
                            System.out.print("Enter reason for cancel lesson : ");
                            reason = scanner.nextLine().trim();
                            if (reason.isEmpty() || reason.matches("\\d+")) {
                                System.out.println("=> Please enter valid reason to cancel class : ");
                                System.out.println("Previous menu (YES/NO) : ");
                                previous_menu = scanner.nextLine().trim();
                                if (previous_menu.equalsIgnoreCase("Y") || previous_menu.equalsIgnoreCase("Yes")) {
                                    correct_reason = true;
                                }
                            } else {
                                //cancel this lesson
                                management.cancelLearnerBookings(booking_number, reason);
                                correct_reason = true;
                                return correct_reason;
                            }
                        } while (!correct_reason);
                    }
                }
            }

        }
        return false;
    }

    //change lesson
    public boolean changeLesson() {
        String new_lesson_id;
        int lesson_capacity;
        int registration_number = management.getRegistrationNumber();
        String booking_number = null;
        //show learner booking details to select for change
        showLearnerBookingInformation(registration_number,0);
        if (!getLearnerBookings(registration_number,0).isEmpty()) {
            System.out.print("Enter one booking id from above to change lesson : ");
            booking_number = scanner.nextLine().trim();
            if (booking_number.isEmpty() || booking_number.matches("\\d+")) {
                System.out.println("=> Please enter correct booking id to change lesson");
            } else {
                //chcek learner enter correct booking numer 
                if (!correctBookingId(booking_number, registration_number)) {
                    System.out.println("=> This booking number " + booking_number + " does not exist");
                } else {
                    //check selected booking number not attended or cancel
                    String booking_status = getInfoByBookingNumber(booking_number).getConfirmation_status();
                    if (booking_status.equalsIgnoreCase("Attended")) {
                        System.out.println("=> You have already attended this booking id " + booking_number);
                    } else if (booking_status.equalsIgnoreCase("Cancelled")) {
                        System.out.println("=> You have already cancelled this booking id " + booking_number);
                    } else {
                        //show selected booking details
                        selectedBookingInfo(booking_number);
                        //show timetable to select lesson for booking
                        String action = timetable.filterLessonList(registration_number,"Change");
                        if (action != null) {
                            System.out.print("Enter lesson id to change lesson : ");
                            new_lesson_id = scanner.nextLine().trim();
                            if (new_lesson_id.isEmpty() || !correctLessonId(new_lesson_id)) {
                                System.out.println("=> Please enter correct lesson id to change lesson");
                            } else {
                                //check not duplicate booking
                                if (duplicateBooking(registration_number, new_lesson_id)) {
                                    System.out.println("=> This lesson id " + new_lesson_id + " already booked by you");
                                } else {
                                    //get current lesson capacity
                                    lesson_capacity = timetable.getLessonInfromation(new_lesson_id).getLesson_capacity();
                                    if (!(lesson_capacity > min_capacity && lesson_capacity < max_capacity)) {
                                        System.out.println("=> This lesson id " + new_lesson_id + " is fully booked.");
                                    } else {
                                        //get lesson level
                                        int lesson_level = timetable.getLessonInfromation(new_lesson_id).getLesson_level();
                                        //get learner level
                                        int learner_level = learner.getLearnerInfromation(registration_number).getLearner_level();
                                        if (!(learner_level == lesson_level || (learner_level + 1) == lesson_level)) {
                                            System.out.println("=> You can change only level " + learner_level + " & " + (learner_level + 1) + " lessons");
                                        } else {
                                            //change lesson
                                            management.changeLearnerBookings(booking_number, new_lesson_id);
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    //attend lesson
    public boolean attendLesson() {
        int registration_number = management.getRegistrationNumber();
        String booking_number;
        boolean lesson_attend = false;
        //show learner booking details to select booking for attend
        showLearnerBookingInformation(registration_number,0);
        if (!getLearnerBookings(registration_number,0).isEmpty()) {
            System.out.print("Enter one booking id from above to attend lesson : ");
            booking_number = scanner.nextLine().trim();
            if (booking_number.isEmpty() || booking_number.matches("\\d+")) {
                System.out.println("=> Please enter correct booking id to attend lesson");
            } else {
                //chcek learner enter correct booking numer 
                if (!correctBookingId(booking_number, registration_number)) {
                    System.out.println("=> This booking number " + booking_number + " does not exist");
                } else {
                    //check selected booking number not attended or cancel
                    String booking_status = getInfoByBookingNumber(booking_number).getConfirmation_status();
                    if (booking_status.equalsIgnoreCase("Attended")) {
                        System.out.println("=> You have already attended this booking id " + booking_number);
                    } else if (booking_status.equalsIgnoreCase("Cancelled")) {
                        System.out.println("=> You have already cancelled this booking id " + booking_number);
                    } else {
                        //show selected booking details
                        selectedBookingInfo(booking_number);
                        //attend this lesson
                        //get learner feedback after attended class
                        Feedback feedback1 = feedback.learnerFeedback(booking_number);
                        lesson_attend = management.attendLearnerBookings(booking_number, feedback1);
                        if (lesson_attend) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //show learner booking details
    public void showLearnerBookingInformation(int registration_number,int month) {
        String booking_id;
        String lesson_id;
        int learner_id;
        int educator_id;
        String lesson_name;
        int lesson_level;
        String lesson_day;
        String lesson_date;
        String lesson_duration;
        String status;
        String learner_name;
        int learner_level;
        String educator_name;
        double lesson_fees;
        String reason_to_cancel_lesson;
        List<BookingHandler> learner_booking = getLearnerBookings(registration_number,month);
        if (!booking_list.isEmpty()) {
            if (!learner_booking.isEmpty()) {
                System.out.println("\n***********************************************************************************************************************************************************************************************************************");
                System.out.printf("%-10s * %-12s * %-12s * %-12s * %-12s * %-18s * %-15s * %-12s * %-12s * %-12s * %-12s * %-10s * %-25s *\n",
                        "Booking Id", "Lesson Id", "Lesson Name", "Lesson Level", "Lesson Day", "Lesson Duration", "Lesson Date", "Status", "Learner Name", "Learner Level", "Educator", "Amount", "Reason for cancel lesson");

                System.out.println("***********************************************************************************************************************************************************************************************************************");
                for (int i = 0; i < learner_booking.size(); i++) {
                    booking_id = learner_booking.get(i).getBooking_number();
                    lesson_id = learner_booking.get(i).getLessonId();
                    status = learner_booking.get(i).getConfirmation_status();
                    learner_id = learner_booking.get(i).getRegistration_number();
                    //create lesson object to get lesson information
                    AquaticTimetable aquatic = timetable.getLessonInfromation(lesson_id);
                    lesson_name = aquatic.getLesson_name();
                    lesson_level = aquatic.getLesson_level();
                    lesson_day = aquatic.getLesson_day();
                    lesson_date = aquatic.getLesson_date();
                    lesson_duration = aquatic.getLesson_duration();
                    educator_id = aquatic.getEducator_id();
                    lesson_fees = aquatic.getLesson_fees();
                    educator_name = educator.getEducatorInfromation(educator_id).getEducator_name();
                    //create learner object to get learner information
                    Learner learner1 = learner.getLearnerInfromation(learner_id);
                    learner_name = learner1.getLearner_name();
                    learner_level = learner1.getLearner_level();
                    reason_to_cancel_lesson = learner_booking.get(i).getReason_to_cancel_lesson();
                    System.out.printf("%-10s * %-12s * %-12s * %-12s * %-12s * %-18s * %-15s * %-12s * %-12s * %-13s * %-12s * %-10s *  %-25s *\n",
                            booking_id, lesson_id, lesson_name, "Level " + lesson_level, lesson_day, lesson_duration, lesson_date, status, learner_name, "Level " + learner_level, educator_name, " $ "+lesson_fees, reason_to_cancel_lesson);

                }
                System.out.println("***********************************************************************************************************************************************************************************************************************");

            } else {
                System.out.println("=> Error: No bookings details available in the record ");
            }
        } else {
            System.out.println("=> Error : No bookings details available in the record ");
        }
    }

    //show selected booking Infromation
    public void selectedBookingInfo(String booking_number) {
        String lesson_id;
        int registration_number;
        int educator_id;
        System.out.println("\n\t\tSelected Booking Details");
        for (int i = 0; i < booking_list.size(); i++) {
            if (booking_list.get(i).getBooking_number().equalsIgnoreCase(booking_number)) {
                lesson_id = booking_list.get(i).getLessonId();
                registration_number = booking_list.get(i).getRegistration_number();
                System.out.println("Booking number  : " + booking_list.get(i).getBooking_number());
                //create lesson object to get lesson information
                AquaticTimetable aquatic = timetable.getLessonInfromation(lesson_id);
                educator_id = aquatic.getEducator_id();
                System.out.println("Lesson Id       : " + lesson_id);
                System.out.println("Lesson Name     : " + aquatic.getLesson_name());
                System.out.println("Lesson Level    : " + aquatic.getLesson_level());
                System.out.println("Lesson Day      : " + aquatic.getLesson_day());
                System.out.println("Lesson Duration : " + aquatic.getLesson_duration());
                System.out.println("Lesson Date     : " + aquatic.getLesson_date());
                System.out.println("Booking Status  : " + booking_list.get(i).getConfirmation_status());
                //create learner object to get learner information
                Learner learner1 = learner.getLearnerInfromation(registration_number);
                System.out.println("Learner Name    : " + learner1.getLearner_name());
                System.out.println("Learner Level   : " + learner1.getLearner_level());
                System.out.println("Educator Name   : " + educator.getEducatorInfromation(educator_id).getEducator_name());
                System.out.println("Lesson Fees     : $ " + aquatic.getLesson_fees());
                System.out.println();
            }

        }
    }

    //get learner booking details
    public List<BookingHandler> getLearnerBookings(int registration_number,int month) {
        int month_number;
        List<BookingHandler> learner_bookings = new ArrayList<>();
        for (BookingHandler booking : booking_list) {
             month_number = Integer.parseInt(timetable.getLessonInfromation(booking.getLessonId()).getLesson_date().substring(3, 5));
            if (booking.getRegistration_number() == registration_number&&month_number==month || registration_number == 0&&month==0||booking.getRegistration_number() == registration_number&&month==0) {
                learner_bookings.add(booking);
            }
        }
        return learner_bookings;
    }

    //get booking information by booking number
    public BookingHandler getInfoByBookingNumber(String booking_number) {
        int index = 0;
        while (index < booking_list.size()) {
            BookingHandler bookings = booking_list.get(index);
            if (bookings.getBooking_number().equalsIgnoreCase(booking_number)) {
                return bookings;
            }
            index++;
        }
        return null;
    }

    //get booking infomation by id
    public Map<String, Integer> learnerBookingCount(int registration_number, int month) {
        int month_number;
        int total_booking = 0;
        int total_attend = 0;
        int total_cancel = 0;
        //create map list to add booking details
        Map<String, Integer> learner_bookings = new HashMap<>();
        for (BookingHandler booking : booking_list) {
            month_number = Integer.parseInt(timetable.getLessonInfromation(booking.getLessonId()).getLesson_date().substring(3, 5));
            if ((booking.getRegistration_number() == registration_number && month_number == month) || (booking.getRegistration_number() == registration_number && 0 == month)) {
                if (booking.getConfirmation_status().equalsIgnoreCase("Booked") || booking.getConfirmation_status().equalsIgnoreCase("Changed")) {
                    total_booking++;
                } else if (booking.getConfirmation_status().equalsIgnoreCase("Cancelled")) {
                    total_cancel++;
                } else if (booking.getConfirmation_status().equalsIgnoreCase("Attended")) {
                    total_attend++;
                }
            }
        }
        //store details in map list
        learner_bookings.put("total_booking", total_booking);
        learner_bookings.put("total_attend", total_attend);
        learner_bookings.put("total_cancel", total_cancel);

        //return details
        return learner_bookings;

    }

    //chcek user enter correct lesson id to book lesson
    public boolean correctLessonId(String lesson_id) {
        for (AquaticTimetable aquatic : timetable.lesson_list) {
            if (aquatic.getLesson_id().equalsIgnoreCase(lesson_id)) {
                return true;
            }
        }
        return false;
    }

    //check for duplicate booking
    public boolean duplicateBooking(int registration_number, String lesson_id) {
        for (BookingHandler booking : booking_list) {
            if (booking.getRegistration_number() == registration_number && booking.getLessonId().equalsIgnoreCase(lesson_id)) {
                if (booking.getConfirmation_status().equalsIgnoreCase("Booked") || booking.getConfirmation_status().equalsIgnoreCase("Changed")) {
                    return true;
                }
            }
        }
        return false;
    }

    //check correct booking id
    public boolean correctBookingId(String booking_number, int registration_number) {
        for (BookingHandler booking : booking_list) {
            if (booking.getBooking_number().equalsIgnoreCase(booking_number) && booking.getRegistration_number() == registration_number) {
                return true;
            }
        }
        return false;
    }

}
