<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/ressources/style.css" />">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<jsp:include page="inc/_head.jsp" />

<br>

<c:forEach var="news" items="${ news }">
	<div class="card" style="width: 18rem;">
	  <img src="${ news._img }" class="card-img-top" alt="...">
	  <div class="card-body">
	    <h5 class="card-title">${ news._title }</h5>
	    <p class="card-text">${ news._description }</p>
	  </div>
	</div>
</c:forEach>
</body>
</html>