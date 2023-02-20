package com.finance.controller;

import com.finance.model.calculations.Amortization;
import com.finance.model.calculations.AmortEntry;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {

    @RequestMapping(path="/amort", method = RequestMethod.GET)
    public List<AmortEntry> buildAmortSchedule(@RequestParam  double apy,
                                               @RequestParam double loanAmount,
                                               @RequestParam int period,
                                               @RequestParam(defaultValue="$") char sign)
    {

        if(loanAmount <= 0 || apy <= 0 || period <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data");
        }

        Amortization amortization = new Amortization(apy, loanAmount, period);
        List<AmortEntry> schedule = amortization.createSchedule(sign);

        return schedule;
    }
}
