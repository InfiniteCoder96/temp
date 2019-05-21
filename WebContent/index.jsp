<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hotel Avinka | Contact</title>
    
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/styles-merged.css">
    <link rel="stylesheet" href="assets/css/style.min.css">
    <link rel="stylesheet" href="assets/css/custom.css">

    <style>
      #selector{
        background-color: #000;
      }
    </style>
    <script>  
		function validateform(){  
		var fname=document.myform.fname.value;  
		var lname=document.myform.lname.value; 
		var email=document.myform.email.value;  
		var mobile=document.myform.mobile.value;  
		var address=document.myform.address.value;  
		var check_in=document.myform.check_in.value;  
		var check_out=document.myform.check_out.value;  
		  

		  
		if (fname==null || lname=="" || email==""|| mobile=="" || address=="" || check_in=="" || check_out==""){  
		  alert("All the fields should be filled");  
		  return false;  
		}else if(mobile.length!=10){  
		  alert("Mobile Number must be 10 numbers long.");  
		  return false;  
		  }  
		}  
	</script> 
  </head>
  <body>

  <!-- START: header -->
  
  <div class="probootstrap-loader"></div>

  <header role="banner" class="probootstrap-header">
    <div class="container">
        <a href="#" class="probootstrap-logo">Hotel Avinka</a>
        

        <div class="mobile-menu-overlay"></div>

        <nav role="navigation" class="probootstrap-nav hidden-xs">
          <ul class="probootstrap-main-nav" align="center">
            <li><a href="#">Home</a></li>
			<li><a href="#">About</a></li>
            <li><a href="#">Gallery</a></li>
            <li><a href="#">Services</a></li>
            <li><a href="#">Contact</a></li>
			
          </ul>
          <ul class="probootstrap-right-nav hidden-xs">
             <li><a href="#" style="color:#fff;">SIGN IN</a></li>
          </ul>
        </nav>
    </div>
  </header>
  <!-- END: header -->
  <section class="probootstrap-slider flexslider">
    <ul class="slides">
      <li style="background-image: url(assets/img/05.jpg);">
        <div class="container">
          <div class="row">
            <div class="col-md-12 text-center">
              <div class="slides-text probootstrap-animate">
                <h2>Contact</h2>
                <p>Feel free to contact our friendly, trustworthy and efficient staff. Our Staff is always ready for your service.</p>
              </div>
            </div>
          </div>
        </div>
      </li>
    </ul>
  </section>
  <!-- END: slider  -->
  
  <section class="probootstrap-section">
    <div class="container">
      <div class="row">
      
        <div class="col-md-9">

          <form action="reservation" name="myform" method="post" class="probootstrap-form mb60" onsubmit="return validateform()">
            <div class="row">

              <div class="col-md-6">
                <div class="form-group">
                  <label for="fname">First Name</label>
                  <input type="text" class="form-control" id="fname" name="fname" placeholder="First Name">
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-group">
                  <label for="lname">Last Name</label>
                  <input type="text" class="form-control" id="lname" name="lname" placeholder="Last Name">
                </div>
              </div>
            </div>
			 <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label for="fname">Check-In</label>
                  <input type="date" class="form-control" id="check_in" name="check_in">
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label for="lname">Check-Out</label>
                  <input type="date" class="form-control" id="check_out" name="check_out">
                </div>
              </div>
            </div>
			 <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label for="fname">Email</label>
                  <input type="text" class="form-control" id="email" name="email" placeholder="E-mail">
                </div>
              </div>
              <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label for="fname">Mobile Number</label>
                  <input type="text" class="form-control" id="mobile" name="mobile" placeholder="Mobile">
                </div>
              </div>
            </div>
			 </div>
        

            <div class="form-group">
              <label for="message">Address</label>
              <textarea cols="30" rows="5" class="form-control" id="message" name="address" placeholder="Address"></textarea>
            </div>
            <div class="form-group">
              <input type="submit" class="btn btn-primary" id="submit" name="submit" value="Reserve">
            </div>
          </form>
          
        </div><!-- end -->


		<div class="col-md-3">
          <h4>Contact Details</h4>
          <ul class="with-icon colored">
            <li><i class="icon-location2"></i> <span>Hotel Avinka,high level road,kottawa.</span></li>
            <li><i class="icon-mail"></i><span>info@Hotel-Avinka.com</span></li>
            <li><i class="icon-phone2"></i><span>+94 76 472 3812</span></li>
          </ul>
        </div>



      </div>
    </div>
  </section>  
 <!-- <div id="map"></div> -->

  <footer class="probootstrap-footer probootstrap-bg">
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <div class="probootstrap-footer-widget">
            <h4 class="heading">About Hotel Avinka</h4>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Illo labore modi rerum reiciendis est sint repudiandae distinctio autem hic ipsum impedit non, animi quaerat eius et ut exercitationem quidem vitae!</p>
          </div> 
        </div>
        <div class="col-md-4">
          <div class="probootstrap-footer-widget">
            <h4 class="heading">Footer Menu Links</h4>
            <ul class="stack-link">
              <li><a href="#">About</a></li>
              <li><a href="#">Gallery</a></li>
              <li><a href="#">Services</a></li>
              <li><a href="#">Contact Us</a></li>
            </ul>
          </div> 
        </div>
        <div class="col-md-4">
          <div class="probootstrap-footer-widget probootstrap-link-wrap">
            <h4 class="heading">Subscribe</h4>
            <p>Want to know about latest news of Hotel Avinka? To receive our free newsletter directly to your e-mail, Enter your Frequently used Email Address here and click the Subscribe button.</p>
            <form action="#">
              <div class="form-field">
                <input type="text" class="form-control" placeholder="Enter your email">
                <button class="btn btn-subscribe">Subscribe</button>
              </div>
            </form>
          </div>
        </div>
      </div>
      <div class="row copyright">
        <div class="col-md-6">
          <div class="probootstrap-footer-widget">
            <p>&copy; 2019 <a href="#">Hotel Avinka </a> </p>
          </div>
        </div>
        <div class="col-md-6">
          <div class="probootstrap-footer-widget right">
            <ul class="probootstrap-social">
              <li><a href="#"><i class="icon-twitter"></i></a></li>
              <li><a href="#"><i class="icon-facebook"></i></a></li>
              <li><a href="#"><i class="icon-instagram2"></i></a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </footer>

  <div class="gototop js-top">
    <a href="#" class="js-gotop"><i class="icon-chevron-thin-up"></i></a>
  </div>
  

  <script src="assets/js/scripts.min.js"></script>
  <script src="assets/js/main.min.js"></script>

  <!-- Google Map -->
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="assets/js/google-map.js"></script>


  <script src="assets/js/custom.js"></script>

  </body>
</html>