<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="http://localhost:8080/HotelManagement/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="http://localhost:8080/HotelManagement/css/index.css">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="/HotelManagement/">AMA Hotel</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="/HotelManagement/">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Features</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Pricing</a>
      </li>
    </ul>
  </div>
</nav>
	<div>
		<div class="h2 text-info" align="center">Add Payment Method</div>
		<form action="BillingControllerServlet?command=paymentMethod" method="post">
			<div class="container col-md-3">
				<input type="text" hidden="true" name="billID" value="<c:out value="${billID}"/>"/>
				<div class="row">
					<div class="col-sm">Customer Id : </div>
					<input type="text" readonly name="customerId" value="<c:out value="${customerId}"/>"/>
				</div>
				<div class="row">
					<div class="col-sm">Card Name : </div>
					<select name="cardName">
    					<option value="visa">Visa</option>
    					<option value="master">Master</option>
    					<option value="amex">American Express</option>
					</select>
				</div>
				<div class="row">
					<div class="col-sm">Card Number : </div>
					<input type="number" name="cardNumber"/>
				</div>
				<div class="row">
					<div class="col-sm">Card Expiry Date : </div>
					<input type="date" name="exDate" />
				</div>
				<button class="btn btn-success" style="margin:25px;margin-left:30px;" type="submit" >Add Payment Method</button>
			</div>
		</form>
	</div>
</body>
</html>