package model;

public class Plant {
	
	int powerpId;
	String areaId;
	String pName;
	String pLocation;
	String type;
	int	capacity;
	String status;
	
	public  Plant() {
	
}
	
	public Plant(int powerpId,String areaId, String pName,String pLocation,String type,int capacity,String status) {
	
		super();
	
		this.powerpId = powerpId;
		this.areaId = areaId;
		this.pName = pName;
		this.pLocation = pLocation;
		this.type = type;
		this.capacity = capacity;
		this.status =status;
		
		
	
	}
	
	public int getpowerpId() {
		return powerpId;
	}

	public void setpowerpId(int powerpId) {
		this.powerpId = powerpId;
	}
	
	public String getareaId() {
		return areaId;
	}

	public void setareaId(String areaId) {
		this.areaId = areaId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpLocation() {
		return pLocation;
	}

	public void setpLocation(String pLocation) {
		this.pLocation = pLocation;
	}
	
	public String gettype() {
		return type;
	}

	public void settype(String type) {
		this.type = type;
	}
	
	public int getcapacity() {
		return capacity;
	}

	public void setcapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getstatus() {
		return status;
	}

	public void setstatus(String status) {
		this.status = status;
	}
}
