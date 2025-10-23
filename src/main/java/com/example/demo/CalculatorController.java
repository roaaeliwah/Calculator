package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


//entry point of web app
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/calculator")
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;

    @PostMapping("/calculate")
    public String evaluate(@RequestBody CalculatorRequest request) {
        double result;
        try {
            switch(request.getOperation()) {
                case "+":
                case "-":
                case "*":
                case "/":
                    result = calculatorService.eval(request.getNum1(), request.getNum2(), request.getOperation());
                    break;
                case "square":
                    result = calculatorService.square(request.getNum1());
                    break;
                case "sqrt":
                    result = calculatorService.sqrt(request.getNum1());
                    break;
                case "reciprocal":
                    result = calculatorService.reciprocal(request.getNum1());
                    break;
                case "negate":
                    result = calculatorService.negate(request.getNum1());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operation");
            }
            return String.valueOf(result);
        }
        catch (ArithmeticException | IllegalArgumentException e) {
            return "E";
        }

    }
}
