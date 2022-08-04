<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>
	<div class="nav">
	  <input type="checkbox" id="nav-check">
	  <div class="nav-header">
	    <div class="nav-title">
	      App News
	    </div>
	  </div>
	  
	  <div class="nav-links">
	    <a href="<c:url value="/home"/>">Home</a>
	    <a href="<c:url value="/admin"/>">Admin</a>
	    <a href="<c:url value="/login"/>">Login</a>
	  </div>
	</div>
</header>