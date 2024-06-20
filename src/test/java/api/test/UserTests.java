package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests 
{
	Faker faker;
	User userPayload;
	
	public Logger logger; // for logs
	
	@BeforeClass
	
	public void setupData()
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		

		//logs
		logger= LogManager.getLogger(this.getClass());
		
		logger.debug("debugging.....");
	}

	@Test(priority=1)
	
	public void testPostUser()
	{
		logger.info("********** Creating user  ***************");
		
		Response res=UserEndpoints.createUser(userPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("**********User is creatged  ***************");
		
	}
	
	@Test(priority=2)
	
	public void testGetUser()
	{
		logger.info("**********User is creatged  ***************");
		
		Response res=UserEndpoints.getUser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		

		logger.info("**********User info  is displayed ***************");
		
	}
	
	@Test(priority=3)
	
	public void PutUser()
	{	 
		logger.info("********** Updating User ***************");
		
		//update the details
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		Response res=UserEndpoints.updateUser(userPayload,this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("********** User updated ***************");
		
		//checking data after update
		Response resAfterUpdate=UserEndpoints.getUser(this.userPayload.getUsername());
		
		Assert.assertEquals(resAfterUpdate.getStatusCode(), 200);
		
		
	}
	
	@Test(priority=4)
	
	public void testDeleteUser()
	{
		logger.info("**********   Deleting User  ***************");
		
		Response res=UserEndpoints.deleteUser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("********** User deleted ***************");
		
	}
}
