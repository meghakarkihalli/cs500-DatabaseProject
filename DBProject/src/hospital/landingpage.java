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

public class landingpage extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();  
		Connection con;
		PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6;
		String dept = (String)request.getAttribute("attributeName");
		String name = (String)request.getAttribute("attributeName1");
		int id = (int)request.getAttribute("attributeName2");
		
		ResultSet rs = null, rs1, rs2, rs3, rs4, rs5, rs6;
		int deptno = 0;
		
		if(dept.equals("Doctors"))
		{
			deptno=11;
		}
		else if(dept.equals("House Keeping"))
		{
			deptno=22;
		}
		else if(dept.equals("Nurse"))
		{
			deptno=33;
		}
		else if(dept.equals("Admin"))
		{
			deptno=44;
		}
		else if(dept.equals("Security"))
		{
			deptno=55;
		}
		
		try 
		{
			
		  	if(deptno==11)
		  	{
		  		Class.forName("org.postgresql.Driver");
				con=DriverManager.getConnection("jdbc:postgresql://twilbury.cs.drexel.edu/mk3465_cs500","mk3465","iukwrnzveb");  
				ps=con.prepareStatement("select distinct p.pname, p.pid, a.datentime from patient p, appointment a, doctors d where a.pid=p.pid and d.eid=a.eid and d.eid=? order by p.pname");
				ps.setInt(1, id);
				rs= ps.executeQuery();
				ps1=con.prepareStatement("select count(pid) from appointment where eid=?");
				ps1.setInt(1, id);
				rs1=ps1.executeQuery();
				RequestDispatcher rd;
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Doctor's Landing Page</title>");
				out.println("</head>");
				out.println("<link rel=\"stylesheet\" href=\"style.css\" />");
				out.println("<body style=\"background-image: url('texture.jpg');\">");
				out.println("<form action=\"logout\"><button name=\"logout\" style=\"position:absolute;left:1300px;top:10px;\" type=\"submit\" >Logout</button> </form>");
				out.println("<h2 style=\"position:absolute;left:100px;top:10px;font-size=20%;\"><b><u>Doctors name</b></u>: "+name+" </h2>");
				out.println("<h2 style=\"position:absolute; left:100px; top:60px;\"><b><u>Employee id</b></u>: "+ id +" </h2>");
				if(rs1.next())
				out.println("<h3 style=\"position:absolute; left:100px; top:150px;\"><b><u>Number of Patients</b></u>:" + rs1.getString(1) + "</h3>");
				out.println("<h3 style=\"position:absolute; left:100px; top:220px;\"><b><u>Patients Details</b></u>  </h3>");
				out.println("<table style=\"left:70px;top:270px;\">");
				out.println("<tr>");
				out.println("<th>Patient ID</th>");
				out.println("<th>Patient Name</th>");
				out.println("<th>Schedule</th>");
				out.println("<th>Records</th>");
				out.println("</tr>");
				
				while(rs.next())
				{
					out.println("<tr>");
				out.println("<td>"+ rs.getString(2) +"</td>");
				out.println("<td>"+rs.getString(1)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("<td> <form action=\"viewrecord\"><button name=\"bt\" type=\"submit\" value= "+rs.getString(2)+">view record</button></form></td>");
				out.println("</tr>");
				}
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");

		  	}
		  	else if(deptno==22)
		  	{
		  		Class.forName("org.postgresql.Driver");
				con=DriverManager.getConnection("jdbc:postgresql://twilbury.cs.drexel.edu/mk3465_cs500","mk3465","iukwrnzveb");  
				ps=con.prepareStatement("select eid,ename, phone, address from employees where eid=?");
				ps.setInt(1, id);
				rs= ps.executeQuery();
				if(rs==null)
				{
					out.println("wrong password");
				}
				out.println("<html>");
				out.println("<head>");
				out.println("<title>House Keeping Landing Page</title>");
				out.println("</head>");
				out.println("<link rel=\"stylesheet\" href=\"style.css\" />");
				out.println("<body style=\"background-image: url('texture.jpg');\">");
				out.println("<form action=\"logout\"><button name=\"logout\" style=\"position:absolute;left:1300px;top:10px;\" type=\"submit\" >Logout</button> </form>");
				out.println("<h2 style=\"position:absolute;left:100px;top:10px;font-size=20%;\"><b><u>House Keeping Employee name</b></u>: "+name+" </h2>");
				out.println("<h2 style=\"position:absolute; left:100px; top:60px;\"><b><u>Employee id</b></u>: "+ id +" </h2>");
				out.println("<h3 style=\"position:absolute; left:100px; top:220px;\"><b><u> Details</b></u>  </h3>");
				out.println("<table style=\"left:70px;top:270px;\">");
				out.println("<tr>");
				out.println("<th>Employee ID</th>");
				out.println("<th> Name</th>");
				out.println("<th>Contact</th>");
				out.println("<th>Address</th>");
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
				out.println("</body>");
				out.println("</html>");
		  		
		  	}
		
		else if(deptno==55)
	  	{
	  		Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql://twilbury.cs.drexel.edu/mk3465_cs500","mk3465","iukwrnzveb");  
			ps=con.prepareStatement("select eid,ename, phone, address from employees where eid=?");
			ps.setInt(1, id);
			rs= ps.executeQuery();
			if(rs==null)
			{
				out.println("wrong password");
			}
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Security Landing Page</title>");
			out.println("</head>");
			out.println("<link rel=\"stylesheet\" href=\"style.css\" />");
			out.println("<body style=\"background-image: url('texture.jpg');\">");
			out.println("<form action=\"logout\"><button name=\"logout\" style=\"position:absolute;left:1300px;top:10px;\" type=\"submit\" >Logout</button> </form>");
			out.println("<h2 style=\"position:absolute;left:100px;top:10px;font-size=20%;\"><b><u>Employee name</b></u>: "+name+" </h2>");
			out.println("<h2 style=\"position:absolute; left:100px; top:60px;\"><b><u>Employee id</b></u>: "+ id +" </h2>");
			out.println("<h3 style=\"position:absolute; left:100px; top:220px;\"><b><u> Details</b></u>  </h3>");
			out.println("<table style=\"left:70px;top:270px;\">");
			out.println("<tr>");
			out.println("<th>Employee ID</th>");
			out.println("<th> Name</th>");
			out.println("<th>Contact</th>");
			out.println("<th>Address</th>");
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
			out.println("</body>");
			out.println("</html>");
	  		
	  		
	  	}
		else if(deptno==44)
	  	{
	  		Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql://twilbury.cs.drexel.edu/mk3465_cs500","mk3465","iukwrnzveb");  
			ps=con.prepareStatement("select eid,ename, phone, address from employees where eid=?");
			ps.setInt(1, id);
			rs= ps.executeQuery();
			ps2=con.prepareStatement("select p1.pname,p1.max_age from (select pname, max(age(dob)) max_age from patient group by pname) as p1 where p1.max_age=(select max(age(dob)) from patient as p2)");
			rs2=ps2.executeQuery();
			ps3=con.prepareStatement("select e1.ename, min(salary) from employees e1 where salary in (select min(salary) from employees) group by e1.ename");
			rs3=ps3.executeQuery();
			ps4=con.prepareStatement("select e1.ename, max(salary) from employees e1 where salary in (select max(salary) from employees) group by e1.ename");
			rs4=ps4.executeQuery();
			ps5=con.prepareStatement("select i1.policy,i1.max_cover from (select policy, max(upper(coverage)) max_cover from insurance group by policy) as i1 where i1.max_cover=(select max(upper(coverage)) from insurance)");
			rs5=ps5.executeQuery();
			ps6=con.prepareStatement("select i1.policy,i1.min_cover from (select distinct policy, min(lower(coverage)) min_cover from insurance group by policy) as i1 where i1.min_cover=(select min(lower(coverage)) from insurance)");
			rs6=ps6.executeQuery();
			if(rs==null)
			{
				out.println("wrong password");
			}
			out.println("<html>");
			out.println("<head>");
			out.println("<style>");
			out.println("table {");
			   out.println("font-family: arial, sans-serif;");
			   out.println("border-collapse: collapse;");
			   out.println("width: 90%;");
			out.println("position:absolute;;left:70px;top:270px;");
			out.println("}");

			out.println("td, th {");
			   out.println("border: 1px solid #dddddd;");
			   out.println("text-align: left;");
			   out.println("padding: 8px;");
			out.println("}");

			out.println("tr:nth-child(even) {");
			   out.println("background-color: #dddddd;");
			out.println("}");

			
			out.println("</style>");
			out.println("</head>");
			out.println("<link rel=\"stylesheet\" href=\"style.css\" />");
			out.println("<body style=\"background-image: url('texture.jpg');\">");
			out.println("<form action=\"logout\"><button name=\"logout\" style=\"position:absolute;left:1300px;top:10px;\"type=\"submit\" >Logout</button> </form>");
			if(rs.next())
			{
				out.println("<h2 style=\"position:absolute;left:100px;top:10px;font-size=20%;\"><b><u>Admin name</b></u>: "+name+" </h2>");
				out.println("<h2 style=\"position:absolute; left:100px; top:60px;\"><b><u>Employee id</b></u>: "+ id +" </h2>");
			}
			//out.println("<h2 style=\"position:absolute;left:100px;top:180px;font-size=20%;\"><b><u>General Information</b></u></h2>");
			out.println("<h3 style=\"margin-top:200px; margin-left:250px;\">Add profile into : </h3>");
			out.println("<div style=\"display:block;\"><select name=\"insert\" style=\"margin-left:10%\" onchange=\"location = this.options[this.selectedIndex].value;\">");
			out.println("<option >select</option>");
			out.println("<option style=\"width:200px; margin-left:200px; text-align:center; \" value=\"insertemployee.html\">Employee</option>");
			out.println("<option value=\"insertPatient.html\">Patient</option>");
			out.println("</select>");
			while(rs2.next())
			{
				out.print("<h3 style=\"text-align:center;\">Oldest patient is "+rs2.getString(1)+ " has Age : "+rs2.getString(2)+"</h3>");
			}
			while(rs3.next())
			{
				out.print("<h3 style=\"text-align:center;\">Least Salary Paid : "+rs3.getString(2)+ " for employee : "+rs3.getString(1)+"</h3>");
				
			}
			//System.out.println(request.getParameter("insert"));
			while(rs4.next())
			{
				out.print("<h3 style=\"text-align:center;\">Highest Salary Paid : "+rs4.getString(2)+" for employee : "+rs4.getString(1)+"</h3>");
				
			}		
			while(rs5.next())
			{
				out.print("<h3 style=\"text-align:center;\">Current highest covered insurance is : "+rs5.getString(1)+" with coverage amount "+rs5.getString(2)+"</h3>");
				
			}
			while(rs6.next())
			{
				out.print("<h3 style=\"text-align:center;\">Current highest covered insurance is : "+rs6.getString(1)+" with coverage amount : "+rs6.getString(2)+"</h3>");
				
			}

			out.println("</body>");
			out.println("</html>");
	  		
	  		
	  	}
		else if(deptno==33)
	  	{
	  		Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql://twilbury.cs.drexel.edu/mk3465_cs500","mk3465","iukwrnzveb");  
			ps=con.prepareStatement("select * from nurse where eid=?");
			ps.setInt(1, id);
			rs= ps.executeQuery();
			if(rs==null)
			{
				out.println("wrong password");
			}
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Nurse's Landing Page</title>");
			out.println("</head>");
			out.println("<link rel=\"stylesheet\" href=\"style.css\" />");
			out.println("<body style=\"background-image: url('texture.jpg');\">");
			out.println("<form action=\"logout\"><button name=\"logout\" style=\"position:absolute;left:1300px;top:10px;\" type=\"submit\" >Logout</button> </form>");
			out.println("<h2 style=\"position:absolute;left:100px;top:10px;font-size=20%;\"><b><u>Nurse name</b></u>: "+name+" </h2>");
			out.println("<h2 style=\"position:absolute; left:100px; top:60px;\"><b><u>Employee id</b></u>: "+ id +" </h2>");
			out.println("<h3 style=\"position:absolute; left:100px; top:220px;\"><b><u> Details</b></u>  </h3>");
			out.println("<table style=\"left:70px;top:270px;\">");
			out.println("<tr>");
			out.println("<th>Employee ID</th>");
			out.println("<th>Nurse Name</th>");
			out.println("<th>Shift</th>");
			out.println("<th>Ward Numbmer</th>");
			out.println("</tr>");
			out.println("<tr>");
			while(rs.next())
			{
			out.println("<td>"+ rs.getString(1) +"</td>");
			out.println("<td>"+rs.getString(2)+"</td>");
			out.println("<td>"+rs.getString(3)+"</td>");
			out.println("<td>"+rs.getString(4)+"</td>");
			}
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");

	  		
	  	}
	
	
	}
		catch (ClassNotFoundException | SQLException e) 
		{
			System.err.println(e);
			out.println(e.toString());
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
