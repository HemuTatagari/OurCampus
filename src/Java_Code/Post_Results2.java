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


@WebServlet("/Post_Results2")
public class Post_Results2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try{
			HttpSession session3 = request.getSession(false);
			String year = (String) session3.getAttribute("year");
			String branch = (String) session3.getAttribute("branch");
			String section = (String) session3.getAttribute("section");
			String choice = (String) session3.getAttribute("choice");
			String id = (String) session3.getAttribute("id");
			String subject = (String) session3.getAttribute("subject");
			String sem = (String) session3.getAttribute("sem");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Attendance_Management","root","");
			String sql = "select * from "+year+sem+branch+section+"_internals";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String sql1 = "update "+year+sem+branch+section+"_internals set "+subject+"_"+choice+" = ? where id = ?";
				PreparedStatement ps1 = con.prepareStatement(sql1);
				ps1.setString(1, request.getParameter(""+rs.getString("id")+""));
				ps1.setString(2, rs.getString("id"));
				ps1.executeUpdate();
			}
			
			
			response.sendRedirect("AttendancePostSuccesfull.jsp");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
