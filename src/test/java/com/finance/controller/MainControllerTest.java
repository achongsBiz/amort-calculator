package com.finance.controller;

import com.finance.model.calculations.AmortEntry;

import net.minidev.json.JSONObject;
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

        AmortEntry  entries [] = get("api/amort?apy=0.073&loanAmount=73000&period=60")
                .as(AmortEntry[].class);

        AmortEntry firstEntry = entries[0];
        assertEquals("$73000.00",firstEntry.getBegBalance().trim() );

        AmortEntry lastEntry = entries[entries.length-1];
        assertEquals("$0.00", lastEntry.getEndBalance().trim());

    }


    @Test
    public void npvEndpointTest() {

        String [] cashFlowArr = {"100.00", "200.00", "300.00"};
        String testRate = "11";
        JSONObject body = new JSONObject();
        body.put("cashFlows", cashFlowArr);
        body.put("rate", testRate);

        given().contentType("application/json")
        .body(body.toString()).when().post("/api/npv").then().statusCode(200)
                .assertThat().body("npv", equalTo(471.77f));

    }
}