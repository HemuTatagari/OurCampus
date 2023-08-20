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


@WebServlet("/Login2")
public class Login2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		int flag = 1;
		String role="", corr_pass = "", branch=" ";
		 
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Attendance_Management","root","");
			String sql = "select * from roles where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				flag = 0;
				role = rs.getString("role");
				String sql1 = "select * from "+role+" where id = ?";
				PreparedStatement ps1 = con.prepareStatement(sql1);
				//ps1.setString(1, role);
				ps1.setString(1, id);
				ResultSet rs1 = ps1.executeQuery();
				
				while(rs1.next()){
					corr_pass = rs1.getString("password");
					if(role.equals("student")) {
						branch = rs1.getString("branch");
					}
				}
				
				if(corr_pass.equals(password)){
					if(role.equals("student")) {
						response.sendRedirect("Student_Home.jsp"); // student home
					} else if(role.equals("admin")) {
						response.sendRedirect("Admin_Home.jsp"); //  Admin home
					}else {
						
					
						response.sendRedirect("Lecturer_Home.jsp"); // Lecturer home
					}
				
					HttpSession session1 = request.getSession();
					session1.setAttribute("id", id);
					session1.setAttribute("branch", branch);
				} else {
					response.sendRedirect("LoginInvalidPassword.jsp");
				}
			}
			
			if(flag == 1) {
				response.sendRedirect("LoginInvalidId.jsp");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
