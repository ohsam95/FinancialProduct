package com.cos.financial.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.financial.action.Action;
import com.cos.financial.action.action.bankClickAction;
import com.cos.financial.action.action.homeAction;
import com.cos.financial.action.action.typeClickAction;

@WebServlet("/financial")
public class FinancialController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FinancialController() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
		
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String cmd = request.getParameter("cmd");
		Action action = router(cmd);
		action.execute(request, response);
	}
	public Action router(String cmd) {
		if (cmd.equals("home")) {
			return new homeAction();
		}else if (cmd.equals("bankClick")) {
			return new bankClickAction();
		}else if (cmd.equals("typeClick")) {
			return new typeClickAction();
		}
		return null;
	}
	
	
}
