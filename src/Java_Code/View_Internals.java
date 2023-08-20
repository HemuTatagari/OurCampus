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


@WebServlet("/View_Internals")
public class View_Internals extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String choice = request.getParameter("choice");
		String branch = "", section = "", firstName = "", lastName = "";
		try {
			HttpSession session1 = request.getSession(false);
			String id = (String) session1.getAttribute("id");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Attendance_Management","root","");
			String sql = "select * from student where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				branch = rs.getString("branch");
				firstName = rs.getString("firstName");
				lastName = rs.getString("lastName");
				section = rs.getString("section");
			}
			
			String sql1 = "select * from subjects where year = "+choice.charAt(0)+" and branch = ? and sem= "+choice.charAt(1)+"";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1, branch);
			ResultSet rs1 = ps1.executeQuery();
			
			out.print("ID :"+id+"");
			out.print("Name :"+firstName + lastName+ "");
			out.print("<table>");
			
			out.print("<tr> <th>Code </th> <th> Subject </th> <th> Assignment 1 </th> <th> Assignment 2 </th> <th> Internal 1</th> <th> Internal 2 </th></tr>");
			while(rs1.next()) {
				out.print("<tr>");
				out.print("<td> "+rs1.getString("code")+" </td>");
				out.print("<td> "+rs1.getString("subject")+" </td>");
				
				String sql2 = "select * from "+choice.charAt(0)+ choice.charAt(1) + branch + section+"_internals where id = ?";
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ps2.setString(1, id);
				ResultSet rs2 = ps2.executeQuery();
				
				while(rs2.next()) {
					out.print("<td> "+rs2.getString("" +rs1.getString("subject")+"_a1")+" </td>");
					out.print("<td> "+rs2.getString("" +rs1.getString("subject")+"_a2")+" </td>");
					out.print("<td> "+rs2.getString("" +rs1.getString("subject")+"_m1")+" </td>");
					out.print("<td> "+rs2.getString("" +rs1.getString("subject")+"_m2")+" </td>");
				}
				
				
				out.print("</tr>");
			}
			
			out.print("</table>");
			out.print("<a href='Student_Home.jsp'> Home </a>");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
