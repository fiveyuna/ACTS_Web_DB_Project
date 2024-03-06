package controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.RegisterUserController;
import controller.user.UserSessionUtils;
import model.Post;
import model.Review;
import model.User;
import model.service.ExistingUserException;
import model.service.PostManager;
import model.service.ReviewManager;
import model.service.UserManager;

public class UploadReviewController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UploadReviewController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		// Session user object
		String curUserId = UserSessionUtils.getLoginUserId(request.getSession());
		UserManager userManager = UserManager.getInstance();
		User user = userManager.findUser(curUserId);
		
		// get parameter
		PostManager postManager = PostManager.getInstance();
		int postId = Integer.parseInt(request.getParameter("postId"));
		Post post = postManager.findPost(postId);
		
		// create review
		Review review = new Review(
				request.getParameter("content"),
				Integer.parseInt(request.getParameter("score")),
				user.getUserId(),
				post.getPostId());
		
		log.debug("Create Review : {}", review);
		
		ReviewManager manager = ReviewManager.getInstance();
		manager.create(review);
		
		ReviewManager reviewManager = ReviewManager.getInstance();
		List<Review> reviewList = reviewManager.findReviewList(post.getPostId());
		
//		request.setAttribute("postId", postId);	
//		request.setAttribute("post", post);
//		request.setAttribute("nickname", postManager.getPostUserNickName(post.getWriterId()));
//		request.setAttribute("reviewList", reviewList);
		
        return "redirect:/comm/main";	// Move to post info
		
//		try {
//			
//	        
//		} catch (ExistingUserException e) {	// �삁�쇅 諛쒖깮 �떆 �쉶�썝媛��엯 form�쑝濡� forwarding
//            request.setAttribute("registerFailed", true);
//			request.setAttribute("exception", e);
//			request.setAttribute("user", user);
//			return "/user/registerForm.jsp";
//		}
	}

}
