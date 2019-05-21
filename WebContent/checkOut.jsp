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
		<div class="h2 text-info" style="margi-top:50px;margin-bottom:30px;" align="center">Checked Out Bill</div>
		<form action="BillingControllerServlet?command=checkOut" method="post">
			<input type="text" name="customerId" hidden="true" value="<c:out value="${customerId}"/>"/>
			<div>
				<c:forEach items="${roomIDs}" var="roomIDs">
					<input type="text" name="roomIDs" hidden="true" value="<c:out value="${roomIDs}"/>"/>
				</c:forEach>
			</div>
			<div class="container col-md-3" >
				<div class="row">
					<div class="col-sm">Total Room Fee : </div>
					<c:out value="${totalRoomFee}" />
					<input type="text" name="roomFee" hidden="true" value="<c:out value="${totalRoomFee}" />"/>
				</div>
				<div class="row">
					<div class="col-sm">Total Drink Fee : </div>
					<c:out value="${totalDrinkFee}" />
					<input type="text" name="drinkFee" hidden="true" value="<c:out value="${totalDrinkFee}" />"/>
				</div>
				<div class="row">
					<div class="col-sm">Total Food Fee : </div>
					<c:out value="${totalFoodFee}" />
					<input type="text" name="foodFee" hidden="true" value="<c:out value="${totalFoodFee}" />"/>
				</div>
				<div class="row">
					<div class="col-sm">Total Laundry Fee : </div>
					<c:out value="${totalLaundryFee}" />
					<input type="text" name="landryFee" hidden="true" value="<c:out value="${totalLaundryFee}" />"/>
				</div>
				<div class="row">
					<div class="col-sm">Total Cost : </div>
					<c:out value="${totalCost}" />
					<input type="text" name="totCost" hidden="true" value="<c:out value="${totalCost}" />"/>
				</div>
				<div class="row">
					<div class="col-sm">Payment Method : </div>
					<select name="payment">
    					<option value="cash">Cash Payment</option>
    					<option value="card">Card Payment</option>
					</select>
				</div>
				<div class="row" style="margin:10px;">
					<button class="btn btn-success"  type="submit" name="btn" value="checkOut">Check Out</button>
					<button class="btn btn-primary"  type="submit" name="btn" value="paymentMethod">Add Payment Method</button>
				</div>
			</div>
		</form>
<%-- 		<form action="BillingControllerServlet?command=redirectPayment" method="post">
					<input type="text" name="customerId" hidden="true" value="<c:out value="${customerId}"/>"/>
					<button type="submit" >Add Payment Method</button>
				</form> --%>
		<!-- <a href="/HotelManagement/paymentMethod.jsp">Add Payment Method</a> -->
	</div>
</body>
</html>