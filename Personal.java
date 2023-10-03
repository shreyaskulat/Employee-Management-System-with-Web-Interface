package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/personallink")
public class Personal extends HttpServlet {
	Connection con=null;
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8?user=root&password=sql@123");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession(false);
		PrintWriter pw=resp.getWriter();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String query="select address from customer where name=?";
		
		if(session!=null) {
			String empName=(String) session.getAttribute("empName");
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, empName);
				rs=pstmt.executeQuery();
				pw.print("<table border='2' bgcolor=pink>");
				pw.print("<tr>");
				pw.print("<th> Employee Name</th>");
				pw.print("<th> Employee Address</th>");
				pw.print("</tr>");
				if(rs.next()) {
					pw.print("<tr>");
					pw.print("<td>"+empName+"</td>");
					pw.print("<td>"+rs.getString(1)+"</td>");
					pw.print("</tr>");
				}
				pw.print("</table>");
				pw.print("<a href='logoutlink'><button>LOGOUT</button></a>");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}
