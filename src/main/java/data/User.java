package data;

public class User {

    private String personalFirstName;
    private String personalLastName;
    private String personalMiddleName;
    private String personalPhoneNumber;
    private String personalNumberOfPassport;
    private String citizenFirstName;
    private String citizenLastName;
    private String citizenMiddleName;
    private String citizenBirthDate;
    private String citizenNumberOfPassport;
    private String citizenGender;

    private String dateOfMarriage;
    private String newLastName;
    private String anotherPersonLastName;
    private String anotherPersonFirstName;
    private String anotherPersonMiddleName;
    private String birth_of_anotoherPerson;
    private String anotherPersonPassport;

    private String birth_place;
    private String birth_mother;
    private String birth_father;

    private String death_dateOfDeath;
    private String death_placeOfDeath;
    private String mode;

    public String getPersonalPhoneNumber() {
        return personalPhoneNumber;
    }

    public String getMode() {
        return mode;
    }

    public String getDeath_dateOfDeath() {
        return death_dateOfDeath;
    }

    public String getDeath_placeOfDeath() {
        return death_placeOfDeath;
    }

    public String getBirth_place() {
        return birth_place;
    }

    public String getBirth_mother() {
        return birth_mother;
    }

    public String getBirth_father() {
        return birth_father;
    }

    public String getNewLastName() {
        return newLastName;
    }

    public String getPersonalFirstName() {
        return personalFirstName;
    }

    public String getPersonalLastName() {
        return personalLastName;
    }

    public String getPersonalMiddleName() {
        return personalMiddleName;
    }

    public String getPersonalNumberOfPassport() {
        return personalNumberOfPassport;
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

    public String getCitizenNumberOfPassport() {
        return citizenNumberOfPassport;
    }

    public String getCitizenGender() {
        return citizenGender;
    }

    public String getDateOfMarriage() {
        return dateOfMarriage;
    }

    public String getAnotherPersonLastName() {
        return anotherPersonLastName;
    }

    public String getAnotherPersonFirstName() {
        return anotherPersonFirstName;
    }

    public String getAnotherPersonMiddleName() {
        return anotherPersonMiddleName;
    }

    public String getBirth_of_anotoherPerson() {
        return birth_of_anotoherPerson;
    }

    public String getAnotherPersonPassport() {
        return anotherPersonPassport;
    }

    public static class Builder {
        private User newUser;

        public Builder() {
            newUser = new User();
        }

        public Builder withName(String name) {
            newUser.personalFirstName = name;
            return this;
        }

        public Builder withSurname(String surname) {
            newUser.personalLastName = surname;
            return this;
        }

        public Builder withMiddleName(String middleName) {
            newUser.personalMiddleName = middleName;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            newUser.personalPhoneNumber = phoneNumber;
            return this;
        }

        public Builder withPassportNumber(String passportNumber) {
            newUser.personalNumberOfPassport = passportNumber;
            return this;
        }

        public Builder withCitizenFirstName(String citizenFirstName) {
            newUser.citizenFirstName = citizenFirstName;
            return this;
        }

        public Builder withCitizenLastName(String citizenLastName) {
            newUser.citizenLastName = citizenLastName;
            return this;
        }

        public Builder withCitizenMiddleName(String citizenMiddleName) {
            newUser.citizenMiddleName = citizenMiddleName;
            return this;
        }

        public Builder withCitizenBirthDate(String citizenBirthDate) {
            newUser.citizenBirthDate = citizenBirthDate;
            return this;
        }

        public Builder withCitizenPassportNumber(String citizenPassportNumber) {
            newUser.citizenNumberOfPassport = citizenPassportNumber;
            return this;
        }

        public Builder withCitizenGender(String citizenGender) {
            newUser.citizenGender = citizenGender;
            return this;
        }

        public Builder withMarriageRegistrationDate(String marriageRegistrationDate) {
            newUser.dateOfMarriage = marriageRegistrationDate;
            return this;
        }

        public Builder withSpouseNewLastName(String spouseNewLastName) {
            newUser.newLastName = spouseNewLastName;
            return this;
        }

        public Builder withSpouseLastName(String spouseLastName) {
            newUser.anotherPersonLastName = spouseLastName;
            return this;
        }

        public Builder withSpouseFirstName(String spouseFirstName) {
            newUser.anotherPersonFirstName = spouseFirstName;
            return this;
        }

        public Builder withSpouseMiddleName(String spouseMiddleName) {
            newUser.anotherPersonMiddleName = spouseMiddleName;
            return this;
        }

        public Builder withSpouseBirthDate(String spouseBirthDate) {
            newUser.birth_of_anotoherPerson = spouseBirthDate;
            return this;
        }

        public Builder withSpousePassportNumber(String spousePassportNumber) {
            newUser.anotherPersonPassport = spousePassportNumber;
            return this;
        }

        public Builder withBirthPlace(String birthPlace) {
            newUser.birth_place = birthPlace;
            return this;
        }

        public Builder withMotherName(String motherName) {
            newUser.birth_mother = motherName;
            return this;
        }

        public Builder withFatherName(String fatherName) {
            newUser.birth_father = fatherName;
            return this;
        }

        public Builder withDeathPlace(String deathPlace) {
            newUser.death_dateOfDeath = deathPlace;
            return this;
        }

        public Builder withDeathDate(String deathDate) {
            newUser.death_placeOfDeath = deathDate;
            return this;
        }

        public Builder withMode(String mode) {
            newUser.mode = mode;
            return this;
        }

        public User build() {
            return newUser;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "applicantFirstName='" + personalFirstName + '\'' +
                ", applicantLastName='" + personalLastName + '\'' +
                ", applicantMiddleName='" + personalMiddleName + '\'' +
                ", applicantPhoneNumber='" + personalPhoneNumber + '\'' +
                ", applicantPassportNumber='" + personalNumberOfPassport + '\'' +
                ", citizenFirstName='" + citizenFirstName + '\'' +
                ", citizenLastName='" + citizenLastName + '\'' +
                ", citizenMiddleName='" + citizenMiddleName + '\'' +
                ", citizenBirthDate='" + citizenBirthDate + '\'' +
                ", citizenPassportNumber='" + citizenNumberOfPassport + '\'' +
                ", citizenGender='" + citizenGender + '\'' +
                ", marriageRegistrationDate='" + dateOfMarriage + '\'' +
                ", spouseNewLastName='" + newLastName + '\'' +
                ", spouseLastName='" + anotherPersonLastName + '\'' +
                ", spouseFirstName='" + anotherPersonFirstName + '\'' +
                ", spouseMiddleName='" + anotherPersonMiddleName + '\'' +
                ", spouseBirthDate='" + birth_of_anotoherPerson + '\'' +
                ", spousePassportNumber='" + anotherPersonPassport + '\'' +
                ", birthPlace='" + birth_place + '\'' +
                ", motherName='" + birth_mother + '\'' +
                ", fatherName='" + birth_father + '\'' +
                ", deathPlace='" + death_dateOfDeath + '\'' +
                ", deathDate='" + death_placeOfDeath + '\'' +
                ", mode='" + mode + '\'' +
                '}';
    }
}
