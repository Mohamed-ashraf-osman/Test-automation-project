package utils;


        import io.restassured.RestAssured;
        import io.restassured.response.Response;
        import pages.PutUserBody;
        import usersData.CreateUserBody;
        import pages.PutUserBody;


public class Users {
    CreateUserBody createUserBody;
    PutUserBody putUserBody;


    public Response createUser(int id, String userName, String firstName, String lastName,
                               String email,String password, String phone,int userStatus){

        createUserBody = new CreateUserBody();
        createUserBody.setId(id);
        createUserBody.setUsername(userName);
        createUserBody.setFirstName(firstName);
        createUserBody.setLastName(lastName);
        createUserBody.setEmail(email);
        createUserBody.setPassword(password);
        createUserBody.setPhone(phone);
        createUserBody.setUserStatus(userStatus);

        return RestAssured.given().log().all().header("accept","application/json")
                .header("Content-Type","application/json")
                .body(createUserBody).when().post("/user");
    }

    public Response getUserCreatedByUserName(String name){

        return RestAssured.given().log().all()
                .pathParam("username",name).when().get("/user/{username}");

    }

    public Response putUserByName(String name, int id, String firstName, String lastName,
                                  String email){
        putUserBody = new PutUserBody();
        putUserBody.setId(id);
        putUserBody.setUsername(name);
        putUserBody.setFirstName(firstName);
        putUserBody.setLastName(lastName);
        putUserBody.setEmail(email);

        return RestAssured.given().log().all().header("accept","application/json")
                .header("Content-Type","application/json").pathParam("userName",name)
                .body(putUserBody).when().put("/user/{userName}");
    }

    public Response getUserUpdatedByName(String name){
        return  RestAssured.given().log().all()
                .pathParam("username",name).when().get("/user/{username}");
    }

    public Response deleteUserByName(String name){
        return RestAssured.given().log().all().pathParam("username",name)
                .when().delete("/user/{username}");
    }

    public Response checkUserDeletedByName(String name){
        return  RestAssured.given().log().all()
                .pathParam("username",name).when().get("/user/{username}");
    }





}