package com.finance.model;

import com.finance.model.calculations.NetPresentValue;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class NetPresentValueTest {

    @Test
    public void calculateNPVTest() {

        String[] cashFlowArr = {"873.21", "943.12", "222.22", "12.09"};

        NetPresentValue npvTest = new NetPresentValue(cashFlowArr, new BigDecimal(5.33));
        BigDecimal actualResult = npvTest.calculateNPV();
        BigDecimal expectedResult = new BigDecimal(1879.09).setScale(2, RoundingMode.HALF_UP);

        assertEquals(expectedResult, actualResult);
    }
}