package com.cos.financial.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.financial.db.DBconn;
import com.cos.financial.model.Bank;
import com.cos.financial.model.FinancialProduct;
import com.cos.financial.model.TypeList;


public class FinancialRepository {
	private static final String TAG = "FinancialRepository : ";
	private static FinancialRepository instance = new FinancialRepository();
	private FinancialRepository() {}
	public static FinancialRepository getinstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public List<Bank> home() {
		final String SQL = "select id,bankname from bank order by id";
				List<Bank> Banks = new ArrayList<Bank>();
				try {
					conn=DBconn.getConnection();
					pstmt = conn.prepareStatement(SQL);
					
					rs=pstmt.executeQuery();
					while (rs.next()) {
						Bank bank = Bank.builder()
								.id(rs.getInt(1))
								.bankname(rs.getString(2))
								.build();
						Banks.add(bank);
					}
					return Banks;
				} catch (Exception e) {
					System.out.println(TAG+"home : "+e.getMessage());
				}finally {
					DBconn.close(conn, pstmt);
				}
				return null;
	}
	public List<TypeList> bankClick(String bankname) {
		final String SQL = "select id,producttype from list where bankname=? order by id";
				List<TypeList> typeLists = new ArrayList<TypeList>();
				System.out.println("왔니?");
				try {
					conn=DBconn.getConnection();
					pstmt = conn.prepareStatement(SQL);
					pstmt.setString(1, bankname);
					
					rs=pstmt.executeQuery();
					while (rs.next()) {
						TypeList typeList = TypeList.builder()
								.id(rs.getInt(1))
								.productType(rs.getString(2))
								.build();
						typeLists.add(typeList);
					}
					return typeLists;
				} catch (Exception e) {
					System.out.println(TAG+"home : "+e.getMessage());
				}finally {
					DBconn.close(conn, pstmt);
				}
				return null;
	}
	public List<FinancialProduct> typeClick(String bankname, String producttype) {
		final String SQL = "select id,name,bankname,productType from financialProduct where bankname =? and productType = ? order by id "; //sql 바꿔야함
				List<FinancialProduct> financialProducts = new ArrayList<FinancialProduct>();
				try {
					conn=DBconn.getConnection();
					pstmt = conn.prepareStatement(SQL);
					pstmt.setString(1, bankname);
					pstmt.setString(2,producttype);
					rs=pstmt.executeQuery();
					while (rs.next()) {
						FinancialProduct financialProduct = FinancialProduct.builder()
								.id(rs.getInt(1))
								.name(rs.getString(2))
								.bankname(rs.getString(3))
								.productType(rs.getString(4))
								.build();
						financialProducts.add(financialProduct);
					}
					return financialProducts;
				} catch (Exception e) {
					System.out.println(TAG+"home : "+e.getMessage());
				}finally {
					DBconn.close(conn, pstmt);
				}
				return null;
	
}
}
