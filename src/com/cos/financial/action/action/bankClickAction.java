package com.cos.financial.action.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.financial.action.Action;
import com.cos.financial.model.Bank;
import com.cos.financial.model.TypeList;
import com.cos.financial.repository.FinancialRepository;
import com.google.gson.Gson;

public class bankClickAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String bankname = request.getParameter("bankname");
		
		FinancialRepository financialRepository = FinancialRepository.getinstance();
		List<TypeList> typeLists = financialRepository.bankClick(bankname);
//		request.setAttribute("typeLists", typeLists);

		Gson gson = new Gson();
		String typeListJson = gson.toJson(typeLists);

		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(typeListJson);
	}
}
