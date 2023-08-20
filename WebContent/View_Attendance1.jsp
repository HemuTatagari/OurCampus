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
<title>View Attendance</title>
<link rel="stylesheet" href="style2.css">
</head>
<body>

<%

HttpSession session1 = request.getSession(false);
String id = (String) session1.getAttribute("id");

String year = "", branch = "", section = "", sem="";

try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Attendance_Management","root","");
	String sql = "select * from student where id = ?";
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setString(1, id);
	ResultSet rs = ps.executeQuery();

	while(rs.next()) {
		year = rs.getString("year");
		branch = rs.getString("branch");
		section = rs.getString("section");
		sem = rs.getString("sem");
	}
	
	String sql1 = "select * from "+ year + branch + section +" where id = ?";
	PreparedStatement ps1 = con.prepareStatement(sql1);
	ps1.setString(1, id);
	ResultSet rs1 = ps1.executeQuery();
	out.print("<header class='home' ><a href=''#' class='logo'><img src='images/logo.jpg' style='font-size:30px;padding-right: 10px;width:50px;height:50px;''></img>alpha-H</a><nav class='navbar'><a class='active' href='index.html'>HOME</a><a href='about.html'>About Us</a><a href='services.html'>SERVICES</a><a href='team.html'>TEAM</a></nav></header>");

	out.print("<style>body{background-color:#DDC6B6;background-size:cover ;}.form{border:5px solid #262223; width:400px;height:300px;border-radius:14px;margin-left: 35%;margin-top: 15%; background-color:white;text-align:center;}form{color:black;}a{color: black;}td{padding:10px;}</style>");
    out.print("<div class='form'>");
	out.print("<table style='padding-left:25%;'><br>");
	out.print("<font color='#F4A950'><h1 align='center'>Profile</h1></font>");
	
	while(rs1.next()) {
		String sql2 = "select * from subjects where year = ? and branch = ? and sem= ?";
		PreparedStatement ps2 = con.prepareStatement(sql2);
		ps2.setString(1, year);
		ps2.setString(2, branch);
		ps2.setString(3, sem);
		ResultSet rs2 = ps2.executeQuery();
		while(rs2.next()){
			if(rs1.getInt(""+rs2.getString("subject")+"_total") != 0) {
				out.print("<tr> <td style='color:#F4A950;'> "+rs2.getString("subject")+" </td>"+"<td> "+rs1.getInt(""+rs2.getString("subject")+"_total")+" </td>"+"<td> "+rs1.getInt(""+rs2.getString("subject")+"")+" </td>"+"<td> "+(rs1.getInt(""+rs2.getString("subject")+"") * 100) / rs1.getInt(""+rs2.getString("subject")+"_total")+" </td> </tr>");
			} else {
				out.print("<tr> <td style='color:#F4A950;'> "+rs2.getString("subject")+" </td>"+"<td> "+rs1.getInt(""+rs2.getString("subject")+"_total")+" </td>"+"<td> "+rs1.getInt(""+rs2.getString("subject")+"")+" </td>"+"<td> 0 </td> </tr>");
			}
		}
		
		/* out.print("<tr> <td>"+rs1.+" </td> "+"<td> "+rs1.getString(5)+" </td> "+" <td> "+ rs.getString(3)+" </td> </tr>"); */
	}
	
	out.print("</table>");
	out.print("<a href='Student_Home.jsp'> Back </a>");
	
	
} catch(Exception e) {
	e.printStackTrace();
}



%>
</body>
</html>