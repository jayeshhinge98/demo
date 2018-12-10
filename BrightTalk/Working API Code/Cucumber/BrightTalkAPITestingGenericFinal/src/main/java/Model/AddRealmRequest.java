package Model;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "realm")
public class AddRealmRequest {
	
	private String name;

	
	private String description;

	@XmlElement
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String string) {
		this.name = string;
	}

	public String serialiseRealm() {
		try {
			StringWriter writer = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(AddRealmRequest.class);
			Marshaller m = context.createMarshaller();
			m.marshal(this, writer);
			return writer.toString();
			
		} catch (Exception e) {
			//TODO:: Error handling
		}
		return null;
	}
	
	public AddRealmRequest deSerailiseRealm(String input) {
		try {
		JAXBContext context = JAXBContext.newInstance(AddRealmRequest.class);
		Unmarshaller m = context.createUnmarshaller();
		return (AddRealmRequest)m.unmarshal(new StringReader(input));
		} catch(Exception e) {
			//TODO:: Error handling
			e.printStackTrace();
			return null;
		}
	}
	
}
