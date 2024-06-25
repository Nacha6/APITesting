package Testcases;

import net.minidev.json.JSONObject;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIChaining {
	int userid;
	@Test
	  public void createusers()
	  {
		
		JSONObject user = new JSONObject();
		JSONObject userAddress = new JSONObject();
		
		user.put("user_first_name", "Nachu");
		user.put("user_last_name", "Lakshman");
		user.put("user_contact_number", "7298830480");
		user.put("user_email_id", "ln144@gmail.com");
		user.put("userAddress", userAddress);
		
		
		userAddress.put("plotNumber", "L-4433");
		userAddress.put("street", "four seasons way");
		userAddress.put("state", "california");
		userAddress.put("country", "USA");
		userAddress.put("zipCode", 28117);
	    System.out.println(user.toString());
	    
		 baseURI = "https://userserviceapi-a54ceee3346a.herokuapp.com/uap";
		 Response response = 
		 RestAssured		
		 .given()
		   .auth().basic("Numpy@gmail.com", "userapi123")  
		   .contentType(ContentType.JSON)
		   .body(user.toString())
		   
	     .when()
		   .post("/createusers")
		 .then()
		  .assertThat()
		  .statusCode(201)
		  .extract().response();
		
		 System.out.println(response.body().asPrettyString());
		 
	      userid = response.path("user_id");
			
					  RestAssured
			          .given()
		                  .auth().basic("Numpy@gmail.com","userapi123") 
		                  .contentType(ContentType.JSON)
		                  .pathParam("userId",userid) 
		                  .when()
		                   .get("/user/{userId}")       
		               .then()
		               .assertThat()
		     		  .statusCode(200)
		     		  .extract()
		     		  .response();
					  Assert.assertTrue(response.getBody().asString().contains("user_id"));
					  
					  
					 String userfirstname = response.path("user_first_name");
						
					  RestAssured
			          .given()
		                  .auth().basic("Numpy@gmail.com","userapi123") 
		                  .contentType(ContentType.JSON)
		                  .when()
		                   .get("/users/username/{userFirstName}",userfirstname)       
		               .then()
		               .assertThat()
		     		  .statusCode(200);
		     		
					  
					  user.put("user_first_name", "Nacha");
					 
						  
						  RestAssured
					   .given()
						  .auth().basic("Numpy@gmail.com","userapi123")
						  .baseUri("https://userserviceapi-a54ceee3346a.herokuapp.com/uap")
						  .header("Content-Type", "application/json")
						  .body(user.toString())
					  .when()
						  .put("/updateuser/{userId}",userid)
						  .prettyPrint();
						  
				  Response response1 =   
	              RestAssured
	               .given()
	                 .auth().basic("Numpy@gmail.com","userapi123")
	                 .baseUri("https://userserviceapi-a54ceee3346a.herokuapp.com/uap")
	                 .header("Content-Type", "application/json")
	                 .body(user.toString())
	                .when()
	                  .delete("/deleteuser/{userId}",userid)
	                .then()
		               .assertThat()
		     		  .statusCode(200)
		     		  .extract()
		     		  .response();
				      System.out.println("User deleted successfully" +response1.asPrettyString());
		     		  
		     		 
	  
	  
  }
	  }

	

