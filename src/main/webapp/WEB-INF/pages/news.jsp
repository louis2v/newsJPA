<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="inc/_header.jsp">
<jsp:param name="titlePage" value="news page"/>
</jsp:include>

<jsp:include page="inc/_head.jsp" />

<div class="singleNews">
	<div class="card" style="width: 18rem;">
	  <img src="${ news._img }" class="card-img-top" alt="...">
	  <div class="card-body">
	    <h5 class="card-title">${ news._title }</h5>
	    <p class="card-text">${ news._description }</p>
	  </div>
	</div>
</div>

<hr>

<div class="singleNews">
	<c:forEach var="comment" items="${commentaires}">
		<div class="card cardComment">
		  <h5 class="card-header">From : ${comment.id_user._pseudo }</h5>
		  <div class="card-body">
		    <p class="card-text"> ${ comment._content }</p>
		  </div>
		</div>
		<br>
	</c:forEach>
</div>

<fieldset class="singleNews">
	<legend>Laisser un commentaire</legend>
	<c:choose>
         <c:when test = "${sessionScope.logged}">
            <form action="<c:url value="/news?act=add&news=${news._id}"/>" method="POST">
				<div><textarea placeholder="laisser un commentaire ici" name="comment" rows="5" cols="50"></textarea></div>
				<input type="submit" value="poster" />
			</form>
         </c:when>
         
         <c:otherwise>
         	<p>Vous devez être connecté pour laisser un commmentaire</p>
	    	<a class="btn btn-primary" href="<c:url value="/login?act=login"/>">Login</a>
         </c:otherwise>
      </c:choose>

</fieldset>

<br>
</body>
</html>