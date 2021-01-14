<%-- 
    Document   : home
    Created on : 15-Dec-2020, 12:10:38
    Author     : Simon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <style>
            .tablex {
             position: fixed;
                bottom: 0;
                right: 0;
            }
        </style>
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

    <div id="tbl">
        
    </div>
    <h1>Available rooms</h1>
  <table id="tbl2" class="table">
  <thead>
    <tr>
      <th scope="col">Room</th>
      <th scope="col">User count</th>
      <th scope="col"></th>
          <th scope="col"></th>
              <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  </tbody>
</table>
    


  <table id="tbl3" style="background-color: black;" class="table tablex">
  <thead>
    <tr>
 
    </tr>
  </thead>
  <tbody>
  </tbody>
</table>
 

</div>
    </body>
   <script>
                       
 
        function onChangeName(e){

                 window.location.href = '/mychatwww/updateChatRoom/'+e+'/'+document.getElementById('exampleFormControlInput1').value;
        }
        
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "GET", "http://localhost:29565/mychatwww/getrooms", false ); // false for synchronous request
        xmlHttp.send( null );
        var array = JSON.parse(xmlHttp.responseText);
        console.log(array);
    
        var xmlHttp2 = new XMLHttpRequest();
        xmlHttp2.open( "GET", "http://localhost:29565/mychatwww/getusersinroom", false ); // false for synchronous request
        xmlHttp2.send( null );
        var usersinroom = JSON.parse(xmlHttp2.responseText);
        console.log(usersinroom);
        
    


        var table = document.getElementById("tbl2");
        for(var i = 0; i<array.length; i++) {
            var row = table.insertRow(i);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
            var cell5 = row.insertCell(4);

            // Add some text to the new cells:
            cell1.innerHTML = array[i].chatName;
            cell2.innerHTML = array[i].users.length;
            cell3.innerHTML = '<a href="/mychatwww/deleteChatRoom/'+array[i].chatName+'" type="button" class="btn btn-danger">Delete</>';
            cell4.innerHTML =  '<input type="text" class="form-control" id="exampleFormControlInput1" placeholder="new name">';
            cell5.innerHTML =  `<button onclick="onChangeName('${array[i].chatName}')" class="btn btn-primary">Update</button>`;
        }
        
        
        var table3 = document.getElementById("tbl3");
        for(var i = 0; i<usersinroom.length; i++) {
            var row = table3.insertRow(i);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
            var cell5 = row.insertCell(4);

            // Add some text to the new cells:
            cell1.innerHTML = '<span style="color: white;">' + usersinroom[i].nickname+ '</span>';
            cell2.innerHTML = '<span style="color: white;">' +usersinroom[i].email+ '</span>';
            cell3.innerHTML = '<span style="color: white;">' + usersinroom[i].channel+ '</span>';
            cell4.innerHTML = '<a href="/mychatwww/kickUserOut/'+usersinroom[i].nickname+'" type="button" class="btn btn-danger">Kick</>';
        }






    </script>

</html>


