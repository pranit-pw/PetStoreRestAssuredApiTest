package api.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.Endpoint.UserEndponits;
import api.Payloads.User;
import api.Utilities.DataProviders;
import io.restassured.response.Response;

public class UserDDTest {
	
	
	@Test(priority = 1, dataProvider = "allData", dataProviderClass = DataProviders.class)
	public void testCreateMultipleUsers(String userId, String userName, String fName, String lName, String email, String pass, String ph) {
		
		
		User requestBody = new User();
		
		requestBody.setId(Integer.parseInt(userId));
		requestBody.setUsername(userName);
		requestBody.setFirstName(fName);
		requestBody.setLastName(lName);
		requestBody.setEmail(email);
		requestBody.setPassword(pass);
		requestBody.setPhone(ph);
		
		Response resp = UserEndponits.createUser(requestBody);
		System.out.println(resp.asPrettyString());
		Assert.assertEquals(resp.getStatusCode(), 200);
	}
	
	
	
	@Test(priority = 2, dataProvider = "singleColumn", dataProviderClass = DataProviders.class)
	public void testDeleteUserByUserName(String userName) {
		
		Response resp = UserEndponits.deleteUser(userName);
		Assert.assertEquals(resp.getStatusCode(), 200);
	}

}
