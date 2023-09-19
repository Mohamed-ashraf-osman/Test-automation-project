package TestCases;

import utils.Users;
import Data.ReadData;
import Data.ReadData;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import utils.Users;

import java.io.FileNotFoundException;

public class TestBase {
    Users users;
    ReadData createUserData;
    ReadData putUserData;

    @BeforeMethod
    public void setData() throws FileNotFoundException {
        users = new Users();
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        createUserData = new ReadData("src/main/java/utils/CreatUserData.json");
        putUserData = new ReadData("src/main/java/utils/PutData.json");

    }
}