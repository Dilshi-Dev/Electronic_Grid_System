package models;

public class Inquiry {

	
	private Integer cusID;
	private String Name;
	private String NIC;
	private Integer Home_ele_no  ;
	private String Address;
	private String Email;
	private Integer contact;
	private String message;
	private String Inquirtype;
	private String Inquirystatus;
	
	
	public Integer getcusID() {
		return cusID;
	}
	public void setcusID(Integer cusID) {
		this.cusID = cusID;
	}
	public String geName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public String getNIC() {
		return NIC;
	}
	public void setNIC(String NIC) {
		this.NIC = NIC;
	}
	public Integer getHome_ele_no() {
		return Home_ele_no;
	}
	public void setHome_ele_no(Integer Home_ele_no) {
		this.Home_ele_no = Home_ele_no;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String Address) {
		this.Address = Address;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String Email) {
		this.Email = Email;
	}
	public Integer getContact() {
		return contact;
	}
	public void setContact(Integer contact) {
		this.contact = contact;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getInquiryStatus() {
		return Inquirystatus;
	}
	public void setInquiryStatus(String Inquirystatus) {
		this.Inquirystatus = Inquirystatus;
	}
	
	public String getInquiryType() {
		return Inquirytype;
	}
	public void setInquiryType(String Inquirytype) {
		this.Inquirytype = Inquirytype;
	}
	
	
}
