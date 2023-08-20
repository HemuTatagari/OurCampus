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


@WebServlet("/Add_Student")
public class Add_Student extends HttpServlet {
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
		String fee_category = request.getParameter("category");
		String year = request.getParameter("year");
		String section = request.getParameter("section");
		String mail = request.getParameter("mail");
		String fatherName = request.getParameter("fatherName");
		String motherName = request.getParameter("motherName");
		String caste = request.getParameter("caste");
		String religion = request.getParameter("religion");
		String aadhar = request.getParameter("aadhar");
		String school = request.getParameter("school");
		String school_grade = request.getParameter("school_grade");
		String college = request.getParameter("college");
		String pre_grade = request.getParameter("pre_grade");
		String sem = request.getParameter("sem");
		
		
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Attendance_Management","root","");
			String sql = "insert into student values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.setString(5,year);
			ps.setString(30, sem);
			ps.setString(6, branch);
			ps.setString(7, section);
			ps.setString(8, mobile);
			ps.setString(9, mail);
			ps.setString(10, address);
			ps.setString(11, fatherName);
			ps.setString(12, motherName);
			ps.setString(13, aadhar);
			ps.setString(14, caste);
			ps.setString(15, religion);
			ps.setString(16, school);
			ps.setString(17, school_grade);
			ps.setString(18, pre_grade);
			ps.setString(19,college);
			ps.setString(20, null);
			ps.setString(21, null);
			ps.setString(22, null);
			ps.setString(23, null);
			ps.setString(24, null);
			ps.setString(25, null);
			ps.setString(26, null);
			ps.setString(27, null);
			ps.setString(28, gender);
			ps.setString(29, fee_category);
			
			
			ps.executeUpdate();
			
			String sql1 = "insert into roles values(?,?)";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1, id);
			ps1.setString(2, "student");
			ps1.executeUpdate();
			
			String sql2 = "insert into "+year+branch+section+"(id, name) values(?,?)";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, id);
			ps2.setString(2, firstName + lastName);
			ps2.executeUpdate();
			
			String sql3 = "insert into "+branch+year+sem+"_results (id, name) values(?,?)";
			PreparedStatement ps3 = con.prepareStatement(sql3);
			ps3.setString(1, id);
			ps3.setString(2, firstName + lastName);
			ps3.executeUpdate();
			
			String sql4 = "insert into "+year+branch+section+"_internals (id, name) values(?,?)";
			PreparedStatement ps4 = con.prepareStatement(sql4);
			ps4.setString(1, id);
			ps4.setString(2, firstName + lastName);
			ps4.executeUpdate();
			
			// Add Succesfull
			response.sendRedirect("Add_Succesfull.jsp");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
