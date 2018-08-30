package hospital;

import java.beans.Statement;
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


public class insertemployee extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();  
		Connection con = null;
		PreparedStatement ps,ps2;
		java.sql.Statement st;
		int eid=Integer.parseInt(request.getParameter("eid"));
		String username=request.getParameter("username");  
		String password=request.getParameter("password");  
		String ename=request.getParameter("ename");
		//String date = (request.getParameter("dob"));
		String dob=request.getParameter("dob");
		String gender=request.getParameter("gender");
		long phone=Long.parseLong(request.getParameter("phone"));
		String address=request.getParameter("address");
		int did=Integer.parseInt(request.getParameter("did"));
		int salary=Integer.parseInt(request.getParameter("salary"));
		
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
				int val = st.executeUpdate("insert into employees values ('"+eid+"','"+username+"','"+password+"','"+ename+"','"+mySqlDate+"','"+gender+"','"+phone+"','"+address+"','"+did+"','"+salary+"')");
				out.println("<script type=\"text/javascript\">");
				out.println("alert(\"Added!\")");
				out.println("window.location='insertemployee.html'");
				out.println("</script>");
				
		}
		catch (SQLException e) 
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert(\"Wrong Entry!\")");
			out.println("window.location='insertemployee.html'");
			out.println("</script>");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
