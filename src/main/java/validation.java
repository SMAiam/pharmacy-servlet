
import java.sql.*;
public class validation {
	 
	static	Connection con = null;
	static	boolean a = false;
	public boolean check(String name , String password) {


		String get="select * from customer where username=? and password=?";
		try {
				System.out.println("inside validation form");
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=  DriverManager.getConnection("jdbc:mysql://localhost:3306/medidb","ug","ug");
				System.out.println("connection established successfully");
				
				PreparedStatement ps =  con.prepareStatement(get);
				ps.setString(1, name);
				ps.setString(2, password);			
				ResultSet rs =ps.executeQuery();
		        a = rs.next();    // if no data in the table then it will return false ;
			
		}catch(Exception e) {
	        e.printStackTrace();
	    }
		
		return a;
	}
}





