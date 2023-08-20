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
<title>IView Profile</title>
</head>
<body>
<%

HttpSession session1 = request.getSession(false);
String id = (String) session1.getAttribute("id");

try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Attendance_Management","root","");
	String sql = "select * from student where id = ?";
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setString(1, id);
	ResultSet rs = ps.executeQuery();
	
	out.print("<table>");
	
	while(rs.next()) {
		out.print("<tr><td> </td> <td> </td> </tr>");
	}
	
	out.print("</table>");
	
} catch(Exception e) {
	e.printStackTrace();
}

%>
</body>
</html>