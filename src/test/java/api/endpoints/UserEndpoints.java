package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payloads.User;
import io.restassured.response.Response;

//Created for perform create , read, update,delete request for user api

public class UserEndpoints 
{
	public static Response createUser(User payload)
	{
		Response res=given()
			.contentType("Application/json")
			.accept("Application/json")
			.body(payload)
		
		
		.when()
			.post(Routes.post_url);
				
		return res;
	}
	
	public static Response getUser(String userName)
	{
		Response res=given()
			.pathParam("username", userName)
	
		.when()
			.get(Routes.get_url);
		
		return res;
				
	}
	
	public static Response updateUser(User payload,String userName)
	{
		Response res=given()
			.contentType("Application/json")
			.accept("Application/json")
			.body(payload)
			.pathParam("username", userName)
		
		
		.when()
			.put(Routes.put_url);
				
		return res;
	}
	public static Response deleteUser(String userName)
	{
		Response res=given()
			.pathParam("username", userName)
	
		.when()
			.delete(Routes.delete_url);
		
		return res;
				
	}
	

}
