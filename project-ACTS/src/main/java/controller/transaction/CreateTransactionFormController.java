package controller.transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Post;
import model.User;
import model.service.PostManager;
import model.service.UserManager;

public class CreateTransactionFormController implements Controller {
	
	private static final Logger log = LoggerFactory.getLogger(CreateTransactionFormController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String curUserId = UserSessionUtils.getLoginUserId(request.getSession());
		UserManager userManager = UserManager.getInstance();

		String postUserNickName = null;
		
		PostManager postManager = PostManager.getInstance();
		Post post = null;
		User user = null;
		int postId;
		
		// login check
		if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";
        }
		
		postId = Integer.parseInt(request.getParameter("postId"));
		user = userManager.findUser(curUserId);
		post = postManager.findPost(postId);
		postUserNickName = postManager.getPostUserNickName(Integer.parseInt(request.getParameter("writerId")));
		
		request.setAttribute("user", user);
		request.setAttribute("post", post);
		request.setAttribute("nickname", postUserNickName);
		
		//���߿� ����: ���� �� ���̸� �ֹ� ���ϵ���
		int iwriterId = user.getUserId();
    	if (iwriterId == post.getWriterId()) {

			request.setAttribute("trasactionCreateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("������ ���� �ֹ��� �� �����ϴ�."));  
			
			// ���� �α����� ����ڰ� �Խñ� �ۼ��� ������ ��� �ֹ��� �� ����, ����� ���� ȭ������ ���� �޼����� ����
			return "/post/sellerPostInfo.jsp";   // �˻��� �Խñ� ������ post update form���� ����     
    	}
 
    	return "/post/transactionForm.jsp";
	}

}
