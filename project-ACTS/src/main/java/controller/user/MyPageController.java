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
    	// 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
		UserManager manager = UserManager.getInstance();
		HttpSession session = request.getSession();
		String loginAccountId = UserSessionUtils.getLoginUserId(session);
		User user = null;

    	
    	try {
    		user = manager.findUser(loginAccountId);	// 로그인 사용자 정보 검색
			System.out.println("로그인아이디: " + user.getAccountId());
		} catch (UserNotFoundException e) {				
	        return "redirect:/user/login";
		}	
		
    	
    	request.setAttribute("user", user);		// 사용자 정보 저장				
		return "/user/myPage.jsp";				// 사용자 보기 화면으로 이동
    }

}
