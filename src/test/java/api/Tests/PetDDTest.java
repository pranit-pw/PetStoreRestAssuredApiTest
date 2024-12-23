package api.Tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.Endpoint.PetEndpoints;
import api.Payloads.Pet;
import api.Utilities.DataProviders;
import io.restassured.response.Response;

public class PetDDTest {
	
	@Test(priority = 1, dataProvider = "allData", dataProviderClass = DataProviders.class)
	public void addNewPetsInStore(String id, String CId, String CName, String PName, String Purl, String TId, String TName, String status) {
		
		Pet requestBody = new Pet();
		
		requestBody.setId(Integer.parseInt(id));
		Pet.Category category = new Pet.Category();
		category.setId(Integer.parseInt(CId));
		category.setName(CName);
		requestBody.setName(PName);
		requestBody.setPhotoUrls(List.of(Purl));
		Pet.Tag tag = new Pet.Tag();
		tag.setId(Integer.parseInt(TId));
		tag.setName(TName);
		requestBody.setStatus(status);
		
		Response response = PetEndpoints.addNewPet(requestBody);
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	
	
	@Test(priority = 2, dataProvider = "singleColumn", dataProviderClass = DataProviders.class)
	public void deletePetsFromStore(String id) {
		
		Response response = PetEndpoints.deletePet(Integer.parseInt(id));
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
