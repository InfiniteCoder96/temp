<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="http://localhost:8080/HotelManagement/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="http://localhost:8080/HotelManagement/css/index.css">
</head>
<body>
<script type="text/javascript">
</script>
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
	<div class="h2 text-info" style="margin-top:30px;margin-bottom:20px;" align="center">Checked Out Bill</div>
	<form action="BillingControllerServlet?command=calTotal" method="post">
	<input type="text" name="customeId" hidden="true" value="<c:out value="${customerId}"/>"/>
		<div>
			<div class="h4 text-success" align="left" style="margin-top:20px;margin-bottom:20px;margin-left: 50px;">Room Details</div>
			<div class="container">
				<div class="row" style="border: 1px solid #4834d4;">
					<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Room ID</div>
					<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Price</div>
					<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Service charge</div>
					<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Check In Date</div>
					<div class="col-sm"></div>
				</div>
				<c:forEach items="${custRoom}" var="custRoom">
					<div class="row" style="border: 1px solid #4834d4;">
						<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">
							<c:out value="${custRoom.roomID}" />
						</div>
						<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">
							<c:out value="${custRoom.price}" />
						</div>
						<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">
							<c:out value="${custRoom.serviceChargePerDay}" />
						</div>
						<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">
							<c:out value="${custRoom.checkInDate}" />
						</div>
						<div class="form-check col-sm">
							<input class="form-check-input" type="checkbox" 
								value="<c:out value="${custRoom.roomID}" />"
							name="checkCheckOut"> <label class="form-check-label">
							Check Out </label>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div>
			<div class="h4 text-success" align="left" style="margin-top:20px;margin-bottom:20px;margin-left: 50px;">Beverage Details</div>
			<div class="container">
				<div class="row" style="border: 1px solid #4834d4;">
					<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Drink ID</div>
					<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Drink Name</div>
					<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Price</div>
					<div class="col-sm"></div>
				</div>
				<c:forEach items="${barlist}" var="barlist">
					<div class="row" style="border: 1px solid #4834d4;">
						<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">
							<c:out value="${barlist.drinkID}" />
						</div>
						<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">
							<c:out value="${barlist.drinkName}" />
						</div>
						<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">
							<c:out value="${barlist.price}" />
						</div>
						<div class="form-check col-sm">
							<input class="form-check-input" type="checkbox" value="<c:out value="${barlist.price}" />"
								name="drink"> 
								<label class="form-check-label"> Add </label>
								<label class="form-check-label">  Quantity : </label>
								<input type="number" style="width:50px" name="drinkQ">	
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div>
		<div class="h4 text-success" align="left" style="margin-top:20px;margin-bottom:20px;margin-left: 50px;">Food Details</div>
		<div class="container">
			<div class="row" style="border: 1px solid #4834d4;">
				<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Food ID</div>
				<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Food Name</div>
				<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Price</div>
				<div class="col-sm"></div>
			</div>
			<c:forEach items="${foodlist}" var="foodlist">
				<div class="row" style="border: 1px solid #4834d4;">
					<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">
						<c:out value="${foodlist.foodID}" />
					</div>
					<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">
						<c:out value="${foodlist.foodName}" />
					</div>
					<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">
						<c:out value="${foodlist.price}" />
					</div>
					<div class="form-check col-sm">
						<input class="form-check-input" type="checkbox" value="<c:out value="${foodlist.price}" />"
							name="food"> <label class="form-check-label"> Add </label>	
							<label class="form-check-label">  Quantity : </label>
							<input type="number" style="width:50px" name="foodQ">
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div>
		<div class="h4 text-success" align="left" style="margin-top:20px;margin-bottom:20px;margin-left: 50px;">Laundry Service</div>
		<div class="container">
			<div class="row"style="border: 1px solid #4834d4;">
				<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Cloth ID</div>
				<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Cloth</div>
				<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">Price</div>
				<div class="col-sm"></div>
			</div>
			<c:forEach items="${laundrylist}" var="laundrylist">
				<div class="row" style="border: 1px solid #4834d4;">
					<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">
						<c:out value="${laundrylist.clothID}" />
					</div> 
					<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">
						<c:out value="${laundrylist.cloth}" />
					</div>
					<div class="col-sm" style="border-right: 1px solid #4834d4;height:30px;">
						<c:out value="${laundrylist.price}" />
					</div>
					<div class="form-check align-items-center col-sm">
						<input class="form-check-input" name="cloth" type="checkbox" value="<c:out value="${laundrylist.price}" />"
							id="defaultCheck1"> <label class="form-check-label"> Add </label>
							<label class="form-check-label">  Quantity : </label>
							<input type="number" style="width:50px" name="clothQ">					
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
		<button class="btn btn-primary btn-lg" style="margin:50px;" type="submit" value="bill">submit</button>
	</form>
</body>
</html>