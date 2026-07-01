package api;

import api.data.UserDataRequest;
import api.data.UserDataResponse;
import config.Config;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateUserViaApi {

    private final UserDataRequest createDataRequest;

    public CreateUserViaApi(UserDataRequest createDataRequest) {
        this.createDataRequest = createDataRequest;
    }

    public String fetchAccessToken() {
        return fetch().body().as(UserDataResponse.class).getAccessToken();
    }

    private Response fetch() {
        return given()
                .baseUri(Config.getBaseURI())
                .contentType(ContentType.JSON)
                .body(createDataRequest)
                .post("/api/auth/register");
    }

}
