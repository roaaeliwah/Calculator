package com.example.demo;


import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public double eval(double num1, double num2, String operation){
        switch(operation){
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "x":
                return num1 * num2;
            case "÷":
                if(num2 == 0)
                    throw new ArithmeticException("Division by zero");
                return (double) num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operation");
        }
    }
    public double square(double num1){
        return num1 * num1;
    }
    public double sqrt(double num1){
        if(num1 < 0)
            throw new ArithmeticException("Negative square root");
        return Math.sqrt(num1);
    }
    public double reciprocal(double num1){
        if(num1 == 0)
            throw new ArithmeticException("Division by zero");
        return 1/num1;
    }
    public double negate(double num1){
        return -num1;
    }

}


