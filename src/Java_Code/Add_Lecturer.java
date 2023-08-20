package Java_Code;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Add_Lecturer")
public class Add_Lecturer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String branch = request.getParameter("branch");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String subject = request.getParameter("subject");
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Attendance_Management","root","");
			String sql = "insert into lecturer values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.setString(5, branch);
			ps.setString(6, mobile);
			ps.setString(7, address);
			ps.setString(8, gender);
			ps.setString(9, subject);
			
			ps.executeUpdate();
			
			String sql1 = "insert into roles values(?,?)";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1, id);
			ps1.setString(2, "lecturer");
			ps1.executeUpdate();
			
			// Add Succesfull
			response.sendRedirect("Add_Succesfull.jsp");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
