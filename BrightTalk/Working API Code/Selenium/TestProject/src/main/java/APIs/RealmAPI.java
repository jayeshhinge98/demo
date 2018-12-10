package APIs;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class RealmAPI {

    public String GetResponseForGETCall(String url) throws ClientProtocolException, IOException {
        StringBuffer result = new StringBuffer();
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        request.setHeader("Content-Type","application/xml");
        HttpResponse response = client.execute(request);
        //System.out.println("response - "+response.getEntity().getContent());
        int responseCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code: " + responseCode);
        try {
            if (responseCode == 200) {
                System.out.println("PASS: Get Response is Successful..");
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                    //System.out.println(result.toString());
                }
            }
            return result.toString();

        } catch (Exception ex) {
            result.append("FAIL: Get Response Failed");
            return result.toString();
        }

    }

    public String GetResponseForDELETECall(String url) throws ClientProtocolException, IOException {
        StringBuffer result = new StringBuffer();
        HttpClient client = new DefaultHttpClient();
        //String url="http://api.openweathermap.org/data/2.5/weather?q=London";
        HttpDelete request = new HttpDelete(url);
        request.setHeader("Content-Type","application/xml");
        HttpResponse response = client.execute(request);
        //System.out.println("response - "+response.getEntity().getContent());
        int responseCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code: " + responseCode);
        try {
            if (responseCode == 204) {
                System.out.println("PASS: DELETE Response is Successfully");
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                    //System.out.println(result.toString());
                }
            }
            return result.toString();

        } catch (Exception ex) {
            result.append("Get Response Failed");
            return result.toString();
        }

    }

    public String GetResponseForPOSTCallForCreateRealm(String url, String name, String description) throws ClientProtocolException, IOException {
        String xml = "<realm name=\""+name+"\">\n" +
                "<description>"+description+"</description>\n" +
                "</realm>";
        StringBuffer result = new StringBuffer();
        HttpClient client = new DefaultHttpClient();

        HttpPost request = new HttpPost(url);
        HttpEntity entity = new ByteArrayEntity(xml.getBytes("UTF-8"));
        request.setEntity(entity);
        request.setHeader("Content-Type","application/xml; charset=utf-8");
        HttpResponse response = client.execute(request);
        int responseCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code: " + responseCode);
        try {
            if (responseCode == 400) {
                System.out.println("FAIL: POST Response is bad");
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                    System.out.println(result.toString());
                }
            } else if (responseCode == 201) {
                System.out.println("PASS: POST Response is Successful.");
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                    //System.out.println(result.toString());
                }
            }
            return result.toString();

        } catch (Exception ex) {
            result.append("Get Response Failed");
            return result.toString();
        }
    }

    public String randomStringWithNo(int length) {
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public String GetResponseForGETCallForID(String url) throws ClientProtocolException, IOException {
        StringBuffer result = new StringBuffer();
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        request.setHeader("Content-Type","application/xml");
        HttpResponse response = client.execute(request);
        //System.out.println("response - "+response.getEntity().getContent());
        int responseCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code: " + responseCode);
        try {
            if (responseCode == 200) {
                //System.out.println("FAIL: Get Response is Successful");
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                    //System.out.println(result.toString());
                }
            }
            else if (responseCode == 400) {
                System.out.println("PASS: Get Response with max integer Realm ID is bad(400)");
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                    System.out.println(result.toString());
                }
            }
            return result.toString();
        } catch (Exception ex) {
            result.append("FAIL: Get Response Failed");
            return result.toString();
        }
    }
    public String GetResponseForGETCallForNonExistID(String url) throws ClientProtocolException, IOException {
        StringBuffer result = new StringBuffer();
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        request.setHeader("Content-Type","application/xml");
        HttpResponse response = client.execute(request);
        //System.out.println("response - "+response.getEntity().getContent());
        int responseCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code: " + responseCode);
        try {
            if (responseCode == 200) {
                //System.out.println("FAIL: Get Response is Successful");
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                    //System.out.println(result.toString());
                }
            }
            else if (responseCode == 404) {
                System.out.println("PASS: Get Response for not exist Realm ID is bad(404)");
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                    System.out.println(result.toString());
                }
            }
            return result.toString();
        } catch (Exception ex) {
            result.append("FAIL: Get Response Failed");
            return result.toString();
        }
    }
    
	public String GetResponseForPOSTCallToCreateRealm(String url,
    	 String name, String description) throws ClientProtocolException, IOException {
		 String xml = "<realm name=\""+name+"\">\n" +
	                "<description>"+description+"</description>\n" +
	                "</realm>";
	        StringBuffer result = new StringBuffer();
	        String code="";
	        HttpClient client = new DefaultHttpClient();
	        HttpPost request = new HttpPost(url);
	        HttpEntity entity = new ByteArrayEntity(xml.getBytes("UTF-8"));
	        request.setEntity(entity);
	        request.setHeader("Content-Type","application/xml; charset=utf-8");
	        HttpResponse response = client.execute(request);
	        int responseCode = response.getStatusLine().getStatusCode();
	        System.out.println("Response Code: " + responseCode);
	        try {
	            if (responseCode == 400) {
	          //    System.out.println("PASS: POST Response is bad");
	                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	                String line = "";
	                
	                while ((line = reader.readLine()) != null) {
	                    result.append(line);	                    
	                    System.out.println(result.toString());
	                }
	            } else if (responseCode == 201) {
	                System.out.println("FAIL: POST Response is Successful.");
	                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	                String line = "";
	                while ((line = reader.readLine()) != null) {
	                    result.append(line);
	                    //System.out.println(result.toString());
	                }
	            }
	            return result.toString();

	        } catch (Exception ex) {
	            result.append("Get Response Failed");
	            return result.toString();
	        }

	}

}
