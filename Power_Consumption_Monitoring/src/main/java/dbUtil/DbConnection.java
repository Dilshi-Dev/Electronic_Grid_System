package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public Connection connect() {
		Connection con = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/power_plant", "root", "");
			System.out.print("Successfully Connected");

		} catch (Exception e) {

			System.out.print("Connection Failed");
			e.printStackTrace();
			System.out.print(e);
		}

		return con;
	}

}
