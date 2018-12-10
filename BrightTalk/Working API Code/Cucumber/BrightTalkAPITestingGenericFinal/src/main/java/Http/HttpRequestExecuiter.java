package Http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import Model.HttpResponse;

public class HttpRequestExecuiter {

	public static HttpResponse executeAsync(HttpRequest httpRequest) {

		HttpURLConnection connection = null;
		try {
			URL url = new URL(httpRequest.getUrl());
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(httpRequest.getHttpMethod());
			// add http headers
			HashMap<String, String> httpHeaders = (HashMap<String, String>) httpRequest.getHeaders();
		
			for(Map.Entry<String, String> entry : httpHeaders.entrySet())
				connection.setRequestProperty(entry.getKey(), entry.getValue());

			connection.setDoInput(true);

			//add http body
			if (httpRequest.getRequestObject() != null) {
				connection.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
				wr.writeBytes(httpRequest.getRequestObject().toString());
				wr.flush();
				wr.close();
			}

			//make HTTPResponse object
			HttpResponse response = new HttpResponse();

			//get response code
			response.setStatusCode(connection.getResponseCode());

			//get Response headers
			response.setResponseHeaders(connection.getHeaderFields());

			//check response content type user parser according json or xml or string
			if(connection != null && connection.getContentType() != null) {
			if (connection.getContentType().equals(HttpRequest.CONTENTTYPE.XML.toString())) {
				//Parse xml
				String responseString = getStringFromInputStream(connection.getErrorStream() == null ? connection.getInputStream() : connection.getErrorStream());

				if(responseString != null) {
					Object unMarshalledObject = xmlToUnmarshellObject(responseString, httpRequest.getResponseClasses());
					if (unMarshalledObject == null) {
						response.setResponse(responseString);
					} else {
						response.setResponse(unMarshalledObject);
					}
				}

			} else if (connection.getContentType().equals(HttpRequest.CONTENTTYPE.JSON.toString())) {
				//TODO ::Parse json
			}else {
				//TODO:: Parse text
			}
			} else {
				response.setResponse(null);
			}

			return response;
		} catch (Exception e) {
		}
		finally {
			connection.disconnect();
		}
		return null;
	}

	/**
	 * Method to get String from inputStream
	 * 
	 * @param inputStream
	 * @return
	 */
	private static String getStringFromInputStream(InputStream inputStream) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					inputStream));
			String inputLine;
			StringBuilder responseString = new StringBuilder();
			while ((inputLine = in.readLine()) != null)
				responseString.append(inputLine);

			return responseString.toString();

		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	private static Object xmlToUnmarshellObject(String text, Class[] classes) {

		for (Class class1 : classes) {

			try {

				JAXBContext context = JAXBContext.newInstance(class1);
				Unmarshaller m = context.createUnmarshaller();
				Object object = m.unmarshal(new StringReader(text));

				return object;
			} catch (Exception e) {
				// If exception occurs then go to next class
				continue;
			}

		}
		return null;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}
