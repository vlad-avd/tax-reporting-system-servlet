package ua.kpi.util;

import ua.kpi.model.enums.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionChecker {
    private Map rolePages = new HashMap<>();

    public PermissionChecker() {
        rolePages.put(Role.ROLE_GUEST, Arrays.asList("home", "registration", "login"));
        rolePages.put(Role.ROLE_USER, Arrays.asList("home", "registration", "login", "report.*", "profile.*", "logout"));
        rolePages.put(Role.ROLE_INSPECTOR, Arrays.asList("home", "registration", "login", "report.*", "profile.*", "logout", "report-verification.*"));
        rolePages.put(Role.ROLE_ADMIN, Arrays.asList("home", "registration", "login", "report.*", "profile.*", "logout", "report-verification.*", "user.*", "reports.*"));
    }

    public boolean checkPermission(HttpServletRequest request, Role role){
        String path = request.getRequestURI().replaceFirst(".*/tax-reporting-system/", "");
        List<String> pages = (List<String>) rolePages.get(role);

        return pages.stream()
                .anyMatch(path::matches);
    }
}
