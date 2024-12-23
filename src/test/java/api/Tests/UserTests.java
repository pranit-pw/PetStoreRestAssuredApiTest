package api.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Endpoint.UserEndponits;
import api.Payloads.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setup() {
		
		userPayload = new User();
		faker = new Faker();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setUsername(faker.name().username());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	
	
	@Test(priority = 1)
	public void testCreateUser() {
		
		Response response = UserEndponits.createUser(userPayload);
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.header("Content-Type"), "application/json");
		Assert.assertEquals(response.header("Transfer-Encoding"), "chunked");
		Assert.assertEquals(response.header("Server"), "Jetty(9.2.9.v20150224)");
		Assert.assertEquals(response.jsonPath().getString("code"), "200");
	}
	
	
	
	@Test(priority = 2)
	public void testGetUserByName() {
		
		Response response = UserEndponits.readUser(this.userPayload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("username"), userPayload.getUsername());
		Assert.assertEquals(response.jsonPath().getString("email"), userPayload.getEmail());
	}
	
	
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
		
		Response response = UserEndponits.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("code"), "200");
		
		
		// Check response after update
		Response afterUpdate = UserEndponits.readUser(this.userPayload.getUsername());
		afterUpdate.then().log().all();
		
		Assert.assertEquals(afterUpdate.getStatusCode(), 200);
		Assert.assertEquals(afterUpdate.jsonPath().getString("firstName"), userPayload.getFirstName());
		Assert.assertEquals(afterUpdate.jsonPath().getString("lastName"), userPayload.getLastName());
		Assert.assertEquals(afterUpdate.jsonPath().getString("email"), userPayload.getEmail());
	}
	
	
	
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
		
		Response response = UserEndponits.deleteUser(this.userPayload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("code"), "200");
		Assert.assertEquals(response.jsonPath().getString("message"), userPayload.getUsername());
	}
	
	
	
	@Test(priority = 5)
	public void testUserLogin() {
		
		Response response = UserEndponits.userLogin(this.userPayload.getUsername(), this.userPayload.getPassword());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	
	@Test(priority = 6)
	public static void testUserLogout() {
		
		Response response = UserEndponits.userLogout();
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
