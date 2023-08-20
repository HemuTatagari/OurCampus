<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="java.sql.Connection" %>
    <%@ page import="java.sql.DriverManager" %>
    <%@ page import="java.sql.ResultSet" %>
     <%@ page import="java.sql.PreparedStatement" %>
     <%@ page import="java.io.PrintWriter" %>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<style>
body{
            background-color:#DDC6B6;
            background-size:cover ;
        }
        .form{
            border:5px solid #262223;
            width:425px;
            height:325px;
            border-radius:14px;
            margin-left: 35%;
            margin-top: 10%;
            background-color:white;
            text-align:center;
            
        }
        form{
            color:black;
        }
        a{
             text-decoration: none; 
            color: black;
        }
</style>
</head>
<body>

<a href="View_Attendance1.jsp">View Attendance</a>
<a href="View_Results.jsp">View Results</a>
<a href="View_Internals.jsp">View Internals</a>
<a href="ViewProfile_Student.jsp">Profile</a>
<a href="index.jsp">Logout</a>

</body>
</html>