package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.gson.Gson;

import models.Inquiry;

public class Queries {
	
	Connection con = null;
	String output = "";
	
	public Queries() {
		
		String url = "jdbc:mysql://localhost:3306/pafdatabse";
		//Provide the correct details: DBServer/DBName, username, password
		String username = "root";
		String password = "1234";
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url,username,password);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
		
		if(con != null) {
			System.out.println("Electronic Grid System Database Connected successfully");
		} else {
			System.out.println("Electronic Grid System Database Connection failed");
		}
		
		
	}

	public String getAll() {
			
			output = "<table border='1'><tr>"+
			"<th>cusID</th>"+
			"<th>Name</th>" +
			"<th>NIC/th>" +
			"<th>Home ELectricity No</th>" +
			"<th>Address</th>" +
			"<th>Email</th>" +
			"<th>Contact Number</th>" +
			"<th>Inquiry Type</th>" +
			"<th>Message</th>" +
			"<th>Inquiry Status</th></tr>";
			
			String sql = "select * from inquiry";
		
			try {
				
				Statement st = this.con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				
				while(rs.next()) {
						
					output += "<tr><td>" + rs.getInt(1) + "</td>";
					output += "<td>" + rs.getString(2) + "</td>";
					output += "<td>" + rs.getString(3) + "</td>";
					output += "<td>" + rs.getInt(4) + "</td>";
					output += "<td>" + rs.getString(5)+"</td>";
					output += "<td>" + rs.getString(6) + "</td>";
					output += "<td>" + rs.getInt(7) + "</td>";
					output += "<td>" + rs.getString(8) + "</td>";
					output += "<td>" + rs.getString(9) + "</td>";
					output += "<td>" + rs.getString(10) + "</td></tr>";

				}
				
				output += "</tr></table>";
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return output;
		}
	
	public String getOne(int cudID) {
		
		String sql = "select * from inquiry where cusID="+cudID;
		Inquiry req = new Inquiry();
		Gson gson = new Gson();
		try {
			
			Statement st = this.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				
				req.setcusID(rs.getInt(1));
				req.setName(rs.getString(2));
				req.setNIC(rs.getString(3));
				req.setHome_ele_no(rs.getInt(4));
				req.setAddress(rs.getString(5));
				req.setEmail(rs.getString(6));
				req.setContact(rs.getInt(7));
				req.setMessage(rs.getString(8));
				req.setInquiryType(rs.getString(9));
				req.setInquiryStatus(rs.getString(10));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return gson.toJson(req);
	}
	
	public String Insert(Inquiry request) {
		
		String sql = "insert into inquiry values(?,?,?,?,?,?,?,?,?,?)";
		
			try {
			
				PreparedStatement st = this.con.prepareStatement(sql);
				
				st.setInt(1,request.getcusID());
				st.setString(2,request.getName());
				st.setString(3,request.getNIC());
				st.setInt(4,request.getHome_ele_no());
				st.setString(5,request.getAddress());
				st.setString(6,request.getEmail());
				st.setInt(7,request.getContact());
				st.setString(8,request.getMessage());
				st.setString(9,request.getInquiryType());
				st.setString(10,request.getInquiryStatus());
				
				st.executeUpdate();
				output = "Add Inquiry successfully";
			
			} catch (Exception e) {
				
				System.err.println(e.getMessage());
				output = "Error while inserting the item.";
				
			}
			
			return output;
		
	}

	public String Update(
			Integer cusID,
			String Name,
			String NIC,
			Integer Home_ele_no,
			String Address,
			String Email,
			Integer contact,
			String message,
			String Inquirytype,
			String Inquirystatus,
			) {
		
		
		String sql = "UPDATE inquiry SET Name=?,NIC=?,Home_ele_no=?,Address=?,Email=?,contact=?,message=?,Inquirytype=?,Inquirystatus=?"
				+ "WHERE cusID=?";
		
			try {
			
				PreparedStatement st = this.con.prepareStatement(sql);
				
				st.setString(1,Name);
				st.setString(2,NIC);
				st.setInt(3,Home_ele_no);
				st.setString(4,Address);
				st.setString(5,Email);
				st.setInt(6,contact);
				st.setString(7,message);
				st.setString(8,Inquirytype);
				st.setString(9,Inquirystatus);
				st.setInt(10,cusID);
				
				st.executeUpdate();
				
				output = "Update successfully";
			
			} catch (Exception e) {
				
				System.err.println(e.getMessage());
				output = "Error while updating the item.";
				
			}
			
			return output;
		
	}

	public String delete(Integer cusID){
		
		String sql = "delete from inquiry where cusID=?";
		
		try {
		
			PreparedStatement st = this.con.prepareStatement(sql);
			
			st.setInt(1,cusID);
			st.executeUpdate();
			
			output = "Delete successfully";
		
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
			
			output = "Error while deleting the record.";
			
		}
		
		return output;
	
	}

}
