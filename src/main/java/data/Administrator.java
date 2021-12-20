package data;

public class Administrator {

    private String personalFirstName;
    private String personalLastName;
    private String personalMiddleName;
    private String personalPhoneNumber;
    private String personalNumberOfPassport;
    private String dateofbirth;

//    public Administrator(String adminFirstName, String adminLastName, String adminMiddleName, String adminPhoneNumber, String adminPassportNumber, String dateofbirth) {
//        this.personalFirstName = adminFirstName;
//        this.personalLastName = adminLastName;
//        this.personalMiddleName = adminMiddleName;
//        this.personalPhoneNumber = adminPhoneNumber;
//        this.personalNumberOfPassport = adminPassportNumber;
//        this.dateofbirth = dateofbirth;
//    }

    public String getPersonalFirstName() {
        return personalFirstName;
    }

    public String getPersonalLastName() {
        return personalLastName;
    }

    public String getPersonalMiddleName() {
        return personalMiddleName;
    }

    public String getPersonalPhoneNumber() {
        return personalPhoneNumber;
    }

    public String getPersonalNumberOfPassport() {
        return personalNumberOfPassport;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public static class Builder {
        private Administrator newAdministrator;

        public Builder() {
            newAdministrator = new Administrator();
        }

        public Administrator.Builder withName(String name) {
            newAdministrator.personalFirstName = name;
            return this;
        }

        public Administrator.Builder withSurname(String surname) {
            newAdministrator.personalLastName = surname;
            return this;
        }

        public Administrator.Builder withMiddleName(String middleName) {
            newAdministrator.personalMiddleName = middleName;
            return this;
        }

        public Administrator.Builder withPhoneNumber(String phoneNumber) {
            newAdministrator.personalPhoneNumber = phoneNumber;
            return this;
        }

        public Administrator.Builder withPassportNumber(String passportNumber) {
            newAdministrator.personalNumberOfPassport = passportNumber;
            return this;
        }

        public Administrator.Builder withDateofbirth(String dateofbirth) {
            newAdministrator.dateofbirth = dateofbirth;
            return this;
        }
        public Administrator build(){
            return newAdministrator;
        }
    }
}
