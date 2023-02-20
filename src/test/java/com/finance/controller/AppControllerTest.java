package com.finance.controller;

import com.finance.model.calculations.AmortEntry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

class AppControllerTest {

    @Test
    public void amortEndpointTest() {

        AmortEntry  entries [] = get("api/amort?apy=0.073&loanAmount=73000&period=60")
                .as(AmortEntry[].class);

        AmortEntry firstEntry = entries[0];
        assertEquals("$73000.00",firstEntry.getBegBalance().trim() );

        AmortEntry lastEntry = entries[entries.length-1];
        assertEquals("$0.00", lastEntry.getEndBalance().trim());

    }

}