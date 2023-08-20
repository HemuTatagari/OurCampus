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

@WebServlet("/ViewAttendance_Lecturer")
public class ViewAttendance_Lecturer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String year = request.getParameter("year");
		String section = request.getParameter("section");
		
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
			
			String sql1 = "select * from "+year + branch + section+"";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ResultSet rs1 = ps1.executeQuery();
			
			out.print("<table>");
			out.print("<tr> <th> ID </th> "+"<th> Name </th>"+" <th> Total Classes </th> "+" <th> Attended </th> "+" <th> Percentage </th></tr>");
			
			while(rs1.next()) {
				out.print("<tr> <td> "+rs1.getString("id")+" </td> <td> "+rs1.getString("name")+" </td> <td> "+rs1.getInt(""+subject+"_total")+" </td> <td> "+rs1.getInt(""+subject+"")+" </td> <td> "+(rs1.getInt(""+subject+"") * 100 )/ (rs1.getInt(""+subject+"_total"))+" </td> </tr>");
			}
			out.print("</table>");
			
			out.print("<a href='Lecturer_Home.jsp'> Home</a>");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	} 

}
