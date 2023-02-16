

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class validation_medicin {

	static	Connection con = null;
	static	boolean a = false;
	public boolean check(String medicin_id, String title , String qty,String unit_price ) {
		
		String get ="Select * from medicin where medicin_id=?";
		
		try {
			 System.out.println("inside validation_ medicin  form");
			
			    Class.forName("com.mysql.cj.jdbc.Driver");
				con=  DriverManager.getConnection("jdbc:mysql://localhost:3306/medidb","ug","ug");
				System.out.println("connection established successfully");

				PreparedStatement pr = con.prepareStatement(get);
				pr.setString(1, medicin_id);
				ResultSet rs = pr.executeQuery();
				a=rs.next();
			
				if(a==false) {
					
					String put = "insert into medicin (medicin_id,title,qty,unity_price) values(?,?,?,?)";
					
					PreparedStatement pt = con.prepareStatement(put);
									
					pt.setString(1, medicin_id);
					pt.setString(2, title);
					pt.setString(3, qty);
					pt.setString(4, unit_price);
					pt.executeUpdate();
				}else {
					a=true;
				}
		
		}catch(Exception e) {
	        e.printStackTrace();
	    }
			
		return a;
	
}
}
