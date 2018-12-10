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
public class GetRealmSuccess {
	
	private String id;
	private String name;
	private String description;
	private String key;
	
	@XmlAttribute
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@XmlAttribute
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElement
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public String serialiseRealm(GetRealmSuccess getRealmSuccess) {
		try {
			StringWriter writer = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(GetRealmSuccess.class);
			Marshaller m = context.createMarshaller();
			m.marshal(getRealmSuccess, writer);
			return writer.toString();
			
		} catch (Exception e) {
		}
		return null;
	}
	
	public GetRealmSuccess deSerailiseRealm(String input) {
		try {
		JAXBContext context = JAXBContext.newInstance(GetRealmSuccess.class);
		Unmarshaller m = context.createUnmarshaller();

		GetRealmSuccess success = (GetRealmSuccess)m.unmarshal(new StringReader(input));
		
		System.out.println("Get Realm Response :" + serialiseRealm(success));
		return success;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
	