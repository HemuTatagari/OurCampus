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


@WebServlet("/Attendance2")
public class Attendance2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String year= "", branch = "", section = "", subject ="";
		int new_att = 0, tot = 0;
		
		String[] selected = request.getParameterValues("selected");
		
		try{
			HttpSession session2 = request.getSession(false);
			year = (String) session2.getAttribute("year");
			branch = (String) session2.getAttribute("branch");
			section = (String) session2.getAttribute("section");
			subject = (String) session2.getAttribute("subject");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Attendance_Management","root","");
			int i = 0;
			while(i < selected.length) {
				String sql = "select * from "+ year + branch + section +" where id = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, selected[i]);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					tot = rs.getInt(""+subject+"_total") + 1;
					new_att = rs.getInt(""+subject+"") + 1;
				}
				
				String sql1 = "update "+ year + branch + section +" set "+subject+" = ? where id = ?";
				PreparedStatement ps1 = con.prepareStatement(sql1);
				ps1.setInt(1, new_att);
				ps1.setString(2, selected[i]);
				ps1.executeUpdate();
				
				i++;
					
			}
			
			String sql2 = "update "+ year + branch + section +" set "+subject+"_total = ?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setInt(1, tot);
			ps2.executeUpdate();
				
			
			
			/*
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Attendance_Management","root","");
			String sql = "select * from "+ year + branch + section +"";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			int i = 0;
			int size = selected.length;
			while(i < size) {
				String sql1 = "update "+ year + branch + section +" set "+subject+" = ? where id = ?";
				PreparedStatement ps1 = con.prepareStatement(sql1);
				ps1.setInt(1, rs.getInt("subject") + 1);
				ps1.setString(2, rs.getString("id"));
				ps1.executeUpdate();.
				}
				
				new_att = rs.getInt(""+subject+"_total") + 1;
				String sql2 = "update "+ year + branch + section +" set "+subject+"_total = ? where id = ?";
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ps2.setInt(1, new_att);
				ps2.setString(2, rs.getString("id"));
				ps2.executeUpdate();
				
				
			}
			*/
			response.sendRedirect("AttendancePostSuccesfull.jsp");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
