package com.finance.model.input;

public class NPVIn {

    private String [] cashFlows;
    private String rate;
    private String period;

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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
