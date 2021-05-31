package com.finance.controller;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

class MainControllerTest {

    @Test
    public void pvEndpointTest() {

        get("/api/pv?interest=6.2&futureValue=1000&period=15").then().statusCode(200).
                assertThat().body("pv", equalTo(405.63f));
    }


    @Test
    public void fvEndpointTest() {
        get("/api/fv?interest=5.3&presentValue=1000&period=12").then().statusCode(200)
                .assertThat().body("fv", equalTo(1858.41f));
    }

    @Test
    public void amortEndpointTest() {
        Response response = get("api/amort?apy=7.3&loanAmount=73000&period=60")
                .then().extract().response();

        int periods = response.jsonPath().getList("$").size();
        assertEquals(60, periods);
    }


    @Test
    public void npvEndpointTest() {

    }
}