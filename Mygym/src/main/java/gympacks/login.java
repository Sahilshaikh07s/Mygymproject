package gympacks;

import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.*;

@WebServlet("/Sahil")
 
public class login extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		PrintWriter out=res.getWriter();
		String username=req.getParameter("usernames");
		String password=req.getParameter("pass");
		  
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mygym","root","7499");
			PreparedStatement ps=con.prepareStatement("select*from gym where username=? and password=?");
			 ps.setString(1, username);
			 ps.setString(2, password);
			 
			 ResultSet rs =ps.executeQuery();
			 if(rs.next())
			 {
				 res.setContentType("text/html");
				 RequestDispatcher rd=req.getRequestDispatcher("/Dietplan.jsp");
				 rd.forward(req,res);
			 }
			 else
			 {
				 res.setContentType("text/html");
				 out.println("<script>");
				 out.println("alert('Invalid username or password ');");
				 out.println("window.location='login.jsp';");
				 out.println("</script>");
			 }
		}
		catch(Exception e)
		{
			out.println(e.getMessage());
		}
	}
}

