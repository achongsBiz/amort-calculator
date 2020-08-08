package com.finance.model;

import com.finance.model.calculations.CashFlowSingleValue;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class CashFlowTest {

    private CashFlowSingleValue testCashFlowSingleValue = new CashFlowSingleValue(new BigDecimal(832.21), "3.23", 6);

    @Test
    void getFV() {
        BigDecimal expectedResult = new BigDecimal(1007.09).setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualResult = testCashFlowSingleValue.getFV().setScale(2, RoundingMode.HALF_UP);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getPV() {
        BigDecimal expectedResult = new BigDecimal(687.70).setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualResult = testCashFlowSingleValue.getPV().setScale(2, RoundingMode.HALF_UP);

        assertEquals(expectedResult, actualResult);
    }
}