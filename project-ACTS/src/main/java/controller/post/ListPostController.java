package controller.post;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Post;
import model.User;
import model.service.PostManager;

public class ListPostController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {		
    	
		PostManager manager = PostManager.getInstance();
		request.setAttribute("postList", null);
		List<Post> postList = manager.findPostList();
		
		System.out.println(postList);
		
		// List<User> userList = manager.findUserList(currentPage, countPerPage);

		// userList 객체와 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("postList", postList);				
//		request.setAttribute("curUserId", 
//				UserSessionUtils.getLoginUserId(request.getSession()));		

		// 사용자 리스트 화면으로 이동(forwarding)
		return "/comm/main.jsp";        
    }
}
