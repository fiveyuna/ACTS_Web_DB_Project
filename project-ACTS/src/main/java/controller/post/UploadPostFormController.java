package controller.post;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;

public class UploadPostFormController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
    	
    	/*
    	String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}		
    	*/
    	
//		UserManager manager = UserManager.getInstance();
//		List<User> userList = manager.findUserList();
//		// List<User> userList = manager.findUserList(currentPage, countPerPage);

		// userList ��ü�� ���� �α����� ����� ID�� request�� �����Ͽ� ����
//		request.setAttribute("userList", userList);				
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));		
		System.out.println("����α����� ���̵�:" + UserSessionUtils.getLoginUserId(request.getSession()));
		System.out.println("��������α����� ���̵�:" + request.getParameter("curUserId"));
		//�Խñ� �ۼ� ȭ������ �̵�
		return "/post/postForm.jsp";
    }
		
}
