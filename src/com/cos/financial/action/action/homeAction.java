package com.cos.financial.action.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.financial.action.Action;
import com.cos.financial.model.Bank;
import com.cos.financial.repository.FinancialRepository;

public class homeAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		FinancialRepository financialRepository = FinancialRepository.getinstance();
		List<Bank> banks = financialRepository.home();
		request.setAttribute("banks", banks);
		
		RequestDispatcher dis = request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);
		
		
	}
}
