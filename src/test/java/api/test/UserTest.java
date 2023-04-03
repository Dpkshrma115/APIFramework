package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.github.javafaker.Internet;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setUsername(faker.name().username());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		//Logs
		logger = LogManager.getLogger(this.getClass());
		logger.debug("++++++++debug++++++++++");
			}
	public  String userName ()	{
		String uName = this.userPayload.getUsername();
		return uName;
	}
	@Test (priority =1)
	public void testPostUser() {
		logger.info("+++++++++++Creating User++++++++++++");
		Response res = UserEndPoints.createUser(userPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);

		logger.info("++++++++++++UserCreated++++++++++++++");
	}
@Test(priority =2)
public void testReadUser() {
	logger.info("++++++++++++readUser++++++++++++++");
	Response res = UserEndPoints.readUser( UserTest.this.userName());
	res.then()
	.log().all();
	Assert.assertEquals(res.getStatusCode(), 200);

	logger.info("+++++++++++UserRead+++++++++++++++");
}
@Test(priority =3 )
public void testUpdateUser() {

	logger.info("+++++++++++UpdateUser+++++++++++++++");
	userPayload.setPassword(faker.internet().password());
	userPayload.setEmail(faker.internet().safeEmailAddress());
	userPayload.setPhone(faker.phoneNumber().cellPhone());
	
	Response updatedRes = UserEndPoints.updateUser(UserTest.this.userName(), userPayload);
	updatedRes.then().log().body();
	Assert.assertEquals(updatedRes.getStatusCode()  , 200);

	logger.info("++++++++++++UserUpdated++++++++++++++");
}
@Test(priority =4)
public void testDeleteUser() {

	logger.info("++++++++++++DeleteUser++++++++++++++");
	Response deleteRes = UserEndPoints.deleteUser( UserTest.this.userName());
	deleteRes.then()
	.log().all();
	Assert.assertEquals(deleteRes.getStatusCode(), 200);

	logger.info("+++++++++++UserDeleted+++++++++++++++");
}
}
