<%-- 
    Document   : home
    Created on : 15-Dec-2020, 12:10:38
    Author     : Simon
--%>

<%@page import="com.bean.ChatRoom"%>
<%@page import="java.util.ArrayList"%>
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
            ArrayList<ChatRoom> list = (ArrayList<ChatRoom>)request.getAttribute("list");
            
            if(name!= null && name.contains("admin")){%>

              <li><a href="mychatwww/admin">Admin</a></li>
           <%}%>

    </ul>
  </div>
</nav>

<div class="container">

    <h1>Welcome <%= request.getAttribute("result") %> 
    </h1>

    <h2>Here is the available rooms:</h2> 
    
    <table class="table table-dark">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Channel</th>
      <th scope="col">User count</th>
      <th scope="col">Join</th>
    </tr>
  </thead>
  <tbody>
      
     <% for(int i=0; i<list.size(); i++) { %>
      <tr>
     <th scope="row"><%=i%></th>
      <td><%=list.get(i).getChatName()%></td>
      <td><%=list.get(i).getUsers().size()%></td>
      <td><a type="button" class="btn btn-primary" href="<%="mychatwww/joinChatRoom/"+list.get(i).getChatName() + "/" + name%>">Join</a></td>
    </tr>
    <%}%>
    


  </tbody>
</table>
    
    
    

</div>
    </body>
</html>
