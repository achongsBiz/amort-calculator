package com.finance.model.calculations;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CashFlowSingleValue {

    private BigDecimal cashFlow;
    private BigDecimal rateFactor;
    private int period;

    public CashFlowSingleValue(BigDecimal cashFlow, String rate, int period) {
        this.cashFlow = cashFlow;
        this.rateFactor = (new BigDecimal(rate)).divide(new BigDecimal(100)).add(new BigDecimal(1));
        this.period = period;
    }

    public BigDecimal getFV() {
        BigDecimal futureValue =  this.cashFlow.multiply(this.rateFactor.pow(period));
        return futureValue.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getPV() {

        return this.cashFlow.divide(this.rateFactor.pow(period), 2, RoundingMode.HALF_UP);
    }
}
