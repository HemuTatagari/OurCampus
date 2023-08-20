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


@WebServlet("/Update_Results")
public class Update_Results extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String sem = request.getParameter("sem");
		String year = request.getParameter("year");
		String branch = request.getParameter("branch");
		String section = request.getParameter("section");
		
		HttpSession session3 = request.getSession();
		session3.setAttribute("year", year);
		session3.setAttribute("sem", sem);
		session3.setAttribute("branch", branch);
		session3.setAttribute("section", section);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Attendance_Management","root","");
			String sql = "select * from "+year+branch+section+"";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			out.print("<form action='Update_Results2' method='get'>");
			out.print("<table>");
			out.print("<tr> <th>ID </th> "+"<th> Name </th> ");
			String sql3 = "select * from subjects where year = ? and branch = ? and sem= ?";
			PreparedStatement ps3 = con.prepareStatement(sql3);
			ps3.setString(1, year);
			ps3.setString(2, branch);
			ps3.setString(3, sem);
			ResultSet rs3 = ps3.executeQuery();
			
			while(rs3.next()) {
				out.print("<th>"+rs3.getString("subject")+"</th>");
			}
			
			out.print(" <th> Total </th></tr>");
			while(rs.next()){
				out.print("<tr> <td>"+rs.getString("id")+" </td> "+"<td> "+rs.getString("name")+" </td> ");
				String sql4 = "select * from subjects where year = ? and branch = ? and sem= ?";
				PreparedStatement ps4 = con.prepareStatement(sql4);
				ps4.setString(1, year);
				ps4.setString(2, branch);
				ps4.setString(3, sem);
				ResultSet rs4 = ps4.executeQuery();
				
				while(rs4.next()) {
					out.print("<td> <input type='text' name='"+rs.getString("id")+"_"+rs4.getString("subject")+"'</td>");
				}
				
				out.print("<td> <input type='text' name='"+rs.getString("id")+"' placeholder='Grade...'> </td> </tr>");
			}
			out.print("</table>");
			
			out.print("<input type='submit' value='Post'>");
			out.print("</form>");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
