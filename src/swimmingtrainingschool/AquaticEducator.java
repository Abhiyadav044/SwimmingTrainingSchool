package swimmingtrainingschool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AquaticEducator {

    private int educator_id;
    private String educator_name;
    private String educator_email;
    private String educator_contact;
    private String educator_gender;
    private String educator_experience;
    private String educator_age;
    public List<AquaticEducator> educator_list;

    public int getEducator_id() {
        return educator_id;
    }

    public String getEducator_name() {
        return educator_name;
    }

    public String getEducator_email() {
        return educator_email;
    }

    public String getEducator_contact() {
        return educator_contact;
    }

    public String getEducator_gender() {
        return educator_gender;
    }

    public String getEducator_experience() {
        return educator_experience;
    }

    public String getEducator_age() {
        return educator_age;
    }

    public List<AquaticEducator> getEducator_list() {
        return educator_list;
    }

    public AquaticEducator(int educator_id, String educator_name, String educator_email, String educator_contact, String educator_gender, String educator_experience, String educator_age) {
        this.educator_id = educator_id;
        this.educator_name = educator_name;
        this.educator_email = educator_email;
        this.educator_contact = educator_contact;
        this.educator_gender = educator_gender;
        this.educator_experience = educator_experience;
        this.educator_age = educator_age;
    }

    public AquaticEducator() {
        educator_list = new ArrayList<>();
        savePreRegisteredEducator();
    }

    //add predefined educator information
    public void savePreRegisteredEducator() {
        Arrays.asList(
                new AquaticEducator(218541, "Thomson", "thomson@gmail.com", "041234651234", "Male", "3 yrs.", "45"),
                new AquaticEducator(218542, "Jenny", "jenny@gmail.com", "041234652234", "Female", "5 yrs.", "35"),
                new AquaticEducator(218543, "Mandy", "mandy@gmail.com", "041234653234", "Female", "2 yrs.", "37"),
                new AquaticEducator(218544, "lily", "lily@gmail.com", "041234654234", "Female", "4 yrs.", "62"),
                new AquaticEducator(218545, "Harry", "harry@gmail.com", "041234655234", "Male", "1 yrs.", "21")
        ).forEach(educator_list::add);
    }

    //show educator information
    public void educatorInformation() {
        int educator_id;
        String educator_name;
        String educator_gender;
        String educator_age;
        String educator_contact;
        String educator_email;
        String educator_experience;
        if (!educator_list.isEmpty()) {
            System.out.println("*******************************************************************************************");
            System.out.printf("%-11s * %-9s * %-7s * %-4s * %-12s * %-17s * %-11s *\n", "Educator Id", "Name", "Gender", "Age", "Contact", "Email", "Experience");
            System.out.println("*******************************************************************************************");
            for (int i = 0; i < educator_list.size(); i++) {
                educator_id = educator_list.get(i).getEducator_id();
                educator_name = educator_list.get(i).getEducator_name();
                educator_email = educator_list.get(i).getEducator_email();
                educator_gender = educator_list.get(i).getEducator_gender();
                educator_age = educator_list.get(i).getEducator_age();
                educator_experience = educator_list.get(i).getEducator_experience();
                educator_contact = educator_list.get(i).getEducator_contact();
                System.out.printf("%-11s * %-9s * %-7s * %-4s * %-12s * %-17s * %-11s *\n",
                        educator_id, educator_name, educator_gender, educator_age, educator_contact, educator_email, educator_experience);

            }
            System.out.println("*******************************************************************************************");
        }
    }

    //get educator information by educator id
    public AquaticEducator getEducatorInfromation(int educator_id) {
        int index = 0;
        while (index < educator_list.size()) {
            AquaticEducator educator = educator_list.get(index);
            if (educator.getEducator_id() == educator_id) {
                return educator;
            }
            index++;
        }
        return null;
    }

}
