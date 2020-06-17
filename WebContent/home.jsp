<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"> <!--  -->
  <title>버전 1</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
<div class="row">
<div class="col-md-6">

  <h2>은행 선택</h2>
        
  <table class="table table-hover">
    <thead>
      <tr>
        <th>번호</th>
        <th>은행명</th>
      </tr>
    </thead>
    <tbody id = "table">
        <c:forEach var = "bank" items="${banks}">
      <tr>
        <td>${bank.id}</td>
        <td onclick="bankClick('${bank.bankname}')" style="cursor: pointer;">${bank.bankname}</td>	
      </tr>
      </c:forEach>
    </tbody>

  </table>
</div>


<div class="col-md-6">
  <h2>금융상품타입 선택</h2>
        
       
  <table class="table table-hover">
    <thead>
      <tr>
        <th>번호</th>
        <th>상품 타입</th>
      </tr>
    </thead>
    <tbody id = "table2">
<%--         <c:forEach var = "typeList" items="${typeLists}"> --%>
<!--       <tr> -->
<%--         <td>${typeList.id}</td> --%>
<%--         <td>${typeList.productType}</td>	 --%>
<!--       </tr> -->
<%--       </c:forEach> --%>
    </tbody>

  </table>
</div>

</div>
<div class="col-md-12">
  <h2>금융상품 상세보기</h2>
        
  <table class="table table-hover">
    <thead>
      <tr>
        <th>번호</th>
        <th>상품 이름</th>
        <th>은행 이름</th>
        <th>상품 	타입</th>
      </tr>
    </thead>
    <tbody id = "table3">
<%--         <c:forEach var = "bank" items="${banks}"> --%>
<!--       <tr> -->
<%--         <td>${bank.id}</td> --%>
<%--         <td>${bank.bankname}</td>	 --%>
<!--       </tr> -->
<%--       </c:forEach> --%>
      
    </tbody>

  </table>
  </div>
</div>

<script>
function bankClick(bankname){
	$.ajax({
		type:"get",
		url:"/financial/financial?cmd=bankClick&bankname="+bankname,
		dataType:"json"		
	}).done(function(result){
		$("#table2").empty();
		for (var typeList of result) {
			var string =
				"<tr>\r\n" + 
				"			        <td>"+typeList.id+"</td>\r\n" + 
				"			        <td onclick = \"typeClick('"+bankname+"','"+ typeList.productType+"')\" style=\"cursor: pointer; \">"+typeList.productType+"</td>\r\n" +  
				"			      </tr>";
				
				$("#table2").append(string);
				
				$("#table3").empty();
			
		}
	}).fail(function(error) {
		alert("에러야 진정해.... 살려줘....")
	});
}

function typeClick(bankname,producttype){
	$.ajax({
		type:"get",
		url:"/financial/financial?cmd=typeClick&bankname="+bankname+"&producttype="+producttype,
		dataType:"json"
	}).done(function(result){
		$("#table3").empty();
		for (var financialProduct of result) {
			var string =
				"<tr>\r\n" + 
				"			        <td>"+financialProduct.id+"</td>\r\n" + 
				"			        <td>"+financialProduct.name+"</td>\r\n" +  
				"			        <td>"+financialProduct.bankname+"</td>\r\n" +  
				"			        <td>"+financialProduct.productType+"</td>\r\n" +  
				"			      </tr>";
				
				$("#table3").append(string);
			
		}
	}).fail(function(error){
		alert("에러야 진정해.... 살려줘....")
	});
}
</script>


</body>
</html>