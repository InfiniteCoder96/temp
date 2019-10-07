<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard - Hi, ${sessionScope.user}</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"></head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<body>

	<nav class="navbar navbar-dark bg-dark">
	  <a class="navbar-brand" href="#">HRRS</a>
	  
	  <ul class="nav justify-content-center">
		  <li class="nav-item">
		    <a class="nav-link active" href="reservation_controller?command=ALL">BOOKINGS</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link" href="room_controller?command=ALL">ROOMS</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link" href="customer_controller?command=ALL">CUSTOMERS</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link" href="user_controller?command=ALL">SYS. USERS</a>
		  </li>
		</ul>

		<ul class="nav justify-content-end">
		  
		  <li class="nav-item">
		    <a class="nav-link" href="#">LOGOUT</a>
		  </li>
		</ul>
	</nav>

	<div class="container">
		<div class="row">
			<div class="col-md-12" >
				<div style="margin-top:30px;height:500px;overflow-y:auto;">
				
				<c:if test="${not empty ALL_BOOKINGS}">
					<table class="table table-fixed" style="height:500px;">
					  <thead class="thead-dark">
					    <tr>
					      <th scope="col">#</th>
					      <th scope="col">First</th>
					      <th scope="col">Last</th>
					      <th scope="col">Handle</th>
					    </tr>
					  </thead>
					  <tbody>
					    <tr>
					      <th scope="row">1</th>
					      <td>Mark</td>
					      <td>Otto</td>
					      <td>@mdo</td>
					    </tr>
					    
					  </tbody>
					</table>
				</c:if>
				
				<c:if test="${not empty ALL_ROOMS}">
					<table class="table table-fixed" style="height:500px;">
					  <thead class="thead-dark">
					    <tr>
					      <th scope="col">#</th>
					      <th scope="col">First</th>
					      <th scope="col">Last</th>
					      <th scope="col">Handle</th>
					    </tr>
					  </thead>
					  <tbody>
					    <tr>
					      <th scope="row">1</th>
					      <td>Mark</td>
					      <td>Otto</td>
					      <td>@mdo</td>
					    </tr>
					    
					  </tbody>
					</table>
				</c:if>
				
				<c:if test="${not empty ALL_CUSTOMERS}">
					<table class="table table-fixed" style="height:500px;">
					  <thead class="thead-dark">
					    <tr>
					      <th scope="col">#</th>
					      <th scope="col">First</th>
					      <th scope="col">Last</th>
					      <th scope="col">Handle</th>
					    </tr>
					  </thead>
					  <tbody>
					    <tr>
					      <th scope="row">1</th>
					      <td>Mark</td>
					      <td>Otto</td>
					      <td>@mdo</td>
					    </tr>
					    
					  </tbody>
					</table>
				</c:if>
				
				<c:if test="${not empty ALL_USERS}">
					<table class="table table-fixed" style="height:500px;">
					  <thead class="thead-dark">
					    <tr>
					      <th scope="col">#</th>
					      <th scope="col">First</th>
					      <th scope="col">Last</th>
					      <th scope="col">Handle</th>
					    </tr>
					  </thead>
					  <tbody>
					    <tr>
					      <th scope="row">1</th>
					      <td>Mark</td>
					      <td>Otto</td>
					      <td>@mdo</td>
					    </tr>
					    
					  </tbody>
					</table>
				</c:if>
				
				
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>