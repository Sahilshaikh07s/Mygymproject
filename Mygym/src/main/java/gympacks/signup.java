package gympacks;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;

@WebServlet("/Gym1")

public class signup extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		PrintWriter out =res.getWriter();
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String repass=req.getParameter("repassword");
				
		
			try
		{
				if(password.equals(repass))
				{	
					
				
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mygym","root","7499");
			PreparedStatement ps=con.prepareStatement("insert into gym values(?,?,?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, repass);
			int i =ps.executeUpdate();
				
			         if(i>0)

			           {
				res.setContentType("text/html");
				out.println("<script>");
				out.println("alert('Sign-Up is successfully complete !');");
				out.println("window.location='login.jsp';");
				out.println("</script>");
		          	}
			
		 
			        else
			           {
			         	out.println("Please Enter correct information");
			              }
				}
			else
			{

				out.println("<script>");
				out.println("alert('Please check Re-password');");
				out.println("window.location='signup.jsp';");
				out.println("</script>");
			}
		}
		catch(Exception e )
		{
			out.println(e.getMessage());
		}
	}
}


