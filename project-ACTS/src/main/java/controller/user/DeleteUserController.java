package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.User;
import model.service.UserManager;

public class DeleteUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String deleteId = request.getParameter("accountId");
    	log.debug("Delete User : {}", deleteId);

    	UserManager manager = UserManager.getInstance();		
		HttpSession session = request.getSession();	
		
		System.out.println((UserSessionUtils.isLoginUser("admin", session) && !deleteId.equals("admin")));
		System.out.println(!UserSessionUtils.isLoginUser("admin", session) && UserSessionUtils.isLoginUser(deleteId, session));
	
		if ((UserSessionUtils.isLoginUser("admin", session) && 	// �α����� ����ڰ� �������̰� 	
			 !deleteId.equals("admin"))							// ���� ����� �Ϲ� ������� ���, 
			   || 												// �Ǵ� 
			(!UserSessionUtils.isLoginUser("admin", session) &&  // �α����� ����ڰ� �����ڰ� �ƴϰ� 
			  UserSessionUtils.isLoginUser(deleteId, session))) { // �α����� ����ڰ� ���� ����� ��� (�ڱ� �ڽ��� ����)
			
			System.out.println("���� ������ �Ǿ��׿�!");
			manager.remove(deleteId);				// ����� ���� ����
			System.out.println("���� ������ �Ǿ��׿�!");
			if (UserSessionUtils.isLoginUser("admin", session))	// �α����� ����ڰ� ������ 	
				return "redirect:/comm/main";		// ����� ����Ʈ�� �̵�
			else 									// �α����� ����ڴ� �̹� ������
				return "redirect:/user/logout";		// logout ó��
		}
		
		/* ������ �Ұ����� ��� */
		User user = manager.findUser(deleteId);	// ����� ���� �˻�
		request.setAttribute("user", user);						
		request.setAttribute("deleteFailed", true);
		String msg = (UserSessionUtils.isLoginUser("admin", session)) 
				   ? "�ý��� ������ ������ ������ �� �����ϴ�."		
				   : "Ÿ���� ������ ������ �� �����ϴ�.";													
		request.setAttribute("exception", new IllegalStateException(msg));            
		return "/comm/main.jsp";		// ����� ���� ȭ������ �̵� (forwarding)	
	}
}
