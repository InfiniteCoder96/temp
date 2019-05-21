<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>hotel</title>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/HotelManagement/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/HotelManagement/css/index.css">
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
<img alt="" src="/HotelManagement/images/sara-dubler-584399-unsplash.jpg">
<div class="text-primary h2" style="margin-top:50px;margin-bottom:30px;" align="center">New AMA Hotel</div> 
<div class="row justify-content-center">
	<a class="btn btn-primary btn-lg" href="/HotelManagement/showCustomer">Customers</a>
	<a class="btn btn-success btn-lg" href="/HotelManagement/show"> Check Out Customer </a>
</div>

</body>
</html>