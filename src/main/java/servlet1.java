import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servlet1")
public class servlet1 extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			
			String fnm = request.getParameter("username");                      // get data from form parameters
			String pass = request.getParameter("password");
	
	                 
			session.setMaxInactiveInterval(60*4);
			
			PrintWriter pr = response.getWriter();	
			
			try
			{		

				validation vd = new validation();
				
				if(vd.check(fnm,pass)) {
					pr.println("welcome " +fnm);
					HttpSession sh = request.getSession();  			
				    sh.setAttribute("safe_key",fnm);
				    
					System.out.println(pass);	
					response.setContentType("text/html");
					
					javax.servlet.RequestDispatcher rs = request.getRequestDispatcher("/homepage_4.html");   // redirect the page
					rs.include(request, response);                         // sending data from first servlet to next;
                       // response.sendRedirect("next servlet")// here we go to next servlet . but the response 
					   //object passed from here will not hold any data there as the data ot was holding here	 
				
				}
				else {

					response.setContentType("text/html");
					String st="User Dosen't Exist";
				    pr.println("<html><head>"+"<p>"+st+"</p>"+"</head></html>");
					

				    javax.servlet.RequestDispatcher rs = request.getRequestDispatcher("/login_1.html");   // redirect the page
					rs.include(request, response);    
				
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
					
	}

}
