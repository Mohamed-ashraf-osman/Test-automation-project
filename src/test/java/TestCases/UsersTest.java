package TestCases;



import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class UsersTest extends TestBase {

    JsonPath jsonPath;


    @Test
    public void createUser() throws FileNotFoundException {
        String response = users.createUser(Integer.parseInt(createUserData.getTestData("id")),createUserData.getTestData("username")
                        ,createUserData.getTestData("firstName"),createUserData.getTestData("lastName"),
                        createUserData.getTestData("email"), createUserData.getTestData("password"),
                        createUserData.getTestData("phone"), Integer.parseInt(createUserData.getTestData("userStatus")))
                .then().extract().response().asString();

        System.out.println(response);

    }

    @Test(priority = 1)
    public void getCreatedUserByUserName() throws FileNotFoundException {
        String response  = users.getUserCreatedByUserName(createUserData.getTestData("username"))
                .then().extract().response().asString();

        System.out.println(response);

        jsonPath = new JsonPath(response);
        String email = jsonPath.getString("email");
        System.out.println(email);
        Assert.assertEquals(createUserData.getTestData("email"),email);
    }

    @Test(priority = 2)
    public void PutUserByName() throws FileNotFoundException {
        users.putUserByName( putUserData.getTestData("username"),
                Integer.parseInt(putUserData.getTestData("id")),
                putUserData.getTestData("firstName"),putUserData.getTestData("lastName"),
                putUserData.getTestData("email")).then().statusCode(200).extract().response().asString();




    }

    @Test(priority = 3)
    public void getUserUpdated() throws FileNotFoundException {
        String response = users.getUserUpdatedByName(putUserData.getTestData("username"))
                .then().extract().response().asString();

        jsonPath = new JsonPath(response);
        String email = jsonPath.getString("email");
        String firstName = jsonPath.get("firstName");
        System.out.println(firstName);
        System.out.println(email);

        Assert.assertEquals(putUserData.getTestData("email"),email);
        Assert.assertEquals(putUserData.getTestData("firstName"),firstName);
    }

    @Test(priority = 4)
    public void deleteUserByName() throws FileNotFoundException {
        String response = users.deleteUserByName(putUserData.getTestData("username"))
                .then().extract().response().asString();

        System.out.println(response);
    }

    @Test(priority = 5)
    public void checkUserDeleted() throws FileNotFoundException {
        String response = users.checkUserDeletedByName(putUserData.getTestData("username"))
                .then().assertThat().statusCode(404).extract().response().asString();

        System.out.println(response);
    }
}