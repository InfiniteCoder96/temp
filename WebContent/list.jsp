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
<link rel="stylesheet" href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">

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
		
		<div class="col-12" style="margin-top: 25px;">
		
			<div class="card">
				<div class="card-body">
					<table class="table table-bordered table-striped" id="myTable">
						<thead>
							<tr>
								<td>Id</td>
								<td>first name</td>
								<td>last name</td>
								<td>email</td>
								<td>mobile</td>
								<td>check In</td>
								<td>check out</td>
								<td>Address</td>
								<td>Action</td>
							</tr>
						</thead>
						
						<tbody>
									<%
				  				
				  				PreparedStatement stmt = null;
				  				ResultSet rs = null;
				  			
				  				String query = "SELECT * FROM reservation ";
				  				
				  				try{
				  					Connection conn = dbconnect.connect();
				  					stmt = conn.prepareStatement(query);
				  					rs = stmt.executeQuery();
				  					
				  					while(rs.next()){
				  						%>
				  							<tr>
				  								
				  								<td><%out.print(rs.getString(1)); %></td>
				  								<td><%out.print(rs.getString(2)); %></td>
				  								<td><%out.print(rs.getString(3)); %></td>
				  								<td><%out.print(rs.getString(4)); %></td>
				  								<td><%out.print(rs.getString(5)); %></td>
				  								<td><%out.print(rs.getString(6)); %></td>
				  								<td><%out.print(rs.getString(7)); %></td>
				  								<td><%out.print(rs.getString(8)); %></td>
				  								<td>
				  								
												<form action="edit_r" method="post" style="display: inline;">
													<input type="hidden" name="r_id" value="<%out.print(rs.getString(1));%>">
													<button type="submit" class="btn btn-warning btn-sm" id="btn_download"><i class="fas fa-edit"></i></button>
												</form>
												<form  action="delete_r" method="post" style="display: inline;">
													<input type="hidden" name="r_id" value="<%out.print(rs.getString(1));%>">
													<button type="submit" class="btn btn-danger btn-sm" id="btn_download"><i class="fas fa-trash-alt"></i></button>
												</form>
												</td>
				  							</tr>
				  						<% 
				  					}
				  				}
				  				catch(Exception e){
				  					out.print(e);
				  				}
				  			%>
						</tbody>
					</table>
		
				</div>
			</div>
		
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="crossorigin="anonymous"></script>
	<script src="http://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
		$(document).ready( function () {
	    	$('#myTable').DataTable();
		} );
	</script>
</body>
</html>