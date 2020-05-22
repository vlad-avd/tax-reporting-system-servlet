package ua.kpi.constant;

public interface Pages {
    public static final String ROOT_FOLDER = "/WEB-INF/view/";
    public static final String GUEST_PAGES = "guest/";
    public static final String USER_PAGES = "user/";
    public static final String INSPECTOR_PAGES = "inspector/";
    public static final String ADMIN_PAGES = "admin/";

    public static final String HOME = "index.jsp";
    public static final String LOGIN = "login.jsp";
    public static final String REGISTRATION = "registration.jsp";
    public static final String REPORT_LIST = "report-list.jsp";
    public static final String REPORT = "report.jsp";
    public static final String INDIVIDUAL_PERSON_REPORT = "/create-individual-person-report.jsp";
    public static final String LEGAL_ENTITY_REPORT = "/create-legal-entity-report.jsp";
    public static final String PROFILE = "profile.jsp";
    public static final String EDIT_PROFILE = "edit-profile.jsp";

    public static final String USER_LIST = "user-list.jsp";
    public static final String EDIT_USER = "edit-user.jsp";

    public static final String REPORT_VERIFICATION_LIST = "report-verification-list.jsp";
    public static final String REPORT_VERIFICATION = "report-verification.jsp";

    public static final String HOME_PATH = "home";
    public static final String LOGIN_PATH = "login";
    public static final String REPORT_PATH = "report";
    public static final String PROFILE_PATH = "profile";
    public static final String USER_LIST_PATH = "user";

    public static final String REDIRECT = "redirect:";
    public static final String INCORRECT_PATH = "";
}
