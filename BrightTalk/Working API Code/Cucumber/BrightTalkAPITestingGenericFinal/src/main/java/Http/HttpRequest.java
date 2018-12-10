package Http;

import java.util.HashMap;
import java.util.Map;

import Helper.AppConstants;
import Model.ErrorResponse;
import Model.GetRealmSuccess;

@SuppressWarnings("rawtypes")
public class HttpRequest {
	private String url;
	private Object requestObject;
	private Map<String, String> headers = new HashMap<String, String>();
	private String httpMethod;
	private Class[] responseClasses;

	public enum HttpMethod {
		POST("POST"), GET("GET"), PUT("PUT"), DELETE("DELETE"), HEAD("HEAD");
		private final String name;       

		private HttpMethod(String s) {
			name = s;
		}

		public boolean equalsName(String otherName) {
			// (otherName == null) check is not needed because name.equals(null) returns false 
			return name.equals(otherName);
		}

		public String toString() {
			return this.name;
		}
	}

	public enum CONTENTTYPE {
		JSON ("application/json"),
		XML ("application/xml"),
		TEXT ("application/text");

		private final String name;       

		private CONTENTTYPE(String s) {
			name = s;
		}

		public boolean equalsName(String otherName) {
			// (otherName == null) check is not needed because name.equals(null) returns false 
			return name.equals(otherName);
		}

		public String toString() {
			return this.name;
		}
	}

	public String getUrl() {
		return url;
	}

	public Class[] getResponseClasses() {
		return responseClasses;
	}

	public void setResponseClasses(Class[] responseClasses) {
		this.responseClasses = responseClasses;
	}

	public void addRequestHeader(String kay, String value) {
		headers.put(kay, value);
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Object getRequestObject() {
		return requestObject;
	}

	public void setRequestObject(Object requestObject) {
		this.requestObject = requestObject;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	/**
	 * static Method to create get realm API object
	 * @param relamId
	 * @return
	 */
	public static HttpRequest getRelamRequest(String relamId) {
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(AppConstants.GET_REALM_URL + relamId);
		httpRequest.addRequestHeader("Content-­type", CONTENTTYPE.XML.toString());
		httpRequest.addRequestHeader("Connection", "keep-alive");
		httpRequest.setHttpMethod(HttpMethod.GET.name());

		//possible response classes
		Class[] responseClasses = new Class[]{GetRealmSuccess.class, ErrorResponse.class};
		httpRequest.setResponseClasses(responseClasses);

		return httpRequest;
	}

	/**
	 * static method to create add real m object
	 * @param requestObject
	 * @return
	 */
	public static HttpRequest addRealmRequest(String requestObject) {
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(AppConstants.INSERT_REALM_URL);
		httpRequest.addRequestHeader("Content-Type", CONTENTTYPE.XML.toString());
		httpRequest.setHttpMethod(HttpMethod.POST.name());
		httpRequest.setRequestObject(requestObject);

		//possible response classes
		Class[] responseClasses = new Class[]{GetRealmSuccess.class, ErrorResponse.class};
		httpRequest.setResponseClasses(responseClasses);

		return httpRequest;
	}

	/**
	 * Method to create delete realm API
	 * @param realmId
	 * @return
	 */
	public static HttpRequest deleteRealmRequest(String realmId) {
		HttpRequest httpRequest = new HttpRequest();

		httpRequest.setUrl(AppConstants.GET_REALM_URL + realmId);
		httpRequest.addRequestHeader("Content-­type", CONTENTTYPE.XML.toString());
		httpRequest.addRequestHeader("Connection", "keep-alive");
		httpRequest.setHttpMethod(HttpMethod.DELETE.name());

		//possible response classes
		Class[] responseClasses = new Class[]{ErrorResponse.class};
		httpRequest.setResponseClasses(responseClasses);

		return httpRequest;
	}
}
