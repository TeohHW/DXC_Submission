<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="css/style.css">
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.min.css'>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.all.min.js"></script>
    <meta charset="ISO-8859-1">
    <title>Register</title>
  </head>
  <body>
  <input type="hidden" id="status"value="<%=request.getAttribute("status")%>">
    <div class="wrapper">
      <div class="title"> Create Account </div>
      <form id="registerform" name="registerform" action="Registration" method="post"  >
        <div class="field">
        	<input type="text" name="username" id="username" required>
        	<label>Username</label>
        </div>
        <div class="field">
        	<input type="password" name="password" id="password" onkeyup='checkPw();' required>
        	<label>Password</label>
        </div>
         <div class="field">
        	<input type="password" name="confirmpw" id="confirmpw" onkeyup='checkPw();' required>
        	<label>Confirm Password</label>
        </div>
        <span id='matchingPw' style="float:right"></span>
        <div class="select">
          <br>
          	<label>Choose your role:</label>
          <br>
          <select id="role" name="role">
          	<option value="User">User</option>
            <option value="Manager">Manager</option>
          </select>
          <span class="focus"></span>
        </div>
        <div class="field">
         	<input id="registerBtn" class="registerBtn" type="submit" value="Register" >
        </div>
        <div class="signup-link">
        	<a href="LoginPage.jsp">Go back</a>
        </div>
      </form>
    </div>
  </body>
<script>
	//Confirm 2nd password
	var checkPw = function() {
	  if (document.getElementById('password').value == document.getElementById('confirmpw').value) {
	    document.getElementById('matchingPw').style.color = 'green';
	    document.getElementById('matchingPw').innerHTML = 'Passwords match';
	    document.getElementById("registerBtn").disabled = false;
	  } else {
	    document.getElementById('matchingPw').style.color = 'red';
	    document.getElementById('matchingPw').innerHTML = 'Passwords do not match!';
	    document.getElementById("registerBtn").disabled = true;
	  }
	}
	//Popup message
	var status = document.getElementById("status").value;
	if (status == "success") {
		Swal.fire("Success!","Account created!","success").then(() => {
				window.location.href = "LoginPage.jsp";
			});}
	else if (status == "failed") {
		Swal.fire("Username is already taken!","Please try again!","error").then(() => {
				window.location.href = "Registration.jsp";
			});}
</script>
</html>