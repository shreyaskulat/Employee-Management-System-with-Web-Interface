package test;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/employeedashboardlink")
public class employeedashboard extends HttpServlet{
	Connection con = null;
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8?user=root&password=sql@123");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PreparedStatement pstmt = null;
		HttpSession ses=req.getSession(true);
		ResultSet rs = null;
		String user = (String) req.getAttribute("username");
		String name = (String) req.getAttribute("name");
		ses.setAttribute("username",user);
		PrintWriter pw = resp.getWriter();
		pw.print("<h1>hello "+name+"</h1>");
		
		pw.print("<a href='personallink'>Get Personal Information</a><br><br>");
		pw.print("<a href='professionallink'>Get Professional Information</a><br><br><br>");
		pw.print(user);
		pw.print(name);
		pw.print("<a href='logoutlink' ><input type='submit' value='LOGOUT' /></a>");
		
	}
}