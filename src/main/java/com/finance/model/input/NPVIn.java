package com.finance.model.input;

public class NPVIn {

    private String [] cashFlows;
    private String rate;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String[] getCashFlows() {
        return cashFlows;
    }

    public void setCashFlows(String[] cashFlows) {
        this.cashFlows = cashFlows;
    }

}
