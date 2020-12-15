package lc.forward.autotests.source.utils.api;

import io.restassured.RestAssured;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

public interface UserRegistration extends Specification {
    default String getSessionID(String mobilePhone, String email) {
        return RestAssured
                .given()
                .spec(getRequestSpecification())
                .baseUri("https://subscribe-rf-front-test.forward.lc/")
                .basePath("bouncer/v1/pin")
                .body(new HashMap<String, String>() {{
                    put("email", email);
                    put("mobilePhone", mobilePhone);
                }})
                .post()
                .then()
                .spec(getResponseSpecification())
                .extract()
                .body()
                .jsonPath()
                .getString("resultData.sessionId");
    }

    default void enterSMSCode(String sessionID) {
        RestAssured
                .given()
                .spec(getRequestSpecification())
                .baseUri("https://subscribe-rf-front-test.forward.lc/")
                .basePath("bouncer/v1/pin/".concat(sessionID).concat("/smscode"))
                .body(new HashMap<String, String>() {{
                    put("smsCode", "1111");
                }})
                .post()
                .then()
                .assertThat()
                .spec(getResponseSpecification())
                .body("resultData.result", equalTo("SMS_MATCH"));
    }

    default String enterNewPin(String sessionID) {
        return RestAssured
                .given()
                .spec(getRequestSpecification())
                .baseUri("https://subscribe-rf-front-test.forward.lc/")
                .basePath("bouncer/v1/pin/".concat(sessionID).concat("/pin"))
                .body(new HashMap<String, String>() {{
                    put("pin", "1111");
                }})
                .post()
                .then()
                .assertThat()
                .spec(getResponseSpecification())
                .extract()
                .body().jsonPath().getString("resultData.token");
    }

    /**
     * main method
     *
     * @param mobilePhone User mobile phone
     * @param email       User email
     */
    default String registerUser(String mobilePhone, String email) {
        String sessionID = getSessionID(mobilePhone, email);
        enterSMSCode(sessionID);
        String token = enterNewPin(sessionID);
        System.out.println("user generated");
        return token;
    }
}
