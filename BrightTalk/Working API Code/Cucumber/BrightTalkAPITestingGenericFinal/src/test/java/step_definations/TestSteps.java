package step_definations;

import java.net.HttpURLConnection;
import java.util.List;

import org.testng.Assert;

import Http.HttpRequest;
import Http.HttpRequestExecuiter;
import Model.AddRealmRequest;
import Model.ErrorResponse;
import Model.GetRealmSuccess;
import Model.HttpResponse;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestSteps {
	private String id;
	private String name;
	private String description;
	private String contentType = "application/xml";
	private HttpResponse httpGetResponse;
	private HttpResponse httpDeleteResponse;
	private HttpResponse httpPostResponse;
	AddRealmRequest realm = new AddRealmRequest();
	GetRealmSuccess getRealmSuccess;

	@When("^a user sends request with payload with name and description$")
	public void a_user_sends_request_with_payload_with_name_and_description(DataTable dataTable) throws Throwable {
		System.out.println("========================Test case 1=========================");
		System.out.println("========================Create Realm request================");
		List<List<String>> data = dataTable.raw();
		this.name = data.get(0).get(0);
		this.description = data.get(0).get(1);
		realm.setName(name);
		realm.setDescription(description);
		httpPostResponse = HttpRequestExecuiter.executeAsync(HttpRequest.addRealmRequest(realm.serialiseRealm()));
	}

	@Then("^the status code is \"([^\"]*)\"$")
	public void the_status_code_is(String statusCode) throws Throwable {		
		if(httpPostResponse.getStatusCode()==Integer.parseInt(statusCode)){
			System.out.println("PASS: Status code matches.");
		}
		else{
			ErrorResponse error = (ErrorResponse) httpPostResponse.getResponse();
			String errorCode = error.getCode();
			String description = error.getMessage();
			int responseCode = httpPostResponse.getStatusCode();
			Assert.fail("FAIL: Status code doesn't match.\n" + "Actual Response Code is ==> " + Integer.toString(responseCode)
					+ "\nExpected Response code is ==> "+statusCode+"\nError is ==> " + errorCode + "\nDescription is ==> " + description);			
		}
	}

	@Then("^the status code for get is \"([^\"]*)\"$")
	public void the_status_code_for_get_is(String statusCode) throws Throwable {
		if(httpGetResponse.getStatusCode()==Integer.parseInt(statusCode)){
			System.out.println("PASS: Status code matches.");
		}
		else{
			ErrorResponse error = (ErrorResponse) httpGetResponse.getResponse();
			String errorCode = error.getCode();
			String description = error.getMessage();
			int responseCode = httpGetResponse.getStatusCode();
			Assert.fail("FAIL: Status code doesn't match.\n" + "Actual Response Code is ==> " + Integer.toString(responseCode)
					+ "\nExpected Response code is ==> "+statusCode+"\nError is ==> " + errorCode + "\nDescription is ==> " + description);			
		}
	}
	@Then("^the status code for deletenotinteger is \"([^\"]*)\"$")
	public void the_status_code_deletenotinteger_is(String statusCode) throws Throwable {
		if(httpDeleteResponse.getStatusCode()==Integer.parseInt(statusCode)){
			System.out.println("PASS: Status code matches.");
		}
		else{
			ErrorResponse error = (ErrorResponse) httpDeleteResponse.getResponse();
			String errorCode = error.getCode();
			String description = error.getMessage();
			int responseCode = httpDeleteResponse.getStatusCode();
			Assert.fail("FAIL: Status code doesn't match.\n" + "Actual Response Code is ==> " + Integer.toString(responseCode)
					+ "\nExpected Response code is ==> "+statusCode+"\nError is ==> " + errorCode + "\nDescription is ==> " + description);			
		}
	}
	

	@Then("^response for request is proper$")
	public void response_for_request_is_proper() throws Throwable {
		// API Success
		if (httpPostResponse != null && httpPostResponse.getResponse() != null
				&& httpPostResponse.getStatusCode() == HttpURLConnection.HTTP_CREATED
				&& httpPostResponse.getResponse() instanceof GetRealmSuccess) {
			getRealmSuccess = (GetRealmSuccess) httpPostResponse.getResponse();
			Assert.assertEquals(getRealmSuccess.getName(), name,"Response name matches");
			Assert.assertEquals(getRealmSuccess.getDescription(), description,"Response description matches");
			Assert.assertEquals(httpPostResponse.getContentType(), contentType,"Response content-type matches");
			System.out.println("PASS: Name, Description and Content-Type matches.");
		}

		// API failure
		if (httpPostResponse != null && httpPostResponse.getResponse() != null
				&& httpPostResponse.getResponse() instanceof ErrorResponse) {
			ErrorResponse error = (ErrorResponse) httpPostResponse.getResponse();
			String errorCode = error.getCode();
			String description = error.getMessage();
			int responseCode = httpPostResponse.getStatusCode();
			Assert.fail("FAIL: Response doesn't match.\n" + "ResponseCode is+>" + Integer.toString(responseCode)
					+ "\nError is=>" + errorCode + "\nDescription is+>" + description);
		}
	}

	@When("^a user sends request with payload with samename and description$")
	public void a_user_sends_request_with_payload_with_samename_and_description(DataTable dataTable) throws Throwable {
		System.out.println("========================Test case 2=========================");
		System.out.println("===========Create Realm request with same name==============");
		List<List<String>> data = dataTable.raw();
		this.name = data.get(0).get(0);
		this.description = data.get(0).get(1);
		realm.setName(name);
		realm.setDescription(description);
		httpPostResponse = HttpRequestExecuiter.executeAsync(HttpRequest.addRealmRequest(realm.serialiseRealm()));
	}

	@Then("^the response for request with duplicate RealmName is proper$")
	public void the_response_for_request_with_duplicate_RealmName_is_proper() throws Throwable {
		verifyErrorReponseForCreateRealm();
	}

	@When("^a user sends request with payload with empty name and description$")
	public void a_user_sends_request_with_payload_with_empty_name_and_description(DataTable dataTable)
			throws Throwable {
		System.out.println("========================Test case 3=========================");
		System.out.println("===========Create Realm request with empty name=============");
		List<List<String>> data = dataTable.raw();
		this.name = data.get(0).get(0);
		this.description = data.get(0).get(1);
		realm.setName(name);
		realm.setDescription(description);
		httpPostResponse = HttpRequestExecuiter.executeAsync(HttpRequest.addRealmRequest(realm.serialiseRealm()));
	}

	@Then("^the response for request with empty RealmName is proper$")
	public void the_response_for_request_with_empty_RealmName_is_proper() throws Throwable {
		verifyErrorReponseForCreateRealm();
	}

	@When("^a user sends request with payload with max length name and description$")
	public void a_user_sends_request_with_payload_with_max_length_name_and_description(DataTable dataTable) throws Throwable {
		System.out.println("========================Test case 4=========================");
		System.out.println("===========Create Realm request with max length name========");
		List<List<String>> data = dataTable.raw();
		this.name = data.get(0).get(0);
		this.description = data.get(0).get(1);
		realm.setName(name);
		realm.setDescription(description);
		httpPostResponse = HttpRequestExecuiter.executeAsync(HttpRequest.addRealmRequest(realm.serialiseRealm()));
	}
	
	@Then("^the response for request with max length RealmName is proper$")
	public void the_response_for_request_with_max_length_RealmName_is_proper() throws Throwable {
		verifyErrorReponseForCreateRealm();
	}

	@When("^a user sends request with payload with name and max length description$")
	public void a_user_sends_request_with_payload_with_name_and_max_length_description(DataTable dataTable)
			throws Throwable {
		System.out.println("========================Test case 5=========================");
		System.out.println("=========Create Realm request with max length description===");
		List<List<String>> data = dataTable.raw();
		this.name = data.get(0).get(0);
		this.description = data.get(0).get(1);
		realm.setName(name);
		realm.setDescription(description);
		httpPostResponse = HttpRequestExecuiter.executeAsync(HttpRequest.addRealmRequest(realm.serialiseRealm()));
	}

	@Then("^the response for request with max description$")
	public void the_response_for_request_with_max_description() throws Throwable {
		verifyErrorReponseForCreateRealm();
	}
	
	@When("^a user sends request to create and get realm details$")
	public void a_user_sends_request_to_create_and_get_realm_details(DataTable dataTable) throws Throwable {
		System.out.println("========================Test case 6=========================");
		System.out.println("======================Get Realm details=====================");
		List<List<String>> data = dataTable.raw();
		this.name = data.get(0).get(0);
		this.description = data.get(0).get(1);
		realm.setName(name);
		realm.setDescription(description);
		httpPostResponse = HttpRequestExecuiter.executeAsync(HttpRequest.addRealmRequest(realm.serialiseRealm()));		
		if (httpPostResponse != null && httpPostResponse.getResponse() != null
				&& httpPostResponse.getStatusCode() == HttpURLConnection.HTTP_CREATED
				&& httpPostResponse.getResponse() instanceof GetRealmSuccess) {
			getRealmSuccess = (GetRealmSuccess) httpPostResponse.getResponse();
			id=getRealmSuccess.getId();
			System.out.println("ID is=>"+id);
			httpGetResponse = HttpRequestExecuiter.executeAsync(HttpRequest.getRelamRequest(id));
		} else {
			ErrorResponse error = (ErrorResponse) httpPostResponse.getResponse();
			String errorCode = error.getCode();
			String description = error.getMessage();
			int responseCode = httpPostResponse.getStatusCode();
			Assert.fail("FAIL: POST Response error.\n" + "ResponseCode is+>" + Integer.toString(responseCode)
					+ "\nError is=>" + errorCode + "\nDescription is+>" + description);
		}

	}

	@Then("^the response for get realm details should be proper$")
	public void the_response_for_get_realm_details_should_be_proper() throws Throwable {
		// API success
		if (httpGetResponse != null && httpGetResponse.getResponse() != null
				&& httpGetResponse.getStatusCode() == HttpURLConnection.HTTP_OK
				&& httpGetResponse.getResponse() instanceof GetRealmSuccess) {
			GetRealmSuccess getRealmSuccess = (GetRealmSuccess) httpGetResponse.getResponse();			
			Assert.assertEquals(getRealmSuccess.getId(), id,"Response Id matches");
			Assert.assertEquals(getRealmSuccess.getName(), name,"Response name matches");
			Assert.assertEquals(getRealmSuccess.getDescription(), description,"Response Description matches");
			Assert.assertEquals(httpGetResponse.getContentType(), contentType,"Response content-type matches");
			System.out.println("PASS: Get Response ID, Name, Description and Content-type matches.");
			
		}

		// API failure
		if (httpGetResponse != null && httpGetResponse.getResponse() != null
				&& httpGetResponse.getResponse() instanceof ErrorResponse) {
			ErrorResponse error = (ErrorResponse) httpGetResponse.getResponse();
			Assert.fail("FAIL: Get Response doesn't match.\n" +error.toString());			
		}

	}
	
	@When("^a user sends request to get realm details for$")
	public void a_user_sends_request_to_get_realm_details_for(DataTable dataTable) throws Throwable {
		System.out.println("========================Test case 7=========================");
		System.out.println("===========Get Realm details for max length realm id========");
		List<List<String>> data = dataTable.raw();
		this.id = data.get(0).get(0);
		httpGetResponse=HttpRequestExecuiter.executeAsync(HttpRequest.getRelamRequest(id));		    
	}
	
	@Then("^the response for max legth realm id details should be proper$")
	public void the_response_for_max_length_realm_id_details_should_be_proper() throws Throwable {
		verifyErrorReponseToGetRealm();
	}

	@When("^a user sends request to get realm details for non exist realm$")
	public void a_user_sends_request_to_get_realm_details_for_non_exist_realm(DataTable dataTable) throws Throwable {
		System.out.println("========================Test case 8=========================");
		System.out.println("===========Get Realm details for non exist realm id=========");
		List<List<String>> data = dataTable.raw();
		this.id = data.get(0).get(0);
		httpGetResponse=HttpRequestExecuiter.executeAsync(HttpRequest.getRelamRequest(id));
	}
	
	@Then("^the response for get realm details for non existing should be proper$")
	public void the_response_for_get_realm_details_for_non_existing_should_be_proper() throws Throwable {
		if(httpGetResponse.getResponse() == null && httpGetResponse.getStatusCode() == HttpURLConnection.HTTP_NOT_FOUND) {
			ErrorResponse error = (ErrorResponse) httpPostResponse.getResponse();
			String errorCode = error.getCode();			
			int responseCode = httpGetResponse.getStatusCode();
			if(errorCode.equalsIgnoreCase("RealmNotFound")){
				System.out.println("PASS: Realm not found for non existing realm Id");
			}else{
				Assert.fail("FAIL: Get response doesn't match for non existing id"+error.toString());
			}			
		} else {			
			Assert.fail("FAIL: Get response API failed.");			
		}
	}

	@When("^a user create and delete realm$")
	public void a_user_create_and_delete_realm(DataTable dataTable) throws Throwable {
		System.out.println("========================Test case 9=========================");
		System.out.println("======================Delete Realm==========================");
		List<List<String>> data = dataTable.raw();
		this.name = data.get(0).get(0);
		this.description = data.get(0).get(1);
		realm.setName(name);
		realm.setDescription(description);
		httpPostResponse = HttpRequestExecuiter.executeAsync(HttpRequest.addRealmRequest(realm.serialiseRealm()));		
		if (httpPostResponse != null && httpPostResponse.getResponse() != null
				&& httpPostResponse.getStatusCode() == HttpURLConnection.HTTP_CREATED
				&& httpPostResponse.getResponse() instanceof GetRealmSuccess) {
			getRealmSuccess = (GetRealmSuccess) httpPostResponse.getResponse();
			id=getRealmSuccess.getId();
			System.out.println("ID is=>"+id);
			httpDeleteResponse = HttpRequestExecuiter.executeAsync(HttpRequest.deleteRealmRequest(id));
		} else {
			ErrorResponse error = (ErrorResponse) httpPostResponse.getResponse();
			String errorCode = error.getCode();
			String description = error.getMessage();
			int responseCode = httpPostResponse.getStatusCode();
			Assert.fail("FAIL: POST Response error.\n" + "ResponseCode is+>" + Integer.toString(responseCode)
					+ "\nError is=>" + errorCode + "\nDescription is+>" + description);
		}
	}

	@Then("^the status code for delete is \"([^\"]*)\"$")
	public void the_status_code_for_delete_is(String arg1) throws Throwable {
		if(httpDeleteResponse.getResponse() == null && httpDeleteResponse.getStatusCode() == HttpURLConnection.HTTP_NO_CONTENT) {			
			System.out.println("PASS: Realm deleted successfully");
		} else {			
			ErrorResponse error = (ErrorResponse) httpDeleteResponse.getResponse();
//			String errorCode = error.getCode();
//			String description = error.getMessage();
			Assert.fail("FAIL: Delete response doesn't match"+error.toString());			
		}
	}

	@When("^a user sends request to delete realm details for$")
	public void a_user_sends_request_to_delete_realm_details_for(DataTable dataTable) throws Throwable {
		System.out.println("========================Test case 10=========================");
		System.out.println("===================Delete Realm for non exist realm id=======");
		List<List<String>> data = dataTable.raw();
		this.id = data.get(0).get(0);
		httpDeleteResponse = HttpRequestExecuiter.executeAsync(HttpRequest.deleteRealmRequest(id));		
	}
	
	@Then("^the response for delete realm should be proper$")
	public void the_response_for_delete_realm_should_be_proper() throws Throwable {
		if(httpDeleteResponse.getResponse() == null && httpDeleteResponse.getStatusCode() == HttpURLConnection.HTTP_BAD_REQUEST) {
			ErrorResponse error = (ErrorResponse) httpDeleteResponse.getResponse();
			String errorCode = error.getCode();		
			if(errorCode.equalsIgnoreCase("InvalidRealmId")){
				System.out.println("PASS: For non integer value of Realm Id verified successfully");
			}else{
				Assert.fail("FAIL: Delete response doesn't match for non existing id"+error.toString());
			}
		} else {
			//API failure
			Assert.fail("FAIL: Delete API request failed.=>");		
		}
	}
	
	public void createRealm(String name1, String description1, HttpResponse response1) {
		realm.setName(name1);
		realm.setDescription(description1);
		response1 = HttpRequestExecuiter.executeAsync(HttpRequest.addRealmRequest(realm.serialiseRealm()));

	}
	
	public void verifyErrorReponseToGetRealm() {
		if (httpGetResponse != null && httpGetResponse.getResponse() != null) {
			ErrorResponse error = (ErrorResponse) httpGetResponse.getResponse();
			String errorCode = error.getCode();			
			int responseCode = httpGetResponse.getStatusCode();
			if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
				if (errorCode.equalsIgnoreCase("InvalidRealmName")) {
					System.out.println("PASS: Response for max length Realm Id matches.");
				}else {
					Assert.fail("FAIL: Response doesn't matches and error for request is -> " + error.toString());
				}
			}
		} else {
			Assert.fail("FAIL: API request failed.");
		}
	}
	
	public void verifyErrorReponseForCreateRealm() {
		if (httpPostResponse != null && httpPostResponse.getResponse() != null) {
			ErrorResponse error = (ErrorResponse) httpPostResponse.getResponse();
			String errorCode = error.getCode();			
			int responseCode = httpPostResponse.getStatusCode();
			if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
				System.out.println("INFO: Getting Bad Response Code 400.");
				if (errorCode.equalsIgnoreCase("MissingRealmName")) {
					System.out.println("PASS: Response for empty Realm name matches.");
				} else if (errorCode.equalsIgnoreCase("DuplicateRealmName")) {
					System.out.println("PASS: Response for duplicate Realm name matches.");
				} else if (errorCode.equalsIgnoreCase("InvalidRealmName")) {
					System.out.println("PASS: Response for max length Realm name matches.");
				} else if (errorCode.equalsIgnoreCase("InvalidRealmDescription")) {
					System.out.println("PASS: Response for max length Realm description matches.");
				} else {
					Assert.fail("FAIL: Response doesn't matches and error for request is -> " + error.toString());
				}
			}
		} else {
			Assert.fail("FAIL: API request failed.");
		}
	}

}
