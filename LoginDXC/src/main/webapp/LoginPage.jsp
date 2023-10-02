<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="css/style.css">
	<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.min.css'>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.all.min.js"></script>
    <meta charset="ISO-8859-1">
    <title>Login</title>
  </head>
  <body>
  <input type="hidden" id="status"value="<%=request.getAttribute("status")%>">
    <div class="wrapper">
      <div class="title"> Login </div>
      <form action="Login" method="post">
        <div class="field">
        	<input type="text" name="username" id="username" required>
        	<label>Username</label>
        </div>
        <div class="field">
        	<input type="password" name="password" id="password" required>
        	<label>Password</label>
        </div>
        <div class="field">
        	<input type="submit" value="Login">
        </div>
        <div class="signup-link">
        	<a href="Registration.jsp">Don't have an account? Sign up</a>
        </div>
      </form>
    </div>
  </body>
 <script>
	var status = document.getElementById("status").value;
	if (status == "failed") {
		Swal.fire("Incorrect Username/Password!","Please try again!","error");
	}
 </script>
</html>