<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../include/head2.jsp" %>
<meta charset="UTF-8">
<title>ErrorPage</title>
</head>
<body>
	<!-- 
	Failed URL: ${url} Exception: ${exception.message} 
	<c:forEach items="${exception.stackTrace}" var="ste">
	${ste}
	</c:forEach> 
	-->
	작업 중 오류가 발생했습니다.
</body>
</html>