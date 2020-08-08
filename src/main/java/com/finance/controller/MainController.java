package com.finance.controller;

import com.finance.model.calculations.CashFlowSingleValue;
import com.finance.model.calculations.Amortization;
import com.finance.model.calculations.AmortEntry;
import com.finance.model.calculations.NetPresentValue;
import com.finance.model.input.NPVIn;
import com.finance.model.output.FVOut;
import com.finance.model.output.NPVOut;
import com.finance.model.output.PVOut;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    @RequestMapping(path="/pv", method = RequestMethod.GET)
    public PVOut calcPresentValue(@RequestParam  String interest,
                                  @RequestParam double futureValue,
                                  @RequestParam int period)
    {
        CashFlowSingleValue cashFlowSingleValue = new CashFlowSingleValue(new BigDecimal(futureValue), interest, period);
        return new PVOut(cashFlowSingleValue.getPV());
    }

    @RequestMapping(path="/npv", method = RequestMethod.POST)
    public NPVOut calcNetPresentValue(@RequestBody NPVIn npvIn) {

        NetPresentValue npv = new NetPresentValue(
                npvIn.getCashFlows(),
                new BigDecimal(npvIn.getRate()));

        return new NPVOut(npv.calculateNPV());
    }

    @RequestMapping(path="/fv", method = RequestMethod.GET)
    public FVOut calcFutureValue(@RequestParam  String interest,
                                 @RequestParam double presentValue,
                                 @RequestParam int period)
    {
        CashFlowSingleValue cashFlowSingleValue = new CashFlowSingleValue(new BigDecimal(presentValue), interest, period);
        return new FVOut(cashFlowSingleValue.getFV());
    }
//test
    @RequestMapping(path="/amort", method = RequestMethod.GET)
    public List<AmortEntry> buildAmortSchedule(@RequestParam  double apy,
                                               @RequestParam double loanAmount,
                                               @RequestParam int period,
                                               @RequestParam(defaultValue="$") char sign)
    {

        Amortization amortization = new Amortization(apy, loanAmount, period);
        List<AmortEntry> schedule = amortization.createSchedule(sign);

        return schedule;
    }

}
