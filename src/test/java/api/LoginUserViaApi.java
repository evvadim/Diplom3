package api;

import api.data.UserDataRequest;
import api.data.UserDataResponse;
import config.Config;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginUserViaApi {

    private final UserDataRequest loginDataRequest;

    public LoginUserViaApi(String email, String password) {
        this.loginDataRequest = new UserDataRequest(email, password, null);
    }

    public String fetchAccessToken() {

        return fetch().body().as(UserDataResponse.class).getAccessToken();

    }

    private Response fetch() {

        Response response = given()
                .baseUri(Config.getBaseURI())
                .contentType(ContentType.JSON)
                .body(loginDataRequest)
                .post("/api/auth/login");

        response.then().statusCode(200);

        return response;
    }

}
