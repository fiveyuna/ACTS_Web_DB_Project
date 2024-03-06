package controller.transaction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Transaction;
import model.User;
import model.service.TransactionManager;
import model.service.UserManager;
import model.service.UserNotFoundException;

public class MySellerTransactionListController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
		// 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		System.out.println("userID" + userId);
		TransactionManager transactionMan =  TransactionManager.getInstance();
		List<Transaction> transactionList;
	
		transactionList = transactionMan.findMySellerTransactionList(userId);
		
		request.setAttribute("userId", userId);
    	request.setAttribute("transactionList", transactionList);			
		return "/user/mySellerTransaction.jsp";
    }
}
