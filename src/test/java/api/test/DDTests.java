package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviderClass;
import io.restassured.response.Response;

public class DDTests {
	@Test (priority =1, dataProvider ="Data" , dataProviderClass= DataProviderClass.class)
	public void testPostUser(String userID, String userName, String fName, String lName, String email, String password, String phone) {
		
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		
		Response res  = UserEndPoints.createUser(userPayload);
		Assert.assertEquals(res.getStatusCode(), 200);;
		
	}
@Test (priority =2, dataProvider ="UserNames" , dataProviderClass= DataProviderClass.class)
	public void testDeleteUser (String userName) {
		Response res = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
	}	
	

}
