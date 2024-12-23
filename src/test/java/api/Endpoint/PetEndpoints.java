package api.Endpoint;

import static io.restassured.RestAssured.given;

import api.Payloads.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndpoints {
	
	
    public static Response addNewPet(Pet requestBody) {
		
		Response response = given()
				               .accept(ContentType.JSON)
				               .contentType(ContentType.JSON)
				               .body(requestBody)
				            .when()
				               .post(Routes.post_addnewpet_url);
		return response;
	}
    
    
    
    
    public static Response updateExistingPet(Pet requestBody) {
    	
    	Response response = given()
    			                .accept(ContentType.JSON)
    			                .contentType(ContentType.JSON)
    			                .body(requestBody)
    			            .when()
    			                .put(Routes.put_updatepetdetails_url);
    	
    	return response;
    }
    
    
    
    
    public static Response findPetById(int id) {
    	
    	Response response = given()
    			               .pathParam("petId", id)
    			            .when()
    			                .get(Routes.get_findpetbyid_url);
    	
    	return response;
    }
    
    
    
    
    public static Response findPetByStatus() {
    	
    	Response response = given()
    			                .accept(ContentType.JSON)
    			                .queryParam("status", Pet.getRandomStatus())
    			           .when()
    			               .get(Routes.get_findpetbystatus_url);
    	
    	return response;
    }
    
    
    
    
    public static Response updatePetWithFormData(int id, String name, String stat) {
    	
    	Response response = given()
    			                .accept(ContentType.JSON)
    			                .contentType("application/x-www-form-urlencoded")
    			                .pathParam("petId", id)
    			                .formParam("name", name)
    			                .formParam("status", stat)
    			           .when()
    			                .post(Routes.post_updatepetwithformdata_url);
    	
    	return response;
    }
    
    
    
    
    public static Response deletePet(int id) {
    	
    	Response response = given()
    			                .pathParam("petId", id)
    			            .when()
    			                .delete(Routes.delete_deletepet_url);
    	
    	return response;
    }

}
