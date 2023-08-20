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
<title>Profile</title>
<link rel="stylesheet" href="style2.css">
</head>
<body>

<%

try {
	HttpSession session1 = request.getSession(false);
	String id = (String) session1.getAttribute("id");
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Attendance_Management","root","");
	String sql = "select * from student where id = ?";
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setString(1, id);
	ResultSet rs = ps.executeQuery();
	out.print("<header class='home' ><a href=''#' class='logo'><img src='images/logo.jpg' style='font-size:30px;padding-right: 10px;width:50px;height:50px;''></img>alpha-H</a><nav class='navbar'><a class='active' href='index.html'>HOME</a><a href='about.html'>About Us</a><a href='services.html'>SERVICES</a><a href='team.html'>TEAM</a></nav></header>");

	out.print("<style>body{background-color:#DDC6B6;background-size:cover ;}.form{border:5px solid #262223; width:500px;height:500px;overflow-y:scroll;overflow-x:hidden;border-radius:14px;margin-left: 35%;margin-top: 10%; background-color:white;text-align:center;}form{color:black;}a{color: black;}td{padding:10px;}</style>");
    out.print("<div class='form'>");
	out.print("<table style='padding-left:20%;'><br>");
	out.print("<font color='#F4A950'><h1 align='center'>Profile</h1></font>");
	while(rs.next()) {
		
		out.print("<tr> <td style='color:#F4A950;'> ID :  </td> "+" <td> "+rs.getString("id")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> First Name :  </td> "+" <td> "+rs.getString("firstName")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> Last Name :  </td> "+" <td> "+rs.getString("lastName")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> Father Name :  </td> "+" <td> "+rs.getString("fatherName")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> Mother Name :  </td> "+" <td> "+rs.getString("motherName")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> Password :  </td> "+" <td> "+rs.getString("password")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> Year :  </td> "+" <td> "+rs.getString("year")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> Branch :  </td> "+" <td> "+rs.getString("branch")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> Section :  </td> "+" <td> "+rs.getString("section")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> Mobile :  </td> "+" <td> "+rs.getString("mobile")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> E-mail :  </td> "+" <td> "+rs.getString("mail")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> Address :  </td> "+" <td> "+rs.getString("address")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> Religion :  </td> "+" <td> "+rs.getString("caste")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> Caste :  </td> "+" <td> "+rs.getString("religion")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> Aadhar No :  </td> "+" <td> "+rs.getString("aadhar")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> School :  </td> "+" <td> "+rs.getString("school")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> SSC Grade :  </td> "+" <td> "+rs.getString("school_grade")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'>College :  </td> "+" <td> "+rs.getString("college")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> Inter Grade :  </td> "+" <td> "+rs.getString("pre_grade")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> I Year I sem :  </td> "+" <td> "+rs.getString("g11")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> I Year II sem :  </td> "+" <td> "+rs.getString("g12")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> II Year I sem :  </td> "+" <td> "+rs.getString("g21")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> II Year II sem :  </td> "+" <td> "+rs.getString("g22")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> III Year I sem :  </td> "+" <td> "+rs.getString("g31")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> III Year II sem :  </td> "+" <td> "+rs.getString("g32")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> IV Year I sem :  </td> "+" <td> "+rs.getString("g41")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> IV Year II sem :  </td> "+" <td> "+rs.getString("g42")+" </td> </tr>");
		out.print("<tr> <td style='color:#F4A950;'> Fee Category :  </td> "+" <td> "+rs.getString("fee_category")+" </td> </tr>");
	}
	
	out.print("<table>");
	
	out.print("<a href='Student_Home.jsp'> Home </a><br></div><br>");
	
} catch(Exception e) {
	e.printStackTrace();
}


%>



</body>
</html>