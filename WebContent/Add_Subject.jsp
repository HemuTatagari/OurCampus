<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Subject</title>
</head>
<body>

<form action="Add_Subject" method="get" >
<input type="text" name="code" placeholder="Subject Code">
<input type="text" name="subject" placeholder="Subject Name">
<input type="text" name="faculty" placeholder="Faculty Name">
<input type="text" name="year" placeholder="Year">
<input type="text" name="branch" placeholder="Branch">
<input type="text" name="sem" placeholder="Semester">
<input type="submit" value="ADD">

<a href="Admin_Home.jsp">Back</a>

</form>

</body>
</html>