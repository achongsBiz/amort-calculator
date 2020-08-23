# TVM Calculator

## Introduction
This is an API microservice that performs Time Value of Money calculations. It supports 
the following calls:

##Amortization Schedule (GET): 
Builds an amortization schedule
Sample call: 

```/api/amort?apy=0.052&loanAmount=75000&period=360```
* apy: Annual Percentage Yield. In this example, an apy of 5.2% is used.
* loamAmount: The loan amount.
* period: The term of the loan in months 

##Net Present Value (POST): 
Returns the NPV given an array of cash flows
  Sample call: 
  
  ```/api/npv```
    
* The body of the request is in the following format: 

```{"rate" : "5.7", "cashFlows" : ["500", "200", "400"], "period" : 10}```

* **rate**: The discount rate, in this example 5.7%
* **cashFlows**: An array containing future cash flows in chronological order.

##Present Value (GET): 
Returns the present value of a single future cash flow
  
Sample call: 

```/api/pv?interest=3.2&futureValue=1000&period=3```

* **interest**: In this example, an interest of 3.2%.
* **futureValue**: The cash flow's future period at the specified period.
* **period**: The period in the future corresponding to the futureValue.
    
##Future Value (GET): 
Returns the future value of a single cash flow
  
Sample call:  

```/api/fv?interest=3.2&presentValue=1000&period=3```
* **interest**: In this example, an interest of 3.2%.
* **presentValue**: The cash flow's current value.
* **period**: The number of periods to project the presentValue.
    

## Architecture

* Server:
    * SpringBoot (in Java)
    * Maven

* Testing:
    * Unit Tests through JUnit

## Visuals

![screenshot](https://github.com/achongsBiz/readme-files/blob/master/tvm-calculator/tvm-1.PNG)
