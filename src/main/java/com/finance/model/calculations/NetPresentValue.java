package com.finance.model.calculations;

import java.math.BigDecimal;

public class NetPresentValue {

    BigDecimal [] cashFlows;
    BigDecimal rate;

    public NetPresentValue (String [] cashFlows, BigDecimal rate) {

        this.cashFlows = new BigDecimal[ cashFlows.length];
        for (int i =0; i < cashFlows.length; i++) {
            this.cashFlows[i] = new BigDecimal(cashFlows[i]);
        }

        this.rate = rate;
    }

    public BigDecimal calculateNPV() {

        BigDecimal npv = BigDecimal.ZERO;

        for (int i=0; i < this.cashFlows.length; i++) {
            int currentPeriod = i+1;
            CashFlowSingleValue cashFlowSingleValue = new CashFlowSingleValue(cashFlows[i],this.rate.toString(), currentPeriod);
            npv = npv.add(cashFlowSingleValue.getPV());
        }

        return npv;
    }

}
