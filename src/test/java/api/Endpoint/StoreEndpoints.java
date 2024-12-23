package api.Endpoint;

import static io.restassured.RestAssured.given;

import api.Payloads.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndpoints {

	
	public static Response getStoreInventory() {
		
		Response response = given()
				                .accept(ContentType.JSON)
				                .header("api_key", "pet1998")
				            .when()
				               .get(Routes.get_storeinventory_url);
		
		return response;                 
	}
	
	
	
	
	public static Response placeOrder(Store requestBody) {
		
		Response response = given()
				               .accept(ContentType.JSON)
				               .contentType(ContentType.JSON)
				               .body(requestBody)
				            .when()
				               .post(Routes.post_placeorder_url);
		
		return response;
	}
	
	
	
	
	public static Response getOrderDetails(int id) {
		
		Response response = given()
				                .pathParam("orderId", id)
				            .when()
				                .get(Routes.get_storeorderdetails_url);
		
		return response;
	}
	
	
	
	
	public static Response deleteOrder(int id) {
		
		Response response = given()
				                .pathParam("orderId", id)
				            .when()
				                .delete(Routes.delete_storeorder_url);
		
		return response;
	}
	
}
