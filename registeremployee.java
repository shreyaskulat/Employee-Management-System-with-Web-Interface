package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/databaselink")
public class registeremployee extends HttpServlet{
	Connection con= null;
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
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		double salary = Double.parseDouble(req.getParameter("salary"));
		String desg = req.getParameter("desg");
		String user = req.getParameter("user");
		String pass = req.getParameter("password");
		PrintWriter pw = resp.getWriter();
		
		PreparedStatement pstmt = null;
		
		String query = "insert into customer values(?,?,?,?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1,0);
			pstmt.setString(2, name);
			pstmt.setString(3, address);
			pstmt.setDouble(4, salary);
			pstmt.setString(5, desg);
			pstmt.setString(6, user);
			pstmt.setString(7, pass);
			int count = pstmt.executeUpdate();
			pw.print("<h1 style='color:green'> Employee Register Successfully </h1>");
			
			RequestDispatcher rd = req.getRequestDispatcher("index.html");
			rd.include(req, resp);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}