package controller.post;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Post;
import model.User;
import model.service.*;
//import model.service.UserManager;

//���� ���ε带 ���� API�� ����ϱ� ����...
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;

//���� �뷮 �ʰ��� ���� Exception Ŭ������ FileUploadBase Ŭ������ Inner Ŭ������ ó��
import org.apache.commons.fileupload.servlet.*;

public class UploadPostController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UploadPostController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// ���� �� �ۼ����� writerId�� ��� ����
		String curUserId = UserSessionUtils.getLoginUserId(request.getSession());
		UserManager userManager = UserManager.getInstance();
		User user = userManager.findUser(curUserId);
//    System.out.println("user: " + user);

		File dir = null;
		
		String title = null;
		String description = null;
		String categoryId = null;
		String status = null;
		String price = null;
		String pType = null;
		String filename = null; // �̹��� ���� �̸�
		boolean check = ServletFileUpload.isMultipartContent(request);
		// ���۵� �������� ���ڵ� Ÿ���� multipart ���� ���θ� üũ�Ѵ�.
		// ���� multipart�� �ƴ϶�� ���� ������ ó������ �ʴ´�.

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
						if (item.getFieldName().equals("title"))
							title = value;
						// key ���� name�̸� name ������ ���� �����Ѵ�.
						else if (item.getFieldName().equals("description"))
							description = value;
						// key ���� id�̸� id ������ ���� �����Ѵ�.
						else if (item.getFieldName().equals("categoryId"))
							categoryId = value;
						// key ���� pw�̸� pw ������ ���� �����Ѵ�.
						else if (item.getFieldName().equals("status"))
							status = value;
						else if (item.getFieldName().equals("price"))
							price = value;
						else if (item.getFieldName().equals("pType"))
							pType = value;
					} else {// �����̶��...
						if (item.getFieldName().equals("image")) {
							// key ���� picture�̸� ���� ������ �Ѵ�.
							filename = item.getName();// ���� �̸� ȹ�� (�ڵ� �ѱ� ó�� ��)
							if (filename == null || filename.trim().length() == 0)
								continue;
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

//      Post prod = new Post(
//            request.getParameter("title"),
//            request.getParameter("description"),
//            request.getParameter("imgUrl"),
//            Integer.parseInt(request.getParameter("categoryId")),
//            request.getParameter("status"),
//            Integer.parseInt(request.getParameter("price")),
//            request.getParameter("pType"),
//            user.getUserId());
		log.debug("Create title : {}", title);
		Post prod = new Post(title, description, filename, Integer.parseInt(categoryId), status,
				Integer.parseInt(price), pType, user.getUserId());
		request.setAttribute("post", prod);

		log.debug("Create ProductForm : {}", prod);

		try {

			PostManager postManager = PostManager.getInstance();
			postManager.create(prod);

			log.debug("Create ProductForm : {}", prod);
			return "redirect:/comm/main";
		} catch (Exception e) {
			request.setAttribute("uploadFail", true);
			request.setAttribute("exception", e);
			request.setAttribute("post", prod);
			// �Խñ� �ۼ� ȭ������ �̵�
			return "/post/postForm.jsp";
		}

	}

}