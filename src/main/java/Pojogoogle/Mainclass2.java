package Pojogoogle;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Mainclass2 {

	public static addplace buildingrequestpayload(String name, String language, String address, String phonenumber, String accuracy, String website, String latitude, String longitude, List<String> type) {
		
		
		addplace place=new addplace();
		place.setAccuracy(accuracy);
		place.setAddress(address);
		place.setPhone_number(phonenumber);
		place.setWebsite(website);
		place.setLanguage(language);
		place.setName(name);
		
		location locate=new location();
		locate.setLat(latitude);
		locate.setLng(longitude);
		place.setLocation(locate);
		
		List<String> list=new ArrayList<String>();
		list.addAll(type);
		place.setTypes(list);
		System.out.println("testnew");
		return place;
		
		
	}
		
		 //RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
	//	.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		 
		 
		// ResponseSpecification respn=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
	
		
		//RequestSpecification result=given().spec(specbuilder.requestspec());
	//segeregated
			
	//AppPlaceresponse res= result.body(place).log().body().when().post("/maps/api/place/add/json")
			//.then().log().all().spec(specbuilder.responsespec()).extract().response().as(AppPlaceresponse.class);
	
	//System.out.println(res.getId());
	
	
	 
	
		
		
		
		
	}

	
	
//}
