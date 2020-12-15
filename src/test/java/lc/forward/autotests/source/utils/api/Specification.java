package lc.forward.autotests.source.utils.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public interface Specification {

    default ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build()
                ;
    }

    default RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri("https://subscribe-rf-front-test.forward.lc")
                .addHeader("Device-Type", "WEB")
                .setContentType(ContentType.JSON)
                .build()
                ;
    }
}
