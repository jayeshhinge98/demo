package Main;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import Http.HttpRequest;
import Http.HttpRequestExecuiter;
import Model.AddRealmRequest;
import Model.ErrorResponse;
import Model.GetRealmSuccess;
import Model.HttpResponse;


public class MainTest {

	public static void main(String[] args) {
		getRealm();

		//addRealm();

		//deleteRealm();
		
	}

	private static void getRealm() {
		HttpResponse httpResponse = HttpRequestExecuiter.executeAsync(HttpRequest.getRelamRequest("75"));

		//API success
		if(httpResponse != null && httpResponse.getResponse() != null && httpResponse.getStatusCode() == HttpURLConnection.HTTP_OK && httpResponse.getResponse() instanceof GetRealmSuccess) {
			GetRealmSuccess getRealmSuccess = (GetRealmSuccess) httpResponse.getResponse();
			String key = getRealmSuccess.getKey();
			String description = getRealmSuccess.getDescription();
			String id  = getRealmSuccess.getId();
			String name = getRealmSuccess.getName();
			System.out.println("Content-Type is+>"+httpResponse.getContentType());
			
		} 

		//API failure
		if(httpResponse != null && httpResponse.getResponse() != null && httpResponse.getResponse() instanceof ErrorResponse) {
			ErrorResponse error = (ErrorResponse) httpResponse.getResponse();
			String errorCode = error.getCode();
			String description = error.getMessage();
			int responseCode = httpResponse.getStatusCode();
		}
	}

	private static void addRealm() {
		AddRealmRequest realm = new AddRealmRequest();
		realm.setName("BrightTALK Aloha");
		realm.setDescription("test description");
		HttpResponse addresponse = HttpRequestExecuiter.executeAsync(HttpRequest.addRealmRequest(realm.serialiseRealm()));

		//API Success
		if(addresponse != null && addresponse.getResponse() != null && addresponse.getStatusCode() == HttpURLConnection.HTTP_CREATED && addresponse.getResponse() instanceof GetRealmSuccess) {
			GetRealmSuccess getRealmSuccess = (GetRealmSuccess) addresponse.getResponse();
			String key = getRealmSuccess.getKey();
			String description = getRealmSuccess.getDescription();
			String id  = getRealmSuccess.getId();
			String name = getRealmSuccess.getName();
		} 
		
		//API failure
		if(addresponse != null && addresponse.getResponse() != null && addresponse.getResponse() instanceof ErrorResponse) {
			ErrorResponse error = (ErrorResponse) addresponse.getResponse();
			String errorCode = error.getCode();
			String description = error.getMessage();
		}
	}

	private static void deleteRealm() {
		HttpResponse deleteResponse = HttpRequestExecuiter.executeAsync(HttpRequest.deleteRealmRequest("1352"));
		if(deleteResponse.getResponse() == null && deleteResponse.getStatusCode() == HttpURLConnection.HTTP_NO_CONTENT) {
			//API Success
			System.out.println("Realm deleted successfully");
		} else {
			//API failure
			ErrorResponse error = (ErrorResponse) deleteResponse.getResponse();
			String errorCode = error.getCode();
			String description = error.getMessage();
		}
	}
}
