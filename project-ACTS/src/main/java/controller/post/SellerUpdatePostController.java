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
		//사용자 정보 넘김
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
		String filename = null; // 이미지 파일 이름
		String writerId = null;
		boolean check = ServletFileUpload.isMultipartContent(request);
		// 전송된 데이터의 인코드 타입이 multipart 인지 여부를 체크한다.
		// 만약 multipart가 아니라면 파일 전송을 처리하지 않는다.

    	
    	
		// 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
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
				 
				// 현재 로그인한 사용자가 수정 대상 사용자인 경우 -> 수정 가능
				return "/post/postUpdateForm.jsp";   // 검색한 게시글 정보를 post update form으로 전송     
			
			}    
			
			// else 수정 불가능한 경우 사용자 보기 화면으로 오류 메세지를 전달
			postUserNickName = postManager.getPostUserNickName( post.getWriterId());
			System.out.println("수정실패: " + postUserNickName);
			request.setAttribute("nickname", postUserNickName);
			request.setAttribute("postUpdateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("타인의 게시글은 수정할 수 없습니다."));      
		
			return "/post/sellerPostInfo.jsp";
		}
	
		
		//request처리, 파일 처리와 관련된 부분
		if (check) {// 파일 전송이 포함된 상태가 맞다면

			// 아래와 같이 하면 Tomcat 내부에 복사된 프로젝트의 폴더 밑에 upload 폴더가 생성됨
			ServletContext context = request.getServletContext();
			String path = context.getRealPath("/upload");
			dir = new File(path);

			// Tomcat 외부의 폴더에 저장하려면 아래와 같이 절대 경로로 폴더 이름을 지정함
			// File dir = new File("C:/Temp");

			if (!dir.exists())
				dir.mkdir();
			// 전송된 파일을 저장할 실제 경로를 만든다.

			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// 파일 전송에 대한 기본적인 설정 Factory 클래스를 생성한다.
				factory.setSizeThreshold(10 * 1024);
				// 메모리에 한번에 저장할 데이터의 크기를 설정한다.
				// 10kb 씩 메모리에 데이터를 읽어 들인다.
				factory.setRepository(dir);
				// 전송된 데이터의 내용을 저장할 임시 폴더를 지정한다.

				ServletFileUpload upload = new ServletFileUpload(factory);
				// Factory 클래스를 통해 실제 업로드 되는 내용을 처리할 객체를 선언한다.
				upload.setSizeMax(10 * 1024 * 1024);
				// 업로드 될 파일의 최대 용량을 10MB까지 전송 허용한다.
				upload.setHeaderEncoding("utf-8");
				// 업로드 되는 내용의 인코딩을 설정한다.

				List<FileItem> items = (List<FileItem>) upload.parseRequest(request);

				// upload 객체에 전송되어 온 모든 데이터를 Collection 객체에 담는다.
				for (int i = 0; i < items.size(); ++i) {
					FileItem item = (FileItem) items.get(i);
//              	commons-fileupload를 사용하여 전송받으면 
					// 모든 parameter는 FileItem 클래스에 하나씩 저장된다.

					String value = item.getString("utf-8");
					// 넘어온 값에 대한 한글 처리를 한다.

					if (item.isFormField()) {// 일반 폼 데이터라면...
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
			
					} else {// 파일이라면...
						
							if (item.getFieldName().equals("image")) {
								// key 값이 picture이면 파일 저장을 한다.
								filename = item.getName();// 파일 이름 획득 (자동 한글 처리 됨)
								if (filename == null || filename.trim().length() == 0) {
									int iPostId = Integer.parseInt(postId);
									filename = postManager.getImgUrl(iPostId);
									System.out.println("filename: " + filename);
									continue;
								}
								// 파일이 전송되어 오지 않았다면 건너 뛴다.
								filename = filename.substring(filename.lastIndexOf("\\") + 1);
								// 파일 이름이 파일의 전체 경로까지 포함하기 때문에 이름 부분만 추출해야 한다.
								// 실제 C:\Web_Java\aaa.gif라고 하면 aaa.gif만 추출하기 위한 코드이다.
								File file = new File(dir, filename);
								item.write(file);
								// 파일을 upload 경로에 실제로 저장한다.
								// FileItem 객체를 통해 바로 출력 저장할 수 있다.
						
					}
				}
				}

			} catch (SizeLimitExceededException e) {
				// 업로드 되는 파일의 크기가 지정된 최대 크기를 초과할 때 발생하는 예외처리
				e.printStackTrace();
			} catch (FileUploadException e) {
				// 파일 업로드와 관련되어 발생할 수 있는 예외 처리
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

