package controller.user;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    public static final String USER_SESSION_KEY = "accountId";

    /* ���� �α����� ������� ID�� ���� */
    public static String getLoginUserId(HttpSession session) {
        String accountId = (String)session.getAttribute(USER_SESSION_KEY);
     
        return accountId;
    }

    /* �α����� ���������� �˻� */
    public static boolean hasLogined(HttpSession session) {
        if (getLoginUserId(session) != null) {
            return true;
        }
        return false;
    }

    /* ���� �α����� ������� ID�� userId���� �˻� */
    public static boolean isLoginUser(String accountId, HttpSession session) {
        String loginUser = getLoginUserId(session);
        System.out.println(loginUser);
        if (loginUser == null) {
            return false;
        }
        return loginUser.equals(accountId);
    }
}
