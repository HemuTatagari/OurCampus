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

@WebServlet("/View_Results")
public class View_Results extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String choice = request.getParameter("choice");
		
		try{
			HttpSession session1 = request.getSession(false);
			String id = (String) session1.getAttribute("id");
			String branch = (String) session1.getAttribute("branch");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Attendance_Management","root","");
			String sql = "select * from "+branch + choice+"_results where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				char year = choice.charAt(0);
				String sql1 = "select * from subjects where year = "+choice.charAt(0)+" and branch = ? and sem= "+choice.charAt(1)+"";
				PreparedStatement ps1 = con.prepareStatement(sql1);
				ps1.setString(1, branch);
				ResultSet rs1 = ps1.executeQuery();
				out.print("ID : "+rs.getString("id")+"");
				out.print("Name : "+rs.getString("name")+"");
				out.print("<table>");
				out.print("<tr> <th>Subject</th> <th>Max Grade </th> <th> Grade Scored </th></tr>");
				while(rs1.next()) {
					out.print("<tr><td> "+rs1.getString("subject")+" </td> <td> 10 </td>  <td>"+rs.getString(""+rs1.getString("subject")+"")+" </td>  </tr>");
				}
				
				out.print("<tr><td> Total </td> <td> 10 </td>  <td>"+rs.getString("total")+" </td>  </tr>");
				
				out.print("</table>");
				
			}
			
			out.print("<a href='Student_Home.jsp'>Home</a>");
			
			
		} catch(Exception e) {
			response.sendRedirect("Wrong.jsp");
		}
	}

}
