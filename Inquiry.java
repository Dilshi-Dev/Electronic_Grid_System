package controllers;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import models.Help;
import utils.Queries;

@Path("/InquiryRequest")
public class HelpController {

	Queries query = new Queries();
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getAllInquiry()
	{
		//List<ConRequest> requests = new ArrayList<>();
		String requests =  this.query.getAll();
		return requests;
		
	}
	
	@GET
	@Path("/{cusID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String singleHelp(@PathParam("cusID")int cusID)
	{
		
		return this.query.getOne(cusID);
	
	}
	
	@POST
	@Path("/Add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)	
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addInquiry(
			@FormParam("cusID") Integer cusID,
			@FormParam("Name") String Name,
			@FormParam("NIC") String NIC,
			@FormParam("Home_ele_no") Integer Home_ele_no  ,
			@FormParam("Address") String Address,
			@FormParam("email") String email,
			@FormParam("contact") Integer contact,
			@FormParam("message") String message,
			@FormParam("inquirtype") String inquirtype,
			@FormParam("inquirystatus") String inquirystatus,	
	)
	{
		
		Inquiry request = new Inquiry();
		
		request.setcusID(cusID);
		request.seName(Name);
		request.setNIC(NIC);
		request.setHome_ele_no(Home_ele_no );
		request.setAddress(Address);
		request.setEmail(Email);
		request.setContact(contact);
		request.setMessage(Message);
		request.setInquirtype(inquirtype);
		request.setInquirystatus(inquirystatus);
		
		String output = this.query.Insert(request);
		
		return output;
		
	}
	
	
	@PUT
	@Path("/Update")
	//@Consumes(MediaType.APPLICATION_JSON)	
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.TEXT_PLAIN)
	public String UpdateInquiry(String Data)
	{
		
		JsonObject Object = new JsonParser().parse(Data).getAsJsonObject();
		
		Integer cusID = Object.get("cudID").getAsInt();
		String Name = Object.get("Name").getAsString();
		String NIC = Object.get("NIC").getAsString();
		Integer Home_ele_no = Object.get("Home_ele_nor").getAsInt();
		String Address = Object.get("Address").getAsString();
		String Email = Object.get("Email").getAsString();
		Integer contact = Object.get("contact").getAsInt();
		String message = Object.get("message").getAsString();
		String inquirtype = Object.get("inquirtype").getAsString();
		String inquirystatus = Object.get("inquirystatus").getAsString();

		
		String output = this.query.Update(cusID,Name,NIC,Home_ele_no,Address,Email,contact,message,inquirtype,inquirystatus);
		
		return output;
		
	}
	
	
	@DELETE
	@Path("/Delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInquiry(String Data)
	{

		org.jsoup.nodes.Document doc = Jsoup.parse(Data, "", org.jsoup.parser.Parser.xmlParser());
		String id = doc.select("cusID").text();
		String output = query.delete(Integer.parseInt(cusID));
		
		return output;
	}
	
	
	
	
	
}
