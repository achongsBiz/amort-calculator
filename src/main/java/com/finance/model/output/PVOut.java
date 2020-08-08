package com.finance.model.output;

import java.math.BigDecimal;

public class PVOut {

    BigDecimal PV;

    public PVOut(BigDecimal pv) {
        this.PV = pv;
    }

    public BigDecimal getPV() {
        return PV;
    }

    public void setPV(BigDecimal PV) {
        this.PV = PV;
    }
}
