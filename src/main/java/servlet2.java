import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/servlet2")
public class servlet2 extends HttpServlet {
   
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		HttpSession session = request.getSession();
		response.setContentType("text/html"); 
		 	 		 
		String  userName= request.getParameter("username");
		String  firstname= request.getParameter("firstname");
		String  email= request.getParameter("email");
		String  password= request.getParameter("password1");
		String  password2= request.getParameter("password2");
		
		System.out.println(userName+firstname+email+password+password2);

		String url = "jdbc:mysql://localhost:3306/medidb";
		String sql="insert into customer(username,password,email,firstname) values(?,?,?,?)";
	
		String get="select * from customer where username=?";
		
		PrintWriter pr = response.getWriter();
	
		try
		{	
			System.out.println("servlete");
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection(url,"ug","ug");
		
			System.out.println("connection established successfully");
				
			boolean a;
			PreparedStatement p1 = con.prepareStatement(get);
			p1.setString(1, userName);			
			ResultSet re =p1.executeQuery();
	        a = re.next();	
         
	        if(a==true) {
	        	
	        	System.out.println("a==true");
	       	    String st="User Already Exist";
		       	PrintWriter out = response.getWriter();
		       	pr.print("<html><head>");
		       	pr.print("<script type=\"text/javascript\">alert(" + st + ");</script>");
		       	pr.print("</head><body></body></html>");
			 
			 
	       	    javax.servlet.RequestDispatcher rs = request.getRequestDispatcher("/registration_2.html");
				rs.forward(request, response);
	        	
	        }
	        else
	        {
	        
			
				if(!password.equals(password2)) {
					
					
					 String st="Password Mismatch";
					 
					 pr.print("<html><head>");
					 pr.print("<script type=\"text/javascript\">alert(" + st + ");</script>");
					 pr.print("</head><body></body></html>");
					 
					 
	    			 javax.servlet.RequestDispatcher rs = request.getRequestDispatcher("/registration_2.html");
					 rs.forward(request, response);
					
				}
				else
				{
					
					PreparedStatement p2 = con.prepareStatement(sql);
					p2.setString(1, userName);
					p2.setString(2, password);
					p2.setString(3, email);
					p2.setString(4, firstname);
					p2.executeUpdate();
					String st="Registered Successfully";
					pr.println("<html> alert("+st+")</html>");
				 
				    javax.servlet.RequestDispatcher rs = request.getRequestDispatcher("/login_1.html");
					rs.forward(request, response);
				}
		}	
				
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
	
		
	}

}
