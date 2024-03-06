package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.User;

public class MyInfoController implements Controller {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	

		UserManager manager = UserManager.getInstance();
		HttpSession session = request.getSession();
		String loginAccountId = UserSessionUtils.getLoginUserId(session);
		User user = null;	
		
    
    	try {
			user = manager.findUser(loginAccountId);	// 사용자 정보 검색
		} catch (UserNotFoundException e) {				
	        return "redirect:/user/login";
		}	
		
    	request.setAttribute("user", user);		// 사용자 정보 저장				
		return "/user/myInfo.jsp";				// 사용자 보기 화면으로 이동
    }

}
