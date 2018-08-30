package hospital;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.postgresql.util.PSQLException;


public class insertpatient extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();  
		Connection con;
		PreparedStatement ps;
		java.sql.Statement st;
		
		String pid=(request.getParameter("pid"));
		String pname=request.getParameter("pname");
		String address=request.getParameter("address");
		String dob=request.getParameter("dob");
		String gender=request.getParameter("gender");
		String nominee=request.getParameter("nominee");
		String iid=(request.getParameter("iid"));
		String password=request.getParameter("password");  
				
		Date date = null;
		java.sql.Date mySqlDate = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
			mySqlDate = new java.sql.Date(date.getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try
		{  
				Class.forName("org.postgresql.Driver");  
				con=DriverManager.getConnection("jdbc:postgresql://twilbury.cs.drexel.edu/mk3465_cs500","mk3465","iukwrnzveb");  
				st = con.createStatement();
				int val = st.executeUpdate("insert into patient values ('"+pid+"','"+pname+"','"+address+"','"+mySqlDate+"','"+gender+"','"+nominee+"','"+iid+"','"+password+"')");
				out.println("<script type=\"text/javascript\">");
				out.println("alert(\"Added!\")");
				out.println("window.location='insertpatient.html'");
				out.println("</script>");
		
		}
		
		catch (ClassNotFoundException e) {
			System.err.println(e);
			out.println(e.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert(\"Wrong Entry!\")");
			out.println("window.location='insertpatient.html'");
			out.println("</script>");
			e.printStackTrace();
		}
	
	}


/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
}

}