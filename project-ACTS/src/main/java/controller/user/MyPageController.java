package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.User;

public class MyPageController implements Controller {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
    	
		UserManager manager = UserManager.getInstance();
		HttpSession session = request.getSession();
		String loginAccountId = UserSessionUtils.getLoginUserId(session);
		User user = null;

    	
    	try {
    		user = manager.findUser(loginAccountId);	// �α��� ����� ���� �˻�
			System.out.println("�α��ξ��̵�: " + user.getAccountId());
		} catch (UserNotFoundException e) {				
	        return "redirect:/user/login";
		}	
		
    	
    	request.setAttribute("user", user);		// ����� ���� ����				
		return "/user/myPage.jsp";				// ����� ���� ȭ������ �̵�
    }

}
