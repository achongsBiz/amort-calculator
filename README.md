# Amortization Schedule Builder

## Introduction
This is an API microservice that builds an amortization schedule

![screenshot](https://github.com/achongsBiz/readme-files/blob/master/tvm-calculator/amort.png)


## Amortization Schedule (GET): 
Builds an amortization schedule
Sample call: 

```/api/amort?apy=0.052&loanAmount=75000&period=360```
* apy: Annual Percentage Yield. In this example, an apy of 5.2% is used.
* loanAmount: The loan amount.
* period: The term of the loan in months. In this example, 360 months.

## Web Interface
A simple web interface that consumes the above endpoint is available through:

```/amort.html```


## Architecture

* Server:
    * SpringBoot (in Java)
    * Maven

* Testing:
    * Unit Tests through JUnit
    * Endpoint Testing through RestAssured
