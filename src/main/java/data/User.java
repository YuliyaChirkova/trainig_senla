package data;

public class User {
    public String applicantFirstName;
    public String applicantLastName;
    public String applicantMiddleName;
    public String applicantPhoneNumber;
    public String applicantPassportNumber;
    public String citizenFirstName;
    public String citizenLastName;
    public String citizenMiddleName;
   // public String citizenBirthDate;
    public String citizenPassportNumber;
    public String citizenGender;
    public String registrationDate;
    // public String newLastName;
    public String spouseLastName;
    public String spouseFirstName;
    public String spouseMiddleName;
    public String spouseBirthDate;
    public String spousePassportNumber;



    public User(String userFirstName, String userLastName, String userMiddleName, String userPhoneNumber, String userPassportNumber) {
        this.applicantFirstName = userFirstName;
        this.applicantLastName = userLastName;
        this.applicantMiddleName = userMiddleName;
        this.applicantPhoneNumber = userPhoneNumber;
        this.applicantPassportNumber = userPassportNumber;
    }

    public String getApplicantFirstName() {
        return applicantFirstName;
    }

    public String getApplicantLastName() {
        return applicantLastName;
    }

    public String getApplicantMiddleName() {
        return applicantMiddleName;
    }

    public String getUserPhoneNumber() {
        return applicantPhoneNumber;
    }

    public String getApplicantPassportNumber() {
        return applicantPassportNumber;
    }
    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getSpouseLastName() {
        return spouseLastName;
    }

    public String getSpouseFirstName() {
        return spouseFirstName;
    }

    public String getSpouseMiddleName() {
        return spouseMiddleName;
    }

    public String getSpouseBirthDate() {
        return spouseBirthDate;
    }

    public String getSpousePassportNumber() {
        return spousePassportNumber;
    }


}
