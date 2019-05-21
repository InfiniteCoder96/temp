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
	<div class="h2 text-info" style="margin-top:20px;margin-bottom:20px;" align="center">Customer Bills</div>
<!-- 	<div class="searchField" style="margin-right:200px; " >
		<form class="form-inline justify-content-end" method="get" action="customerEditBillSearch">
			<input class="form-control mr-sm-2" style="width:500px" type="search"
					placeholder="Search" name="custName">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>
	</div> -->
	<div class="col-md-12" style="margin-top:20px;">
		<div class="container ">
			<div class="row" style="border: 1px solid #4834d4;">
				<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Bill ID</div>
				<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Client ID</div>
				<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Room Fee</div>
				<div class="col-sm-2" style="border-right: 1px solid #4834d4;height:30px;">Beverages Fee</div>
				<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Food Fee</div>
				<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Laundry Fee</div>
				<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Total Cost</div>
				<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Payment</div>
				<div class="col-sm"></div>
			</div>
			<c:forEach items="${billList}" var="billList">
				<div class="row" style="border: 1px solid #4834d4;">
					<div class="col-sm" style="height:30px;border-right: 1px solid #4834d4;border-bottom: 1px solid #4834d4;">
						<c:out value="${billList.billID}" />
					</div>
					<div class="col-sm" style="height:30px;border-right: 1px solid #4834d4;border-bottom: 1px solid #4834d4;">
						<c:out value="${billList.customerID}" />
					</div>
					<div class="col-sm" style="height:30px;border-right: 1px solid #4834d4;border-bottom: 1px solid #4834d4;">
						<c:out value="${billList.roomFee}" />
					</div>
					<div class="col-sm-2" style="height:30px;border-right: 1px solid #4834d4;border-bottom: 1px solid #4834d4;">
						<c:out value="${billList.drinkFee}" />
					</div>
					<div class="col-sm" style="height:30px;border-right: 1px solid #4834d4;border-bottom: 1px solid #4834d4;">
						<c:out value="${billList.foodFee}" />
					</div>
					<div class="col-sm" style="height:30px;border-right: 1px solid #4834d4;border-bottom: 1px solid #4834d4;">
						<c:out value="${billList.laundryFee}" />
					</div>
					<div class="col-sm" style="height:30px;border-right: 1px solid #4834d4;border-bottom: 1px solid #4834d4;">
						<c:out value="${billList.total}" />
					</div>
					<div class="col-sm" style="height:30px;border-right: 1px solid #4834d4;border-bottom: 1px solid #4834d4;">
						<c:out value="${billList.paymentMethod}" />
					</div>
					<div class="col-sm ">
						<a class="" href="/HotelManagement/getBill?billid=<c:out value='${billList.billID}' />">Edit Bill</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

</body>
</html>