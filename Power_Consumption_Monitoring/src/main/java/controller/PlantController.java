package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import dbUtil.DbConnection;
import model.Plant;

public class PlantController {


	DbConnection dbObj = new DbConnection();
	
	
	//view
	public String ViewPlants() {

			String output = "";
			
			Plant  p = new Plant();
			
			try {
				Connection con = dbObj.connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
			}
				
			// Prepare the html table 
			output = "<table border=\"1\"><tr><th>Power Plant Id</th> "
						+ "<th>Area ID</th> "+"<th>Power Plant Name</th>"+"<th>Location</th> "+" <th>Type</th>"
								+"<th>Capacity(MWh)</th>"+"<th>Status</th></tr>";

			String query = "select * from plant_db";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				p.setpowerpId(rs.getInt("powerpId"));
				p.setareaId(rs.getString("areaId"));
				p.setpName(rs.getString("pName"));
				p.setpLocation(rs.getString("pLocation"));
				p.settype(rs.getString("type"));
				p.setcapacity(rs.getInt("capacity"));
				p.setstatus(rs.getString("status"));
				
				
				// Add into the html table
				output += "<tr><td>" + p.getpowerpId() + "</td>";
				output += "<td>" + p.getareaId() + "</td>";
				output += "<td>" + p.getpName() + "</td>";
				output += "<td>" + p.getpLocation() + "</td>";
				output += "<td>" + p.gettype() + "</td>";
				output += "<td>" + p.getcapacity() + "</td>";
				output += "<td>" + p.getstatus() + "</td>";
				
					
				}
			
				con.close();
				
				// Complete the html table
				output += "</table>";

			} catch (Exception e) {
				output = "Error while reading Power Plant Details.";
				System.err.println(e.getMessage());
			}

			return output;
		}
	
	public String AddPlant(Plant p) {

		String output = "";
			
		try {

			Connection con = dbObj.connect();
				
			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " INSERT INTO plant_db (powerpId, areaId, pName, pLocation, type, capacity, status) VALUES (?, ?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, p.getpowerpId());
			preparedStmt.setString(2,p.getareaId());
			preparedStmt.setString(3,p.getpName() );
			preparedStmt.setString(4,p.getpLocation());
			preparedStmt.setString(5,p.gettype() );
			preparedStmt.setInt(6,p.getcapacity() );
			preparedStmt.setString(7,p.getstatus() );
				
				
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted a new plant record successfully";

			} catch (Exception e) {
				output = "Error while inserting";
				System.err.println(e.getMessage());
			}

			return output;
		}
	//update
	public String UpdatePlant(Plant p) {

		String output = "";

		try {
			Connection con = dbObj.connect();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
				
			// create a prepared statement
			String query = "UPDATE plant_db SET areaId=?, pName=?, pLocation=?, type=?, capacity=?, status=?  WHERE powerpId =?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			
			// binding values
			
			preparedStmt.setString(1, p.getareaId());
			preparedStmt.setString(2, p.getpName());
			preparedStmt.setString(3, p.getpLocation());
			preparedStmt.setString(4, p.gettype());
			preparedStmt.setInt(5, p.getcapacity());
			preparedStmt.setString(6, p.getstatus());
			preparedStmt.setInt(7, p.getpowerpId());
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully [ ID : "+p.getpowerpId()+" ]";
			
			} catch (Exception e) {
				output = "Error while updating the Plant Id " + p.getpowerpId();
				System.err.println(e.getMessage());
			}
			return output;
			
		}
	
	//delete
	public String DeletePlant(Plant p) {
		
		String output = "";
		
		try {

			Connection con = dbObj.connect();
			
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "DELETE FROM plant_db WHERE powerpId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			 preparedStmt.setInt(1, p.getpowerpId());
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully [ Plant ID : "+ p.getpowerpId()+" ]";

		} catch (Exception e) {

			output = "Error while deleting the  Fund Id :" + p.getpowerpId();
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	//view by id
	public String ViewPlantById(int powerpId) {
		
		String output = "";
		
		Plant  p = new Plant();
		
		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
		}
			
		// Prepare the html table 
		output = "<table border=\"1\"><tr><th>Power Plant Id</th>"
					+ "<th>Area ID</th> "+"<th>Power Plant Name</th> "+" <th>Location</th> "+" <th>Type</th> "+" <th>Capacity(MWh)</th>"+"<th>Status</th></tr>";

		String query = "select * from plant_db where powerpId = '"+powerpId+"'";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		// iterate through the rows in the result set
		while (rs.next()) {

			p.setpowerpId(rs.getInt("powerpId"));
			p.setareaId(rs.getString("areaId"));
			p.setpName(rs.getString("pName"));
			p.setpLocation(rs.getString("pLocation"));
			p.settype(rs.getString("type"));
			p.setcapacity(rs.getInt("capacity"));
			p.setstatus(rs.getString("status"));
			
			// Add into the html table
			output += "<tr><td>" + p.getpowerpId() + "</td>";
			output += "<td>" + p.getareaId() + "</td>";
			output += "<td>" + p.getpName() + "</td>";
			output += "<td>" + p.getpLocation() + "</td>";
			output += "<td>" + p.gettype() + "</td>";
			output += "<td>" + p.getcapacity() + "</td>";
			output += "<td>" + p.getstatus() + "</td>";
				
			}
		
			con.close();
			
			// Complete the html table
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the Fund Details.";
			System.err.println(e.getMessage());
		}

		return output; 
	}
	

}
