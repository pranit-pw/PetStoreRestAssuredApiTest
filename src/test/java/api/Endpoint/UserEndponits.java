package api.Endpoint;

import static io.restassured.RestAssured.given;
import api.Payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndponits {
	
	
	public static Response createUser(User payload) {
		
		Response response = given()
				               .accept(ContentType.JSON)
				               .contentType(ContentType.JSON)
				               .body(payload)
				            .when()
				               .post(Routes.Post_url);
		return response;
	}
	
	
	
	
	public static Response readUser(String userName) {
		
		Response response = given()
				                .pathParam("username", userName)
				            .when()
				                .get(Routes.get_url);
		
		return response;
	}
	
	
	
	
    public static Response updateUser(String userName ,User payload) {
		
		Response response = given()
	                           .accept(ContentType.JSON)
	                           .contentType(ContentType.JSON)
				               .pathParam("username", userName)
				               .body(payload)
				            .when()
				               .put(Routes.update_url);
		return response;
	}
    
    
    
    
    public static Response deleteUser(String userName) {
    	
    	Response response = given()
    			                .pathParam("username", userName)
    			            .when()
    			                .delete(Routes.delete_url);
    	return response;
    }
    
    
    
    
    public static Response userLogin(String userName, String pass) {
    	
    	Response response = given()
    			               .queryParam("usename", userName)
    			               .queryParam("password", pass)
    			          .when()
    			               .get(Routes.get_url_login);
    	
    	return response;	
    }
    
    
    
    
    public static Response userLogout() {
    	
    	Response response = given()
    			            .when()
    			                .get(Routes.get_url_logout);
    	
    	return response;
    }

}
