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

@WebServlet("/professionallink")
public class Professional extends HttpServlet{
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
		HttpSession ses=req.getSession(false);
		PrintWriter pw=resp.getWriter();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String name = (String) ses.getAttribute("username");
		//String empName=(String) session.getAttribute("empName");
		String query="select id,salary,desg from customer where user=?";
		//pw.print("<h1> name="+name+"</h1>");
		
		if(ses!=null) {
			
			try {
				
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, name);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					int id=rs.getInt(1);
					double sal=rs.getDouble(2);
					String desg=rs.getString(3);
					pw.print("<h1> Id="+id+"</h1>");
					pw.print("<h1>Sal="+sal+"</h1>");
					pw.print("<h1>desg="+desg+"</h1>");
				}
				pw.print("<a href='logoutlink'><button>LOGOUT</button></a>");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}
