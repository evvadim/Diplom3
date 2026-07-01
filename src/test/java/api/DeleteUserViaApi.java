package api;

import config.Config;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteUserViaApi {

    private final String accessToken;

    public DeleteUserViaApi(String accessToken) {
        this.accessToken = accessToken;
    }

    public void deleteUser() {

        Response response = given()
                .baseUri(Config.getBaseURI())
                .header("Authorization", accessToken)
                .delete("/api/auth/user");

        response.then().statusCode(202);
    }

}
