package APITestingPojos;

import static io.restassured.RestAssured.baseURI;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Utilities.FileNameConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class PostAPIRequestUsingPojos
{
  @Test
  public void PostAPIRequest()
  {
	  try 
	  {
		  
		  //jsonschema validation
		  String jsonSchema = FileUtils.readFileToString(new File(FileNameConstants.JSON_SCHEMA),"UTF-8");
		  UserAddress userAddress = new UserAddress("L-4433","one way","Chicago","USA","28115");
		  User user = new User("haritha","lily","1243832543","l9@gmail.com",userAddress);
		  
		  ObjectMapper objectmapper = new ObjectMapper();
		  String requestbody = objectmapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
		  
		  System.out.println("This is the requestbody" +requestbody);
		  
	
		  
		 // De-serialization
		  User userdetails = objectmapper.readValue(requestbody, User.class);
		  System.out.println(userdetails.getUser_first_name());
		  System.out.println(userdetails.getUser_last_name());
		  
	  
		  System.out.println(userdetails.getUserAddress().getPlotNumber());
	  
		  System.out.println(userdetails.getUserAddress().getStreet());
	  System.out.println(userdetails.getUserAddress().getCountry());
	  
		  
	  baseURI = "https://userserviceapi-a54ceee3346a.herokuapp.com/uap";
	      Response response = 
		  RestAssured
		  .given()
		    .auth().basic("Numpy@gmail.com", "userapi123") 
		   
		    .contentType(ContentType.JSON)
		    .body(requestbody)
		  .when()
		    .post("/createusers")
		  .then()
		    .assertThat()
		    .statusCode(201)
		    .extract()
		    .response();
	      
	     int userId = response.path("user_id");

         //System.out.println(jsonSchema);
	      baseURI = "https://userserviceapi-a54ceee3346a.herokuapp.com/uap";
		  RestAssured
          .given()
              .auth().basic("Numpy@gmail.com","userapi123") 
              .contentType(ContentType.JSON)
              
              .when()
               .get("/user/{userId}",userId)       
           .then()
           .assertThat()
 		  .statusCode(200)
 		  .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
 		  
	  }    
		   
	     
		    
		   
		  
		  
	    
	  catch (JsonProcessingException e) 
	  {
		
		e.printStackTrace();
		
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
	  
  }
}
