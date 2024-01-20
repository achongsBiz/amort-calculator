# Amortization Schedule Builder

## Introduction
This is a microservice with a simple API designed to build amortization schedules.

![screenshot](https://github.com/achongsBiz/readme-files/blob/master/tvm-calculator/amort.png)


## Amortization Schedule (GET): 
Builds an amortization schedule, here is a sample call:

```(GET) /api/amort?apy=0.052&loanAmount=75000&period=360```
* apy: Annual Percentage Yield. In this example, an apy of 5.2% is used.
* loanAmount: The loan amount.
* period: The term of the loan in months. In this example, 360 months.

## Web Interface
A simple web interface that consumes the above endpoint is available:

```/amort.html```

## Architecture

* Server:
    * SpringBoot (in Java)
    * Static front end with CSS, HTML, JavaScript
