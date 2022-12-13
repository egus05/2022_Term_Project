import java.sql.Connection;
import java.sql.DriverManager;

public class Tmp1 {
	public static void main(String[] args)
	{
		OpenTable ot = new OpenTable();
		
		ot.select("SELECT*FROM User");
		
		try
		{
			
			
			while(ot.rs.next())
			{
				System.out.println(ot.rs.getString("user_id")
						+ ot.rs.getString("password")
						+ ot.rs.getString("email")
						+ ot.rs.getString("name")
						+ ot.rs.getString("age")
						+ ot.rs.getString("birthday")
						+ ot.rs.getString("gender"));
			}
		}
		catch(Exception e)
		{
			System.out.println("error123");
		}
		ot.close();
	}
	
	
}

