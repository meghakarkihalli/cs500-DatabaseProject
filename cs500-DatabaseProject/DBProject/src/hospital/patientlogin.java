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
import org.postgresql.util.PSQLException;

@SuppressWarnings("serial")
public class patientlogin extends HttpServlet 
{  
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{  
		//response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
        String n=request.getParameter("uname");  
		String p=request.getParameter("psw");  
		
		          
		try
		{  
				Class.forName("org.postgresql.Driver");  
				Connection con=DriverManager.getConnection("jdbc:postgresql://twilbury.cs.drexel.edu/mk3465_cs500","mk3465","iukwrnzveb");  
				PreparedStatement ps=con.prepareStatement("select pid, pname from patient where pid=? and password=?");  
				ps.setString(1,n);  
				ps.setString(2,p);  
				ResultSet rs = ps.executeQuery();
				RequestDispatcher rd;
			
				String s1, s2;
				while(rs.next())
				{
					s1 = rs.getString(1);
					s2 = rs.getString(2);
					request.setAttribute("attributeName1",s1);
					request.setAttribute("attributeName2",s2);
				}
				rd = request.getRequestDispatcher("/patientlandingpage");
				rd.forward(request,response);
		}
		catch (SQLException e) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert(\"Invalid Username or Password!\")");
			out.println("</script>");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
