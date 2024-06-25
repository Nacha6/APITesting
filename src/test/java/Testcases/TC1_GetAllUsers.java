package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TC1_GetAllUsers 
{
	@Test
	  public void getallusers()
	  {
		    //Response response = 
		    RestAssured
		    .given()
		       .auth().basic("Numpy@gmail.com","userapi123")
		       .baseUri("https://userserviceapi-a54ceee3346a.herokuapp.com/uap")
		    .when()
		       .get("/users")  
		       .then()
		       .assertThat()
		       .statusCode(200);
		       //.header("Contect-Type", "application/json")
		       //.extract()
		       //.response();
		      // Assert.assertTrue(response.getBody().asString().contains("user_id"));
		    
		       	    
	         
	  } 
}
