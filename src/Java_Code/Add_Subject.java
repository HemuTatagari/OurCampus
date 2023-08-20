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


@WebServlet("/Add_Subject")
public class Add_Subject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String code = request.getParameter("code");
		String subject = request.getParameter("subject");
		String year = request.getParameter("year");
		String branch = request.getParameter("branch");
		String faculty = request.getParameter("faculty");
		String sem = request.getParameter("sem");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Attendance_Management","root","");
			String sql = "insert into subjects values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, subject);
			ps.setString(3, faculty);
			ps.setString(4, year);
			ps.setString(5, branch);
			ps.setString(6, sem);
			ps.executeUpdate();
			
			String sql1 = "alter table "+branch+year+sem+"_results add "+subject+" varchar(43) default 0";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.executeUpdate();
			
			String sql2 = "alter table "+year+sem+branch+"a_internals add "+subject+"_a1 varchar(43) default 0, "+subject+"_a2 varchar(43) default 0, "+subject+"_m1 varchar(43) default 0, "+subject+"_m2 varchar(43) default 0";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.executeUpdate();
			
			String sql3= "alter table "+year+branch+"a add "+subject+" int(43) default 0, "+subject+"_total int(43) default 0 ";
			PreparedStatement ps3 = con.prepareStatement(sql3);
			ps3.executeUpdate();
			
			
			
		
			
			response.sendRedirect("Add_Succesfull.jsp");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
