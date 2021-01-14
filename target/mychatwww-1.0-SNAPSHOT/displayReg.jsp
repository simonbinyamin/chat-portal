<%-- 
    Document   : home
    Created on : 15-Dec-2020, 12:10:38
    Author     : Simon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to our Chat Server</title>
      
        <base href="/">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/x-icon" href="favicon.ico">
        <!-- Latest compiled and minified CSS -->
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

      <!-- jQuery library -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

      <!-- Latest compiled JavaScript -->
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    


    <body>
        <nav class="navbar navbar-inverse">
  <div  class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="mychatwww/home">Chat server</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="mychatwww/home">Home</a></li>
      <li><a href="mychatwww/register">Register</a></li>
      
      <% 
          String name = (String)request.getAttribute("result");

            if(name!= null && name.contains("admin")){%>

              <li><a href="mychatwww/admin">Admin</a></li>
           <%}%>

    </ul>
  </div>
</nav>

<div class="container">

    <p>Registering is done, welcome Mr/Ms:</p>
   <%= request.getAttribute("result") %>
</div>
    </body>
</html>
