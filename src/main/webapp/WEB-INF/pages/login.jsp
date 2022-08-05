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

<fieldset>
<legend>Connexion</legend>
<form action="<c:url value="/login?act=logout"/>" method="POST">
	<input type="text" placeholder="pseudo" name="pseudo">
	<input type="password" placeholder="password" name="password">
	<input type="submit" value="Se connecter">
</form>
<a href="<c:url value="/subscribe"/>">S'inscrire</a>
</fieldset>
</body>
</html>