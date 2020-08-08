package com.finance.model.output;

import java.math.BigDecimal;

public class NPVOut {

    BigDecimal NPV;

    public NPVOut(BigDecimal npv) {
        this.NPV = npv;
    }

    public BigDecimal getNPV() {
        return NPV;
    }

    public void setNPV(BigDecimal NPV) {
        this.NPV = NPV;
    }
}
