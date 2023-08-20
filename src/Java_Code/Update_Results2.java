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


@WebServlet("/Update_Results2")
public class Update_Results2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		try{
			HttpSession session3 = request.getSession(false);
			String year = (String) session3.getAttribute("year");
			String sem = (String) session3.getAttribute("sem");
			String branch = (String) session3.getAttribute("branch");
			String section = (String) session3.getAttribute("section");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Attendance_Management","root","");
			String sql = "select * from student where year = ? and branch = ? and section = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, year);
			ps.setString(2, branch);
			ps.setString(3, section);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				String sql1 = "update student set g"+year+sem+"= ? where id = ?";
				PreparedStatement ps1 = con.prepareStatement(sql1);
				ps1.setString(1, request.getParameter(""+rs.getString("id")+""));
				ps1.setString(2, rs.getString("id"));
				
				ps1.executeUpdate();
				
				String sql2 = "select * from subjects where year = ? and branch = ? and sem= ?";
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ps2.setString(1, year);
				ps2.setString(2, branch);
				ps2.setString(3, sem);
				ResultSet rs2 = ps2.executeQuery();
				
				while(rs2.next()) {
					String sql3 = "update "+branch+year+sem+"_results set "+rs2.getString("subject")+"= ?, total = ? where id = ?";
					PreparedStatement ps3 = con.prepareStatement(sql3);
					ps3.setString(1, request.getParameter(""+rs.getString("id")+"_"+rs2.getString("subject")+""));
					ps3.setString(2, request.getParameter(""+rs.getString("id")+"") );
					ps3.setString(3, rs.getString("id"));
					
					ps3.executeUpdate();
				}
				
			}
			
			response.sendRedirect("Add_Succesfull.jsp");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
