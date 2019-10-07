<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>Reservation</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">
<!--===============================================================================================-->
	<link rel="icon" type="image/png" href="images/icons/favicon.png"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/themify/themify-icons.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/slick/slick.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/lightbox2/css/lightbox.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body class="animsition">
	<c:if test="${sessionScope.AVAIL_ROOM_IDS == null}">
		<c:redirect url="reservation.jsp"></c:redirect>
	</c:if>
	<!-- Header -->
	<header>
		<!-- Header desktop -->
		<div class="wrap-menu-header gradient1 trans-0-4">
			<div class="wrap_header trans-0-3">
					<!-- Logo -->
					

					<!-- Menu -->
					<div class="wrap_menu p-l-45 p-l-0-xl">
						<nav class="menu">
							<ul class="main_menu">
										<li>
											<a href="index.html">Home</a>
										</li>
										<li>
											<a href="reservation.jsp">Reservation</a>
										</li>
		
										<li>
											<a href="gallery.html">Gallery</a>
										</li>
		
										<li>
											<a href="about.html">About</a>
										</li>
										<li>
											<a href="contact.html">Contact</a>
										</li>
							<c:choose>
								<c:when test="${not empty sessionScope.user}">
									<c:if test="${sessionScope.user eq 'admin'}">
										<li class="dropdown">
									        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
									        	<button class=" btn btn-sm  btn-danger"
														>Hi, ${sessionScope.user}</button><span class="caret"></span>
									        </a>
									        <ul class="dropdown-menu">
									          <li><a href="adminDashboard.jsp">dashboard</a></li>
									          <li><a href="logout.jsp">logout</a></li>
									        </ul>
									     </li>
									</c:if>
									<c:if test="${sessionScope.user != 'admin'}">
										<li class="dropdown">
									        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
									        	<button class=" btn btn-sm  btn-danger"
														>Hi, ${sessionScope.user}</button><span class="caret"></span>
									        </a>
									        <ul class="dropdown-menu">
									          <li><a href="logout.jsp">logout</a></li>
									        </ul>
									    </li>
									</c:if>
								</c:when>
								<c:otherwise>
										<li>
											<a href="login.jsp">Sign In</a>
										</li>
								</c:otherwise>
							</c:choose>
							</ul>
						</nav>
					</div>

					<!-- Social -->
					<div class="social flex-w flex-l-m p-r-20">
						<a href="#"><i class="fa fa-tripadvisor" aria-hidden="true"></i></a>
						<a href="#"><i class="fa fa-facebook m-l-21" aria-hidden="true"></i></a>
						<a href="#"><i class="fa fa-twitter m-l-21" aria-hidden="true"></i></a>

						<button class="btn-show-sidebar m-l-33 trans-0-4"></button>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Sidebar -->
	<aside class="sidebar trans-0-4">
		<!-- Button Hide sidebar -->
		<button class="btn-hide-sidebar ti-close color0-hov trans-0-4"></button>

		<!-- - -->
		<ul class="menu-sidebar p-t-95 p-b-70">
			<li class="t-center m-b-13">
				<a href="index.html" class="txt19">Home</a>
			</li>

			<li class="t-center m-b-13">
				<a href="menu.html" class="txt19">Menu</a>
			</li>

			<li class="t-center m-b-13">
				<a href="gallery.html" class="txt19">Gallery</a>
			</li>

			<li class="t-center m-b-13">
				<a href="about.html" class="txt19">About</a>
			</li>

			<li class="t-center m-b-13">
				<a href="blog.html" class="txt19">Blog</a>
			</li>

			<li class="t-center m-b-33">
				<a href="contact.html" class="txt19">Contact</a>
			</li>

			<li class="t-center">
				<!-- Button3 -->
				<a href="reservation.jsp" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					Reservation
				</a>
			</li>
		</ul>

		<!-- - -->
		<div class="gallery-sidebar t-center p-l-60 p-r-60 p-b-40">
			<!-- - -->
			<h4 class="txt20 m-b-33">
				Gallery
			</h4>

			<!-- Gallery -->
			<div class="wrap-gallery-sidebar flex-w">
				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-01.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-01.jpg" alt="GALLERY">
				</a>

				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-02.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-02.jpg" alt="GALLERY">
				</a>

				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-03.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-03.jpg" alt="GALLERY">
				</a>

				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-05.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-05.jpg" alt="GALLERY">
				</a>

				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-06.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-06.jpg" alt="GALLERY">
				</a>

				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-07.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-07.jpg" alt="GALLERY">
				</a>

				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-09.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-09.jpg" alt="GALLERY">
				</a>

				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-10.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-10.jpg" alt="GALLERY">
				</a>

				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-11.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-11.jpg" alt="GALLERY">
				</a>
			</div>
		</div>
	</aside>

	
	<!-- Title Page -->
	<section class="bg-title-page flex-c-m p-t-160 p-b-80 p-l-15 p-r-15" style="background-image: url(images/bg-title-page-03.jpg);">
		<h2 class="tit6 t-center">
			Reserve your stay
		</h2>
		
	</section>


	<!-- Reservation -->
	<section class="section-reservation bg1-pattern p-t-100 p-b-113">
		<div class="container">
		
	
			<div class="row">
				<div class="col-lg-12 p-b-30">
					<div class="t-center">
					
					
						<span class="tit2 t-center">
							Reservation Details
						</span>

						
					</div>

					<form class="wrap-form-reservation size22 m-l-r-auto" method="post" action="reservation_controller">
					
					
					<input type="hidden" name="command" value="RESERVE">
					<input type="hidden" name="amount" value="${RES_DETAILS.get(2) * RES_DETAILS.get(3) * 10000}">
						<div class="row">
							<div class="col-md-4">
								<!-- Date -->
								<span class="txt9">
									Check In
								</span>

								<div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
									<input class=" bo-rad-10 sizefull txt10 p-l-20 bg-danger text-white" type="date" name="checkIn" id="checkIn" value="${RES_DETAILS.get(0)}" readonly="readonly">
									
								</div>
							</div>
							
							<div class="col-md-4">
								<!-- Date -->
								<span class="txt9">
									Check Out
								</span>

								<div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
									<input class=" bo-rad-10 sizefull txt10 p-l-20 bg-danger text-white" type="date" name="checkOut" id="checkOut" value="${RES_DETAILS.get(1)}" readonly="readonly">
									
								</div>
							</div>
							
							<div class="col-md-4">
								<!-- Date -->
								<span class="txt9">
									Nights
								</span>
								<div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
									<input class="bo-rad-10 sizefull txt10 p-l-20 bg-danger text-white" type="text" name="nights" id="nights" value="${RES_DETAILS.get(2)}" placeholder="Nights" readonly="readonly">
									 <i class="btn-heart fa fa-heart ab-r-m hov-pointer m-r-18" aria-hidden="true"></i>
								</div>
							</div>

							
						</div>

						<div class="row">
							<div class="col-md-4">
								<!-- Date -->
								<span class="txt9">
									Rooms
								</span>

								<div class="wrap-inputtime size12 bo2 bo-rad-10 m-t-3 m-b-23 ">
									<!-- Select2 -->
									<select class="selection-1 bg-danger text-white" name="rooms" value="${RES_DETAILS.get(3)}" readonly="readonly">
										<option>1</option>
										<option>2</option>
										<option>3</option>
										<option>4</option>
										<option>5</option>
										<option>6</option>
										<option>7+</option>
									</select>
								</div>
							</div>
							
							<div class="col-md-4">
								<!-- Date -->
								<span class="txt9">
									Adults
								</span>

								<div class="wrap-inputtime size12 bo2 bo-rad-10 m-t-3 m-b-23">
									<!-- Select2 -->
									<select class="selection-1" name="adults" value="${RES_DETAILS.get(4)}" >
										<option>1</option>
										<option>2</option>
										<option>3</option>
										<option>4</option>
										<option>5</option>
										<option>6</option>
										<option>7</option>
										<option>8</option>
										<option>9</option>
										<option>10</option>
										<option>11</option>
										<option>12</option>
										<option>13</option>
										<option>14</option>
										<option>15</option>
										<option>16</option>
										<option>17</option>
										<option>18</option>
										<option>19</option>
										<option>20+</option>
									</select>
								</div>
							</div>
							
							<div class="col-md-4">
								<!-- Date -->
								<span class="txt9">
									Children
								</span>
								
								<div class="wrap-inputtime size12 bo2 bo-rad-10 m-t-3 m-b-23">
									<!-- Select2 -->
									<select class="selection-1" name="childs" value="${RES_DETAILS.get(5)}">
										<option>1</option>
										<option>2</option>
										<option>3</option>
										<option>4</option>
										<option>5</option>
										<option>6</option>
										<option>7+</option>
									</select>
								</div>
							</div>

							
						</div>
						
						
						<div class="t-center">
							<span class="tit2 t-center">
								Booking Details
							</span>

						</div>
						<div class="row">
							<div class="col-md-12">
								<!-- Date -->
								<span class="txt9">
									Full Name
								</span>

								<div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
									<input class=" bo-rad-10 sizefull txt10 p-l-20" type="text" name="fullname" id="fullname" >
									
								</div>
							</div>
							</div>
							<div class="row">
							<div class="col-md-6">
								<!-- Date -->
								<span class="txt9">
									Address
								</span>

								<div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
									<textarea class=" bo-rad-10 sizefull txt10 p-l-20" type="text" name="address" id="address"></textarea>
									
								</div>
							</div>
							
							<div class="col-md-6">
								<!-- Date -->
								<span class="txt9">
									Country
								</span>
								<div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
									<select class="selection-1" name="country">
										<option>Austria</option>
										<option>Bangladesh</option>
										<option>Belgium</option>
										<option>Bolivia</option>
										<option>China</option>
										<option>Canada</option>
										<option>Egypt</option>
										<option>France</option>
										<option>Sri Lanka</option>
										<option>United States of America</option>
									</select>
								</div>
							</div>

							
						</div>
						
						<div class="row">
							<div class="col-md-4">
								<!-- Date -->
								<span class="txt9">
									Mobile
								</span>

								<div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
									<input class=" bo-rad-10 sizefull txt10 p-l-20" type="text" name="mobile_no" id="mobile_no">
									
								</div>
							</div>
							
							<div class="col-md-8">
								<!-- Date -->
								<span class="txt9">
									Email
								</span>

								<div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
									<input class=" bo-rad-10 sizefull txt10 p-l-20" type="text" name="email" id="email">
									
								</div>
							</div>
							
							

							
						</div>

						<div class="t-center">
							<span class="tit2 t-center">
								Payment Details
							</span>

						</div>
						
						<div class="row">
							<div class="col-md-4 offset-md-4">
								<!-- Date -->
								<span class="txt9">
									Payment Method
								</span>
								<div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
									<select class="selection-1" name="payment_method" id="payment_method" onchange="java_script_:show(this.options[this.selectedIndex].value)">
										<option>-- Select Payment Type --</option>
										<option value="1">Credit / Debit Card</option>
										<option value="2">At Hotel</option>
									</select>
								</div>
							</div>

							
						</div>
						<div class="row" id="card_details" style="display:none">
							
							<div class="row">
								<div class="col-md-12">
							<div class="card">
								  
								  <div class="card-body">
								  
									
								  
								  <div class="wrap-btn-booking flex-c-m m-t-6">
										<!-- Button3 -->
										<h3>No. of Nights: ${RES_DETAILS.get(2)} </h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<h3>No. of Rooms: ${RES_DETAILS.get(3)} </h3>
										
										
									</div>
									<div class="wrap-btn-booking flex-c-m m-t-6" style="margin-top:30px">
										<!-- Button3 -->
										<h2>Total due payment: Rs. ${RES_DETAILS.get(2) * RES_DETAILS.get(3) * 10000} /- </h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										
										
										
									</div>
									<div class="row">
								<div class="col-md-4">
									<!-- Date -->
									<span class="txt9">
										Accepted Cards
									</span>
									<div class="row">
										<div class="col-md-12">
											<i class="fa fa-cc-visa" style="color:navy;"></i>
							              <i class="fa fa-cc-amex" style="color:blue;"></i>
							              <i class="fa fa-cc-mastercard" style="color:red;"></i>
							              <i class="fa fa-cc-discover" style="color:orange;"></i>
										</div>
										
									</div>
									
								</div>
							</div>
								  <div class="row" style="margin-top:50px">
								    <div class="col-md-6">
										<span class="txt9">
											CARD NUMBER
										</span>
		
										<div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
											<input class=" bo-rad-10 sizefull txt10 p-l-20" type="text" name="cardno" id="cardno">
										</div>
									</div>
									<div class="col-md-6">
										<div class="row">
										<div class="col-md-6">
											<span class="txt9">
											EXPIRY MONTH
											</span>
			
											<div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
												<input class=" bo-rad-10 sizefull txt10 p-l-20" placeholder="MM" type="text" name="expmonth" id="expmonth">
												
											</div>
											</div>
										<div class="col-md-6">
											
											<span class="txt9">
											EXPIRY YEAR
											</span>
											<div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
												<input class=" bo-rad-10 sizefull txt10 p-l-20" placeholder="YY" type="text" name="expyear" id="expyear">
												
											</div>
										</div>
										</div>
									</div>
									<div class="col-md-4">
										<span class="txt9">
											CV CODE
										</span>
		
										<div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
											<input class=" bo-rad-10 sizefull txt10 p-l-20" type="text" name="cvcode" id="cvcode">
											
										</div>
									</div>
								  </div>
								  </div>
								</div>
							</div>
							</div>
						</div>
						<div class="wrap-btn-booking flex-c-m m-t-6">
							<!-- Button3 -->
							<button type="submit" class="btn3 flex-c-m size13 txt11 trans-0-4">
								PROCEED
							</button>
						</div>
					</form>
				</div>
			</div>

			<div class="info-reservation flex-w p-t-80">
				<div class="size23 w-full-md p-t-40 p-r-30 p-r-0-md">
					<h4 class="txt5 m-b-18">
						Reserve by Phone
					</h4>

					<p class="size25">
						Donec quis euismod purus. Donec feugiat ligula rhoncus, varius nisl sed, tincidunt lectus.
						<span class="txt25">Nulla vulputate</span>
						, lectus vel volutpat efficitur, orci
						<span class="txt25">lacus sodales</span>
						 sem, sit amet quam:
						<span class="txt24">(001) 345 6889</span>
					</p>
				</div>

				<div class="size24 w-full-md p-t-40">
					<h4 class="txt5 m-b-18">
						For Event Booking
					</h4>

					<p class="size26">
						Donec feugiat ligula rhoncus:
						<span class="txt24">(001) 345 6889</span>
						, varius nisl sed, tinci-dunt lectus sodales sem.
					</p>
				</div>

			</div>
		</div>
	</section>


	<!-- Footer -->
	<footer class="bg1">
		<div class="container p-t-40 p-b-70">
			<div class="row">
				<div class="col-sm-6 col-md-4 p-t-50">
					<!-- - -->
					<h4 class="txt13 m-b-33">
						Contact Us
					</h4>

					<ul class="m-b-70">
						<li class="txt14 m-b-14">
							<i class="fa fa-map-marker fs-16 dis-inline-block size19" aria-hidden="true"></i>
							8th floor, 379 Hudson St, New York, NY 10018
						</li>

						<li class="txt14 m-b-14">
							<i class="fa fa-phone fs-16 dis-inline-block size19" aria-hidden="true"></i>
							(+1) 96 716 6879
						</li>

						<li class="txt14 m-b-14">
							<i class="fa fa-envelope fs-13 dis-inline-block size19" aria-hidden="true"></i>
							contact@site.com
						</li>
					</ul>

					<!-- - -->
					<h4 class="txt13 m-b-32">
						Opening Times
					</h4>

					<ul>
						<li class="txt14">
							09:30 AM – 11:00 PM
						</li>

						<li class="txt14">
							Every Day
						</li>
					</ul>
				</div>

				<div class="col-sm-6 col-md-4 p-t-50">
					<!-- - -->
					<h4 class="txt13 m-b-33">
						Latest twitter
					</h4>

					<div class="m-b-25">
						<span class="fs-13 color2 m-r-5">
							<i class="fa fa-twitter" aria-hidden="true"></i>
						</span>
						<a href="#" class="txt15">
							@colorlib
						</a>

						<p class="txt14 m-b-18">
							Activello is a good option. It has a slider built into that displays the featured image in the slider.
							<a href="#" class="txt15">
								https://buff.ly/2zaSfAQ
							</a>
						</p>

						<span class="txt16">
							21 Dec 2017
						</span>
					</div>

					<div>
						<span class="fs-13 color2 m-r-5">
							<i class="fa fa-twitter" aria-hidden="true"></i>
						</span>
						<a href="#" class="txt15">
							@colorlib
						</a>

						<p class="txt14 m-b-18">
							Activello is a good option. It has a slider built into that displays
							<a href="#" class="txt15">
								https://buff.ly/2zaSfAQ
							</a>
						</p>

						<span class="txt16">
							21 Dec 2017
						</span>
					</div>
				</div>

				<div class="col-sm-6 col-md-4 p-t-50">
					<!-- - -->
					<h4 class="txt13 m-b-38">
						Gallery
					</h4>

					<!-- Gallery footer -->
					<div class="wrap-gallery-footer flex-w">
						<a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-01.jpg" data-lightbox="gallery-footer">
							<img src="images/photo-gallery-thumb-01.jpg" alt="GALLERY">
						</a>

						<a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-02.jpg" data-lightbox="gallery-footer">
							<img src="images/photo-gallery-thumb-02.jpg" alt="GALLERY">
						</a>

						<a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-03.jpg" data-lightbox="gallery-footer">
							<img src="images/photo-gallery-thumb-03.jpg" alt="GALLERY">
						</a>

						<a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-04.jpg" data-lightbox="gallery-footer">
							<img src="images/photo-gallery-thumb-04.jpg" alt="GALLERY">
						</a>

						<a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-05.jpg" data-lightbox="gallery-footer">
							<img src="images/photo-gallery-thumb-05.jpg" alt="GALLERY">
						</a>

						<a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-06.jpg" data-lightbox="gallery-footer">
							<img src="images/photo-gallery-thumb-06.jpg" alt="GALLERY">
						</a>

						<a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-07.jpg" data-lightbox="gallery-footer">
							<img src="images/photo-gallery-thumb-07.jpg" alt="GALLERY">
						</a>

						<a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-08.jpg" data-lightbox="gallery-footer">
							<img src="images/photo-gallery-thumb-08.jpg" alt="GALLERY">
						</a>

						<a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-09.jpg" data-lightbox="gallery-footer">
							<img src="images/photo-gallery-thumb-09.jpg" alt="GALLERY">
						</a>

						<a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-10.jpg" data-lightbox="gallery-footer">
							<img src="images/photo-gallery-thumb-10.jpg" alt="GALLERY">
						</a>

						<a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-11.jpg" data-lightbox="gallery-footer">
							<img src="images/photo-gallery-thumb-11.jpg" alt="GALLERY">
						</a>

						<a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-12.jpg" data-lightbox="gallery-footer">
							<img src="images/photo-gallery-thumb-12.jpg" alt="GALLERY">
						</a>
					</div>

				</div>
			</div>
		</div>

		<div class="end-footer bg2">
			<div class="container">
				<div class="flex-sb-m flex-w p-t-22 p-b-22">
					<div class="p-t-5 p-b-5">
						<a href="#" class="fs-15 c-white"><i class="fa fa-tripadvisor" aria-hidden="true"></i></a>
						<a href="#" class="fs-15 c-white"><i class="fa fa-facebook m-l-18" aria-hidden="true"></i></a>
						<a href="#" class="fs-15 c-white"><i class="fa fa-twitter m-l-18" aria-hidden="true"></i></a>
					</div>

					<div class="txt17 p-r-20 p-t-5 p-b-5">
						Copyright &copy; 2018 All rights reserved  |  Made with <i class="fa fa-heart"></i> by <a href="https://colorlib.com" target="_blank">InofinityLabs</a>
					</div>
				</div>
			</div>
		</div>
	</footer>


	<!-- Back to top -->
	<div class="btn-back-to-top bg0-hov" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="fa fa-angle-double-up" aria-hidden="true"></i>
		</span>
	</div>

	<!-- Container Selection1 -->
	<div id="dropDownSelect1"></div>

	<script>
	// new Date("dateString") is browser-dependent and discouraged, so we'll write
	// a simple parse function for U.S. date format (which does no error checking)
	function parseDate(str) {
	    var mdy = str.split('/');
	    return new Date(mdy[2],mdy[1]-1,mdy[0]);
	}

	function datediff(first, second) {
	    // Take the difference between the dates and divide by milliseconds per day.
	    // Round to nearest whole number to deal with DST.
	    return Math.round((second-first)/(1000*60*60*24));
	}
	
	function calcDays(){
		
		var checkIn = document.getElementById("checkIn").value;
		var checkOut = document.getElementById("checkOut").value;
		
		document.getElementById("nights").value = datediff(parseDate(checkIn), parseDate(checkOut));
	}
	

	function show(select_item) {
		
		var hiddenDiv = document.getElementById("card_details");
		
	    if (select_item == "1") {
		    hiddenDiv.style.visibility='visible';
			hiddenDiv.style.display='block';
			Form.fileURL.focus();
		} 
		else{
			hiddenDiv.style.visibility='hidden';
			hiddenDiv.style.display='none';
		}
	}
	
	
	
	</script>


<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/bootstrap/js/popper.js"></script>
	<script type="text/javascript" src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/daterangepicker/moment.min.js"></script>
	<script type="text/javascript" src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/slick/slick.min.js"></script>
	<script type="text/javascript" src="js/slick-custom.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/parallax100/parallax100.js"></script>
	<script type="text/javascript">
        $('.parallax100').parallax100();
	</script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/lightbox2/js/lightbox.min.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

	
</body>
</html>
