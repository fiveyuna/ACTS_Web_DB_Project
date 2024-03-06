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
		
		//나중에 구현: 내가 쓴 글이면 주문 못하도록
		int iwriterId = user.getUserId();
    	if (iwriterId == post.getWriterId()) {

			request.setAttribute("trasactionCreateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("본인의 글은 주문할 수 없습니다."));  
			
			// 현재 로그인한 사용자가 게시글 작성자 본인인 경우 주문할 수 없음, 사용자 보기 화면으로 오류 메세지를 전달
			return "/post/sellerPostInfo.jsp";   // 검색한 게시글 정보를 post update form으로 전송     
    	}
 
    	return "/post/transactionForm.jsp";
	}

}
