package swimmingtrainingschool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AquaticTimetable {

    private Scanner scanner;
    private AquaticEducator educator;
    private String lesson_id;
    private int educator_id;
    private int lesson_capacity;
    private String lesson_day;
    private String lesson_duration;
    private int lesson_level;
    private String lesson_date;
    private String lesson_name;
    private double lesson_fees;
    public List<AquaticTimetable> lesson_list;
    private final String[] day_list = {"Monday", "Wednesday", "Friday", "Saturday"};

    public String getLesson_id() {
        return lesson_id;
    }

    public int getEducator_id() {
        return educator_id;
    }

    public int getLesson_capacity() {
        return lesson_capacity;
    }

    public void setLesson_capacity(int lesson_capacity) {
        this.lesson_capacity = lesson_capacity;
    }

    public String getLesson_day() {
        return lesson_day;
    }

    public String getLesson_duration() {
        return lesson_duration;
    }

    public int getLesson_level() {
        return lesson_level;
    }

    public String getLesson_date() {
        return lesson_date;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public double getLesson_fees() {
        return lesson_fees;
    }

    public List<AquaticTimetable> getLesson_list() {
        return lesson_list;
    }

    public void setLesson_list(List<AquaticTimetable> lesson_list) {
        this.lesson_list = lesson_list;
    }

    public AquaticTimetable(String lesson_id, int educator_id, int lesson_capacity, String lesson_day, String lesson_duration, int lesson_level, String lesson_date, String lesson_name, double lesson_fees) {
        this.lesson_id = lesson_id;
        this.educator_id = educator_id;
        this.lesson_capacity = lesson_capacity;
        this.lesson_day = lesson_day;
        this.lesson_duration = lesson_duration;
        this.lesson_level = lesson_level;
        this.lesson_date = lesson_date;
        this.lesson_name = lesson_name;
        this.lesson_fees = lesson_fees;
    }

    public AquaticTimetable() {
        lesson_list = new ArrayList<>();
        saveTimetable();
    }

    //intitialize object
    public void initObject(SchoolManagement management) {
        this.scanner = management.getScanner();
        this.educator = management.getEducator();

    }

    //save pre defined 44 lesson in lesson list
    public void saveTimetable() {
        Arrays.asList(
                new AquaticTimetable("SWM_01", 218541, 4, "Wednesday", "4:00 - 5:00 PM", 1, "08-05-2024", "Swim Steps", 1350.00),
                new AquaticTimetable("SWM_02", 218542, 4, "Wednesday", "5:00 - 6:00 PM", 2, "08-05-2024", "Swim Steps", 1400.00),
                new AquaticTimetable("SWM_03", 218543, 4, "Wednesday", "6:00 - 7:00 PM", 5, "08-05-2024", "Swim Steps", 1450.00),
                new AquaticTimetable("SWM_04", 218544, 4, "Friday", "4:00 - 5:00 PM", 4, "10-05-2024", "Water Fun", 1500.00),
                new AquaticTimetable("SWM_05", 218545, 4, "Friday", "5:00 - 6:00 PM", 3, "10-05-2024", "Water Fun", 1550.00),
                new AquaticTimetable("SWM_06", 218541, 4, "Friday", "6:00 - 7:00 PM", 5, "10-05-2024", "Water Fun", 1600.00),
                new AquaticTimetable("SWM_07", 218542, 4, "Saturday", "4:00 - 5:00 PM", 4, "11-05-2024", "Swim Learn", 1650.00),
                new AquaticTimetable("SWM_08", 218543, 4, "Saturday", "5:00 - 6:00 PM", 4, "11-05-2024", "Swim Learn", 1700.00),
                new AquaticTimetable("SWM_09", 218544, 4, "Monday", "4:00 - 5:00 PM", 3, "13-05-2024", "Aqua Intro", 1750.00),
                new AquaticTimetable("SWM_10", 218545, 4, "Monday", "5:00 - 6:00 PM", 2, "13-05-2024", "Aqua Intro", 1800.00),
                new AquaticTimetable("SWM_11", 218541, 4, "Monday", "6:00 - 7:00 PM", 2, "13-05-2024", "Aqua Intro", 1850.00),
                new AquaticTimetable("SWM_12", 218542, 4, "Wednesday", "4:00 - 5:00 PM", 1, "15-05-2024", "Swim Steps", 1900.00),
                new AquaticTimetable("SWM_13", 218543, 4, "Wednesday", "5:00 - 6:00 PM", 1, "15-05-2024", "Swim Steps", 1950.00),
                new AquaticTimetable("SWM_14", 218544, 4, "Wednesday", "6:00 - 7:00 PM", 3, "15-05-2024", "Swim Steps", 2000.00),
                new AquaticTimetable("SWM_15", 218545, 4, "Friday", "4:00 - 5:00 PM", 4, "17-05-2024", "Water Fun", 2050.00),
                new AquaticTimetable("SWM_16", 218541, 4, "Friday", "5:00 - 6:00 PM", 5, "17-05-2024", "Water Fun", 2100.00),
                new AquaticTimetable("SWM_17", 218542, 4, "Friday", "6:00 - 7:00 PM", 2, "17-05-2024", "Water Fun", 2150.00),
                new AquaticTimetable("SWM_18", 218543, 4, "Saturday", "4:00 - 5:00 PM", 1, "18-05-2024", "Swim Learn", 2200.00),
                new AquaticTimetable("SWM_19", 218544, 4, "Saturday", "5:00 - 6:00 PM", 1, "18-05-2024", "Swim Learn", 2250.00),
                new AquaticTimetable("SWM_20", 218545, 4, "Monday", "4:00 - 5:00 PM", 2, "20-05-2024", "Aqua Intro", 2300.00),
                new AquaticTimetable("SWM_21", 218541, 4, "Monday", "5:00 - 6:00 PM", 3, "20-05-2024", "Aqua Intro", 2350.00),
                new AquaticTimetable("SWM_22", 218542, 4, "Monday", "6:00 - 7:00 PM", 4, "20-05-2024", "Aqua Intro", 2400.00),
                new AquaticTimetable("SWM_23", 218543, 4, "Wednesday", "4:00 - 5:00 PM", 5, "22-05-2024", "Swim Steps", 2450.00),
                new AquaticTimetable("SWM_24", 218544, 4, "Wednesday", "5:00 - 6:00 PM", 1, "22-05-2024", "Swim Steps", 2500.00),
                new AquaticTimetable("SWM_25", 218545, 4, "Wednesday", "6:00 - 7:00 PM", 2, "22-05-2024", "Swim Steps", 2550.00),
                new AquaticTimetable("SWM_26", 218541, 4, "Friday", "4:00 - 5:00 PM", 3, "24-05-2024", "Water Fun", 2600.00),
                new AquaticTimetable("SWM_27", 218542, 4, "Friday", "5:00 - 6:00 PM", 4, "24-05-2024", "Water Fun", 2650.00),
                new AquaticTimetable("SWM_28", 218543, 4, "Friday", "6:00 - 7:00 PM", 5, "24-05-2024", "Water Fun", 2700.00),
                new AquaticTimetable("SWM_28", 218543, 4, "Friday", "6:00 - 7:00 PM", 1, "24-05-2024", "Water Fun", 2700.00),
                new AquaticTimetable("SWM_29", 218544, 4, "Saturday", "4:00 - 5:00 PM", 5, "25-05-2024", "Swim Learn", 2750.00),
                new AquaticTimetable("SWM_30", 218541, 4, "Saturday", "5:00 - 6:00 PM", 2, "25-05-2024", "Swim Learn", 2800.00),
                new AquaticTimetable("SWM_31", 218542, 4, "Monday", "4:00 - 5:00 PM", 4, "27-05-2024", "Aqua Intro", 2850.00),
                new AquaticTimetable("SWM_32", 218543, 4, "Monday", "5:00 - 6:00 PM", 3, "27-05-2024", "Aqua Intro", 2900.00),
                new AquaticTimetable("SWM_33", 218544, 4, "Monday", "6:00 - 7:00 PM", 5, "27-05-2024", "Aqua Intro", 2950.00),
                new AquaticTimetable("SWM_34", 218545, 4, "Wednesday", "4:00 - 5:00 PM", 1, "29-05-2024", "Swim Steps", 3000.00),
                new AquaticTimetable("SWM_35", 218541, 4, "Wednesday", "5:00 - 6:00 PM", 2, "29-05-2024", "Swim Steps", 3050.00),
                new AquaticTimetable("SWM_36", 218542, 4, "Wednesday", "6:00 - 7:00 PM", 1, "29-05-2024", "Swim Steps", 3100.00),
                new AquaticTimetable("SWM_37", 218543, 4, "Friday", "4:00 - 5:00 PM", 4, "31-05-2024", "Water Fun", 3150.00),
                new AquaticTimetable("SWM_38", 218544, 4, "Friday", "5:00 - 6:00 PM", 1, "31-05-2024", "Water Fun", 3200.00),
                new AquaticTimetable("SWM_39", 218545, 4, "Friday", "6:00 - 7:00 PM", 3, "31-05-2024", "Water Fun", 3250.00),
                new AquaticTimetable("SWM_40", 218541, 4, "Saturday", "4:00 - 5:00 PM", 2, "01-06-2024", "Swim Learn", 3300.00),
                new AquaticTimetable("SWM_41", 218542, 4, "Saturday", "5:00 - 6:00 PM", 3, "01-06-2024", "Swim Learn", 3350.00),
                new AquaticTimetable("SWM_42", 218543, 4, "Monday", "4:00 - 5:00 PM", 1, "03-06-2024", "Aqua Intro", 3400.00),
                new AquaticTimetable("SWM_43", 218544, 4, "Monday", "5:00 - 6:00 PM", 5, "03-06-2024", "Aqua Intro", 3450.00),
                new AquaticTimetable("SWM_44", 218545, 4, "Monday", "6:00 - 7:00 PM", 2, "03-06-2024", "Aqua Intro", 3500.00)
        ).forEach(lesson_list::add);

    }

    //show lesson timetable
    public void showLessonList(List<AquaticTimetable> filter_lesson_list) {
        int educator_id;
        int lesson_level;
        int lesson_capacity;
        String lesson_id;
        String lesson_name;
        String educator_name = null;
        String educator_experience = null;
        String educator_gender = null;
        String educator_age = null;
        String lesson_day;
        String lesson_date;
        String lesson_duration;
        double lesson_fees;
        //check lesson list is not empty
        if (!filter_lesson_list.isEmpty()) {
            System.out.println("***********************************************************************************************************************************************************************************************");
            System.out.printf("%-10s * %-14s * %-6s * %-15s * %-18s * %-12s * %-12s * %-10s * %-12s * %-17s * %-15s * %-15s *\n",
                    "Lesson Id", "Lesson Name", "Seat", "Lesson Day", "Duration", "Level", "Educator", "Experience", "Educator age", "Educator gender", "Date", "Amount");
            System.out.println("***********************************************************************************************************************************************************************************************");
            for (AquaticTimetable aquatic : filter_lesson_list) {
                //get lesson all infromation to show
                lesson_id = aquatic.getLesson_id();
                lesson_name = aquatic.getLesson_name();
                educator_id = aquatic.getEducator_id();
                lesson_capacity = aquatic.getLesson_capacity();
                lesson_day = aquatic.getLesson_day();
                lesson_duration = aquatic.getLesson_duration();
                lesson_level = aquatic.getLesson_level();
                //get educator infromation to show in timetable
                for (AquaticEducator educator1 : educator.educator_list) {
                    if (educator1.getEducator_id() == educator_id) {
                        educator_name = educator1.getEducator_name();
                        educator_experience = educator1.getEducator_experience();
                        educator_gender = educator1.getEducator_gender();
                        educator_age = educator1.getEducator_age();
                    }
                }
                lesson_date = aquatic.getLesson_date();
                lesson_fees = aquatic.getLesson_fees();

                //print timetable details in table
                System.out.printf("%-10s * %-14s * %-6s * %-15s * %-18s * %-12s * %-12s * %-10s * %-12s * %-17s * %-15s * %-15s *\n",
                        lesson_id, lesson_name, lesson_capacity, lesson_day, lesson_duration, "Level " + lesson_level, educator_name, educator_experience, educator_age, educator_gender, lesson_date, "$ " + lesson_fees);
            }
            System.out.println("***********************************************************************************************************************************************************************************************");

        }
    }

    //filter timetable
    public String filterLessonList(int registration_number, String status) {
        String option;
        //display all lesson list
        showLessonList(lesson_list);
        do {
            System.out.println("1. Lessons by day");
            System.out.println("2. lessons by level");
            System.out.println("3. Lessons by Educator ");
            if (registration_number != 0) {
                if (status.equalsIgnoreCase("Book")) {
                    System.out.println("4. Select Lesson to book class");
                } else {
                    System.out.println("4. Select Lesson to change class");
                }
                System.out.println("5. Return");
            }else{
                 System.out.println("4. Return");
            }
            System.out.print("Enter one option from above list : ");
            option = scanner.nextLine().trim();
            switch (option) {
                case "1" -> {
                    filterLessonDayWise();
                }
                case "2" -> {
                    filterLessonLevelWise();
                }
                case "3" -> {
                    filterLessonEducatorWise();
                }
                case "4" -> {
                    return "select_lesson";

                }
                default -> {

                }
            }
        } while (!(option.equals("4") || option.equals("5")));
        return null;
    }

    //filter lesson list level wise
    public void filterLessonLevelWise() {
        String get_level;
        int level;
        System.out.print("Enter one level from 1 to 5 : ");
        get_level = scanner.nextLine().trim();
        if (get_level.isEmpty() || !get_level.matches("[1-5]")) {
            System.out.println("\n=> Error : You entered an incorrect level\n");
        } else {
            level = Integer.parseInt(get_level);
            //create list to store filter lesson list
            List<AquaticTimetable> filter_lesson_list = new ArrayList<>();
            for (int i = 0; i < lesson_list.size(); i++) {
                if (lesson_list.get(i).getLesson_level() == level) {
                    filter_lesson_list.add(lesson_list.get(i));

                }
            }
            //display filter lesson list
            showLessonList(filter_lesson_list);
        }
    }

    //filter lesson list day wise
    public void filterLessonDayWise() {
        String day;
        System.out.print("Enter one day from (Monday/Wednesday/Friday/Saturday) : ");
        day = scanner.nextLine().trim();
        if (day.isEmpty() || correctDay(day) == null) {
            System.out.println("\n=> Error : You entered an incorrect day name \n");
        } else {
            //create list to store filter lesson list
            List<AquaticTimetable> filter_lesson_list = new ArrayList<>();
            for (int i = 0; i < lesson_list.size(); i++) {
                if (lesson_list.get(i).getLesson_day().equalsIgnoreCase(correctDay(day))) {
                    filter_lesson_list.add(lesson_list.get(i));

                }
            }
            //display filter lesson list
            showLessonList(filter_lesson_list);
        }
    }

    //filter lesson list educator wise
    public void filterLessonEducatorWise() {
        String id;
        int educator_id;
        boolean correct_educator;
        //show educator details
        educator.educatorInformation();
        System.out.print("Enter one educator id to show timetable : ");
        id = scanner.nextLine().trim();
        if (id.isEmpty() || !id.matches("\\d+")) {
            System.out.println("\n=> Error : You entered an incorrect educator id\n");
        } else {
            educator_id = Integer.parseInt(id);
            correct_educator = correctEducatorId(educator_id);
            if (!correct_educator) {
                System.out.println("\n=> Error : Educator id " + educator_id + " does not exist \n");
            } else {
                //create list to store filter lesson list
                List<AquaticTimetable> filter_lesson_list = new ArrayList<>();
                for (int i = 0; i < lesson_list.size(); i++) {
                    if (lesson_list.get(i).getEducator_id() == educator_id) {
                        filter_lesson_list.add(lesson_list.get(i));
                    }
                }
                //display filter lesson list
                showLessonList(filter_lesson_list);
            }
        }
    }

    //get lesson information by lesson id
    public AquaticTimetable getLessonInfromation(String lesson_id) {
        int index = 0;
        while (index < lesson_list.size()) {
            AquaticTimetable aquatic = lesson_list.get(index);
            if (aquatic.getLesson_id().equalsIgnoreCase(lesson_id)) {
                return aquatic;
            }
            index++;
        }
        return null;
    }

    //check correct educator id
    public boolean correctEducatorId(int educator_id) {
        for (AquaticEducator educator1 : educator.educator_list) {
            if (educator1.getEducator_id() == educator_id) {
                return true;
            }
        }
        return false;
    }

    //chcek enter day by user is correct or not
    public String correctDay(String select_day) {
        String day = null;
        for (String day_name : day_list) {
            if (day_name.equalsIgnoreCase(select_day) || day_name.substring(0, 3).equalsIgnoreCase(select_day)) {
                day = day_name;
            }
        }
        return day;
    }

}
