package api.Tests;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.Endpoint.StoreEndpoints;
import api.Payloads.Store;
import io.restassured.response.Response;

public class StoreTest {
	
	Store store;
	
	@BeforeClass
	public void setup() {
		
		store = new Store();
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String formatedDateTime = now.format(formatter);
		int randomId = ThreadLocalRandom.current().nextInt(1, 11);
		int randomPetId =  ThreadLocalRandom.current().nextInt(1, 15);
		int randomQuantity =  ThreadLocalRandom.current().nextInt(1, 4);
		
		store.setId(randomId);
		store.setPetId(randomPetId);
		store.setQuantity(randomQuantity);
		store.setShipDate(formatedDateTime);
		store.setStatus("placed");
		store.setComplete(true);
		
	}
	
	
	
	
	@Test(priority = 1)
	public void testStoreInventory() {
		
		Response response = StoreEndpoints.getStoreInventory();
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	
	
	@Test(priority = 2)
	public void testPlaceOrder() {
		
		Response response = StoreEndpoints.placeOrder(store);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	
	
	@Test(priority = 3)
	public void testGetOrderDetails() {
		
		Response response = StoreEndpoints.getOrderDetails(this.store.getId());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("id"), String.valueOf(store.getId()));
		Assert.assertEquals(response.jsonPath().getString("petId"), String.valueOf(store.getPetId()));
		Assert.assertEquals(response.jsonPath().getString("quantity"), String.valueOf(store.getQuantity()));
	}
	
	
	
	
	@Test(priority = 4)
	public void testdeleteOrder() {
		
		Response response = StoreEndpoints.getOrderDetails(this.store.getId());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
