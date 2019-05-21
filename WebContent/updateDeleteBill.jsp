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
		<div class="h2 text-info" style="margi-top:50px;margin-bottom:30px;" align="center">Update Delete Bill</div>
		<form action="BillingControllerServlet?command=modifyBill" method="post">
			<div class="container col-md-3" >
				<c:forEach items="${billList}" var="billList">
				<div class="row">
					<div class="col-sm">Bill ID : </div>
					<input type="text" name="billID" readonly value="<c:out value="${billList.billID}" />"/>
				</div>
				<div class="row">
					<div class="col-sm">Customer ID : </div>
					<input type="text" name="CustomerID" readonly value="<c:out value="${billList.customerID}" />"/>
				</div>
				<div class="row">
					<div class="col-sm">Total Room Fee : </div>
					<input type="text" name="roomFee" value="<c:out value="${billList.roomFee}" />"/>
				</div>
				<div class="row">
					<div class="col-sm">Total Drink Fee : </div>
					<input type="text" name="drinkFee" value="<c:out value="${billList.drinkFee}" />"/>
				</div>
				<div class="row">
					<div class="col-sm">Total Food Fee : </div>
					<input type="text" name="foodFee" value="<c:out value="${billList.foodFee}" />"/>
				</div>
				<div class="row">
					<div class="col-sm">Total Laundry Fee : </div>
					<input type="text" name="landryFee" value="<c:out value="${billList.laundryFee}" />"/>
				</div>
				<div class="row">
					<div class="col-sm">Total Cost : </div>
					<input type="text" name="totCost" value="<c:out value="${billList.total}" />"/>
				</div>
				<div class="row">
					<div class="col-sm">Payment Method : </div>
					<select name="payment" value="<c:out value="${billList.paymentMethod}" />">
    					<option value="cash">Cash Payment</option>
    					<option value="card">Card Payment</option>
					</select>
				</div>
				</c:forEach>
				<div class="row" style="margin:20px;">
					<button class="btn btn-success btn-lg"  type="submit" name="btn" value="update">Update Bill</button>
					<button class="btn btn-danger btn-lg"  type="submit" name="btn" value="delete">Delete Bill</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>