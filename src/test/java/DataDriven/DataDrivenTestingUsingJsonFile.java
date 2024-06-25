package DataDriven;

import static io.restassured.RestAssured.baseURI;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import APITestingListeners.RestAssuredListener;
import APITestingPojos.User;
import APITestingPojos.UserAddress;
import Utilities.FileNameConstants;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;

public class DataDrivenTestingUsingJsonFile 
{
  @Test(dataProvider = "getTestData")
  public void DataDrivenTestingUsingJson(LinkedHashMap<String,Object>testData) throws JsonProcessingException 
  {
	  LinkedHashMap<String,Object> userAddressMap =(LinkedHashMap<String,Object>)testData.get("userAddress");
	  System.out.println("user address  =" +userAddressMap);
	  
	  UserAddress userAddress = new UserAddress(userAddressMap.get("plotNumber").toString(),userAddressMap.get("street").toString(),userAddressMap.get("state").toString(),userAddressMap.get("country").toString(),userAddressMap.get("zipCode").toString());
	  
	  User user = new User(testData.get("user_first_name").toString(),testData.get("user_last_name").toString(),testData.get("user_contact_number").toString(), testData.get("user_email_id").toString(),userAddress);
	  
	  ObjectMapper objectmapper = new ObjectMapper();
	  String requestbody = objectmapper.writerWithDefaultPrettyPrinter().writeValueAsString(user); 
	  
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
  }
  
  @DataProvider(name="getTestData")
  public Object[] getTestDatausingJson() throws IOException
  {
	  
		Object[] object = null;
		try
		{
			
		  String jsonTestData = FileUtils.readFileToString(new File(FileNameConstants.JSON_TEST_DATA),"UTF-8");
		  JSONArray jsonArray = JsonPath.read(jsonTestData, "$");
		  System.out.println("JsonArray is" +jsonArray.toString());
		  
		  object = new Object[jsonArray.size()];
		  for(int i=0;i<jsonArray.size();i++)
		  {
			  object[i] = jsonArray.get(i);
		  }
		
	    } 
	  catch (IOException e) 
	  {
		
		e.printStackTrace();
	}
	  
	  return object;	
	  
  }
  
}
