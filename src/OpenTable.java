
public class OpenTable {
	java.sql.Connection conn;
	java.sql.Statement stmt;
	java.sql.ResultSet rs;
	
	OpenTable(){
		connect();
	}
	
	void connect() {
		String dbinfo = "jdbc:mysql://localhost:3306/Tmp";
		String dbID = "root";
		String dbPW = "1234";
	
	
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		this.conn = java.sql.DriverManager.getConnection(dbinfo,dbID,dbPW);
		this.stmt = this.conn.createStatement();
	}
	
	catch(Exception e)
	{
		System.out.println("conncetion error");
	}
	
	}
	
	void update(String dbCommand) {
		try
		{
			this.stmt.executeUpdate(dbCommand);
		}
		catch(Exception e)
		{
			System.out.println("conncetion error");
		}
	}
	
	void select(String dbSelect)
	{
		try
		{
			this.rs = this.stmt.executeQuery(dbSelect);
		}
		catch(Exception e)
		{
			System.out.println("conncetion error");
		}
		
	}
	
	void close() {
		try
		{
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println("conncetion error");
		}
	}
}
