<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Post Attendance</title>
<link rel="stylesheet" href="style2.css">
<style>
body{
            background-color:#DDC6B6;
            background-size:cover ;
        }
        .form{
            border:5px solid #262223;
            width:250px;
           height:250px;
            border-radius:14px;
            margin-left: 40%;
            margin-top: 15%;
            background-color:white;
            text-align:center;
            padding:15px;
        }
        form{
            color:black;
        }
        /* a{
            text-decoration: none;
            color: white;
        } */
        input{
        margin:5px;
        }
</style>
</head>
<body>
<header class="home" >
	<a href="#" class="logo"><img src="images/logo.jpg" style="font-size:30px;padding-right: 10px;width:50px;height:50px;"></img>alpha-H</a>
	<nav class="navbar">
		<a class="active" href="index.html">HOME</a>
		<a href="about.html">About Us</a>
		<a href="services.html">SERVICES</a>
		<a href="team.html">TEAM</a>
	</nav>
</header>
<div class="form">
<form action="Attendance1" method="get"><font color="#F4A950"><h1 align="center">Attendance</h1></font>

<input type="text" name="year" placeholder="Year"><br><br>
<input type="text" name="section" placeholder="Section" ><br>

<input type="submit" value="Continue">

</form>
</div>
</body>
</html>