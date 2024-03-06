package controller.post;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UpdateUserController;
import model.Post;
import model.service.PostManager;

public class SearchCategoryController implements Controller  {
	private static final Logger log = LoggerFactory.getLogger(SearchCategoryController.class);

	@Override
	    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
			
			String categoryName = request.getParameter("categoryName");
		
			log.debug("SearchCategoryController Request : {}", categoryName);
			
		
			PostManager manager = PostManager.getInstance();
			List<Post> postList = manager.findPostListUseCategory(categoryName);
			
			// postList 媛앹껜瑜� request�뿉 ���옣�븯�뿬 寃뚯떆湲� 由ъ뒪�듃 �솕硫댁쑝濡� �씠�룞(forwarding)
			request.setAttribute("postList", postList);
			return "/post/searchPostList.jsp";
	}
}
