package Pojogoogle;
//enum is special class in java with collection of methods
public enum apiresources {
	
	AddplaceApi("/maps/api/place/add/json"),//method with argument
	GetplaceApi("/maps/api/place/get/json"),
	DeleteplaceApi("/maps/api/place/delete/json");
	
	private String resource;

	apiresources(String resource) {     //constructor with parameter same like mehods of addplaceapi
		
		this.resource=resource;        //this keyword refers to current class variable or object
	}



public String getresource() {
	return resource;
	
	
}
}