package Java_Code;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Post_Results")
public class Post_Results extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String year = request.getParameter("year");
		String section = request.getParameter("section");
		String choice = request.getParameter("choice");
		String sem = request.getParameter("sem");
		
		String branch="", subject="";
		
		try{
			HttpSession session1 = request.getSession(false);
			String id = (String) session1.getAttribute("id");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Attendance_Management","root","");
			String sql = "select * from lecturer where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				subject = rs.getString("subject");
				branch = rs.getString("branch");
			}
			
			HttpSession session3 = request.getSession();
			session3.setAttribute("year", year);
			session3.setAttribute("branch", branch);
			session3.setAttribute("section", section);
			session3.setAttribute("choice", choice);
			session3.setAttribute("subject", subject);
			session3.setAttribute("sem", sem);
			session3.setAttribute("id", id);
			
			
			String sql1 = "select * from "+year+sem+branch+section+"_internals";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ResultSet rs1 = ps1.executeQuery();
			out.print("<style>body{background-color:#DDC6B6;background-size:cover ;}.form{border:5px solid #262223; width:425px;height:325px;overflow-y:scroll;overflow-x:hidden;border-radius:14px;margin-left: 35%;margin-top: 10%; background-color:white;text-align:center;}form{color:black;}a{color: white;}</style>");
			out.print("<div class='form'><form action='Post_Results2' action='get' >");
			out.print("<table style='padding:50px;'><br>");
			
			out.print("<tr> <th> ID </th> <th> Name </th> <th> marks </th></tr>");
			while(rs1.next()) {
				out.print("<tr> <td style='padding:10px;'>"+rs1.getString("id")+" </td> <td style='padding:10px;'>"+rs1.getString("name")+"</td> <td style='padding:10px;'> <input type='text' name='"+rs1.getString("id")+"'> </td> </tr>");
			}
			out.print("</table>");
			out.print("<input type='submit' value='Submit' style='width:150px;border:4px solid #262223;border-radius: 5px;'>");
			out.print("</form><div>");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
