package model;
import java.sql.*;

public class Payment {
	
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paf", "root", "root");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertPayment(String customerName, String accountNo, int amount, String cardNumber,String date,int cvv)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into payment(`id`,`customerName`,`accountNo`,`amount`,`cardNumber`,`date`,`cvv`)"+ " values (?, ?, ?, ?, ?,?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, customerName);
	 preparedStmt.setString(3, accountNo);
	 preparedStmt.setInt(4,amount);
	 preparedStmt.setString(5, cardNumber);
	 preparedStmt.setString(6, date);
	 preparedStmt.setInt(7, cvv);
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String getAllPaymnts()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Customer Name</th><th>Account Number</th>" + "<th>Amount</th>" + "<th>Card Number</th>" +  "<th>Date</th>" + "<th>CVV</th>" +"<th>Remove</th></tr>";

	 String query = "select * from payment";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String id = Integer.toString(rs.getInt("id"));
	 String customerName = rs.getString("customerName");
	 String accountNo = rs.getString("accountNo");
	 String amount = Integer.toString(rs.getInt("amount")) ;
	 String cardNumber = rs.getString("cardNumber");
	 String date = rs.getString("date");
	 String cvv = Integer.toString(rs.getInt("cvv")) ;
	 // Add into the html table
	 output += "<tr><td>" + customerName + "</td>";
	 output += "<td>" + accountNo + "</td>";
	 output += "<td>" + amount + "</td>";
	 output += "<td>" + cardNumber + "</td>";
	 output += "<td>" + date + "</td>";
	 output += "<td>" + cvv + "</td>";
	 // buttons
	 output += "<td><form method='post' action='users.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"+ "<input name='id' type='hidden' value='" + id+ "'>" + "</form></td></tr>";}
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String updatePayment(String id, String customerName, String accountNo, String amount, String cardNumber,String date,String cvv)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for updating."; }
	// create a prepared statement
	String query = "UPDATE payment SET customerName=?,accountNo=?,amount=?,cardNumber=?,date=?,cvv=? WHERE id=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setString(1, customerName);
	preparedStmt.setString(2, accountNo);
	preparedStmt.setInt(3, Integer.parseInt(amount));
	preparedStmt.setString(4, cardNumber);
	preparedStmt.setString(5, date);
	preparedStmt.setInt(6, Integer.parseInt(cvv));
	preparedStmt.setInt(7, Integer.parseInt(id));
	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Updated successfully";
	}
	catch (Exception e)
	{
	output = "Error while updating the item.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	public String deletePayment(String id)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for deleting."; }
	// create a prepared statement
	String query = "delete from payment where id=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(id));
	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Deleted successfully";
	}
	catch (Exception e)
	{
	output = "Error while deleting the item.";
	System.err.println(e.getMessage());
	}
	return output;
	}

}
