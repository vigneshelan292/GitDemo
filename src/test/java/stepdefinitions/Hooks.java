package stepdefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks  {     //@before only for Setup / Test data creation
	@Before("@Deleteplace")
	public void beforescenario() throws IOException {
		if(stepdefinition.place==null) {
		
		stepdefinition addplace=new stepdefinition();
		
		addplace.sending_request_payload_with_place_information("giri", "malayalam", "nungambakkam", "984185789", "200", "www.google.c", "38.383", "33.42", " rest,park");
		addplace.triggering_the_http_post_method_for_addplace_api_with_resources_attached("POST", "AddplaceApi");
		//addplace.using_the_place_id_deleting_the_created_place_using_resource_by("DELETE", "DeleteplaceApi");
		//Hook should only prepare data (create the place)
		addplace.validating_the_response_with_status_code();
	}
	}
	
}

