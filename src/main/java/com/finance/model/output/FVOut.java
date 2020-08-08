package com.finance.model.output;

import java.math.BigDecimal;

public class FVOut {

    BigDecimal FV;

    public BigDecimal getFV() {
        return FV;
    }

    public void setFV(BigDecimal FV) {
        this.FV = FV;
    }

    public FVOut(BigDecimal fv) {
        this.FV = fv;
    }
}
