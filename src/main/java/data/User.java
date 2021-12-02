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
    public String citizenBirthDate;
    public String citizenPassportNumber;
    public String citizenGender;
    public String marriageRegistrationDate;



    public String spouseNewLastName;
    public String spouseLastName;
    public String spouseFirstName;
    public String spouseMiddleName;
    public String spouseBirthDate;
    public String spousePassportNumber;

    public String birthPlace;
    public String motherName;
    public String fatherName;
    public String deathPlace;

    public String getDeathPlace() {
        return deathPlace;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public String deathDate;
    public String getBirthPlace() {
        return birthPlace;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public User(String marriageRegistrationDate, String spouseNewLastName, String spouseLastName, String spouseFirstName, String spouseMiddleName, String spouseBirthDate, String spousePassportNumber, String birthPlace, String motherName, String fatherName, String deathPlace, String deathDate) {
        this.marriageRegistrationDate = marriageRegistrationDate;
        this.spouseNewLastName = spouseNewLastName;
        this.spouseLastName = spouseLastName;
        this.spouseFirstName = spouseFirstName;
        this.spouseMiddleName = spouseMiddleName;
        this.spouseBirthDate = spouseBirthDate;
        this.spousePassportNumber = spousePassportNumber;
        this.birthPlace = birthPlace;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.deathPlace = deathPlace;
        this.deathDate = deathDate;
    }

    /*public User(String marriageRegistrationDate, String spouseNewLastName, String spouseLastName, String spouseFirstName, String spouseMiddleName, String spouseBirthDate, String spousePassportNumber) {
        this.marriageRegistrationDate = marriageRegistrationDate;
        this.spouseNewLastName = spouseNewLastName;
        this.spouseLastName = spouseLastName;
        this.spouseFirstName = spouseFirstName;
        this.spouseMiddleName = spouseMiddleName;
        this.spouseBirthDate = spouseBirthDate;
        this.spousePassportNumber = spousePassportNumber;
    }*/

    public User(String citizenFirstName, String citizenLastName, String citizenMiddleName, String citizenBirthDate, String citizenPassportNumber, String citizenGender) {
        this.citizenFirstName = citizenFirstName;
        this.citizenLastName = citizenLastName;
        this.citizenMiddleName = citizenMiddleName;
        this.citizenBirthDate = citizenBirthDate;
        this.citizenPassportNumber = citizenPassportNumber;
        this.citizenGender = citizenGender;
    }

    public User(String userFirstName, String userLastName, String userMiddleName, String userPhoneNumber, String userPassportNumber) {
        this.applicantFirstName = userFirstName;
        this.applicantLastName = userLastName;
        this.applicantMiddleName = userMiddleName;
        this.applicantPhoneNumber = userPhoneNumber;
        this.applicantPassportNumber = userPassportNumber;
    }

    public String getSpouseNewLastName() {
        return spouseNewLastName;
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

    public String getCitizenFirstName() {
        return citizenFirstName;
    }

    public String getCitizenLastName() {
        return citizenLastName;
    }

    public String getCitizenMiddleName() {
        return citizenMiddleName;
    }

    public String getCitizenBirthDate() {
        return citizenBirthDate;
    }

    public String getCitizenPassportNumber() {
        return citizenPassportNumber;
    }

    public String getCitizenGender() {
        return citizenGender;
    }

    public String getMarriageRegistrationDate() {
        return marriageRegistrationDate;
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
