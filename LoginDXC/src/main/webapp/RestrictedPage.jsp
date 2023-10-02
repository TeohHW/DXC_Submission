<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="css/style.css">
    <meta charset="ISO-8859-1">
    <title>Restricted page</title>
  </head>
  <body>
    <div class="wrapper">
      <form action="Logout" method="post">
        <div class="field">
          <label>This is a page only accessible by managers.</label>
        </div>
        <br>
        <div class="field">
          <input type="submit" value="Logout">
        </div>
      </form>
    </div>
  </body>
</html>