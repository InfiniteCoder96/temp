<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="data.dbconnect" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
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
		}
		else if(mobile.length!=10){  
		  alert("Mobile Number must be 10 numbers long.");  
		  return false;  
		  }  
		}  
	</script>
	
	<style>
		body {
			background-color: #3F7F89;
		}
		
	</style> 
</head>
<body>
	<div class="container-fluid">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <a class="navbar-brand" href="#">Hotel Avinka</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarNavDropdown">
		    <ul class="navbar-nav">
		      <li class="nav-item active">
		        <a class="nav-link" href="list.jsp">Home <span class="sr-only">(current)</span></a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#">Features</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#">Pricing</a>
		      </li>
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          Dropdown link
		        </a>
		        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
		          <a class="dropdown-item" href="#">Action</a>
		          <a class="dropdown-item" href="#">Another action</a>
		          <a class="dropdown-item" href="#">Something else here</a>
		        </div>
		      </li>
		    </ul>
		  </div>
		</nav>
		
		<div class="row">
			<div class="col-2">
			
			</div>
			<div class="col-8" style="margin-top: 25px;  margin-bottom:10px;">
				<div class="card">
					<div class="card-body">
						<%
			  				PreparedStatement stmt = null;
			  				ResultSet rs = null;
			  			
			  				String query = "SELECT * FROM reservation WHERE id ='"+session.getAttribute("id")+"'";
			  				
			  				try{
			  					Connection conn = dbconnect.connect();
			  					stmt = conn.prepareStatement(query);
			  					rs = stmt.executeQuery();
			  					
			  					while(rs.next()){
	  					%>
			
							<form action="update_r" name="myform" method="post" class="probootstrap-form mb60" onsubmit="return validateform()" style="margin-top: 25px;">
				            <div class="row">
				
							<div class="col-md-4">
				                <div class="form-group">
				                  <label for="fname">Id</label>
				                  <input type="text" class="form-control" readonly="readonly" id="id" name="id" value="<%out.print(rs.getString(1)); %>">
				                </div>
				              </div>
				              <div class="col-md-4">
				                <div class="form-group">
				                  <label for="fname">First Name</label>
				                  
				                  <input type="text" class="form-control" id="fname" name="fname" value="<%out.print(rs.getString(2)); %>">
				                </div>
				              </div>
				
				              <div class="col-md-4">
				                <div class="form-group">
				                  <label for="lname">Last Name</label>
				                  <input type="text" class="form-control" id="lname" name="lname" value="<%out.print(rs.getString(3)); %>">
				                </div>
				              </div>
				            </div>
							 <div class="row">
				              <div class="col-md-6">
				                <div class="form-group">
				                  <label for="fname">Check-In</label>
				                  <input type="date" class="form-control" id="check_in" name="check_in" value="<%out.print(rs.getString(6)); %>">
				                </div>
				              </div>
				              <div class="col-md-6">
				                <div class="form-group">
				                  <label for="lname">Check-Out</label>
				                  <input type="date" class="form-control" id="check_out" name="check_out" value="<%out.print(rs.getString(7)); %>">
				                </div>
				              </div>
				            </div>
							 <div class="row">
				              <div class="col-md-6">
				                <div class="form-group">
				                  <label for="fname">Email</label>
				                  <input type="text" class="form-control" id="email" name="email" value="<%out.print(rs.getString(4)); %>">
				                </div>
				              </div>
				              
				              <div class="col-md-6">
				                <div class="form-group">
				                  <label for="fname">Mobile Number</label>
				                  <input type="text" class="form-control" id="mobile" name="mobile" value="<%out.print(rs.getString(5)); %>">
				                </div>
				              </div>
				       
							 </div>
				        
				
				            <div class="form-group">
				              <label for="message">Address</label>
				              <textarea cols="30" rows="5" class="form-control" id="message" name="address" ><%out.print(rs.getString(8)); %></textarea>
				            </div>
				            <div class="form-group">
				              <input type="submit" class="btn btn-primary" id="submit" name="submit" value="Update">
				            </div>
				          </form>
			          		<% 
			  					}
			  				}
			  				catch(Exception e){
			  					out.print(e);
			  				}
			  			%>
					</div>
				</div>
			</div>
		</div>
	</div>

  						
	
</body>
</html>