Feature: Testing Realm REST API 
	Users should be able to submit GET and POST requests to a web service, 
  represented by Realm

#update the name
@POST
Scenario: Create Realm Id
When a user sends request with payload with name and description
|createRealmID7|description1|
Then the status code is "201"
And response for request is proper

#If the requested realm name matches the name of an existing realm.
@POST
Scenario: Check Duplicate Realm Name
When a user sends request with payload with samename and description
|createRealmID6|description1| 
Then the status code is "400"
And the response for request with duplicate RealmName is proper

#If the mandatory realm name is not supplied or if supplied is blank/empty
@POST
Scenario: Check Empty Realm Name
When a user sends request with payload with empty name and description
||description1|
Then the status code is "400"
And the response for request with empty RealmName is proper

#If the requested realm name is longer than 100 chars.
@POST
Scenario: Check max length validation for Realm Name
When a user sends request with payload with max length name and description
|Name is A sample is a smaller manageable version of a larger group It is a subset containing the characteristics of a larger population|description1|
Then the status code is "400"
And the response for request with max length RealmName is proper

#If the requested realm description is longer than 255 chars.
@POST
Scenario: Check Max length validation for Description 
When a user sends request with payload with name and max length description
|name 1| Description is A sample is a smaller manageable version of a larger group It is a subset containing the characteristics of a larger population Samples are used in statistical testing when population sizes are too large for the test to include all possible members or observations|
Then the status code is "400"
And the response for request with max description

#update the name
#Get the Realm Id details
@GET
Scenario: Get Realm details
When a user sends request to create and get realm details
|getRealmID7|description21|
Then the status code for get is "200"
And the response for get realm details should be proper

#If the requested realm id is not an integer value or if it is an integer value larger than the allowed maximum (9999)
@GET
Scenario: Check max length integer value for id
When a user sends request to get realm details for 
|11000|
Then the status code for get is "400"
And the response for max legth realm id details should be proper

#If the requested realm id does not identify an existing realm.
@GET
Scenario: Check response for realm id which is not exist 
When a user sends request to get realm details for non exist realm
|5000|
Then the status code for get is "404"
And the response for get realm details for non existing should be proper

#update the name
#delete the existing realmId
@DELETE
Scenario: Check delete response for realm ID
When a user create and delete realm
|deleteRealm7|delete realm details|
Then the status code for delete is "204"

#If the requested realm id is not an integer value
@DELETE
Scenario: Check delete response for realm ID for non-exist or not integer value
When a user sends request to delete realm details for 
|11000|
Then the status code for deletenotinteger is "400"
And the response for delete realm should be proper






