package stepdefinitions;
import Pojogoogle.addplace;
import Pojogoogle.location;

import Pojogoogle.specbuilder;
import Pojogoogle.apiresources;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Pojogoogle.AppPlaceresponse;
import Pojogoogle.Mainclass2;
import Pojogoogle.addplace;
import Pojogoogle.apiresources;
import Pojogoogle.specbuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

public class stepdefinition {

	RequestSpecification result;
	Response res;
	AppPlaceresponse receivedresp;
	ResponseSpecification responsespec;
	static String place;

	@Given("sending request payload with place information {string} {string} {string} {string} {string} {string} {string} {string} {string}")
	public void sending_request_payload_with_place_information(String Name, String Language, String Address,
			String Phonenumber, String Accuracy, String Website, String Latitude, String Longitude, String types)
			throws IOException {
		result=specbuilder.requestspec();//which will return RequestSpecification object

		List<String> type = Arrays.asList(types.split(","));

		result = given().spec(result).body(Mainclass2.buildingrequestpayload(Name, Language, Address,
				Phonenumber, Accuracy, Website, Latitude, Longitude, type));
		

	}

	@When("triggering the Http {string} method for {string} with resources attached")
	public void triggering_the_http_post_method_for_addplace_api_with_resources_attached(String method,
			String resource) {
		// constructor will be called with the value of resource which you pass
		apiresources addplaceap = apiresources.valueOf(resource);
		addplaceap.getresource();
		if (method.equalsIgnoreCase("POST")) {
			res = result.log().body().when().post(addplaceap.getresource());
		} else if (method.equalsIgnoreCase("GET")) {
			res = result.log().body().when().get(addplaceap.getresource());
         }
		else if(method.equalsIgnoreCase("DELETE")){
			res=result.log().body().when().delete(addplaceap.getresource());//This logs the body just before sending the POST request.
			
		}
	}

	@Then("validating the response with status code")
	public void validating_the_response_with_status_code() throws FileNotFoundException {
		responsespec=specbuilder.responsespec();

		receivedresp = res.then().spec(responsespec).extract().response().as(AppPlaceresponse.class);
		String id = receivedresp.getId();
		System.out.println(id);
		place = receivedresp.getPlace_id();

	}

	@Then("also validating the {string} is {string}")
	public void also_validating_the_is(String key, String expected) {
		String actual = res.jsonPath().getString(key);// extract the value for key which is status
		Assert.assertEquals(expected, actual);// in junit 1st expected and then we need to give actual

	}

	@Then("validating the {string} is {string}")
	public void validating_the_is(String key, String expected) {
		String actual = res.jsonPath().getString(key);
		Assert.assertEquals(expected, actual);
	}

	@Then("using the PlaceID retrieve the info and maps to {string} by {string}")
	public void using_the_place_id_retrieve_the_info_and_maps_to_by(String expectednanme, String resource) throws IOException {
	    result = given().spec(specbuilder.requestspec()).queryParam("place_id", place);
	    triggering_the_http_post_method_for_addplace_api_with_resources_attached("GET", resource);
	    String getplce=res.asString();
	    JsonPath js=new JsonPath(getplce);
	    String name=js.getString("name");
	    System.out.println(name);
	   Assert.assertEquals(expectednanme, name);
	   }
	
	@Then("using the PlaceID deleting the created place using {string} resource by {string}")
	public void using_the_place_id_deleting_the_created_place_using_resource_by(String method, String resource) throws IOException {
		if (place == null || place.trim().isEmpty()) {
		    throw new RuntimeException("place_id is NULL. Cannot delete.");
		}
		Map<String, String> deletePayload = new HashMap<String, String>();//important
		deletePayload.put("place_id", place);
		
		result=given().spec(specbuilder.requestspec()).body(deletePayload);
		triggering_the_http_post_method_for_addplace_api_with_resources_attached(method, resource);
		res.then().spec(specbuilder.responsespec()).statusCode(200);
		
	System.out.println(res.asString());
		String dele=res.asString();
		 JsonPath js=new JsonPath(dele);
		String deleeteplace=js.get("status");
		System.out.println(deleeteplace);
		
		
	}

}
