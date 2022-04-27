package com;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import controller.PlantController;
import model.Plant;

@Path("/PowerPlants")
public class PlantService {
	
	PlantController pc = new PlantController();

	// get
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPlant() {
		return pc.ViewPlants();
	}

	// getByID
	@GET
	@Path("/{powerpId}")
	@Produces(MediaType.TEXT_HTML)
	public String readPlantById(@PathParam("powerpId") int powerpId) {
		return pc.ViewPlantById(powerpId);
	}

	// add
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String EnterPlantDetails(String TypeData) {
			
		try {
			if(TypeData == null) throw new Exception("Invaild data type");
			else {
				// Convert the input string to a JSON object
				JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
				
				Plant p = new Plant();
					
				p.setpName(djosnObj.get("pName").getAsString());
				p.setareaId(djosnObj.get("areaId").getAsString());
				p.setpLocation(djosnObj.get("pLocation").getAsString());
				p.settype(djosnObj.get("type").getAsString());
				p.setcapacity(djosnObj.get("capacity").getAsInt());
				p.setstatus(djosnObj.get("status").getAsString());
				
				// Read the values from the JSON object
				String output = pc.AddPlant(p);
				return output;
			   }
		 }
			
		catch(Exception e) {
				return "<p> Somethings went wrong <br/> ERROR -  "+ e.toString() +" </p>";
			
		}
	}
	
	//update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdatePlant(String TypeData) {

			try {
				if( TypeData == null ) throw  new Exception("Invalid data type");
				
				// Convert the input string to a JSON object
				JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
				
				Plant p = new Plant();

				p.setpowerpId(djosnObj.get("powerpId").getAsInt());
				p.setareaId(djosnObj.get("areaId").getAsString());
				p.setpName(djosnObj.get("pName").getAsString());
				p.setpLocation(djosnObj.get("pLocation").getAsString());
				p.settype(djosnObj.get("type").getAsString());
				p.setcapacity(djosnObj.get("capacity").getAsInt());
				p.setstatus(djosnObj.get("status").getAsString());
					
					
				String output = pc.UpdatePlant(p);
				return output;
				
			}catch(Exception e) {
				return "<p> Somethings went wrong <br> ERROR - "+e.toString()+" </p>";
			}
			
			
		}
		
	//delete
	
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String DeletePlant(String TypeData) {
			try {
				if(TypeData == null ) throw new Exception("Invalid Id");
				// Convert the input string to a JSON object
				JsonObject doc = new JsonParser().parse(TypeData).getAsJsonObject();
				Plant p = new Plant();
				
					
				// Read the value from the element <ID>
			   	p.setpowerpId(doc.get("powerpId").getAsInt());
					
				String output = pc.DeletePlant(p);
				return output;
			}catch(Exception e) {
				return "<p> Somethings went wrong <br> ERROR - "+e.toString()+ " </p>";
			}
			
			
		}

}
