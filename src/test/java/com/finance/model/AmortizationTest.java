package com.finance.model;

import com.finance.model.calculations.AmortEntry;
import com.finance.model.calculations.Amortization;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AmortizationTest {

    Amortization testAmortization1 = new Amortization(0.082, 150000, 24);
    Amortization testAmortization2 = new Amortization(0.052, 75000, 360);



    @Test
    void scheduleLengthTest() {

        List<AmortEntry> testSchedule1= testAmortization1.createSchedule('€');
        List<AmortEntry> testSchedule2= testAmortization2.createSchedule('€');

        int lengthOfSchedule1 = testSchedule1.size();
        int lengthOfSchedule2 = testSchedule2.size();

        assertEquals(24, lengthOfSchedule1);
        assertEquals(360, lengthOfSchedule2);
    }

    @Test
    void balanceCheck() {

        List<AmortEntry> testSchedule1= testAmortization1.createSchedule('€');
        List<AmortEntry> testSchedule2= testAmortization2.createSchedule('€');

        AmortEntry lastEntrySchedule1 = testSchedule1.get(testSchedule1.size()-1) ;
        AmortEntry lastEntrySchedule2 = testSchedule2.get(testSchedule2.size()-1) ;

        assertEquals("€0.00",lastEntrySchedule1.getEndBalance());
        assertEquals("€0.00",lastEntrySchedule2.getEndBalance());

    }
}