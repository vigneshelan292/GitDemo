package Pojogoogle;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class specbuilder {
	static  RequestSpecification reques;  //just for reference(Dont compare thsi with code)-this will return RequestSpecificationImpl (Some internal claass object name)
	static RequestSpecification placeIdRequestSpec;
	
	
	
	public static RequestSpecification requestspec() throws IOException {
		if(reques==null) {
		
		PrintStream p1=new PrintStream(new FileOutputStream("logging.txt"));
		 
		reques= new RequestSpecBuilder().setBaseUri(getpropert("baseURL")).addFilter(RequestLoggingFilter.logRequestTo(p1))
				.addFilter(ResponseLoggingFilter.logResponseTo(p1))
		.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		return reques; // 
		}
		return reques; 
	}
	
	public static ResponseSpecification responsespec() throws FileNotFoundException {
		
		 
		ResponseSpecification reques= new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		return reques;
		
	}

	public static String getpropert(String key) throws IOException {
		
		Properties propert= new Properties();//java class
		FileInputStream fileinp= new FileInputStream("src/test/resources/global.properties");
		propert.load(fileinp);
		return propert.getProperty(key);
		
}

	public static RequestSpecification requestspec1() throws IOException {
		
		if(placeIdRequestSpec==null) {
		
		PrintStream p1=new PrintStream(new FileOutputStream("loggingnew.txt"));
		 
		placeIdRequestSpec= new RequestSpecBuilder().setBaseUri(getpropert("baseURL")).addFilter(RequestLoggingFilter.logRequestTo(p1))
				.addFilter(ResponseLoggingFilter.logResponseTo(p1))
		.addQueryParam("key", "qaclick123").build();
		return placeIdRequestSpec;
		}
		return placeIdRequestSpec; 
	}
	
	
}
