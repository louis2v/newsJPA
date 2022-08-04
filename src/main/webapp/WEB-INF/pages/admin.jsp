<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/ressources/style.css" />">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="inc/_head.jsp" />

<fieldset>
<legend>Ajouter une news</legend>
<form action="<c:url value="/admin?act=add"/>" method="POST">
	<input type="text" placeholder="titre" name="title">
	<input type="text" placeholder="description" name="description">
	<input type="text" placeholder="image" name="img">
	<input type="submit" value="ajouter">
</form>
</fieldset>

<br>

<h2>Liste des articles </h2>
<c:forEach var="news" items="${ news }" varStatus="status">
<ul>
	<li>${status.count } -</li>
	<li>${ news._title } |</li>
	<li>${ fn:substring(news._description, 0, 40) } ...  |</li>
	<li>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editNews">
		  éditer
		</button>
	</li>
	<li>
		<form action="<c:url value="/admin?act=delete&id=${news._id}"/>" method="POST">
			<input type="submit" value="supprimer" class="btn btn-primary">
		</form>
	</li>
</ul>

<div class="modal fade" id="editNews" tabindex="-1" aria-labelledby="editNewsLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="editNewsLabel">Editer ma news</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="<c:url value="/admin?act=edit&id=${news._id}"/>" method="POST">
		    <input type="text" placeholder="titre" name="title" value="${news._title }"><br>
			<input type="text" placeholder="description" name="description" value="${ news._description}"><br>
			<input type="text" placeholder="image" name="img" value="${news._img }"><br>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-bs-dismiss="modal">Close</button>
				<input type="submit" value="éditer" class="btn btn-primary">
			</div>
		</form>
      </div>
    </div>
  </div>
</div>

</c:forEach>

<script>
$(document).on("click", ".open-AddBookDialog", function () {
    var myBookId = $(this).data('id');
    $(".modal-body #bookId").val( myBookId );
    // As pointed out in comments, 
    // it is unnecessary to have to manually call the modal.
    // $('#addBookDialog').modal('show');
});
</script>
</body>
</html>