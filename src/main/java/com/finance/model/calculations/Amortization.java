package com.finance.model.calculations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Amortization {

    private BigDecimal apyDivByPeriod;
    private BigDecimal amt;
    private int period;

    public Amortization(double apy, double amt, int period) {

        this.amt = new BigDecimal(amt);
        this.period = period;
        this.apyDivByPeriod = new BigDecimal(apy)
                .divide(new BigDecimal(12), 10, RoundingMode.HALF_UP);
    }

    private BigDecimal getPmt() {

        BigDecimal numerator =  amt.multiply(this.apyDivByPeriod);

        BigDecimal innerTerm = BigDecimal.ONE.add(this.apyDivByPeriod).pow(this.period);
        BigDecimal denominator = BigDecimal.ONE.subtract(BigDecimal.ONE
                .divide(innerTerm, 10, RoundingMode.HALF_UP));

        return numerator.divide(denominator, 10, RoundingMode.HALF_UP);
    }

    public List<AmortEntry> createSchedule(char sign) {

        BigDecimal balance = this.amt;
        List<AmortEntry> schedule = new ArrayList<>();

        for (int i=1; i <= this.period; i++ ) {

            BigDecimal interestThisPeriod = balance.multiply(this.apyDivByPeriod);
            BigDecimal principalThisPeriod = this.getPmt().subtract(interestThisPeriod);
            BigDecimal endBalance = balance.subtract(principalThisPeriod);

            AmortEntry amortEntry = new AmortEntry(
                    i,
                    balance.setScale(2, RoundingMode.HALF_UP).toString(),
                    principalThisPeriod.setScale(2, RoundingMode.HALF_UP).toString(),
                    interestThisPeriod.setScale(2, RoundingMode.HALF_UP).toString(),
                    sign,
                    endBalance.setScale(2, RoundingMode.HALF_UP).toString()
            );
            schedule.add(amortEntry);

            balance = endBalance;
        }

        return schedule;
    }

}
