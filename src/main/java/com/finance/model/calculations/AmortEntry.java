package com.finance.model.calculations;

public class AmortEntry {

    private int period;
    private String begBalance;
    private String principal;
    private String interest;
    private String endBalance;
    private char sign;
    
    public AmortEntry(int period, String begBalance, String principal, String interest, char sign, String endBalance) {

        this.sign = sign;
        this.period = period;
        this.begBalance = sign +  begBalance;
        this.principal = sign +  principal;
        this.interest =  sign + interest;
        this.endBalance = sign + endBalance;
    }

    public void setEndBalance(String endBalance) {
        this.endBalance = endBalance;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = this.sign + period;
    }

    public String getBegBalance() {
        return begBalance;
    }

    public String getPrincipal() {
        return principal;
    }

    public String getInterest() {
        return interest;
    }

    public String getEndBalance() {
        return endBalance;
    }
}
