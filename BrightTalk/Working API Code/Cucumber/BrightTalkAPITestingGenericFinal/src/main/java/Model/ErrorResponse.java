package Model;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorResponse {
	
	private String code;
	private String message;


	@XmlElement
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@XmlElement
	public String getMessage() {
		return message;
	}
	
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String serialiseErrorResponse() {
		try {
			StringWriter writer = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(ErrorResponse.class);
			Marshaller m = context.createMarshaller();
			m.marshal(this, writer);
			return writer.toString();
			
		} catch (Exception e) {
		}
		return null;
	}
	
	public ErrorResponse deSerailiseErrorResponse(String input) {
		try {
		JAXBContext context = JAXBContext.newInstance(ErrorResponse.class);
		Unmarshaller m = context.createUnmarshaller();
		ErrorResponse error= (ErrorResponse)m.unmarshal(new StringReader(input));
		System.out.println("*************Error Response************\n" +
						"Error Code :" + error.code + "\n" +
								"Error Message :" + error.message + "\n" +
										"*******************End Of Response************");
		return error;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return "*************Error Response************\n" +
//				" Response Code :" + responseCode + "\nContent length :" + contentLength + "\n" +
//				"Error Code :" + code + "\n" +
//						"Error Message :" + message + "\n" +
//								"*******************End Of Response************";
//	}
}
