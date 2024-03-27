<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>scope 생명주기 확인</title>
</head>
<body>
	<h3>page : ${pageScope.str}</h3> <%-- scope.jsp 에서 생명주기 끝나서 안나옴 --%>
	
	<h3>request : ${requestScope.str}</h3> 
	<%-- (요청받은 Servlet)ScopeController, (위임받은)scope.jsp 에서 생명주기 끝나서 안나옴 --%>
	
	<h3>session : ${sessionScope.str}</h3>
	<%-- 브라우저/세션 종료 시 까지 유지 --%>
	
	<h3>application : ${applicationScope.str}</h3>
	<%-- 서버가 켜져있는 동안 유지 --%>
</body>
</html>