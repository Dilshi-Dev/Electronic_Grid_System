package com;
import model.Payment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payments")
public class PaymentService {

	Payment payment = new Payment();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
	return payment.getAllPaymnts();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("customerName") String customerName,
			 @FormParam("accountNo") String accountNo,
			 @FormParam("amount") int amount,
			 @FormParam("cardNumber") String cardNumber,
	 		@FormParam("date") String date,
				@FormParam("cvv") int cvv)
			{
			 String output = payment.insertPayment(customerName, accountNo, amount, cardNumber, date, cvv);
				return output;

			}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentObj)
	{
	//Convert the input string to a JSON object
	 JsonObject paymentData = new JsonParser().parse(paymentObj).getAsJsonObject();
	//Read the values from the JSON object
	 String id = paymentData.get("id").getAsString();
	 String customerName = paymentData.get("customerName").getAsString();
	 String accountNo = paymentData.get("accountNo").getAsString();
	 String amount = paymentData.get("amount").getAsString();
	 String cardNumber = paymentData.get("cardNumber").getAsString();
	 String date = paymentData.get("date").getAsString();
	 String cvv = paymentData.get("cvv").getAsString();
	 String output = payment.updatePayment(id, customerName, accountNo, amount, cardNumber,date,cvv);
	 return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentObj)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(paymentObj, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String id = doc.select("id").text();
	 String output = payment.deletePayment(id);
	return output;
	}
}
