package api.Tests;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Endpoint.PetEndpoints;
import api.Payloads.Pet;
import io.restassured.response.Response;

public class PetTest {
	
	Faker faker;
	Pet requestBody;
	
	@BeforeClass
	public void setup() {
		
		faker = new Faker();
		requestBody = new Pet();
		int randomId = ThreadLocalRandom.current().nextInt(1, 11);
		int randomCategoryId = ThreadLocalRandom.current().nextInt(1, 11);
		int randomTagId = ThreadLocalRandom.current().nextInt(11, 22);
		String petName = faker.animal().name();
		
		requestBody.setId(randomId);
		Pet.Category category = new Pet.Category();
		category.setId(randomCategoryId);
		category.setName(petName);
		requestBody.setName(petName);
		requestBody.setPhotoUrls(Collections.singletonList(Pet.selectImage()));
		Pet.Tag tag = new Pet.Tag();
		tag.setId(randomTagId);
		tag.setName(faker.name().username());
		requestBody.setStatus(Pet.getRandomStatus());
		
	}
	
	
	
	
	@Test(priority = 1)
	public void addNewPetInStore() {
		
		Response response = PetEndpoints.addNewPet(requestBody);
		
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("id"), String.valueOf(requestBody.getId()));
		Assert.assertEquals(response.jsonPath().getString("name"), requestBody.getName());
	}
	
	
	
	
	@Test(priority = 2)
	public void updateExistingPetDetails() {
		
		int randomTagId = ThreadLocalRandom.current().nextInt(1, 11);
		
		Pet.Tag tag = new Pet.Tag();
		tag.setId(randomTagId);
		tag.setName(faker.name().username());
		requestBody.setStatus(Pet.getRandomStatus());
		
		Response response = PetEndpoints.updateExistingPet(requestBody);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("id"), String.valueOf(requestBody.getId()));
		Assert.assertEquals(response.jsonPath().getString("name"), requestBody.getName());		                
	}
	
	
	
	
	@Test(priority = 3)
	public void findPetById() {
		
		Response response = PetEndpoints.findPetById(this.requestBody.getId());
		
	    response.then().log().all();
	    
	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertEquals(response.jsonPath().getString("id"), String.valueOf(requestBody.getId()));
		Assert.assertEquals(response.jsonPath().getString("name"), requestBody.getName());	
	}
	
	
	
	
	@Test(priority = 4)
	public void findPetByStatus() {
		
		Response response = PetEndpoints.findPetByStatus();
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
//		Assert.assertEquals(response.jsonPath().getString("[0].status"), Pet.getRandomStatus());
	}
	
	
	
	
	@Test(priority = 5)
	public void updatePetDetailsWithFormData() {
		
		Response response = PetEndpoints.updatePetWithFormData(this.requestBody.getId(), this.requestBody.getName(), Pet.getRandomStatus());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("code"), "200");
		Assert.assertEquals(response.jsonPath().getString("message"), String.valueOf(requestBody.getId()));
	}
	
	
	
	
	@Test(priority = 6)
	public void deletePetFromStore() {
		
		Response response = PetEndpoints.deletePet(this.requestBody.getId());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("code"), "200");
		Assert.assertEquals(response.jsonPath().getString("message"), String.valueOf(requestBody.getId()));
	}

}
