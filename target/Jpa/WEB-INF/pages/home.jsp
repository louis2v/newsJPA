<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="inc/_header.jsp">
<jsp:param name="titlePage" value="home page"/>
</jsp:include>

<jsp:include page="inc/_head.jsp" />

<br>
<div class="allTheNews">
	<c:forEach var="news" items="${ news }">
		<div class="card" style="width: 18rem;">
		  <img src="${ news._img }" class="card-img-top" alt="...">
		  <div class="card-body">
		    <h5 class="card-title">${ news._title }</h5>
		    <p class="card-text">${ news._description }</p>
		  </div>
		  <a class="btn btn-success" href="<c:url value="/news?news=${news._id}"/>"> Voir en detail </a>
		</div>
	</c:forEach>
</div>
</body>
</html>