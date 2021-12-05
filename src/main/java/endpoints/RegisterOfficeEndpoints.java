package endpoints;

public class RegisterOfficeEndpoints {
    public static final String BASEURI = "https://regoffice.senla.eu/swagger";
    public static final String  CREATE_USER = "/sendUserRequest";
    public static final String  CREATE_ADMIN = "/sendAdminRequest";
    public static final String  GET_APPLICATION_STATUS = "/getApplStatus/{applicationId}";
    public static final String  GET_ALL_APPLICATIONS = "/getApplications";
    public static final String  CHANGE_STATUS_OF_APPLICATION = "/requestProcess";
}
