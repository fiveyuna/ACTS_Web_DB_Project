package controller.transaction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.TransComment;
import model.Transaction;
import model.service.TransCommentManager;
import model.service.TransactionManager;

public class TransactionInfoController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateTransactionController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		TransactionManager transManager = TransactionManager.getInstance();
		TransCommentManager transCommentManager = TransCommentManager.getInstance();
		
		Transaction transaction = transManager.findTransaction(Integer.parseInt(request.getParameter("transId")));
		List<TransComment> commentList = transCommentManager.findCommentListByTransId(Integer.parseInt(request.getParameter("transId")));
    	
		int transId = Integer.parseInt(request.getParameter("transId"));
		System.out.println("transId: " + transId);
		int commenterId = Integer.parseInt(request.getParameter("commenterId"));
		String commentContent = "";
		
		if( request.getParameter("comment") == null )
			commentContent="defaultContent";
		else
			commentContent = request.getParameter("comment");
		
		TransComment tc = new TransComment(transId, commenterId, commentContent);
		transCommentManager.create(tc);
		System.out.println(tc.getCommentId());
		TransComment tx = transCommentManager.findComment(tc.getCommentId());
		System.out.println(tx);
		
		System.out.println("���Ƥ���������������������������������������������comment��ü: " + transCommentManager.findComment(tc.getCommentId()));
		commentList.add(transCommentManager.findComment(tc.getCommenterId()));
		
		request.setAttribute("transaction", transaction);
		request.setAttribute("commentList", commentList);
		return "/user/transactionInfo.jsp";
	}

	private char[] typeof(int transId) {
		// TODO Auto-generated method stub
		return null;
	}

}
