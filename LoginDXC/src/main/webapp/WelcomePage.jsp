<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="css/style.css">
    <meta charset="ISO-8859-1">
    <title>Welcome</title>
  </head>
  <body>
  <input type="hidden" id="status"value="<%=request.getAttribute("status")%>">
    <div class="wrapper">
      <form action="Logout" method="post">
        <label>Welcome, <%=session.getAttribute("name")%> </label>
        <br><br>
        <span>Your role is: <%=session.getAttribute("role")%> </span>
        <br><br>
        <%if(session.getAttribute("role")=="Manager"){%> <a href="RestrictedPage.jsp">Go to restricted page</a><%}%>
		<br><br>
			<div class="field">
				<input type="submit" value="Logout">
			</div>
	  </form>
	</div>
    </body>
</html>