package lc.forward.autotests.source.utils.api;

import io.restassured.RestAssured;
import lc.forward.autotests.source.utils.IntGenerator;
import lc.forward.autotests.source.utils.repository.ImageType;

import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;
import static java.lang.Thread.sleep;

public interface UploadDocuments extends Specification, IntGenerator{
    default String getBasketId(String goodCode) {
        return RestAssured
                .given()
                .spec(getRequestSpecification())
                .body(new HashMap<String, Object>() {{
                    put("agentId", null);
                    put("basketData", new ArrayList<Object>() {{
                        add(
                                new HashMap<String, Object>() {{
                                    put("count", "1");
                                    put("goodCode", goodCode);
                                    put("tradeInfo", null);
                                }});
                    }});
                    put("channel", "SUBSCRIBE-RF");
                    put("lang", "ru");
                    put("partnerData", new HashMap<String, String>() {{
                        put("infoData", "{}");
                    }});
                    put("usid", UUID.randomUUID().toString().replace("-", ""));
                }})
                .post("/leasing-basket/v1/basket-online/")
                .then()
                .assertThat()
                .spec(getResponseSpecification())
                .extract()
                .body().jsonPath().getString("resultData.basketId");
    }

    default String getApplicationId(String basketId, String mail, String mobilePhone) {
        return RestAssured
                .given()
                .spec(getRequestSpecification())
                .body(
                        new HashMap<String, Object>() {{
                            put("type", "NEW");
                            put("basketId", basketId);
                            put("email", mail);
                            put("mobilePhone", mobilePhone);
                            put("agreementSimpleSign", true);
                            put("previousApplicationId", null);
                        }}
                )
                .post("/application/v2/leasing-application")
                .then()
                .assertThat()
                .spec(getResponseSpecification())
                .extract()
                .body()
                .jsonPath().getString("resultData.applicationID");
    }

    default String getToken(String applicationId) {
        return RestAssured
                .given()
                .spec(getRequestSpecification())
                .body(new HashMap<String, String>() {{
                    put("otp", "1111");
                }})
                .post("application/v2/leasing-application/" + applicationId + "/check-consents-otp")
                .then()
                .assertThat()
                .spec(getResponseSpecification())
                .extract()
                .body()
                .jsonPath().getString("resultData.token");
    }

    default void uploadPassport(String passportData, String token, String applicationId) {
        RestAssured
                .given()
                .spec(getRequestSpecification())
                .header("authorization", "Bearer " + token)
                .header("device-type", "WEB")
                .body(new HashMap<String, Object>() {{
                    put("imageType", ImageType.PASSPORT_RECOGNIZE);
                    put("imageData", passportData);
                }})
                .post("application/v2/leasing-application/upload/" + applicationId + "/photo")
                .then()
                .spec(getResponseSpecification())
                .extract().body();
    }

    default void uploadSelfie(String selfieData, String token, String applicationId) {
        RestAssured
                .given()
                .spec(getRequestSpecification())
                .header("authorization", "Bearer " + token)
                .header("device-type", "WEB")
                .body(new HashMap<String, Object>() {{
                    put("imageType", ImageType.SELFIE);
                    put("imageData", selfieData);
                }})
                .post("application/v2/leasing-application/upload/" + applicationId + "/photo")
                .then()
                .spec(getResponseSpecification())
        ;
        for (int i = 0; i < 60; i++) {
            String status = RestAssured
                    .given()
                    .spec(getRequestSpecification())
                    .basePath("application/v1/leasing-application/" + applicationId + "/check-passport-selfie")
                    .get()
                    .then()
                    .extract().body().jsonPath().getString("resultData.status");
            if (status.equals("CHECKED_SUCCESS")) {
                return;
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException("Can't check selfie! Timeout! \n " + e);
            }
        }
    }

    default void saveClientData(String token, String applicationId) {
        RestAssured
                .given()
                .spec(getRequestSpecification())
                .header("authorization", "Bearer " + token)
                .header("device-type", "WEB")
                .body(new HashMap<String, Object>() {{
                    put("clientInfo", new HashMap<String, Object>() {{
                        put("firstName", "ГЕФЕСТ");
                        put("patronymicName", "ЗЕВСОВИЧ");
                        put("lastName", "СЧАСТЛИВЫЙ");
                        put("hasMiddleName", true);
                        put("sex", "MALE");
                        put("birthDate", generateRandomIntBetween(1940, 1999) + "-0" + generateRandomIntBetween(1, 9) + "-0" + generateRandomIntBetween(1, 9));
                        put("birthPlace", "ГОР. ОЛИМП");
                        put("issueDate", "2020-05-14");
                        put("issuer", "УВД АВТОЗАВОДСКОГО РАЙОНА ГОРОДА НИЖНЕГО НОВГОРОДА");
                        put("issuerCode", "240-019");
                        put("documentType", "PASSPORT");
                        put("registrationAddress", new HashMap<String, Object>() {{
                            put("region", "Москва");
                            put("regionCode", "77");
                            put("district", null);
                            put("districtCode", "000");
                            put("town", "Москва");
                            put("townCode", "000");
                            put("locality", null);
                            put("localityCode", "000");
                            put("localityType", null);
                            put("taxCode", "125124");
                            put("street", "Правды");
                            put("streetCode", "2358");
                            put("streetNum", "3");
                            put("blockNum", null);
                            put("buildingNum", null);
                            put("flatNum", "12");
                        }});
                    }});
                    put("monthlySalary", generateRandomIntBetween(250000, 300000));
                    put("occupation", "COMMERCIAL_EMPLOYEE");
                }})
                .post("/application/v2/leasing-application/" + applicationId + "/client-data")
                .then()
                .assertThat()
                .spec(getResponseSpecification());
    }

    default void sendToReview(String token, String applicationId) {
        RestAssured
                .given()
                .spec(getRequestSpecification())
                .header("authorization", "Bearer " + token)
                .header("device-type", "WEB")
                .post("/application/v2/leasing-application/" + applicationId + "/review")
                .then()
                .assertThat()
                .spec(getResponseSpecification());
    }

    default void checkReview(String token, String applicationId) {
        String status = null;
        for (int i = 0; i < 60; i++) {
            status = given()
                    .spec(getRequestSpecification())
                    .header("authorization", "Bearer " + token)
                    .header("device-type", "WEB")
                    .post("application/v2/leasing-application/" + applicationId + "/leasing-application-short")
                    .then()
                    .assertThat()
                    .spec(getResponseSpecification())
                    .extract()
                    .body().jsonPath().getString("resultData.status");
            if (status.equals("APPROVED")) return;
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    default String getAppId(String token) {
        return RestAssured
                .given()
                .spec(getRequestSpecification())
                .header("authorization", "Bearer " + token)
                .get("cabinet/v1/leasing-contract")
                .then()
                .assertThat()
                .spec(getResponseSpecification())
                .extract()
                .body().jsonPath().getString("resultData.clientLeasingEntities.applicationId").replace("[", "").replace("]", "");
    }

    default void uploadDocuments(String pathPassport, String pathSelfie, String goodCode, String mail, String mobile) throws IOException {
        String basketId = getBasketId(goodCode);
        String applicationId = getApplicationId(basketId, mail, mobile);
        String token = getToken(applicationId);
        uploadPassport(pathPassport, token, applicationId);
        uploadSelfie(pathSelfie, token, applicationId);
        System.out.println("passport and selfie uploaded");
        saveClientData(token, applicationId);
        sendToReview(token, applicationId);
        checkReview(token, applicationId);
        System.out.println("data saved!");
    }
}
