package Model;

import java.util.List;
import java.util.Map;

public class HttpResponse {

	private int statusCode;
	private Object response;
	private String contentType;
	private Map<String, List<String>> responseHeaders;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public Map<String, List<String>> getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(Map<String, List<String>> responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ResponseCode : " + statusCode + "\n response : " + response;
	}

	public String getContentType() {

		for (Map.Entry<String, List<String>> entry : responseHeaders.entrySet()) {
			System.out.println(String.valueOf(entry.getKey()));
			if (entry.getKey() == null) {
				continue;
			}

			if (entry.getKey().equalsIgnoreCase("Content-Type")) {
				return entry.getValue().get(0);

			}
		}

		return null;
	}
}
