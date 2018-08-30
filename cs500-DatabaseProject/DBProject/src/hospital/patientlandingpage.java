package hospital;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class patientlandingpage extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		PreparedStatement ps1, ps2;
		String id = (String)request.getAttribute("attributeName1");
		
		ResultSet rs = null,rs2;
		try
		{
		Class.forName("org.postgresql.Driver");
		Connection con=DriverManager.getConnection("jdbc:postgresql://twilbury.cs.drexel.edu/mk3465_cs500","mk3465","iukwrnzveb");  
		ps1=con.prepareStatement("select distinct p.pname, d.ename, a.datentime, r.ailment, p.pid from patient p, doctors d, appointment a, record r where p.pid=a.pid and a.eid=d.eid and p.pid=r.pid and p.pid=?");
		ps1.setString(1,id );
		rs= ps1.executeQuery();
		ps2=con.prepareStatement("Select i.iname, i.coverage, i.policy from insurance i, patient p where i.iid=p.iid and p.pid=?");
		ps2.setString(1, id);
		rs2=ps2.executeQuery();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Patient Landing Page</title>");
		out.println("</head>");
		out.println("<link rel=\"stylesheet\" href=\"style.css\" />");
		out.println("<body style=\"background-image: url('texture.jpg');\">");
		out.println("<form action=\"logout\"><button name=\"logout\" style=\"position:absolute;left:1300px;top:10px;\" type=\"submit\" >Logout</button> </form>");
		out.println("<h2 style=\"position:absolute;left:100px;top:10px;font-size=20%;\"><b><u>Patient Appointment History</b></u></h2>");
		out.println("<h3 style=\"position:absolute; left:100px; top:120px;\"><b><u>Isurance Details</b></u>  </h3>");
		out.println("<table style=\"left:70px;top:170px;\">");
		out.println("<tr>");
		out.println("<th>Insurance Company Name</th>");
		out.println("<th>Coverage</th>");
		out.println("<th>Policy</th>");
		out.println("</tr>");
		while(rs2.next())
		{
		out.println("<tr>");
		out.println("<td>"+ rs2.getString(1) +"</td>");
		out.println("<td>"+rs2.getString(2)+"</td>");
		out.println("<td>"+rs2.getString(3)+"</td>");
		out.println("<tr>");
		}
		
		out.println("<h3 style=\"position:absolute; left:100px; top:320px;\"><b><u>Details</b></u>  </h3>");
		out.println("<table style=\"left:70px;top:370px;\" \">");
		out.println("<tr>");
		out.println("<th>Patient Name</th>");
		out.println("<th>Attending Doctor</th>");
		out.println("<th>Date and Time</th>");
		out.println("<th>Ailment</th>");
		out.println("</tr>");
		while(rs.next())
		{
		out.println("<tr>");
		out.println("<td>"+ rs.getString(1) +"</td>");
		out.println("<td>"+rs.getString(2)+"</td>");
		out.println("<td>"+rs.getString(3)+"</td>");
		out.println("<td>"+rs.getString(4)+"</td>");
		out.println("</tr>");
		}
		out.println("</table>");
		out.println("<form action=\"viewrecord\"><button style=\" margin-top:450px;margin-left:40%;width:200px;height:50px\" name=\"bt\" type=\"submit\" value= "+id+"><b>View All Record</b></button></form>");
		out.println("</body>");
		out.println("</html>");
  		
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			// TODO Auto-generated catch block
			System.err.println(e);
			out.println(e.toString());
			e.printStackTrace();
		}
			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
