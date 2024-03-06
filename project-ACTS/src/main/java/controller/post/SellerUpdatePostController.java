package controller.post;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.UserManager;
import model.service.PostManager;
import model.Post;
import model.User;

public class SellerUpdatePostController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(UpdatePostController.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//����� ���� �ѱ�
    	UserManager manager = UserManager.getInstance();
    	PostManager postManager = PostManager.getInstance();
    	HttpSession session = request.getSession();
    	String loginAccountId = UserSessionUtils.getLoginUserId(session);
    	User user = null;
    	Post post = null;
    	
    	String postUserNickName = null;
    	
    	File dir = null;
		
    	String postId = null;
		String title = null;
		String description = null;
		String categoryId = null;
		String views = null;
		String status = null;
		String price = null;
		String pType = null;
		String filename = null; // �̹��� ���� �̸�
		String writerId = null;
		boolean check = ServletFileUpload.isMultipartContent(request);
		// ���۵� �������� ���ڵ� Ÿ���� multipart ���� ���θ� üũ�Ѵ�.
		// ���� multipart�� �ƴ϶�� ���� ������ ó������ �ʴ´�.

    	
    	
		// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
    	
  
		if (request.getMethod().equals("GET")) {	
    		int iPostId = Integer.parseInt(request.getParameter("postId"));
    		log.debug("PostUpdateForm Request : {}, {}", loginAccountId, iPostId);
    		
    		user = manager.findUser(loginAccountId);
    		int iwriterId = user.getUserId();
    		
    		post = postManager.findPost(iPostId);
    		
			request.setAttribute("user", user);	
			request.setAttribute("post", post);
	
			if (iwriterId == post.getWriterId()) {
				 
				// ���� �α����� ����ڰ� ���� ��� ������� ��� -> ���� ����
				return "/post/postUpdateForm.jsp";   // �˻��� �Խñ� ������ post update form���� ����     
			
			}    
			
			// else ���� �Ұ����� ��� ����� ���� ȭ������ ���� �޼����� ����
			postUserNickName = postManager.getPostUserNickName( post.getWriterId());
			System.out.println("��������: " + postUserNickName);
			request.setAttribute("nickname", postUserNickName);
			request.setAttribute("postUpdateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("Ÿ���� �Խñ��� ������ �� �����ϴ�."));      
		
			return "/post/sellerPostInfo.jsp";
		}
	
		
		//requestó��, ���� ó���� ���õ� �κ�
		if (check) {// ���� ������ ���Ե� ���°� �´ٸ�

			// �Ʒ��� ���� �ϸ� Tomcat ���ο� ����� ������Ʈ�� ���� �ؿ� upload ������ ������
			ServletContext context = request.getServletContext();
			String path = context.getRealPath("/upload");
			dir = new File(path);

			// Tomcat �ܺ��� ������ �����Ϸ��� �Ʒ��� ���� ���� ��η� ���� �̸��� ������
			// File dir = new File("C:/Temp");

			if (!dir.exists())
				dir.mkdir();
			// ���۵� ������ ������ ���� ��θ� �����.

			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// ���� ���ۿ� ���� �⺻���� ���� Factory Ŭ������ �����Ѵ�.
				factory.setSizeThreshold(10 * 1024);
				// �޸𸮿� �ѹ��� ������ �������� ũ�⸦ �����Ѵ�.
				// 10kb �� �޸𸮿� �����͸� �о� ���δ�.
				factory.setRepository(dir);
				// ���۵� �������� ������ ������ �ӽ� ������ �����Ѵ�.

				ServletFileUpload upload = new ServletFileUpload(factory);
				// Factory Ŭ������ ���� ���� ���ε� �Ǵ� ������ ó���� ��ü�� �����Ѵ�.
				upload.setSizeMax(10 * 1024 * 1024);
				// ���ε� �� ������ �ִ� �뷮�� 10MB���� ���� ����Ѵ�.
				upload.setHeaderEncoding("utf-8");
				// ���ε� �Ǵ� ������ ���ڵ��� �����Ѵ�.

				List<FileItem> items = (List<FileItem>) upload.parseRequest(request);

				// upload ��ü�� ���۵Ǿ� �� ��� �����͸� Collection ��ü�� ��´�.
				for (int i = 0; i < items.size(); ++i) {
					FileItem item = (FileItem) items.get(i);
//              	commons-fileupload�� ����Ͽ� ���۹����� 
					// ��� parameter�� FileItem Ŭ������ �ϳ��� ����ȴ�.

					String value = item.getString("utf-8");
					// �Ѿ�� ���� ���� �ѱ� ó���� �Ѵ�.

					if (item.isFormField()) {// �Ϲ� �� �����Ͷ��...
						if (item.getFieldName().equals("postId"))
							postId = value;
						else if (item.getFieldName().equals("title"))
							title = value;
						else if (item.getFieldName().equals("description"))
							description = value;
						else if (item.getFieldName().equals("categoryId"))
							categoryId = value;
						else if (item.getFieldName().equals("views"))
							views = value;
						else if (item.getFieldName().equals("status"))
							status = value;
						else if (item.getFieldName().equals("price"))
							price = value;
						else if (item.getFieldName().equals("pType"))
							pType = value;
						else if (item.getFieldName().equals("writerId"))
							writerId = value;
			
					} else {// �����̶��...
						
							if (item.getFieldName().equals("image")) {
								// key ���� picture�̸� ���� ������ �Ѵ�.
								filename = item.getName();// ���� �̸� ȹ�� (�ڵ� �ѱ� ó�� ��)
								if (filename == null || filename.trim().length() == 0) {
									int iPostId = Integer.parseInt(postId);
									filename = postManager.getImgUrl(iPostId);
									System.out.println("filename: " + filename);
									continue;
								}
								// ������ ���۵Ǿ� ���� �ʾҴٸ� �ǳ� �ڴ�.
								filename = filename.substring(filename.lastIndexOf("\\") + 1);
								// ���� �̸��� ������ ��ü ��α��� �����ϱ� ������ �̸� �κи� �����ؾ� �Ѵ�.
								// ���� C:\Web_Java\aaa.gif��� �ϸ� aaa.gif�� �����ϱ� ���� �ڵ��̴�.
								File file = new File(dir, filename);
								item.write(file);
								// ������ upload ��ο� ������ �����Ѵ�.
								// FileItem ��ü�� ���� �ٷ� ��� ������ �� �ִ�.
						
					}
				}
				}

			} catch (SizeLimitExceededException e) {
				// ���ε� �Ǵ� ������ ũ�Ⱑ ������ �ִ� ũ�⸦ �ʰ��� �� �߻��ϴ� ����ó��
				e.printStackTrace();
			} catch (FileUploadException e) {
				// ���� ���ε�� ���õǾ� �߻��� �� �ִ� ���� ó��
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	 {

			Post updatePost = new Post(Integer.parseInt(postId), title, description, filename,
					Integer.parseInt(categoryId), status, Integer.parseInt(price), pType, Integer.parseInt(writerId));
			request.setAttribute("post", updatePost);

			postUserNickName = postManager.getPostUserNickName(Integer.parseInt(writerId));
			log.debug("Update Post : {}", updatePost);
			postManager.increasePostView(updatePost);
			postManager.update(updatePost);

			request.setAttribute("postId", updatePost.getPostId());
			request.setAttribute("post", updatePost);
			request.setAttribute("nickname", postUserNickName);
			return "/post/sellerPostInfo.jsp";

		}
	}
}

