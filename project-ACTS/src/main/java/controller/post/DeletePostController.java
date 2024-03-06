package controller.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Post;
import model.dao.UserDAO;
import model.service.PostManager;
import model.service.UserManager;

public class DeletePostController implements Controller {

	 private static final Logger log = LoggerFactory.getLogger(DeletePostController.class);

	    @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
			String deletePostId = request.getParameter("postId");
			String deletePostWriterId = request.getParameter("writerId");
	    	log.debug("Delete Post : {}", deletePostId);

	    	PostManager manager = PostManager.getInstance();
	    	UserManager userManager = UserManager.getInstance();
			HttpSession session = request.getSession();
			UserDAO userDao = new UserDAO();
			
			String deletePostWriterAccountId = userDao.findAccountIdByUserId(deletePostWriterId);
			
			if(!UserSessionUtils.hasLogined(session)) {
				return "redirect:/user/login/form";
			}
			
			if (UserSessionUtils.isLoginUser(deletePostWriterAccountId, session)) {
				manager.remove(Integer.parseInt(deletePostId));
				return "redirect:/comm/main";
			}
			
			Post post = manager.findPost(Integer.parseInt(deletePostId));	// ����� ���� �˻�
			request.setAttribute("post", post);						
			request.setAttribute("deleteFailed", true);
			String msg = (UserSessionUtils.isLoginUser("admin", session)) 
					   ? "�ý��� ������ �Խñ��� ������ �� �����ϴ�."		
					   : "Ÿ���� ������ ������ �� �����ϴ�.";													
			request.setAttribute("exception", new IllegalStateException(msg));
	    
			return "redirect:/comm/main";
	    }
}
