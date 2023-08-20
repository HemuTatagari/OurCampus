<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="style2.css">
		<style>
			.login_body{
				background-color:#DDC6B6;
				background-repeat:no-repeat;
				width:100%:
				position: fit;
			}
		</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>

 <body class="login_body">
		<center>
		<img src="" style="width:120px;height:120px;margin-top:10px;">
		</center>
		<section class="login_section" style="background-color:white;border:4px solid black;">
			<div class="box_container">
				<div class="image">
					<img src=".png">
				</div>
				
				<div class="login">
					<h3>Welcome...</h3>
					<font size=5px>Together...we get Better...</font>
					<form action="./Login2" method="get">
					<div class="sid">
						<input type="text" name="id" placeholder="Enter ID" size="43px">
					</div>
					
					<div class="password">
						<input type="password" name="password" placeholder="Password" size="43px">
						<br><font color="red" font-size = "10px"> ID Not Found.</font> <br>
					</div>
						
					<div class="submit">
					
						<input class="button" type="submit" value="Login" style="width:143px;border:4px solid #262223;border-radius: 5px;">
						
					</div>

					</form>
					
				</div>
			</div>
		
		</section>
	
	</body>
</html>