package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/loginlink")
public class EmpLogin extends HttpServlet{
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
		String user = req.getParameter("user");
		String pass= req.getParameter("password");
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		String dbuser = "";
		String dbpass = "";
		String name ="";
		PrintWriter pw = resp.getWriter();
		
		
		String query = "select * from customer where user=? and password=?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, user);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dbuser = rs.getString("user");
				dbpass = rs.getString("password");
				name = rs.getString("name");
				
			}
			
			
			if (user.equals(dbuser) && pass.equals(dbpass)) {
				HttpSession ses= req.getSession(true);
				req.setAttribute("name", name);
				req.setAttribute("username", user);
				RequestDispatcher rd= req.getRequestDispatcher("employeedashboardlink");
				rd.forward(req, resp);
			}
			else {
				resp.sendRedirect("index.html");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}