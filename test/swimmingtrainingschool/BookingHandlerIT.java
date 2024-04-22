package swimmingtrainingschool;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookingHandlerIT {

    private SchoolManagement management;
    private Learner learner;
    private AquaticTimetable timetable;
    private BookingHandler booking;
    private Feedback feedback;
    private MonthlyRecord record;
    private AquaticEducator educator;
    private Receptionist receptionist;

    public BookingHandlerIT() {
        initObject();

    }

    //initialize management class object
    public void initObject() {
        this.management = SchoolManagement.getInstance();
        this.learner = management.getLearner();
        this.timetable = management.getTimetable();
        this.booking = management.getBooking();
        booking.initObject(management);
        this.feedback = management.getFeedback();
        feedback.initObject(management);
        this.record = management.getRecord();
        record.initObject(management);
        this.educator = management.getEducator();
        this.receptionist = management.getReceptionist();
        receptionist.intiObject(management);
    }

    public void defaultBookings() {
        //save by default booking for attend
        management.saveLearnerBookings(16378, "SWM_41", "Booked");
    }

    //test add learner
    @Test
    public void testAddLearner() {
        System.out.println("\nTest description : test add learner\n");
        String learner_name = "Robin";
        String learner_contact = "04123467895";
        String learner_email = "robin@gmail.com";
        String learner_gender = "Male";
        int learner_age = 4;
        int level = 2;
        int result = 0;
        if (!learner_name.isEmpty() || !receptionist.duplicateName(learner_name)) {
            if (!learner_email.isEmpty() || !receptionist.duplicateEmail(learner_email)) {
                if (!learner_contact.isEmpty() || learner_contact.matches("\\d+")) {
                    if (!learner_gender.isEmpty() || !learner_gender.matches("\\d+")) {
                        if (receptionist.correctAge(learner_age)) {
                            if (receptionist.correctLevel(level)) {
                                result = management.saveLearnerInformation(learner);
                            } else {
                                System.out.println("=> Please enter correct learner level. level should be 1 to 5 ");
                            }
                        } else {
                            System.out.println("=> Please enter correct learner age. age should be 4 to 11 ");
                        }
                    }

                }

            } else {
                System.out.println("=> Please enter correct email");
            }
        } else {
            System.out.println("=> Please enter correct name");
        }

        assertNotNull(result);
        if (result != 0) {
            System.out.println("Your Registration number is : " + result);
        }
    }

    //test attend lesson
    @Test
    public void testAttendLesson() {
        defaultBookings();
        System.out.println(booking.booking_list.get(0).getBooking_number());
        System.out.println("\nTest attend lesson by learner\n");
        int registration_number = 16378;
        String booking_number = "Book_01";
        int rating = 4;
        String reviews = "best class";
        boolean result = false;
        if (learner.correctRegistrationId(registration_number)) {
            //chcek learner enter correct booking numer 
            if (!booking.correctBookingId(booking_number, registration_number)) {
                System.out.println("=> This booking number " + booking_number + " does not exist");
            } else {
                //check selected booking number not attended or cancel
                String booking_status = booking.getInfoByBookingNumber(booking_number).getConfirmation_status();
                if (booking_status.equalsIgnoreCase("Attended")) {
                    System.out.println("=> You have already attended this booking id " + booking_number);
                } else if (booking_status.equalsIgnoreCase("Cancelled")) {
                    System.out.println("=> You have already cancelled this booking id " + booking_number);
                } else {
                    //show selected booking details
                    booking.selectedBookingInfo(booking_number);
                    //attend this lesson
                    Feedback feedback1 = new Feedback(0, rating, reviews, booking_number, 0, 0, "");
                    result = management.attendLearnerBookings(booking_number, feedback1);
                    if (result) {
                        System.out.println("Show booking details after attended lesson");
                        booking.selectedBookingInfo(booking_number);
                    }
                }
            }
        } else {
            System.out.println("=> Registration number " + registration_number + " does not exist ");
        }

        assertTrue(result);

    }

    //test book lesson
    @Test
    public void testBookingLesson() {
        System.out.println("\nTest lesson booked by learner\n");
        int registration_number = 16178;
        String lesson_id = "SWM_38";
        String confirmation_status = "Booked";
        int lesson_capacity;
        int min_capacity = 0;
        int max_capacity = 5;
        //check correct registration_number
        if (learner.correctRegistrationId(registration_number)) {
            if (booking.correctLessonId(lesson_id)) {
                //check not duplicate booking
                if (booking.duplicateBooking(registration_number, lesson_id)) {
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
                            management.saveLearnerBookings(registration_number, lesson_id, confirmation_status);
                            List<BookingHandler> result = booking.booking_list;
                            assertNotNull(result);
                            if (!result.isEmpty()) {
                                System.out.println("Show booked lesson details");
                                booking.showLearnerBookingInformation(registration_number,0);

                            }
                        }
                    }
                }
            } else {
                System.out.println("=> Lesson Id " + lesson_id + " does not exist");
            }
        } else {
            System.out.println("=> Registration number " + registration_number + " does not exist ");
        }
    }

    //test change lesson
    @Test
    public void testChangeLesson() {
        System.out.println("\nTest lesson changed by learner\n");
        int registration_number = 16178;
        String booking_number = "Book_02";
        String status = "Changed";
        String new_lesson_id = "SWM_40";
        int lesson_capacity;
        int min_capacity = 0;
        int max_capacity = 5;
        String expResult = "Changed";
        //check correct registration_number
        if (learner.correctRegistrationId(registration_number)) {
            //chcek learner enter correct booking numer 
            if (!booking.correctBookingId(booking_number, registration_number)) {
                System.out.println("=> This booking number " + booking_number + " does not exist");
            } else {
                //check selected booking number not attended or cancel
                String booking_status = booking.getInfoByBookingNumber(booking_number).getConfirmation_status();
                if (booking_status.equalsIgnoreCase("Attended")) {
                    System.out.println("=> You have already attended this booking id " + booking_number);
                } else if (booking_status.equalsIgnoreCase("Cancelled")) {
                    System.out.println("=> You have already cancelled this booking id " + booking_number);
                } else {
                    //show selected booking details
                    booking.selectedBookingInfo(booking_number);
                    System.out.println("Selected new lesson _id " + new_lesson_id);
                    //check not duplicate booking
                    if (booking.duplicateBooking(registration_number, new_lesson_id)) {
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
                            }
                        }
                    }

                }
            }
        } else {
            System.out.println("=> Registration number " + registration_number + " does not exist ");
        }

        String result = booking.getInfoByBookingNumber(booking_number).getConfirmation_status();
        assertEquals(expResult, result);
        if (result.equalsIgnoreCase("Changed")) {
            System.out.println("Show booking details after change lesson");
            booking.showLearnerBookingInformation(registration_number,0);
        }
    }

    @Test
    public void testDuplicateBooking() {
        System.out.println("\nTest duplicate booking\n");
        int registration_number = 16378;
        String lesson_id = "SWM_41";
        boolean expResult = false;
        boolean result = booking.duplicateBooking(registration_number, lesson_id);
        assertEquals(expResult, result);
        if (result) {
            System.out.println("=> This lesson id " + lesson_id + " already booked by you");
        }
    }

    @Test
    public void testEducatorRecord() {
        System.out.println("\nTest generate educator record\n");
        int month = 6;
        double result = 0;
        for (AquaticEducator educator1 : educator.educator_list) {
            result = feedback.calculateAverageRating(educator1.getEducator_id(), month);
            if (result != 0) {
                record.educatorRecord(month);
            }
        }
        assertNotNull(result);

    }

}
