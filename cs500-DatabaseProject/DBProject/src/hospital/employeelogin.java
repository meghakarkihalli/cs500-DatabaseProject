package hospital;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;  
  
@SuppressWarnings("serial")
public class employeelogin extends HttpServlet 
{  
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{  
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
        String n=request.getParameter("uname");  
		String p=request.getParameter("psw");  
		          
		try
		{  
				Class.forName("org.postgresql.Driver");  
				Connection con=DriverManager.getConnection("jdbc:postgresql://twilbury.cs.drexel.edu/mk3465_cs500","mk3465","iukwrnzveb");
				PreparedStatement ps=con.prepareStatement("select did, ename, eid from employees where username=? and password=?");  
				ps.setString(1,n);  
				ps.setString(2,p);  
				ResultSet rs1 = ps.executeQuery();
				ResultSet rs2 = ps.executeQuery();
				RequestDispatcher rd;
				int s,count=0;
				String s1;
				int s2; 
				
				if(rs2.next())
				{
					s = rs2.getInt("did");
					s1 = rs2.getString("ename");
					s2 = rs2.getInt("eid");
					request.setAttribute("attributeName1",s1);
					request.setAttribute("attributeName2",s2);
					//System.out.println(rs.getString("did"));
					
					if(s==11)
					{
						//System.out.println(rs.getString("did"));
					request.setAttribute("attributeName","Doctors");
				
					}

					else if(s==22)
					{
						//System.out.println(rs.getString("did"));
					request.setAttribute("attributeName","House Keeping");
					
					}


					else if(s==33)
					{
						//System.out.println(rs.getString("did"));
					request.setAttribute("attributeName","Nurse");
					
					}

					else if(s==44)
					{
						//System.out.println(rs.getString("did"));
					request.setAttribute("attributeName","Admin");
					
					}


					else if(s==55)
					{
						//System.out.println(rs.getString("did"));
					request.setAttribute("attributeName","Security");
					
					}
					
					else
					{
					//System.out.println(rs.getString("did"));
						request.setAttribute("attributeName","Invalid Login");
						
					}
					rd = request.getRequestDispatcher("/landingpage");
					rd.forward(request,response);
				
				}
				else
				{
					out.println("<script type=\"text/javascript\">");
					
					out.println("alert(\"Wrong username or pasword!\")");
					out.println("window.location='employeelogin.html'");
					out.println("</script>");
				}
				
         }
		catch (Exception e2) {
			System.err.println(e2);
			out.println(e2.toString());
		}  
          
		out.close();  
	}  
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
	
	}
}  