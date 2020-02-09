package socks;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestUser {


    @Test
    public void userTest() {
        System.out.println("sfdfgdfgfdgfdgdg");
    }

    User newUser;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://167.172.110.35";
        RestAssured.port = 80;
    }

    @Test
    public Response createUser(User newUser) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .post("/register");
    }

    public void createDefaultUser()  {
//        com.socks.User newUser = new com.socks.User();
        newUser.setUsername("Pablo");
        newUser.setPassword("qwerty");
        newUser.setEmail("123@321.gmail");
        createUser(newUser).then().statusCode(201)
                .extract().body().as(User.class);

        newUser = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .post("/register")
                .then()
                .statusCode(201)
                .extract().body()
                .as(User.class);

        User actualUser = RestAssured.given()
                .contentType(ContentType.JSON)
                .get("/customers/" + newUser.getId())
                .then()
                .statusCode(200)
                .extract().body()
                .as(User.class);

        Assertions.assertThat(actualUser).isEqualTo(newUser);

    }
}
