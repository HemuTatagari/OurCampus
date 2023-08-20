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


@WebServlet("/Attendance1")
public class Attendance1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String year = request.getParameter("year");
		String section = request.getParameter("section");
		String branch = "", subject = "";
		
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
				branch = rs.getString("branch");
				subject = rs.getString("subject");
			}
			
			HttpSession session2 = request.getSession();
			session2.setAttribute("year", year);
			session2.setAttribute("branch", branch);
			session2.setAttribute("section", section);
			session2.setAttribute("subject", subject);
			
			String sql1 = "select * from "+ year + branch + section +"";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ResultSet rs1 = ps1.executeQuery();
			
			out.print("<form action='Attendance2' method='get'>");
			out.print("<div style='margin-top : 243px; margin-left : 543px;margin-right : 500px; border:4px solid cyan;'>");
			out.print("<table>");
			
			out.print("<tr> <th>ID </th> "+"<th> Name </th> "+" <th> Select </th></tr>");
			
			while(rs1.next()) {
				out.print("<tr> <td>"+rs1.getString("id")+" </td> "+"<td> "+rs1.getString("name")+" </td> "+" <td> <input type='checkbox' name='selected' value='"+rs1.getString("id")+"' > </td> </tr>");
			}
			
			out.print("</table>");
			out.print("</div>");
			out.print("<input type='submit' value='Submit'>");
			out.print("</form>");
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
