package hospital;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class viewrecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	PrintWriter out = response.getWriter(); 
    	String s = request.getParameter("bt");
    	ResultSet rs = null;
    	//System.out.println(s);
    	
    	try 
		{
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection("jdbc:postgresql://twilbury.cs.drexel.edu/mk3465_cs500","mk3465","iukwrnzveb");  
			PreparedStatement ps=con.prepareStatement("select  r.recid, p.pname, r.ailment,  r.tests, m.medname, r.medquantity, r.sideeffects, r.date, i.policy from record r, medicine m,insurance i, patient p where p.pid=? and m.mid=r.mid and p.iid=i.iid and p.pid=r.pid");
			ps.setString(1, s);
			rs= ps.executeQuery();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Patient Records</title>");
			out.println("</head>");
			out.println("<link rel=\"stylesheet\" href=\"style.css\" />");
			out.println("<body style=\"background-image: url('texture.jpg');>");
			out.println("<form action=\"logout\"><button name=\"logout\" style=\"position:absolute;left:1300px;top:10px;\" type=\"submit\" onClick=\"window.location='homepage.html';\" >Logout</button> </form>");
			out.println("<h2 style=\"position:absolute;left:100px;top:110px;font-size=20%;\"><b><u>Patient Record:</b></u></h2>");
			out.println("<table style=\"left:70px;top:270px;\">");
			out.println( "<tr>");
			out.println("<th>RecordID</th>");
			out.println("<th>Patient Name</th>");
			out.println("<th>Aliment</th>");
		    out.println("<th>Test</th>");
		    out.println("<th>Medicine</th>");
		    out.println("<th>Quantity</th>");
		    out.println("<th>Side effects</th>");
		    out.println("<th>Date</th>");
		    out.println("<th>Insurance Policy</th>");
		    out.println("</tr>");
			while(rs.next())
			{
				out.println( "<tr>");
			    out.println("<td>"+rs.getString(1)+"</td>");
			    out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("<td>"+rs.getString(4)+"</td>");
				out.println("<td>"+rs.getString(5)+"</td>");
				out.println("<td>"+rs.getString(6)+"</td>");
				out.println("<td>"+rs.getString(7)+"</td>");
				out.println("<td>"+rs.getString(8)+"</td>");
				out.println("<td>"+rs.getString(9)+"</td>");
				out.println( "</tr>");	
			}

			out.println("</body>");
			out.println("</html>");
		}
    	catch (ClassNotFoundException | SQLException e) 
		{
    		System.err.println(e);
			out.println(e.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    }
    
    
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
