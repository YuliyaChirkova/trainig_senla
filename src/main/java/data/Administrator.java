package data;

public class Administrator {

    private String adminFirstName;
    private String adminLastName;
    private String adminMiddleName;
    private String adminPhoneNumber;
    private String adminPassportNumber;
    private String adminBirthDate;

    public Administrator(String adminFirstName, String adminLastName, String adminMiddleName, String adminPhoneNumber, String adminPassportNumber, String adminBirthDate) {
        this.adminFirstName = adminFirstName;
        this.adminLastName = adminLastName;
        this.adminMiddleName = adminMiddleName;
        this.adminPhoneNumber = adminPhoneNumber;
        this.adminPassportNumber = adminPassportNumber;
        this.adminBirthDate = adminBirthDate;
    }

    public String getAdminFirstName() { return adminFirstName;}

    public String getAdminLastName() {
        return adminLastName;
    }

    public String getAdminMiddleName() {
        return adminMiddleName;
    }

    public String getAdminPhoneNumber() {
        return adminPhoneNumber;
    }

    public String getAdminPassportNumber() {
        return adminPassportNumber;
    }

    public String getAdminBirthDate() {
        return adminBirthDate;
    }
}
